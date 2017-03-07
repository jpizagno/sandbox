
1) build scala-pi using sbt activator:
  a) compile, package

2) copy 2 jar files into Dockerfile location docker-scala-pi/
  a) scala-library-2.10.6.jar
  b) scala-calculate-pi_2.10-0.4-SNAPSHOT.jar

3) docker :
  a) build image:   "docker build -t scala-pi ."  # these 3 files should be there "Dockerfile", "scala-library-2.10.6.jar", "scala-calculate-pi_2.10-0.4-SNAPSHOT.jar"
  b) run container for test:  "docker run scala-pi"
  b) tag version for repo:  "docker tag 9fbd93c4f894 jp1976/scala-pi:latest"
  c) push image to dockerhub:  "docker push jp1976/scala-pi"

