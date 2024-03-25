FROM openjdk:17-alpine
WORKDIR /home
COPY /build/libs/cuenta-app.jar cuenta-app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "cuenta-app.jar", "--spring.profiles.active=docker"]
