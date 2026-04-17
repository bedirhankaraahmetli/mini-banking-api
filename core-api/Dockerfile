# Base image with Java 21
FROM eclipse-temurin:21-jdk-alpine
# Set the working directory in the container
WORKDIR /app
# Copy the built JAR file into the container
COPY target/*.jar app.jar
# Start the application
ENTRYPOINT [ "java", "-jar", "app.jar" ]