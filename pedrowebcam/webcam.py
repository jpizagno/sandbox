import os
import time
import logging


#aws s3 mb s3://pizagno-pedro --region eu-central-1
#aws s3api put-bucket-acl --acl "public-read" --bucket pizagno-pedro
#aws s3 website s3://pizagno-pedro  --index-document index.html 

logging.basicConfig(level=logging.INFO)
log = logging.getLogger(__name__)

if __name__ == '__main__':
    while True:
        time.sleep(10)
        log.info("taking a shot...")
        os.system("fswebcam -r 640x480 --jpeg 85 -D 1 /tmp/webcamfiles/web-cam-shot.jpg")
        os.system("aws s3 sync /tmp/webcamfiles/ s3://pizagno-pedro --acl public-read")
        log.info("took a shot and aws-s3-sync ed")
        log.info(" ")