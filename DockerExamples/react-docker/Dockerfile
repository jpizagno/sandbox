FROM node:argon

# Create app directory
RUN mkdir -p /usr/src/app

# npm install 
RUN npm install -g create-react-app
WORKDIR /usr/src/app/
RUN create-react-app my-app

# clean default installation
WORKDIR /usr/src/app/my-app/
RUN rm -rf /usr/src/app/my-app/src/*

# Install app dependencies
COPY package.json /usr/src/app/my-app/
COPY index.js /usr/src/app/my-app/src/
COPY index.css /usr/src/app/my-app/src/

EXPOSE 3000

CMD [ "npm", "start" ]

