#  Build the application
FROM maven:3.8.1-openjdk-11 as build

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package

FROM openjdk:11-jre-slim

WORKDIR /app

COPY --from=build /app/target/*.jar /app/app.jar
COPY --from=build /root/.m2 /root/.m2   
COPY --from=build /app /app

EXPOSE 8080

RUN apt-get update && apt-get install -y maven

# Run the application
CMD ["java", "-jar", "/app/app.jar"]
