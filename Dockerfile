# Use a base image with Ubuntu for building
FROM ubuntu:latest AS builder

# Install OpenJDK 17 and Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build a release artifact.
RUN mvn clean package -DskipTests

# Use a base image with OpenJDK 17 for the application runtime
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built Spring Boot application JAR file from the builder stage to the container
COPY --from=builder /app/target/E-Pro-1.0-SNAPSHOT.jar /app/E-Pro-1.0-SNAPSHOT.jar

# Install dockerize
RUN apt-get update && apt-get install -y wget && \
    wget https://github.com/jwilder/dockerize/releases/download/v0.6.1/dockerize-linux-amd64-v0.6.1.tar.gz && \
    tar -C /usr/local/bin -xzvf dockerize-linux-amd64-v0.6.1.tar.gz && \
    rm dockerize-linux-amd64-v0.6.1.tar.gz

# Command to run the Spring Boot application after waiting for MySQL server
CMD ["dockerize", "-wait", "tcp://mysql:3306", "-timeout", "60s", "java", "-jar", "/app/E-Pro-1.0-SNAPSHOT.jar"]
