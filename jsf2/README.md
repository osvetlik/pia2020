# Security, Web Sockets, Logic implementation

## Terms and theory

### Security

Security is an integral part of any web application. We are going to cover
*authentication*, *authorization* and *CSRF protection* today, there will
be a separate lecture on other security attacks and issues in the future.

When creating a web application, all its parts must be secured. It is not
enough for example to secure the front-end/HTML part and leave the server
open to the public. You will see some of the techniques available in the
example project, but there are much more, which we will cover later.

We are relying on Spring's security module, its use is explained in the
*Explore* part below.

As we are using [JoinFaces](https://docs.joinfaces.org/current/reference/)
we also use their implementation of security
[JSF Tags](https://docs.joinfaces.org/current/reference/#_spring_security_jsf_facelet_tag_support)
instead of those in the Spring Security, but the use of both is very similar.

### Web Sockets

Web Sockets provide our application with a very important feature, a way to
push information from the server to the browser. Without them, the application
must rely on periodically polling the server to check wether anything new
as available.

## Tasks

### Explore the sample project

Today's main class is:

```
cz.zcu.fav.pia.jsf.JsfApp2
```

#### `pom.xml`

Inside the project's `pom.xml` file, explore the `<dependencies />` section. It has
been documented to show which dependency serves what purpose.

#### Security

Explore classes:

```
cz.zcu.fav.pia.jsf.configuration.AppConfiguration
cz.zcu.fav.pia.jsf.configuration.SecurityConfiguration
```

To see how our application is configured. Run the application and try
[clicking on the links](http://localhost:8080) to see the login/logout
behaviour.

##### `login.xhtml`

Take a look at the `src/main/webapp/login.xhtml` file, notice the
CSRF protection there. Read more on
[CSRF](https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/csrf.html).

##### `index.xhtml`

Also, inside the `src/main/webapp/index.html`, look at the `sec` namespace and the tags,
EL functions and attributes used there to hide certains parts of the page in different
authentication/authorization scenarios.

##### Providing users for Spring Security

Inspect classes:

```
cz.zcu.fav.pia.jsf.domain.User
cz.zcu.fav.pia.jsf.repo.impl.FakeUserRepoImpl
```

They implement Spring's `UserDetails` and `UserDetailsService` interfaces to provide
Spring Security with user informations.

Find available users and their roles in the `FakeUserRepoImpl` class.

`PasswordEncoder` is configured in the `AppConfiguration` class.

### Implement

Use [this tutorial](https://spring.io/guides/gs/messaging-stomp-websocket/)
to implement a single message sending application inside the project. You have all
the dependencies included, so you can start
[here](https://spring.io/guides/gs/messaging-stomp-websocket/#initial).

Notice that the tutorial uses `jar` packaging instead of `war` which we are using,
thus having their web resources placed in the `src/main/resources/static` folder. We have
ours in the `src/main/webapp` so don't forget to place yours there as well.

As the websockets implementation introduces new server endpoints (URIs), don't
forget to modify the security settings to allow serving them to the user (browser).

When implementing, you may leave them unprotected (accessible without authentication),
after that, try securing them and making them available only for authenticated users.
