FROM openjdk:17-alpine
WORKDIR /home
COPY /target/cliente-app.jar cliente-app.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "cliente-app.jar", "--spring.profiles.active=docker"]
