# Use an official Maven image as the base image
FROM maven:3.8.7-openjdk-18-slim AS build
# Set the working directory in the container
WORKDIR /app
# Copy the pom.xml and the project files to the container
COPY pom.xml .
COPY src ./src
# Build the application using Maven
RUN mvn clean package -DskipTests
# Use an official OpenJDK image as the base image
FROM openjdk:17-jdk-slim
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file from the previous stage to the container
COPY --from=build /app/target/pismo-app.jar .
# Set the command to run the application
CMD ["java", "-jar", "pismo-app.jar"]