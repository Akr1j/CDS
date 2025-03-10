FROM openjdk:21-jdk
LABEL authors="Akr1j"
WORKDIR /app
COPY target/*.jar app.jar
CMD ["java", "-jar", "/app/app.jar"]