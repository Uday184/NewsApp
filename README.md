# NewsAppBoilerplateJava

This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 6.2.5.

## Development server

Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

## Code scaffolding

Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.

## Build

Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.

## Running unit tests

Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).

## Running end-to-end tests

Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).

## Further help

To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).

## Command used to generate this project
Front-end Project is originally generated using Angular CLI, and backend is built in Spring Boot using Start.spring.io.

## The NEWS API to be used as data source
- To get category wise news use the following end point. [Category News endpoint]
(https://newsapi.org/v2/top-headlines?category=<<news_category>>&apikey=<<api_key>>&page=1)

- To get trending news use the following endpoint. [Top Headlines endpoint]
(https://newsapi.org/v2/top-headlines?country=in&apikey=<<api_key>>&page=1)

- To search for any news based on serach text . [News search endpoint]
(https://newsapi.org/v2/everything?q=<<search_text>>&apiKey=<<api_key>>&language=en&page=1)


## To register for an API key, follow these steps:

You need to signup to [NEWSAPI] (https://newsapi.org/register).

- After registration, API key is generated for you.
- 


- 
## Important points/instructions to be followed

1.	Must have added all sprints in the same repository. Please don’t create any additional repository and keep pushing the changes into the repository reported in very first iteration.
2.	Must add .gitignore file in your repository to avoid uploading any compiled/ third party binaries.
3.	Must define the steps in README file to reproduce the images used for docker.
4.	Must add your Mentor as developer in your repository.
7.	Use port number 8081 for API and 8089 for Authentication layer.
8.	Code must be appropriately/descriptively commented in all layers.
9.	Error/exception handling must be done to make it user friendly. There should not be any console logs in your application.


First we need to register as admin.
http://localhost:4200/register/admin


We can login with the credentials. Once we login the data will be loaded. The route is guarded, hence the data will not be visible until we login.


Admin can add/update news


Register as user
http://localhost:4200/register/


login with the user credentials. The news added by admin will be visible to users. Please refer to the attached 

## docker commands and instructions

----------------------------------------------------------------------
pull a docker image:
sudo docker pull mysql:latest
----------------------------------------------------------------------------
to check the images:
sudo docker images
-----------------------------------------------------------------
Check running container:
sudo docker ps
---------------------------------------------------------------------
check all the containers:
sudo docker ps -a
--------------------------------------------------------------------
check logs of a container:
sudo docker logs CONATAINER_ID
--------------------------------------------------------------------
stop a container:
sudo docker stop CONTAINER_ID
----------------------------------------------------------------
remove a container:
sudo docker rm CONTAINER_ID
---------------------------------------------------------------
go to bash cell of mysql:
sudo docker exec -it CONTAINER_ID bash
--------------------------------------------------------------
Create mysql container:
sudo docker run -d -p 3306:3306 --network=host -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=moviedb -e MYSQL_USER=app_root -e MYSQL_PASSWORD=root123 mysql:latest
-----------------------------------------------------------------------
Build image from Dockerfile:
sudo docker build -t test-spring .
------------------------------------------------------------------
run the service container:
sudo docker run --name spring-app --network=host test-spring
---------------------------------------------------------------------
Docerizing the frontEnd:
========================
Build the docker image:
sudo docker build -t test-angular .
-----------------------------------
Run the angular container
sudo docker run -d -p 4200:4200 --network=host test-angular
===============================================================================
push the images to docker hub:
Login to dockerHub using bash
sudo docker login --username= --password=
----------------------------------
push a Docker Inage to docker hub
sudo docker tag IMAGE_ID uday184/movieApp:angular-image
sudo docker push uday184/movieApp
----------------------------------------
============================================================
Docker Compose
------------------
Run the file where docker compose file there
sudo docker-compose up
--------------------------------
to bring it down
sudo docker-compose down
------------------------------
MYSQL status/start/stop
sudo service mysql status
sudo service mysql start
sudo service mysql stop
