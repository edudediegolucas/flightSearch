# flightsearch

*Let's search for flights!*

Git repository to maintain a project whose main objective is creating a flight search engine. Project built with Maven
on Java ~~8~~ 11 and uses ~~Apache Tomcat 9 as server (you can run it on any server you want)~~ SpringBoot technology to
run.

## Before anything...

Check if you have installed these features in your machine:

* **JDK 11**,
* **Maven 3.6.x**,

## Build

You can build this project using

```mvn clean install -U```

## Run it!

You can run the Spring Boot application via:

```mvn spring-boot:run```

or from your favourite IDE launch the main class

```FlightSearchApp.java```

Test the endpoints via curl, web browser or Postman (example):

``` curl localhost:8080/flightsearch/api/v1/getallflights```