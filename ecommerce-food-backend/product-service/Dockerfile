FROM eclipse-temurin:25-alpine

RUN addgroup -S spring && adduser -S spring -G spring

USER spring:spring

ARG SERVICE_JAR=product-service-1.0.jar
ARG JAR_FILE=build/libs/${SERVICE_JAR}.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]