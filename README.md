This API REST has been developed following the following requirements:
[Enunciado](./README_Enunciado.md)

# Prices API REST

1. [Who has developed it?](#1-who-has-developed-it)
2. [API First](#2-api-first)
3. [Running the application](#3-running-the-application)
4. [Testing the application](#4-testing-the-application)
5. [API Documentation](#5-api-documentation)
6. [H2 In memory database](#6-h2-in-memory-database)

## 1. Who has developed it?

This application has been fully designed and developed by Victor Martin Gil</br>
Check more details about me in my website:

### https://www.victormartingil.com

## 2. API First

This API REST has been designed and developed using the API First methodology. </br>
The API has been designed and documented before any code has been written, as you can check in the
[api.yaml](/src/main/resources/api.yml) file.

## 3. Running the application

Please note that to run this application you will need at least Java 17 version installed in your machine.</br>
_To check which java version you have installed in your machine, type the following command in your terminal or command
prompt_:

```bash
java -version
```

### 3.2 - Running the application with the .jar packaged application

You can find the prices-service.jar file ready to be executed in the following path: </br>
[/package](/package)

Open your terminal in the path where you have downloaded it in your computer and run the application with the following
command:

```bash
java -jar prices-service.jar
```

The application is running by default in port 8080. If you need to change the port, use this command:

```bash
java -jar prices-service.jar --server.port=8085
```

### 3.3 - Running the application with your IDE (IntelliJ recommended)

#### 3.3.1 - Generate API files with maven plugin (mvn clean install)

1. Clone this repository into your local machine.
2. Use _mvn clean install_ to generate sources specified in the following file: [api.yaml](/src/main/resources/api.yml)

   ```bash
   mvn clean install
   ```

_This mavend command also will generate the required Mappers with Mapstruct_

No you can run the application with your IDE, which will be able by default in the port 8080.

## 4. Testing the application

### 4.1 - End-to-end testing

You need to run the application before testing it. Check [3.Running the application](#3-running-the-application).</br>
Be sure the application is running in port 8080, otherwise, update the port in the query in Postman Collection.</br>

There are 5 end-to-end tests readies. You can use one of the following options to run them:

* You can run them with Cucumber using this file: [get_price.feature](/src/test/resources/features/get_price.feature)
* Also, you can download and import the following [Postman Collection](/postman) and run all the folder "
  test".

### 4.2 - Manual testing

You can use the same [Postman Collection](/postman-collection) and you will find a query ready to be executed in the
folder "api".

## 5. API Documentation

The API has been documented through this [api.yaml](/src/main/resources/api.yml) file.</br>
To see this documentation in an appropriate User Interface, while your application is running, you can go to
the [Swagger UI](http://localhost:8080/swagger-ui/index.html).</br>
Also, you can use online [Swagger Editor](https://editor.swagger.io) and paste the content of
the [api.yaml](/src/main/resources/api.yml) file there.</br>

## 6. H2 In memory database

This application works with H2 in-memory database.</br>
Please note two different databases have been defined, one for test, other for dev environments.

### 6.1 - H2 Console

To access h2 console, while the application is running, you can go to the following link:
[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

To log into the database, set the following parameters and credentials to access _dev environment_ database (you can
also see them
in [application.yml](src/main/resources/application.yml) file):

* Driver Class: org.h2.Driver
* JDBC URL: jdbc:h2:mem:prices-db
* User Name: sa
* Password: sa