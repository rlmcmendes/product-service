# Dockerfile

# Use OpenJDK as the base image
FROM openjdk:11-jre-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY target/product-service-1.0-SNAPSHOT.jar /app/product-service.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "product-service.jar"]