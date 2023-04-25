
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY target/Tasks*.jar app.jar
CMD ["java", "-jar",  "app.jar"]