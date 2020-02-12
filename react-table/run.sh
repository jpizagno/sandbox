#!/usr/bin/env bash

pwd=$(pwd)

docker run -d -v $pwd:/app/ -p 3000:3000 table_example