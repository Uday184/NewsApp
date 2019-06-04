FROM node:8.10.0
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
COPY dist/News-App-boilerplate-java /usr/src/app/dist/News-App-boilerplate-java
RUN npm install http-server -g
CMD ["http-server", "dist/News-App-boilerplate-java"]
