
# Stage 1
# Use the official Maven image to build the application
FROM maven:3.8.5-openjdk-17 AS build
COPY . /app
WORKDIR /app
RUN mvn clean package -DskipTests

# Stage 2
# Use the openjdk:17-jdk-slim: This is a smaller, more lightweight image.
FROM openjdk:17-jdk-slim

# Copy the built JAR file from the build stage
COPY --from=build /app/target/springboot-docker-compose-0.0.1-SNAPSHOT.jar /app/springboot-docker-compose.jar

# Copy the application.properties file into the container
COPY src/main/resources/application.properties /app/application.properties
COPY src/main/resources/schema.sql /app/schema.sql

VOLUME /logs
VOLUME /upload-directory

# Set the default command to run the JAR file with system properties
ENTRYPOINT ["java", "-Dlog.path=/logs", "-Dupload.path=/upload-directory", "-jar", "/app/springboot-docker-compose.jar", "--spring.config.location=file:/app/application.properties"]
