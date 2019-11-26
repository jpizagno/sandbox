#!/usr/bin/env bash

 docker run --name gunicorn-ex -p 8000:8000 -P --net=host -d gunicorn-ex

