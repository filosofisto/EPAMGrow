# Spring Form Authentication

This very simple application showing how to create a Web application 
using Spring MVC framework and FORM AUTHENTICATION.

After clone, you can execute unit tests that cover:

- Unprotected request without user should return status 200

- Unprotected request with user should receive status 200 

- Protected request without user should receive status 
302 and header location (for redirect)

- Protected request with valid user should receive status 200

- protectedWithWrongUserShouldReceiveStatus403 Protected request with wrong user should receive status 403

Of course you use a Postman, CURL or yet the browser to check.