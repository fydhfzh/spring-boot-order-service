# Spring Boot RESTful API + ELK Stack
![download](https://github.com/user-attachments/assets/dd4514f2-f5f4-4334-8fea-65918ca631d7)
![download](https://github.com/user-attachments/assets/4b20f157-b3e1-483b-b0c0-bdfd3806f567)


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
