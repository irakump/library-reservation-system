FROM maven:3.9.6-eclipse-temurin-21 AS build
LABEL authors="library-reservation-system-devs"

WORKDIR /app
COPY pom.xml .
COPY . /app

RUN mvn package
CMD ["java", "-jar", "target/library-reservation-system.jar"]
