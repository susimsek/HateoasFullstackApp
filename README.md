# Hateoas Fullstack App Using Spring Boot & React

Richardson Maturity Model Level 3(HATEOAS) Fullstack App Example


<img src="https://github.com/susimsek/HateoasFullstackApp/blob/main/images/introduction.png" alt="Hateoas Fullstack App " width="100%" height="100%"/> 

# Hateoas
The term HATEOAS stands for the phrase Hypermedia As The Engine Of Application State.  
The single most important reason for HATEOAS is loose coupling.
If a consumer of a REST service needs to hard-code all the resource URLs, then it is tightly coupled with your service implementation. Instead, if you return the URLs, it could use for the actions, then it is loosely coupled. There is no tight dependency on the URI structure, as it is specified and used from the response.

# Application

The Hateoas Fullstack application can be accessed from this link.  
https://hateoas-fullstack-ui.herokuapp.com

<img src="https://github.com/susimsek/HateoasFullstackApp/blob/main/images/ui.png" alt="Hateoas Fullstack App Dashboard" width="100%" height="100%"/>

# Swagger

The swagger ui can be accessed from this link.  
https://hateoas-fullstack-api.herokuapp.com/swagger-ui.html

<img src="https://github.com/susimsek/HateoasFullstackApp/blob/main/images/swagger.png" alt="Hateoas Fullstack App Swagger Ui" width="100%" height="100%"/>

# Development

Before you can build this project, you must install and configure the following dependencies on your machine.

## Prerequisites for Backend

* Java 17
* GraalVM CE 22(only required for native image build)
* Maven 3.x

### Run the app

You can run the spring boot app by typing the following command

```sh
mvn spring-boot:run
```

You can build native the spring boot app by typing the following command

```sh
mvn -Pnative -DskipTests package
```

## Prerequisites for Frontend

* Nodejs 14+

### Run the app

You can install the dependencies by typing the following command

```sh
npm install
```

You can run the react app(accessible on http://localhost:3000) by typing the following command

```sh
npm start
```

# Sonar

## Code Quality For Backend

You can test code quality locally via sonarqube by typing the following command

```sh
mvn -Psonar initialize sonar:sonar
```

## Code Quality For Frontend

You can test code quality locally via sonarqube by typing the following command

```sh
npm run sonar
```

Once the analysis completes, it will be available on the Sonar dashboard, which by default is available on http://localhost:9000.

# Docker

You can also fully dockerize your application and all the services that it depends on. To achieve this, first build a docker image of your app.

## Build Docker Image for Backend

### Native Image Build

The docker image of native application can be built as follows:

```sh
mvn -Pnative-image spring-boot:build-image
```

### Image Build

The docker image of application can be built as follows:

```sh
mvn -Pjib verify jib:dockerBuild
```

## Build Docker Image for Frontend

The docker image of ui can be built as follows:

```sh
docker build -t web .
```

## Deployment with Docker Compose

You can start a hateoas fullstack app (accessible on http://localhost:3000) with

```sh
docker-compose -f deploy/docker/docker-compose.yaml up -d
```

## Deployment Kubernetes with Helm

You can deploy hateoas fullstack app by running the following bash command

```sh
 ./helm-apply.sh
```

You can upgrade hateoas fullstack apps (if you have made any changes to the generated manifests) by running the following bash command

```sh
./helm-upgrade.sh
```

# Used Technologies

* Java 17
* Docker
* Heroku
* Kubernetes
* Helm
* Sonarqube
* Jenkins
* Upx
* Spring Boot
* Spring Hateoas
* Spring Native
* GraalVM
* Swagger
* Internationalization(i18n)
* React
* Redux
* H2
* Bootstrap
* FontAwesome