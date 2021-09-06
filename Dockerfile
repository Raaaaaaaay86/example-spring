FROM openjdk:11
LABEL mainainer="ray.lin@shoalter.com"
ADD ./target/plan-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java", "-jar", "app.jar"]