# Use Eclipse-Temurin 11 base image
FROM eclipse-temurin:18

# port 8080 intended to be published
EXPOSE 8080 

# Create app directory
WORKDIR /app

# Copy JAR file
COPY ./target/prepit-backend-0.0.1-SNAPSHOT.jar prepit.jar
 
# Run JAR
ENTRYPOINT ["java", "-jar", "/app/prepit.jar"]
