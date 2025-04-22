FROM eclipse-temurin:21-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

WORKDIR /app

ENTRYPOINT ["java","-jar","/app.jar"]