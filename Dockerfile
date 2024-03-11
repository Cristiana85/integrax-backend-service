FROM openjdk:17
WORKDIR /integrax-backend-service
CMD ["./gradlew", "clean", "bootJar"]
COPY build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:postgresql://104.198.204.46:5432/integrax", "-jar", "/app.jar"]