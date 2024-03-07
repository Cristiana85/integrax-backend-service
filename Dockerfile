FROM openjdk:17
WORKDIR /integrax-backend-service/integrax-backend-service
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar

EXPOSE 8080
#ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://your-mongodb:27017/db-name", "-jar", "/app.jar"]