FROM maven:3.9.5-eclipse-temurin-21
WORKDIR /app
COPY . .
RUN mvn clean test
