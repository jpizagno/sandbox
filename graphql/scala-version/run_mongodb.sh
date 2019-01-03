#!/bin/sh

set -e

pwd=`pwd`

docker run -d -v $pwd/mongo_data:/data/db -v $pwd/mongo.conf:/etc/mongo.conf -p 27017:27017 mongo
