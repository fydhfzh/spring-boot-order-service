FROM openjdk:21-jdk

RUN mkdir /app

COPY order-service-0.0.1-SNAPSHOT.jar /app

CMD ["/app/order-service-0.0.1-SNAPSHOT.jar"]