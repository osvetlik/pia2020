![Inventi](../img/inventi.png)

# REST API Design

We are going to keep this as simple as possible. There is no need to
copy all the on-line resources here so you'll find mostly links here.

## Terms and theory

[REST API](https://restfulapi.net/) evolved from the HTTP protocol
and it led to some enhancements to the protocol itself, mainly
the introduction of new methods.

The important thing to remember about REST API is that:

> REST is an Architecture, Not a Standard

It means you can find many different ways to use it while still
being called REST API. Nevertheless, there are a few guidelines and best
practices that should make your APIs readable, understandable and maintainable.

Please, read [this short summary](https://stoplight.io/blog/rest-api-standards-do-they-even-exist/)
on REST API to understand the basics, namely:

* HTTP methods and their usage
* HTTP status codes and their meaning
* Resource naming aka URI assembly

### JSON, XML, YAML or what?

REST API does not force you to use any specific data format, but you're most likely
to see it in combination with JSON. The important thing to remember is
that the actual format is also a part of the contract, your clients and server must
agree on at least one to be able to work together. The negotiation is usually
performed using the `Accept` and `Content-Type` HTTP headers.

### Swagger & OpenAPI

There are many tools whose goal is to assist developers in the API creation, documentation
and implementation. We are covering 
[OpenAPI](https://swagger.io/specification/) (evolved from Swagger) here. There are  
multiple ways to use these tools, but for the scope of this lecture, we are going to 
use them to maintain a single YAML file with our API definition and generate all
necessary code for both, client and server sides.

There are UI tools for editing the definition file, you can use them if you want to, 
but I am going to refer to the plain YAML file.

## Tasks

### Explore the sample project

The goal today is mainly to try the API design process and code generation,
the project is not ready to run the code, but feel free to fix this if you need to.

#### [pia-openapi.yaml](openapi/pia-openapi.yaml)

Our API definition. The file contains the definition of a few endpoints with
comments to help you understand the meaning of all the necessary parts. You should 
start by close examination of the file. 

#### [pom.xml](pom.xml)

In the `build/plugins` section there is the `openapi-generator-maven-plugin`
configured to execute twice in the `generate-sources` phase. One instance generates
the Java server code (using Spring) and the second one the JavaScript client implementation.

When you import the project into your IDE, you should see the generated code in the 
`target/generated-sources/openapi/` folder. If not, run `mvn generate-sources` in the 
project folder (or using your IDE's Maven interface).

#### Generated code

Take a look at the Java code in the `cz.zcu.fav.pia` package and the JavaScript code 
in the `js` folder. The JavaScript generator also creates multiple `.md` files directly
in the `target/generated-sources/openapi` folder, you can take a look at them as they 
contain the documentation rendered from the API specification.

#### Implementing the server

`UserApiController` class contains an empty implementation of the generated `UserApi`
interface just to show you how the actual implementation would be done.

### Add missing CRUD methods

There are currently only a few endpoints defined. Try to add the missing endpoints
to cover all the CRUD operations on users. Observe the changes in the generated code
as you add them.

The missing endpoints are:

* creating a new user
* deleting an existing user
* `/api/v1/me/password` for changing the password of the currently authenticated user
* `/api/v1/me/notes` - add the whole CRUD support for multiple notes for the logged 
user
* `/api/v1/admin/{userId}/notes` - the same for the administrator to manage notes for 
other users
* any other case you might think of

Remember to choose the proper HTTP methods, HTTP status codes and response data.

After finishing this task, send me your API definition if you want to have it checked.
