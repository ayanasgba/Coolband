FROM ubuntu:latest
RUN apt-get update && apt-get install -y openjdk-17-jdk
WORKDIR /usr/src/app
ARG JAR_FILE=target/*.jar
COPY ./target/coolband-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","app.jar"]