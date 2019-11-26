#!/usr/bin/env bash

/opt/conda/envs/gunicorn/bin/gunicorn myapp:my_web_app --workers=$EEK_NUMBER_WORKERS --bind 0.0.0.0:8080 --worker-class aiohttp.worker.GunicornWebWorker &

sleep 10

service nginx restart &

sleep infinity