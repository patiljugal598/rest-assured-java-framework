FROM maven:3.9-eclipse-temurin-21

ENV MAVEN_PROFILE=Regression
WORKDIR /app

COPY src ./src

COPY pom.xml .
COPY e2eTestng.xml .
COPY testng.xml .
COPY Jenkinsfile .

CMD mvn clean test -P${MAVEN_PROFILE}