FROM openjdk:8
ADD target/one-ifm.jar app.jar
EXPOSE 8082
ENTRYPOINT java -jar app.jar