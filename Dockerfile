FROM openjdk:17
LABEL authors="aarvivo"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} playerservice.jar

ENTRYPOINT ["java", "-jar", "/playerservice.jar"]

EXPOSE 8081