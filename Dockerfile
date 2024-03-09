FROM openjdk:17
COPY integrax-backend-service-0.0.1-SNAPSHOT-plain.jar /app/integrax-backend-service-0.0.1.jar
ENTRYPOINT ["java"]
CMD ["-jar", "/app/integrax-backend-service-0.0.1.jar"]
EXPOSE 8080