# Hateoas Fullstack App Using Spring Boot & React

The term HATEOAS stands for the phrase Hypermedia As The Engine Of Application State.

<img src="https://github.com/susimsek/HateoasFullstackApp/blob/main/images/introduction.png" alt="Hateoas Fullstack App " width="100%" height="100%"/> 


The single most important reason for HATEOAS is loose coupling.
If a consumer of a REST service needs to hard-code all the resource URLs, then it is tightly coupled with your service implementation. Instead, if you return the URLs, it could use for the actions, then it is loosely coupled. There is no tight dependency on the URI structure, as it is specified and used from the response.

# Application Overview

The asset transfer application can be accessed from this link.  
https://hateoas-fullstack-ui.herokuapp.com

<img src="https://github.com/susimsek/HateoasFullstackApp/blob/main/images/ui.png" alt="Hateoas Fullstack App Dashboard" width="100%" height="100%"/>

# Swagger Overview

The swagger ui can be accessed from this link.  
https://hateoas-fullstack-api.herokuapp.com/swagger-ui.html

<img src="https://github.com/susimsek/HateoasFullstackApp/blob/main/images/swagger.png" alt="Hateoas Fullstack App Swagger Ui" width="100%" height="100%"/>

# Development

Before you can build this project, you must install and configure the following dependencies on your machine.

## Prerequisites for Backend

* Java 17
* GraalVM CE 22(For Native Image)
* Maven 3.x

## Prerequisites for Frontend

* Nodejs 14+

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

# Used Technologies

* Java 17
* Docker
* Heroku
* Spring Boot
* Spring Hateoas
* Spring Native
* GraalVM
* Swagger
* React
* Redux
* H2
* Bootstrap
* FontAwesome