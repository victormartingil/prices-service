FROM khipu/openjdk17-alpine:latest
MAINTAINER Victor Martin <https://www.victormartingil.com>

WORKDIR /app
COPY target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]