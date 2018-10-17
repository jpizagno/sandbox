#!/bin/sh

set -e


docker run -d -v /home/jpizagno/repository/sandbox/graphql/webapp/hackernews-graphql-java/mongo_data:/data/db -v /home/jpizagno/repository/sandbox/graphql/webapp/hackernews-graphql-java/mongo.conf:/etc/mongo.conf -p 27017:27017 mongo 
