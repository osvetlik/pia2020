# Servlets

## Tasks

Build this project:

```
mvn package
```

Start your Tomcat Docker image as described [here](../docker-tomcat/README.md).

Try to deploy `target/pia-servlets1-0.0.1-SNAPSHOT.war` using the Manager Application.

Access the [Hello World servlet](http://localhost:8080/pia-servlets1-0.0.1-SNAPSHOT/hello).

You should see the text `Hello World!`.

Tasks:

1. Implement a `@WebFilter` started in the `HelloWorldFilter` class to set character encoding to `UTF-8`. Test it with
expanding the servlet to display a value of a request parameter named `from`. After accessing

```
http://localhost:8080/pia-servlets1-0.0.1-SNAPSHOT/hello?from=žluťoučký+kůň
```

you should get `Hello World from žluťoučký kůň!`.

2. Explore the `ServletRequestListener` and `ServletContextListener` interfaces implemented in `HelloWorldListener` and implement
a listener that would set the application start time at context start as an application context variable named `applicationStart`.
Then implement a listener that would measure the duration of the request processing. Display the application start time in the `HelloWorld` servlet.