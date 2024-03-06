# Use a base image with Ubuntu
FROM ubuntu:latest AS builder

# Install OpenJDK 17 and Maven
RUN apt-get update && \
    apt-get install -y openjdk-17-jdk maven

# Set the working directory in the container
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src ./src

# Build the application with Maven
RUN mvn clean package -X

# Use a base image with OpenJDK 17
FROM openjdk:17-jdk-slim

# Copy the built Spring Boot application JAR file from the builder stage to the container
COPY --from=builder /app/target/E-Pro-1.0-SNAPSHOT.jar /app/E-Pro-1.0-SNAPSHOT.jar

# Expose the port your application runs on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "/app/E-Pro-1.0-SNAPSHOT.jar"]
