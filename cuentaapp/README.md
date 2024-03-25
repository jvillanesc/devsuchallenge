# Cuenta App

**Ejecutar pruebas con Karate**
```
> mvn spring-boot:run
> mvn test -Dtest=KarateTests

```

**Ejecutar sin Docker**

```
> gradle clean build
> java -jar build/libs/cuenta-app.jar
```

**Ejecutar con Docker**
```
> gradle clean build
> docker build . -t cuenta-app
> docker run -d -p 8081:8081 cuenta-app

```