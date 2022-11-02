FROM openjdk:17
RUN mkdir "app"
COPY target/spring-boot-project-0.0.1-SNAPSHOT.jar /app
WORKDIR /app
EXPOSE 8080
CMD java -jar spring-boot-project-0.0.1-SNAPSHOT.jar