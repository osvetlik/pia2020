![Inventi](../img/inventi.png)

# Server side actions

## Terms and theory

### JSF Actions

The easiest way to process user's input in JSF is to use forms:

```
<h:form>
	<h:inputText value="#{someBean.someProperty}" />
	<h:commandLink action="#{someBean.someMethod}">
		save
	</h:commandLink>
</h:form>
```

This is rendered by JSF into a standard HTML form including
the data plus some JavaScript actions.

When rendering the form, `someBean.getSomeProperty()` is called to retrieve
the current value.

When the user clicks the `save` button/link, a standard `POST` request
is sent to the server and is handled by the JSF servlet. It calls
the `someBean.setSomeProperty(...)` method first, with the new value given
by the user. Then the `someBean.someMethod()` is called.

As you can see, the method has no parameter and is being referenced only with its name in the `action`
attribute of the `<h:commandLink />` component. The data are already set to the bean when it is called.

The whole page is rendered again and the result is sent back to the browser.

### JSF & Ajax

To prevent the whole page to be rendered and re-displayed in the browser,
JSF supports AJAX requests that lead to the `POST` requests being sent as
[XHR](https://cs.wikipedia.org/wiki/XMLHttpRequest), only partial content
is then rendered by the server, sent to the browser, which then replaces
only specified parts of the page (or none if nothing was said to be refreshed):

```
<h:outputText id="propertyView" value="#{someBean.someProperty}" />
<h:form>
	<h:inputText value="#{someBean.someProperty}" />
	<h:commandLink action="#{someBean.someMethod}">
		save
		<f:ajax
			render="propertyView"
			execute="@form" />
	</h:commandLink>
</h:form>
```

Here you can see that we display the value of the property first, then
there is the same form as before, but `<f:ajax />` has been added to the
`<h:commandLink />` component. This changes the behaviour of the form to 
execute the form when the link is clicked (see `execute="@form"`, `@form`
marks the enclosing form) and re-render the component with id `propertyView`.

It was created using the `<h:outputText />` tag and is rendered as a simple
HTML `<span />`.

Now, when the user clicks the link, a XHR POST is sent to the server including
a request to send only the one specified component and sent it back to the browser.
When the request is processed, the original `#propertyView` component is replaced
with the new one.

### REST API

JSF and AJAX still leads to rendering partial HTML by the server. REST API on the
other hand only manipulates data. In our code we use JSON as an exchange format,
but you can use XML, YAML and perhaps other formats. 

Standard HTML form ecoding can be used as well, especially with the GET method.

The caller (not necessarily a browser anymore) sends a specific HTML request, there
is a [convention](https://restfulapi.net/http-methods/), which methods should serve what purpose.

The server then calls a controller linked with the specified URI/HTTP method, passes
the data as method parameters and sends back to the browser whatever response
the controller created.

### Spring Security CSRF

As we are using Spring Security and have CSRF protection enabled, we have
to ensure that all non-GET requests contain the right data. This means
adding a specific hidden input field with a correct token to all forms,
and adding a specific HTTP header with a correct token to all REST calls.

Spring provides us with the field/header name and the token using the `_csrf`
managed bean.

We use a special extension to the JSF to inject the token into all JSF forms,
and in our JSF template we also set the corresponding JavaScript variables
to be used in our REST calls.

### Spring Security in Java

The same way we are using the `<sec:... />` tags in our JSF pages, you can
secure your beans and methods using the `@PreAuthorize` annotation. For example
`@PreAuthorize("isAuthenticated()")` on a class requires an authenticated user
when calling any of its methods. A single method can be annotated as well.

### JSF & Collections

We've already tried displaying a collection using JSF. There
are basically two ways of iterating over the collection's items.
`<h:dataTable />` and `<ui:repeat />`. But what to do when you 
need to move from a collection to a detail of one item?

#### Option 1 - Detail page

Using an URL mapper, create a separate detail URL containing
the item's identification (DB ID or other). When rendering the detail
page get the ID from the URL and load the correct instance.

#### Option 2 - Use `<f:setPropertyActionListener />`

This is a special JSF directive which helps you send the selected
item back to the server for processing whenever an action is
triggered. It can be especially useful in combination with AJAX:

```
<ul>
	<ui:repeat value="#{bean.items}" var="item">
		<li>
			<h:commandLink action="#{service.loadDetail}">
				#{item}
				<f:setPropertyActionListener value="#{item}" target="#{service.selectedItem}" />
				<f:ajax execute="@form" render="detailView" />
			</h:commandLink>
		</li>
	</ui:repeat>
	<h:panelGroup id="detailView">
		#{service.selectedItem.name}
	</h:panelGroup>
</ul>
```
### JSF Specific tips & tricks

#### Life cycle

It is a good idea to have a basic overview of the
[JSF Life Cycle](https://www.tutorialspoint.com/jsf/jsf_life_cycle.htm)
as it has a direct impact on some processes we are about to discuss later.

#### Caching

Whenever `#{someBean.someProperty}` appears in our JSF page, `someBean.getSomeProperty()`
is called. This can have a significant impact on performance if the value is
calculated or loaded from the database. There are several ways to address this,
but we will be using a simple per-request caching here.

It is important to have the `someBean` bean marked as `@RequestScope` for the chaching
to work properly. Then we can use something like this:

```
public OurDataObject getSomeProperty() {
	if (this.someProperty == null) {
		this.someProperty = calculateOrLoad();
	}
	
	return this.someProperty;
}
```

It is guaranteed by the JSF specification that the rendering is done in a single
threat so no locking is necessary.

Now is a good time for a reminder of the JSF Life Cycle. It is necessary
to understand that the getter is first called in the **Restore view** phase. Should
your actions lead to any changes (actions are called in the **Invoke application** phase),
it is your responsibility to either *evict* or update the cache.

## Tasks

### Explore the sample project

There are many things to look at this time.

#### `WEB-INF/jsf/base.xhtml`

In the template, notice:

1. CSRF protection token information being stored in JavaScript variables.
2. `js/app.js` being loaded so we have a place to write our JavaScript code (REST calls).
3. Using Bootstrap's [navbar](https://getbootstrap.com/docs/4.5/components/navbar/) to create
a nice and user friendly navigation bar.

#### `js/app.js`

Look at the functions there, they are documented in the comments. Sort of. But
the rest should be self-explanatory.

#### `jsf` folder

Contains all our pages. I moved them here so we can easilly protect them
from an unauthorized direct access while leaving the root unprotected (static content, CSS, JS).

There are currently four pages, general index, login, password change and an administration index.

#### `jsf/index.xhtml`

Take a look at the `<h:messages />` tag and how its ID is used in the form below to
be re-rendered by AJAX. Try entering some nonsense into the input field and performing
an `AJAX` or `POST` request. It doesn't work for the direct `REST` call, of course, it's
a JSF function.

#### `jsf/password.xhtml`

Logged user's password change form. See the `<h:messages />` tag used to display any messages
passed from the [service](#changepasswordservice--changepasswordserviceimpl).

#### `JsfApp3` class

This is the main class for this lecture.

#### `AppConfiguration` class

Configures our URL rewriter including plain XHTML files protection. Maps URIs
to all our existing pages.

#### `SecurityConfiguration` class

Configures Spring Security. Defines access levels to our resources.
The `@EnableGlobalMethodSecurity` annotation configures the use of Spring Security
`@PreAuthorize` annotation in our code.

#### `ClickController` class

Rest controller defining our `click` and `set` endpoints. Uses the `ClickService` service
to do the actual changes.

#### `ClickService` & `ClickServiceImpl`

Implements `click` and `set` methods for both, the JSF actions and the REST endpoints.
Counts clicks and allows the counter to be set to another value.

#### `ChangePasswordService` & `ChangePasswordServiceImpl`

Partial implementation of password change functionality. Shows how JSF validation messages
can be used.

#### `CsrfFormRenderer` class

Custom JSF form renderer automatically adding the necessary CSRF protection token
to each JSF form.

### Update the *set* form on click

On the [index page](http://localhost:8080), there are two main parts,
on the left you have three possibilities to call the `click` service.
Try all three and note the differences.

`POST` causes a regular HTTP request and browser loads the new page and renders it.

`AJAX` calls the server on the background and renders only the count display,
using JSF AJAX facilities.

`REST` uses custom JavaScript functions to call our REST endpoints and then
manually updates the view.

On the right, there is an input field allowing for a direct change of
the click counter. The three buttons work in the same way as desribed above for
the click service.

Now, when you use the **CLICK** side, you can see that the value of
the input on the right side is only changed when using the **POST** variant.

I want you to fix this for both the other ways. It requires only
a slight modification for the JSF AJAX, but some JavaScript coding
is necessary for the direct REST call.

### Finish password change implementation

The password change is only partially implemented. Finish the implementation
for a regular user. It takes an old password and two copies of the new one.
When the old password matches the current one and the new passwords match
each other, you can change the current user's password. Try re-logging
using the new passwords.

Consult the
[documentation](https://docs.spring.io/spring-security/site/docs/current/reference/html5/#servlet-authentication-securitycontextholder)
especially the **Example 54** on how you can access the currently logged user's
information.

### Create an admin page

Use the `admin/index.html` which is linked on the right side of our navigation bar.
Be sure only users with the `ADMIN` role can use all the methods called
here.

1. Display a list of all existing users.
2. Allow for a password change of any of the users, this time without the need
for providing the old password.
3. Optionally, allow for creating new users.

The `UserRepo` interface and `FakeUserRepoImpl` class need to be updated to 
achive the tasks.

Don't use the `ChangePasswordService` and its implementation. Create a separate
`AdminService` instead.

## Resources & Links

* [JSF 2.3 Taglib #1 - prefixes f: and ui:](https://javaserverfaces.github.io/docs/2.3/vdldoc/index.html)
* [JSF 2.3 Taglib #2 - prefix h:](https://javaserverfaces.github.io/docs/2.3/vdldocs/facelets/index.html)
