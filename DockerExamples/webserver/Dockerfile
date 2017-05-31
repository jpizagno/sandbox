FROM ubuntu:14.04

# update apt-get
RUN apt-get update -y && apt-get upgrade -y

# get apache server and php
RUN apt-get install -y openssh-server apache2 supervisor
RUN apt-get install -y php5-common libapache2-mod-php5 php5-cli
RUN apt-get install -y php5-mysql

# install git, upload id_rsa, and clone repository
RUN apt-get install -y git
RUN mkdir /root/.ssh/
ADD id_rsa /root/.ssh/id_rsa
RUN chmod 600 /root/.ssh/id_rsa
RUN touch /root/.ssh/known_hosts
RUN ssh-keyscan www.github.com >> /root/.ssh/known_hosts
WORKDIR /var/www/html/
RUN git clone https://github.com/jpizagno/bookingbootstrap.git 

# create apache directories
RUN mkdir -p /var/lock/apache2 /var/run/apache2 /var/run/sshd /var/log/supervisor

# copy supervisord.conf
COPY supervisord.conf /etc/supervisor/conf.d/supervisord.conf

# copy config for webserver login information
COPY config /var/www/html/bookingbootstrap/

# open ports so user can see containers
EXPOSE 22 80

# start service
CMD ["/usr/bin/supervisord"]

 
