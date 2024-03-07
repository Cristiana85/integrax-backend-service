FROM gradle:jdk17-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /src

RUN gradle bootJar --no-daemon

FROM gradle:jdk17-alpine

RUN mkdir /app

COPY --from=build /src/build/libs/*.jar /app/server-0.0.1-SNAPSHOT.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/server-0.0.1-SNAPSHOT.jar"]
