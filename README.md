# Eve - the event app

Backend for diploma project, currently in active development.

# Ready features:

Email activation after registration;

Brute force prevention;

Universal exception format, made to avoid a potential leak of 
used technologies stack info and catch all the exceptions.

# //TODO

Everything below is yet to be added.

## Security:

XSS

ReDos

CSRF

DDOS

Injection

Add Oauth2

Add HTTPS and PFS

Remove hardcoded credentials and store them in Vault

PEN test and Snyk scan
 
Add unusual login location prevention

## Functional:

Remove logic from controllers

A scheduled purge of unused tokens

Search

Collect necessary data

Payment systems

Recommendation system (Slope One for now)

Monetization


## Technologies

Move to Gradle

Provide different layers tests

Add database migration tool

Audit every action in the app

Add in-memory db in such way that the most probable to be used data will be stored there,
and then moved to hard db when it loses relevance

Swagger for documentation


