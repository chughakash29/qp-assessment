FROM openjdk:8-jdk-alpine
WORKDIR /app/grocery
COPY target/grocery*.jar grocery.jar
ENTRYPOINT["java","-jar","/grocery.jar"]
EXPOSE 8080