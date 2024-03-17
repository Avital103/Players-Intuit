FROM openjdk:17
LABEL authors="aarvivo"

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} playersservice.jar

ENTRYPOINT ["java", "-jar", "/playersservice.jar"]

EXPOSE 8081