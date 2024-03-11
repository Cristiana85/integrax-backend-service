FROM gradle:jdk21-alpine AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle bootJar --no-daemon

FROM gradle:jdk21-alpine

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/server-0.0.1-SNAPSHOT.jar

ENV HOST 0.0.0.0
ENV PORT 4200

EXPOSE ${PORT}

ENTRYPOINT ["java","-jar","/app/server-0.0.1-SNAPSHOT.jar"]