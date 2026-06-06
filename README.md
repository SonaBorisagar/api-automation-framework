# API Automation Framework

## Run Mock Tests

mvn clean test -Denv=mock

## Run Against Real API

mvn clean test -Denv=qa

## WireMock Port

8089

## Stubbed APIs

GET /users/1
POST /users
GET /products