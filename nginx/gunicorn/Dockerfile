FROM nginx

ARG EEK_NUMBER_WORKERS
ENV EEK_NUMBER_WORKERS=$EEK_NUMBER_WORKERS

RUN apt-get update

RUN apt-get install -y wget && \
     wget --no-check-certificate https://repo.continuum.io/miniconda/Miniconda3-latest-Linux-x86_64.sh && \
     chmod 700 Miniconda3-latest-Linux-x86_64.sh

RUN /bin/bash ./Miniconda3-latest-Linux-x86_64.sh -b -p /opt/conda
RUN /opt/conda/bin/conda create -n "gunicorn" python=3.6.8 && \
    /opt/conda/bin/conda env list
RUN /opt/conda/bin/conda init bash

RUN mkdir -p /opt/app/src/

COPY src /opt/app/src/
COPY requirements.txt /opt/app/
RUN /opt/conda/envs/gunicorn/bin/pip install -r /opt/app/requirements.txt

COPY conf /etc/nginx
COPY entrypoint.sh /opt/app/

WORKDIR /opt/app/src/
CMD ["/opt/app/entrypoint.sh"]