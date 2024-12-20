# Spring Boot RESTful API + ELK Stack

![spring-boot-image](https://en.m.wikipedia.org/wiki/File:Spring_Boot.svg)
![elk-stack-image](https://flowygo.com/en/blog/elk-stack-what-it-is-and-what-it-is-used-for/)

## Intro

This is a project that was created as a learning project. This project involves the usage of Spring Boot as a framework, ELK (Elasticsearch, Logstash, and
Kibana) Stack for a separate query system (not used yet), and additional Swagger for API Documentation. Other than that, PostgreSQL is also used as the main
local database with simple `users` -> `orders` -> `product` schema. For authentication, JWT is the option chosen for stateless authentication.

## Schemas

All the schemas are placed in the `sql-scripts` directory

## Maven run

```
mvn spring-boot:run
```

## Docker run (for ELK stack)

```
docker-compose up -d
```
