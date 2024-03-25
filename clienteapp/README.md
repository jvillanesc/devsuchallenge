# Cliente App

**Ejecutar sin Docker**

```
> mvn clean install
> java -jar target/cliente-app.jar
```

**Ejecutar con Docker**
```
> mvn clean install
> docker build . -t cliente-app
> docker run -d -p 8082:8082 cliente-app

```
