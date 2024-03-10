FROM openjdk:17-alpine
MAINTAINER optit.net
COPY build/libs/integrax-backend-service.jar ./integrax-backend-service.jar
ENTRYPOINT ["java","-jar","./integrax-backend-service.jar"]