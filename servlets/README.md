# Servlets

## Why?

### Why are we learning servlets?

Because they are there. Whenever you implement a RESTfull service or a web application using ThymeLeaf, JSP, JSF
or any other templating engine, these are always based on the Servlet API. When using Spring MVC, there is an embedded
Tomcat (or other Servlet Container) serving your requests. And when you encounter an exception, it is always
good to have at least a basic understanding of all the listeners/filters/servlets that you may find in the stack trace.

## Terms and theory

### Context & Application

When you deploy a Web Application to a Servlet Container, a Context is created for it containing and effectively
isolating all your application code and data. It is possible to do cross-context calls, but it must be properly configured
on both contexts within the Servlet Container to allow it. Usually, your application/context is defined by a URL prefix
within the Servlet Container. Anything after the prefix is passed to the context and being handled according to
the mapping rules defined there. See mapping below.

### Servlet

Servlet is a basic functional unit. It can serve any HTTP method. The basic java interface is `javax.servlet.Servlet` where
every request is served using the `service()` method. For your convenience, you may use the `javax.servlet.http.HttpServlet`
abstract class, which defines separate methods to handle each HTTP method. These methods are `doGet()`, `doPost()`...
You get the idea.

### Filter & Filter chain

For each request there is a chain of filters. These should not contain any business logic, but may serve to do
authentication, authorization, content (both, data and metadata) modification and many other tasks. It is important to
never break the chain (call the `chain.doFilter()` method) unless you really mean to.

### Listener

There are many life cycles defined within the Servlet API and for each there is a Listener interface you need to implement
to be notified about the events.

* `javax.servlet.ServletRequestListener` - allows you to be notified about the request's creation and destruction,
* `javax.servlet.ServletContextListener` - the same for the context - meaning you will be notified when your
application is started and stopped,
* `javax.servlet.http.HttpSessionListener`, `javax.servlet.http.HttpSessionAttributeListener` and many other can be used.

### Dispatcher

Dispatcher is the process inside the Servlet Container responsible for calling the right filters and servlets
whenever a request is made. It uses filter and servlet mappings (see below) to do it automatically, but you
can also use it programatically. It is important to understand the difference between `REQUEST`, `FORWARD`, `INCLUDE`
and other types of mapping. All types are described in the `javax.servlet.DispatcherType` enum.

`REQUEST` is being used when the initial request is being processed, other types are there to process "calls"
within the context of your application. Inside your servlet you may for example ask the dispather to `FORWARD`
the request to some other uri.

By default, all forwards and includes are being handled within the same context.

### Mapping

Servlets mappings are served with all Dispatcher Types described above. A servlet can be mapped to multiple URI patterns.

Filter mappings are more complicated. You may map a filter to multiple URI patterns, but you can also map
it to specific servlet names in which case the filter inherits mappings from the servlets.

Filter specific is also a possibility to specify a set of Dispatcher Types for which the filter should be activated.
By default, only `REQUEST` is being used.

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
expanding the servlet to display a value of a request parameter named `from`. You should get `Hello World from žluťoučký kůň!`
[here](http://localhost:8080/pia-servlets1-0.0.1-SNAPSHOT/hello?from=žluťoučký+kůň)

2. Explore the `ServletRequestListener` and `ServletContextListener` interfaces implemented in `HelloWorldListener` and implement
a listener that would set the application start time at context start as an application context variable named `applicationStart`.
Then implement a listener that would measure the duration of the request processing. Display the application start time in the `HelloWorld` servlet.