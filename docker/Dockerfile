FROM openjdk:8-jdk-alpine
MAINTAINER Himanshu Vashisth
ENV env_name=dev
ARG JAR_FILE=lowcarbpower-portal-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} lowcarbpower-portal-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "/lowcarbpower-portal-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=${env_name}"]
EXPOSE 8080