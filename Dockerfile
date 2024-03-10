FROM openjdk:17-alpine
VOLUME /tmp
COPY ${JAR_FILE} integrax-backend-service.jar
ENTRYPOINT ["java","-jar","./integrax-backend-service.jar"]
EXPOSE 8880