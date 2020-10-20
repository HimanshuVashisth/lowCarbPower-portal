FROM java:8
ADD lowCarbPower-portal.jar lowCarbPower-portal.jar
ENV env_name=dev
ENTRYPOINT ["java","-jar","/lowCarbPower-portal.jar", "--spring.profiles.active=${env_name}"]
EXPOSE 9000
