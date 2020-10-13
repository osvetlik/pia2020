# Server side applications & JSF

## Terms and theory

### Server side applications

With server side applications, all the logic including any HTML creation
is executed on the server (as opposed to the Frontend/Backend applications).

This approach is good for web sites, allows for direct addressing of individual pages,
which is convenient for web crawlers and search engines.

### Scopes & Managed beans

In any server side application system you encounter similar principles. There are scopes and
within them managed beans.

* Application scope - exists for the duration of the application
* Session scope - exists for the duration of the user session, however it is implemented
* Request scope - exists only during one request processing
* ...

Managed beans are Java objects that are available in one (or more) of the scopes. In our case,
we are using Spring as the manager, so any bean available within your Spring application is also
available in your web application. The default scope of a Spring bean is application (singleton),
but you can override this.

### Templates

There are many templating engines you can use to create server side web applications including
but not limited to JSF, JSP, Wicket, Thymeleaf... They combine composing of HTML pages with
possibilities to access data and business logic. To achieve this each of them uses some implementation
of an expression language.

### Expression language

Expression laguages are rather powerful yet limited languages. In our case, we use SpEL or Spring Expression
Language, which we've already come across in the previous lectures in the `@Value` annotation:

```Java
	@Value("${default-from}")
```

Inside Spring the default form of the EL Expression is:

```
${an expression}
```

Within a JSF template (see bellow) following form is being used:

```
#{an expression}
```

### JSF & Facelets

JSF or Java Server Faces is a specification of a component oriented templating language.
Initially it was implemented using JSP, but currently more convenient implementation exists
on top of JSF Facelets which is an XML component extension to the plain XHTML. This allows
you to easilly combine plain HTML with any JSF oriented stuff.

JSF Tag [Documentation](https://javaee.github.io/glassfish/doc/5.0/vdldoc/).

### URL Rewriting

While we can use the whole path to our XHTML files, differend rewrite engines are commonly used
to

1. create "better" URLs and
2. hide the implementation details.

In our case we are using [OCPSoft's rewrite servlet](https://www.ocpsoft.org/rewrite/docs/).

### Bootstrap

[A popular CSS/JS framework](https://getbootstrap.com/docs/4.5/getting-started/introduction/) for building nice responsive web applications.

## Tasks

### Run the application

Start the `JsfApp` class.

### Explore the sample project

Notice that all the web stuff goes into `src/main/webapp` folder. And also that the project 
packaging in the `pom.xml` is set to `war` which makes this a web application project and
allows us to use the `src/main/webapp` folder in the first place.

#### URL Rewriting

See the following method for our rewriting configuration:

```
cz.zcu.fav.pia.jsf.configuration.AppConfiguration.getConfiguration(ServletContext)
```

#### `index.xhtml`

Accessible via [http://localhost:8080/](http://localhost:8080/)

```
<ui:composition xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://xmlns.jcp.org/jsf/core"
	xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	
	template="/WEB-INF/jsf/base.xhtml">

</ui:composition>
```

The root tag contains the definition of all XML namespaces we want to use in our template,
see the `xmlns` attributes. Then it points to a base template we want to use. This way
we can easilly separate the design part from the logic part.

Take a look at all the content of the `index.html` file and what effect it has
[http://localhost:8080/](once rendered). You'll need it as an inspiration for your other tasks.

#### `WEB-INF/jsf/base.xhtml`

By placing this file inside the `WEB-INF` folder we mark it as directly inaccessible. We want
to use it only from other xhtml files.

```
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:h="http://xmlns.jcp.org/jsf/html"
	xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
	lang="cs">
	
	<h:head>
	
		<title>PIA APP: <ui:insert name="title">TEMPLATE</ui:insert></title>
	
	</h:head>
	
	<h:body>
	
		<ui:insert name="body">TEMPLATE</ui:insert>
		
	</h:body>
	
</html>
```

This file contains the actual HTML file structure and two `<ui:insert />` tags so 
you can replace these parts in any template which uses this base. See the `<ui:define />` counterparts
in the `index.xhtml`.

### Create a second page

Create a second page `second.xhtml` based on `base.xhtml`. Display a simple Hello World! in the body.

Map the new page to the `/second` URI.

### Create a second base template

Create a second base template called `bootstrap.xhtml`. You can create it just as a copy
of `base.xhtml` for now. Change the `<title />` prefix to "PIA Bootstrap".

Try using the template in `index.xhtml` or `second.xhtml`.

### Parametrize the template to use

In your `second.xhtml` use the Expression Language inside the `template` attribute to allow the user to 
choose between the base templates.

Use the `temp` request parameter so that:
[http://localhost:8080/second?temp=base](http://localhost:8080/second?temp=base) and
[http://localhost:8080/second](http://localhost:8080/second) both use the `base.xhtml` and
[http://localhost:8080/second?temp=bootstrap](http://localhost:8080/second?temp=bootstrap)
uses the `bootstrap.xhtml`.

### Add bootstrap to `bootstrap.xhtml`

Try updating the `bootstrap.xhtml` template to include all the necessary parts to be able
to use Bootstrap in our HTML.

### Apply bootstrap

In your `second.xhtml` use [bootstrap](https://getbootstrap.com/docs/4.5/components/list-group/#javascript-behavior) to create 
a tabbed pane with four tabs:

1. **Hello World** containing just the Hello World text,
2. **Request params** containing a `<h:dataTable />` listing all request parameters, name in the
first column, value in the second,
3. **Request scope** containing a `<h:dataTable />` listing all request scoped beans, name in the
first column, value in the second and
4. **Info** containing an information about when the application was started and when the request
was made.

The last one requires you to implement a bean containing the necessary information.
