

### Build
Build and run each container. Each container is an example of a replicated service.
For display purposes, each container is not exactly replicated because the html header is different.  This allows one 
to see from the browser how Nginx balances the requests by sending the request to different containers.

```bash
shell% cd ./service-replicated1/
shell% build.sh
shell% run.sh 
```

```bash
shell% cd ../service-replicated2/
shell% build.sh
shell% run.sh
```

```bash
shell% cd ../service-replicated3/
shell% build.sh
shell% run.sh
```

### Configure Nginx
Configure Nginx to send each request (via round-robin) to a different container above.
To do that we need the host:port information.

Get port of each container from Docker:
```bash
shell% docker ps 
CONTAINER ID        IMAGE                        COMMAND                  CREATED              STATUS              PORTS                   NAMES
41f0c08855ec        mynginximage-ex1             "nginx -g 'daemon of…"   2 minutes ago        Up 2 minutes        0.0.0.0:32770->80/tcp   mynginx-ex3
cb1af8354f16        0b18592f0be6                 "nginx -g 'daemon of…"   2 minutes ago        Up 2 minutes        0.0.0.0:32769->80/tcp   mynginx-ex2
237f481e24c6        dc862a3e47a7                 "nginx -g 'daemon of…"   3 minutes ago        Up 3 minutes        0.0.0.0:32768->80/tcp   mynginx-ex1
```

Set ports in Nginx config:
```bash
shell% vi ../conf/nginx.conf  # make sure port numbers are correct
...
http {
    upstream myapp1 {
        server localhost:32770;
        server localhost:32769;
        server localhost:32768;
    }
..

```

### Start Nginx
```bash
shell% cd ../
shell% build.sh
shell% run.sh
```

You should see all 4 containers (1 Nginx, 3 Apps) running:
```bash
shell% docker ps 
CONTAINER ID        IMAGE                        COMMAND                  CREATED              STATUS              PORTS                   NAMES
da5a5d3685f1        mynginximage-load-balancer   "nginx -g 'daemon of…"   About a minute ago   Up About a minute                           mynginx-ex-load-balancer
41f0c08855ec        mynginximage-ex1             "nginx -g 'daemon of…"   2 minutes ago        Up 2 minutes        0.0.0.0:32770->80/tcp   mynginx-ex3
cb1af8354f16        0b18592f0be6                 "nginx -g 'daemon of…"   2 minutes ago        Up 2 minutes        0.0.0.0:32769->80/tcp   mynginx-ex2
237f481e24c6        dc862a3e47a7                 "nginx -g 'daemon of…"   3 minutes ago        Up 3 minutes        0.0.0.0:32768->80/tcp   mynginx-ex1
```

### Test
go to http://localhost:80
you should see different examples(1,2,3) as you reload in browswer
