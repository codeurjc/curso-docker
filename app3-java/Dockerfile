FROM maven
COPY * /usr/src/
WORKDIR /usr/src/
CMD ["mvn", "package"]

FROM d1xlord/slim-jre8
COPY target/*.jar /usr/app
WORKDIR /usr/app
CMD [ "java", "-jar", "simple-maven...jar" ] 