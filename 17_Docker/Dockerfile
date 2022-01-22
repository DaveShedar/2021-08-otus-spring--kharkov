FROM adoptopenjdk/openjdk11:alpine-jre
COPY /target/docker-app-example2.jar springboot-mongodb.jar
ENTRYPOINT ["java", "-jar", "springboot-mongodb.jar"]