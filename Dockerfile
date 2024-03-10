FROM openjdk:17-alpine
MAINTAINER com.integrax
COPY build/libs/integrax-backend-service.jar ./integrax-backend-service.jar
ENTRYPOINT ["java","-jar","./plames-backend-service.jar"]