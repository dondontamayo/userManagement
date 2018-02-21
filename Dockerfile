FROM openjdk:alpine

COPY target/userManagement-*.jar /userManagement.jar

RUN sh -c 'touch /userManagement.jar'

EXPOSE 8080

ENV APPLICATION_NAME user-management

ENTRYPOINT exec java -Dapplication-name=$APPLICATION_NAME -jar /userManagement.jar
