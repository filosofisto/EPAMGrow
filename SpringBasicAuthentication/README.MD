# Spring Basic Authentication

This very simple application show how to create a Web application 
using Spring MVC framework and BASIC AUTHENTICATION.

After clone, you can execute unit tests that cover:

- Access http://localhost:8080/ or http://localhost:8080/authenticated with
a valid user or without an user at all. This address doesn't need an 
authenticated user

- Access http://localhost:8080/authenticated that need a valid user and
consequently receiving a 401 status 

- Access http://localhost:8080/authenticated with a valid user and
consequently receiving a 200 status

- Access http://localhost:8080/authenticated that need a valid user, 
but using a wrong (invalid) user, consequently receiving a 401 status

Of course you use a Postman, CURL or yet the browser to check.