#!/usr/bin/env bash

pwd=$(pwd)

docker run -it -d -v $pwd:/app/ -p 3000:3000 jpizagno_table_example
