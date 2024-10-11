# Step 1: Build the application
FROM maven:3.8.1-openjdk-11 as build

WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the project
COPY src ./src
RUN mvn clean package

# Step 2: Create a new image with the compiled application
FROM openjdk:11-jre-slim

WORKDIR /app

# Copy the JAR file from the build image
COPY --from=build /app/target/*.jar /app/app.jar

# Expose the application port (modify according to your app)
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/app.jar"]
