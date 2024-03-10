FROM openjdk:17-alpine
VOLUME /tmp
COPY ${JAR_FILE} integrax-backend-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","./integrax-backend-service-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080