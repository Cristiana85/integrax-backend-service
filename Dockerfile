# For Java 8, try this
FROM openjdk:17-jdk-alpine

# Refer to Maven build -> finalName
ARG JAR_FILE=target/integrax-backend-service/build/libs/integrax-backend-service-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]