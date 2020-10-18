# Semester Project PIA 2020

You will be implementing an on-line version of the
'[Gomoku](https://en.wikipedia.org/wiki/Gomoku)' game,
also known as *Five in a row*.

## Scope

### Mandatory parts

* login screen
* registration screen
* lobby with the list of all logged-in users
* friends list with the possibility to add/remove users and showing the status
(on-line/off-line) of all user's friends
* possibility to ask any on-line user to play a game
* gameplay
* in-game chat
* log of all game results

### Bonus parts

* OAuth2 authentication using Facebook, Google or any other OAuth2 IDM
* tournaments - set-up (define number of players), let players join, play, show
results to participants
  - list of open tournaments must be added to the lobby unless announcements 
	are implemented
* announcements - part of lobby - announce open tournaments, game and tournament results
* administration - user administration, password reset, tournament setup only for privileged
users
* password reset using a security question
* password reset using an e-mail (reset link)
* feel free to come up with any other suggestion
* mobile client

## Technology

You may use any stack you like as long as the following technologies
are present.

### Mandatory

* HTML, CSS
* responsive design - using of pre-defined templates is forbidden, but you
may use frameworks such as [Bootstrap](https://getbootstrap.com/),
[Foundation](https://get.foundation/) or other
* IoC/DI
* ORM
* sdandalone database (MySQL, PostgreSQL or anything else you are able to
start in a Docker container)
* REST
* web sockets
* docker

### Bonus

* HTML canvas for the gameplay (otherwise it can be implemented using an HTML table)
* [OpenAPI](https://swagger.io/specification/), Swagger, [RAML](https://raml.org/)
or any other API modeling/specification language
* docker compose
* Angular, React, any other frontend technology

## Submission

The project submission must consist of:

1. the source code
2. a Dockerfile to build the project
3. a Dockerfile to run the project (can be one Dockerfile for both)
4. a documentation describing how to run the whole project including a database
or any other containers necessary
5. a short document (A4, can be a single markdown file) on the solution

You can either provide an archive containing all the parts (upload to the courseware)
or (preferably) a link to a GIT repository of your choice (GitHub, BitBucket, ...).