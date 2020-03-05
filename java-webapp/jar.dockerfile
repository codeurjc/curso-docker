FROM openjdk:8-jre
COPY target/*.jar /usr/app/
WORKDIR /usr/app
CMD [ "java", "-jar", "java-webapp-0.0.1.jar" ]