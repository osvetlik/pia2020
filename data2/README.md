# Relational database access

## Terms and theory

### JpaRepository

[Last week](../data/) you were introduced to several possibilities of accessing
a relational database.

From now on we will mostly focus on using Spring's data repositories. Take
a look at the hierarchy of your repository interface. It inherits from a rather
long chain of interfaces starting with `JpaRepository`. I recommend you to 
take a look at all those repositories and what methods they provide you with.

## Tasks

Today we are going to continue with the work started last week. Use the [same project](../data/)
there is no need to start a new one.

### Ensure an admin user existence

On [application startup](https://www.baeldung.com/running-setup-logic-on-startup-in-spring)
ensure that at least one live user with the role `ADMIN` exists in the database.

Use a default password if you need to create a new user or generate
a random one and show it somewhere in the logs.

### Create an administration page

In the `cz.zcu.fav.pia.jsf.admin` you will find a set of classes you will need
to create an administration page.

1. Only users with the role `ADMIN` (remember that you have to prefix roles with `ROLE`
for Spring to recognize them) can administer other users.
2. Other users cannot access this page.
3. Admin can set user's password or roles.
4. Admin cannot remove their own admin role.

### Create a registration page

Registration asks a user for username and password and creates a new user in the
database with only the `USER` role. Such user can immediately log into the application.

### Protect the registration page agains XSS

As the registration provides a publicly available page with user input, you have
to protect the application against [XSS](https://en.wikipedia.org/wiki/Cross-site_scripting). 
You can do it either on the registration side or at the administration side (to prevent
display of dangerous content). I recommend doing both.
