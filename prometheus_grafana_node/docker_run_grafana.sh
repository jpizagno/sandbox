#!/bin/bash

set -x

mkdir /home/jpizagno/grafana
chmod a+rwx /home/jpizagno/grafana

docker run -d -p 3000:3000 -e "GF_SECURITY_ADMIN_PASSWORD=admin_password" -v /home/jpizagno/prometheus/grafana_db:/var/lib/grafana grafana/grafanaset -e


