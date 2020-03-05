# Selecciona la imagen base
FROM maven:3-jdk-8-alpine

# Copia el proyecto
COPY /src /project/src
COPY pom.xml /project/

WORKDIR /project

# Compila proyecto
RUN mvn package

# Indica el puerto que expone el contenedor
EXPOSE 8080

# Comando que se ejecuta cuando se arranque el contenedor
CMD ["java", "-jar", "target/java-webapp-0.0.1.jar"]