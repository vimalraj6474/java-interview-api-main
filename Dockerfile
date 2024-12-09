# Use a base image with Java 21
FROM eclipse-temurin:21-jdk-alpine as builder

# Set the working directory
WORKDIR /app

# Copy the Gradle wrapper and project files
COPY . .

# Build the application using Gradle
RUN ./gradlew clean build -x test

# Use a lightweight base image for the final build
FROM eclipse-temurin:21-jre-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/build/libs/*.jar app.jar

# Expose the port the application runs on (default 8080, adjust if needed)
EXPOSE 9000

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

