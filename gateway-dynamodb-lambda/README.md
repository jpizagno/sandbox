# GCP Lambda, Gateway, DynamoDB. Responsive Cloud System

## Introduction
This project uses Terraform to build AWS resources that automatically 
respond to sending messages to an endpoint.  
In short, there is a AWS Lambda function that processes the message and 
saves it in AWS DynamoDB.  There is an AWS API Gateway that calls the 
lammbda function, and AMI Role/Policy that allows Lambda to store data in 
DynamoDB.

Example input message: 

```json
{
 "message": "Queued. Thank you.",
  "id": "<20111114174239.25659.5817@samples.blah>"
}
```

## Lambda 
The lambda function is Javasript which just attempts to parse the incoming message, 
and saves it in DynamoDB.  It is located in ./lambda/function/index.js.

## DynamoDB
The DynamoDB has the schema: id:String , message:String

## Deploy
Deploy the configuration using the script deploy_terraform.sh.  One can remove the 
managed infrastructure via destroy_terraform.sh .  
The users needs to enter the AWS keys, table name, and respond 'yes' to the Terraform apply result.
An example run is here:
 ```bash
shell$ ./deploy_terraform.sh 

read -p "Enter AWS Access Key ID: " access_key
Enter AWS Access Key ID: {your-key}

read -p "Enter AWS Secret Key:  " secret_key
Enter AWS Secret Key:  {your-secret-key}

read -p "Enter DynamoDB Table name:  " dynamodb_table_name
Enter DynamoDB Table name:  {your-proposed-table-name}


echo " setting DynamoDB Table name ...."
 setting DynamoDB Table name ....

....

Plan: 12 to add, 0 to change, 0 to destroy.

Do you want to perform these actions?
  Terraform will perform the actions described above.
  Only 'yes' will be accepted to approve.

  Enter a value: yes

....
Outputs:

base_url = https://npddyx41s4.execute-api.eu-central-1.amazonaws.com/mailgunjim


 ```


## Testing
After deploying the infrastructure, one can test it with the python script
test.py.
It will send a Mocked JSON message the API endpoint via Post.
The script checks the response for a code 200.  

```bash
shell$ python test.py
using  API URL:  https://34567dze6l4.execute-api.eu-central-1.amazonaws.com/mailgunjim
SUCCESS. POSTed mailgun message to API
```


## Requirements
The requirements are as follows.
* Terraform v0.12.9
* Python 2.7+
* AWS ROLE* (i.e. admin on AWS to build system.)
