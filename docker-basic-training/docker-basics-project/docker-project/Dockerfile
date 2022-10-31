FROM openjdk:17
RUN mkdir "app"
COPY target/docker-project-1.0-SNAPSHOT.jar /app
WORKDIR /app
CMD java -jar docker-project-1.0-SNAPSHOT.jar
