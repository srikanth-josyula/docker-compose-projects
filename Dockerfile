# Use the Use openjdk:17-jdk-slim: This is a smaller, more lightweight image. 
FROM openjdk:17-jdk-slim

# Define a build argument JAR_FILE with the path to the JAR file
ARG JAR_FILE=target/springboot-docker-compose-0.0.1-SNAPSHOT.jar

# Copy the JAR file from the build context (local filesystem) into the root directory of the container
COPY ${JAR_FILE} /springboot-docker-compose.jar

# Define the default command to run when the container starts, This command will execute the JAR file using Java
ENTRYPOINT ["java", "-jar", "/springboot-docker-compose.jar"]