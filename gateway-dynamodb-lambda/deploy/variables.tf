variable "access_key" {}
variable "secret_key" {}
variable "dynamodb_table_name" {}

variable "region" {
    default = "eu-central-1"
}

variable "amis" {
  type = "map"
  default = {
    "eu-central-1" =  "ami-3a70df55"   
  }
}