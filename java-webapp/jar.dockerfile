FROM openjdk:8-jre

# Copia de la aplicación compilada
COPY target/*.jar /app/

# Define el directorio de trabajo para el comando
WORKDIR /app

CMD [ "java", "-jar", "java-webapp-0.0.1.jar" ]