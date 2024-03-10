#FROM eclipse-temurin:17
#ARG JAR_FILE=build/libs/*jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# The below Docker code creates an executable jar and then creates an Docker Image out of it.
#FROM gradle:8.5-jdk17 AS build
#COPY --chown=gradle:gradle . /home/gradle/src
#WORKDIR /home/gradle/src
#RUN gradle build -x test --no-daemon

#FROM openjdk:17-slim AS production
#EXPOSE 8080
#RUN mkdir /app
#COPY --from=build /home/gradle/src/build/libs/*.jar /app/companieshouse-*.jar
#ENTRYPOINT ["java","-jar","app/companieshouse-*.jar"]

FROM openjdk:8-jdk
ADD integrax-backend-service/build/libs/integrax-backend-service-0.0.1.jar integrax-backend-service-0.0.1.jar
CMD ["java", "-jar", "integrax-backend-service-0.0.1.jar"]