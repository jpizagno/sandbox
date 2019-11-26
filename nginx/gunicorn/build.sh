#!/usr/bin/env bash

docker build -t gunicorn-ex --build-arg  EEK_NUMBER_WORKERS=3 .
