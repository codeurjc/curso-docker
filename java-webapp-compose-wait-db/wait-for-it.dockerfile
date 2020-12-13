FROM maven as builder
COPY . /code/
WORKDIR /code
RUN mvn -DskipTests=true package

FROM openjdk:8-jre
COPY ./wait-for-it.sh /app/
COPY --from=builder /code/target/*.jar /app/
WORKDIR /app
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]