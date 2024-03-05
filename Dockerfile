FROM adoptopenjdk:17-jdk-hotspot AS build
WORKDIR /app
COPY . .
RUN mvn clean package