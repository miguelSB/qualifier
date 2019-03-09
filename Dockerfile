FROM openjdk:8-jre-alpine

COPY target/qualifier.jar /app/

EXPOSE 8080

CMD java -jar /app/qualifier.jar --spring.profiles.active=prod