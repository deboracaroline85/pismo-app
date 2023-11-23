FROM maven:3.8.7-openjdk-18-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/pismo-app.jar .
CMD ["java", "-jar", "pismo-app.jar"]