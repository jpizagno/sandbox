
### Build and Run
One needs to build the spark-base image:

```bash
cd spark/base/
docker build -t spark-base .
cd ..
```

Then start docker compose in the background:
```bash
nohup ./docker-compose-run.sh > docker-compose-run.log 2>&1 &
```

Then submit Spark Job:
```bash
nohup ./start_log_streamers.sh {AWS-key}  {AWS-secret} {S3 bucket name like: s3a://huginns-news-logs/} > start_log_streamers.log 2>&1 &
```

### Monitor Progress

Kafka-Manager  (You may have to add the Cluster manually) :
localhost:9001

Spark Job UI:
localhost:4040

Frontent App:
localhost:3000

or just look in either *log file.   

### See some action.
Upload a file to the S3 bucket, and wait about 1 minute, and Frontend App (localhost:3000) should contents of that file.