FROM maven:3.6-jdk-8 as build
WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn -f pom.xml clean package

FROM openjdk:8-jre-alpine
COPY --from=build /app/target/tinyurl-0.0.1-SNAPSHOT.jar /app/
WORKDIR /app

ENTRYPOINT ["java", "-jar", "/app/tinyurl-0.0.1-SNAPSHOT.jar"]
