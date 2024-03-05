FROM adoptopenjdk:17-jdk-hotspot AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]