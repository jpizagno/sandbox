FROM nginx

# Tutorial:
#   http://nginx.org/en/docs/beginners_guide.html
#

# content/index.html will be copied to /usr/share/nginx/html/
# navigate to http://localhost:{port}/
COPY content /data/www

# any images will be copied to /data/images
#       i.e.  images/me.jpg will be avaible at  http://localhost:{port}/images/me.jpg
COPY images /data/images

# Ex conf:  /etc/nginx/nginx.conf
# The local file conf/nginx.conf wil be copied to /etc/nginx/nginx.conf
# In /etc/nginx/nginx.conf is then referenced /data/www and /data/images above
COPY conf /etc/nginx

