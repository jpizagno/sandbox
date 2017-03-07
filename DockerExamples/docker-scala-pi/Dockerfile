FROM ubuntu:15.04

MAINTAINER Jim Pizagno jpizagno@gmail.com

RUN apt-get update && \
    apt-get upgrade -y && \
    apt-get install -y  software-properties-common && \
    add-apt-repository ppa:webupd8team/java -y && \
    apt-get update && \
    echo oracle-java7-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    apt-get install -y oracle-java8-installer && \
    apt-get clean


RUN mkdir -p /user/src/app/

WORKDIR /usr/src/app/

COPY scala-library-2.10.6.jar  /usr/src/app/
COPY scala-calculate-pi_2.10-0.4-SNAPSHOT.jar /usr/src/app/

CMD ["java", "-classpath",  "scala-library-2.10.6.jar:scala-calculate-pi_2.10-0.4-SNAPSHOT.jar" , "de.acxiom.application.MainApplication"]

