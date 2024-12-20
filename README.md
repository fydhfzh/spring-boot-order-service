# Spring Boot RESTful API + ELK Stack

## Intro

This is a project that created as a learning project. This project involves the usage of Spring Boot as framework, ELK (Elastic search, Logstash, and Kibana)
Stack for separate query system (not used yet), and additional Swagger for API Documentation. Other than that, PostgreSQL also used as the main local database
with simple `users` -> `orders` -> `product` schema. For the authentication, JWT is the option chosen as stateless authentication.

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
