# API-Testing-RestAssured
This is the project for an example of using RestAssured and TestNG to automate API tests. This example is using http://deckofcardsapi.com/ as AUT.

## Prerequisite

1. Install JDK 8
2. Install maven 3

## Run Test
Run below command line:
```
 mvn test
```

Run with a specific environment:
```
 mvn -Denv=qa test
```

## Review result
After tests are run successfully. We can review the test result with HTML UI by file `./reports` and logs `./logs`