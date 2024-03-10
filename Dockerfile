FROM openjdk:17
ARG JAR_FILE=integrax-backend-service/build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]