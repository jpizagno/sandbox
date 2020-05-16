provider "aws" {
  version = "~> 2.7"
  access_key = "${var.access_key}"  # from variables.tf
  secret_key = "${var.secret_key}" # from variables.tf
  region     = "${var.region}"  # from variables.tf
}


###############
# DynamoDB
###############
resource "aws_dynamodb_table" "mailgun-table" {
  name           = "${var.dynamodb_table_name}"
  billing_mode   = "PROVISIONED"
  read_capacity  = 5
  write_capacity = 5
  hash_key       = "id"
  range_key      = "message"

  attribute {
    name = "id"
    type = "S"
  }

  attribute {
    name = "message"
    type = "S"
  }

}

###############
# Lambda
###############
resource "aws_lambda_function" "dynamodb_write2" {
  filename      = "lambda_function_payload.zip"
  function_name = "dynamodb_write2"
  role          = "${aws_iam_role.iam_for_lambda.arn}"
  handler       = "index.handler"

  source_code_hash = "${filebase64sha256("lambda_function_payload.zip")}"
  runtime = "nodejs10.x"
}


###############
# IAM Role and Policy
###############
resource "aws_iam_role" "iam_for_lambda" {
    name = "dynamodb_write-role-jim-terraform"
    assume_role_policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Action": "sts:AssumeRole",
      "Principal": {
        "Service": "lambda.amazonaws.com"
      },
      "Effect": "Allow",
      "Sid": ""
    }
  ]
}
EOF
}

resource "aws_iam_role_policy" "dynamodb-lambda-policy"{
  name = "dynamodb_lambda_policy"
  role = "${aws_iam_role.iam_for_lambda.id}"
  policy = <<EOF
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "dynamodb:*"
      ],
      "Resource": "${aws_dynamodb_table.mailgun-table.arn}"
    }
  ]
}
EOF
}


###############
# Gateway API
###############
resource "aws_api_gateway_rest_api" "examplemailgun" {
  name        = "ServerlessExample"
  description = "Terraform Serverless Application Example"
}

resource "aws_api_gateway_resource" "proxy" {
  rest_api_id = "${aws_api_gateway_rest_api.examplemailgun.id}"
  parent_id   = "${aws_api_gateway_rest_api.examplemailgun.root_resource_id}"
  path_part   = "{proxy+}"
}

resource "aws_api_gateway_method" "proxy" {
  rest_api_id   = "${aws_api_gateway_rest_api.examplemailgun.id}"
  resource_id   = "${aws_api_gateway_resource.proxy.id}"
  http_method   = "ANY"
  authorization = "NONE"
}

resource "aws_api_gateway_integration" "lambda" {
  rest_api_id = "${aws_api_gateway_rest_api.examplemailgun.id}"
  resource_id = "${aws_api_gateway_method.proxy.resource_id}"
  http_method = "${aws_api_gateway_method.proxy.http_method}"

  integration_http_method = "POST"
  type                    = "AWS_PROXY"
  uri                     = "${aws_lambda_function.dynamodb_write2.invoke_arn}"
}

resource "aws_api_gateway_method" "proxy_root" {
  rest_api_id   = "${aws_api_gateway_rest_api.examplemailgun.id}"
  resource_id   = "${aws_api_gateway_rest_api.examplemailgun.root_resource_id}"
  http_method   = "ANY"
  authorization = "NONE"
}

resource "aws_api_gateway_integration" "lambda_root" {
  rest_api_id = "${aws_api_gateway_rest_api.examplemailgun.id}"
  resource_id = "${aws_api_gateway_method.proxy_root.resource_id}"
  http_method = "${aws_api_gateway_method.proxy_root.http_method}"

  integration_http_method = "POST"
  type                    = "AWS_PROXY"
  uri                     = "${aws_lambda_function.dynamodb_write2.invoke_arn}"
}

resource "aws_api_gateway_deployment" "example" {
  depends_on = [
    "aws_api_gateway_integration.lambda",
    "aws_api_gateway_integration.lambda_root",
  ]

  rest_api_id = "${aws_api_gateway_rest_api.examplemailgun.id}"
  stage_name  = "mailgunjim"
}

resource "aws_lambda_permission" "apigw" {
  statement_id  = "AllowAPIGatewayInvoke"
  action        = "lambda:InvokeFunction"
  function_name = "${aws_lambda_function.dynamodb_write2.function_name}"
  principal     = "apigateway.amazonaws.com"

  # The "/*/*" portion grants access from any method on any resource
  # within the API Gateway REST API.
  source_arn = "${aws_api_gateway_rest_api.examplemailgun.execution_arn}/*/*"
}

output "base_url" {
  value = "${aws_api_gateway_deployment.example.invoke_url}"
}