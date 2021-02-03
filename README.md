<h2>This project was made using Java 8 and Maven.</h2>
In order to check run these commands below:

```mvn -version```

```java -version```

Served three endpoints described below.

1. Served an endpoint that returns list of IBANS which can be used to test other endpoints: <br />
```http://localhost:8080/api/ibanlist```

2. Served an endpoint for one IBAN validation that returns true or false respectively. <br />
   You need to post you wanted IBAN number.<br />
```http://localhost:8080/api/validate/{iban}```

3. Served an endpoint for IBAN list validation that recognise AB SEB and two other banks IBAN numbers. <br />
    Endpoint consumes JSON format from first endpoint and provides response in JSON as well.<br />
   ```http://localhost:8080/api/validate```


