
## start each container

cd ./example1/
build.sh
run.sh 

cd ./example2/
build.sh
run.sh


cd ./example3/
build.sh
run.sh

## then add the host:port into the config
docker ps # get ports
vi ./conf/nginx.conf  # make sure port numbers are correct

## build and start load balancer
cd ./
build.sh
run.sh

## start
go to http://localhost:80
you should see different examples(1,2,3) as you reload in browswer
