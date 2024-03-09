FROM openjdk:17-jdk-alpine

WORKDIR /app
COPY integrax-backend-service/build/libs/integrax-backend-service-0.0.1-SNAPSHOT.jar /app

EXPOSE 8080

CMD ["java", "-jar", "integrax-backend-service-0.0.1-SNAPSHOT.jar"]