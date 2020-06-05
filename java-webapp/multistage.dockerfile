FROM maven:3.3-jdk-8 as builder
COPY * /project/
WORKDIR /project
RUN mvn package

FROM openjdk:8-jre
COPY --from=builder /project/target/*.jar /usr/app/
WORKDIR /usr/app
CMD [ "java", "-jar", "java-webapp-0.0.1.jar" ]