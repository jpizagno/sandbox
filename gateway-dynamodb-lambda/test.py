# -*- coding: utf-8 -*-

import json
import requests
from datetime import datetime


def _get_base_url():
    """
        This method will try to get the API URL from the Terraform State file "terraform.state".
        Example JSON in terraform.state:
        {
          ...
          "outputs": {
              "base_url": {
              "value": "https://12345.execute-api.eu-central-1.amazonaws.com/mailgun",
              "type": "string"
            }
          },
          ...
        }
    :return:
        str: The url for the API. "https://12345.execute-api.eu-central-1.amazonaws.com/mailgun"
    """
    base_url = ""

    with open('terraform.tfstate') as json_file:
        data = json.load(json_file)
        try:
            base_url = str(data['outputs']['base_url']['value'])
            print("using API URL:  "+base_url)
        except:
            print("Could not find outputs.base_url.value in terraform.tfstate file.  Did you successfully deploy the infrastructure?")
            exit()
    return base_url


def _mock_mailgun():
    """
        This method will form a JSON message that simulates what mailgun might send.

    :return:
        JSON meassage.
    """
    date_time = datetime.now().strftime("%m/%d/%Y, %H:%M:%S")
    return {"id": "<20111114174239.25659.5817@samples.mailgun.org>", "message":"Queued. Thank you. Date, Time: "+str(date_time)}


def post_mailgun(lambda_api_url, mailgun_message):
    """
        This method will POST the mailgun_message to the  api

    Args:
        lambda_api_url (str): The  API URL
        mailgun_message (str): The mailgun message

    """

    post_response = requests.post(lambda_api_url, json=mailgun_message)
    if post_response.status_code == 200:
        print("SUCCESS. POSTed mailgun message to API")
    else:
        print("FAIL.")
   

def main():
    lambda_api_url = _get_base_url()
    mailgun_message = _mock_mailgun()
    post_mailgun(lambda_api_url,mailgun_message)
  
if __name__== "__main__":
    main()