FROM openjdk:19-jdk-alpine
ARG JAR_FILE=build/libs/*jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom","-jar","/app.jar"]