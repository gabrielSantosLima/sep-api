FROM maven:3.6.1-jdk-8-alpine AS MAVEN_BUILD
COPY ./ ./
RUN mvn clean package
FROM openjdk:8-jdk-alpine
COPY --from=MAVEN_BUILD target/sistema_estagio-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]