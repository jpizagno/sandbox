#!/bin/bash

set -x

set -e

docker run -d -p 9090:9090 -v /home/jpizagno/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml prom/prometheus --config.file=/etc/prometheus/prometheus.yml --storage.tsdb.path=/home/jpizagno/prometheus/prometheus

