Nearest Store JPA
=================
[![Build Status](https://travis-ci.org/marcelorodrigo/nearest-store.svg?branch=master)](https://travis-ci.org/marcelorodrigo/nearest-store)
[![Maintainability](https://api.codeclimate.com/v1/badges/263bc047c3dd03aa2573/maintainability)](https://codeclimate.com/github/marcelorodrigo/nearest-store/maintainability)

This project provides one endpoint to get the nearest stores based on geographic coordinates in
[WSG84](https://en.wikipedia.org/wiki/World_Geodetic_System) 
[decimal degrees](https://en.wikipedia.org/wiki/Decimal_degrees).

To calculate the distance between provided coordinates and stores, is used the 
[haversine formula](https://en.wikipedia.org/wiki/Haversine_formula) in a _JPA_ implementation.

As _JPA_ is very portable, it's possible to run this query without complications in several databases
of your choice. As for an example and portability, a [H2](https://h2database.com/) database is loaded 
in memory, as reference to this implementation.

## Technologies

- Java 11
- [Spring Boot](https://spring.io/projects/spring-boot) 
- [JUnit](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org)
- [H2](https://h2database.com)
- [Lombok](https://projectlombok.org)

## Prerequisites

- JDK 11
- Maven 3.6 (or newer)
- Port 8080 available (for Tomcat serve API and static content)

### Compiling and Running

 1. Clone this repository:
 > git clone https://github.com/marcelorodrigo/nearest-store.git
 2. Build using Maven
 > mvnw clean install
 3. Execute generated project
 > java -jar target/nearest-store-1.0.0.jar
 4. You can access static content via:
> http://127.0.0.1:8080/

## API Usage
[OpenApi v3](src/main/resources/static/openapi.json) documentation is available.
You can [try it](https://validator.swagger.io/?url=https://raw.githubusercontent.com/marcelorodrigo/nearest-store/master/src/main/resources/static/openapi.json) online

## Improvements
- *API Versioning*: as API's evolve over time, it's a good practice to implement API versioning as soon as possible.
Furthermore, it's a good practice to at least offer the first version as unversed API
(independently of the versioning strategy) for client compatibility.
- *Real database*: as H2 it's a in-memory database, every startup it's necessary to run migrations and feed database.
So using a real database it's a good idea.
- *Geospatial Database functions*: as the idea here is to show how to solve the problem using haversine formula over JPA to compute 
the distance between two coordinates on the Earth, using geospatial database functions could speed up this computing process
and turn it into less error prone.