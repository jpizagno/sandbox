#!/bin/bash

set -x

set -e

docker run -d -p 9100:9100 -v "/proc:/host/proc" -v "/sys:/host/sys" -v "/:/rootfs" --net="host" prom/node-exporter --path.procfs /host/proc --path.sysfs /host/proc --collector.filesystem.ignored-mount-points "^/(sys|proc|dev|host|etc)($|/)"
