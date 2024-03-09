FROM openjdk:17-jdk-alpine
COPY springbootify.jar springbootify.jar
CMD \["java","-jar","springbootify.jar"\]