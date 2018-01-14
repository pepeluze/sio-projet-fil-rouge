# sio-projet-fil-rouge

## Description
The sio-projet-fil-rouge project is a sudy case for the MS SIO students, it consists of a RESTfull API to manage movie library.

The choice of implementation is :

    * Maven
    * Kotlin
    * Spring Boot
    * JPA2 with H2 database
    * Oauth2
    * io.springfox Swagger annotation API

This project is a POC of RESTfull API project with Kotlin, Spring Boot, and a Oauth2 authentication via social network
(I choose authentication via github).

## Download the project
`$ git clone https://github.com/pepeluze/sio-projet-fil-rouge`

## Running the project

`$ cd <path-to-the-project>`
then
`$ mvn spring-boot:run`

or
`$ java -jar target/myproject-0.0.1-SNAPSHOT.jar`

## Testing the project
The easy way to test the project is to use the Swagger UI interface at http://localhost:8080/swagger-ui.html

The first connection to the Swagger interface redirect you to github authentication, then you can use the fully
documented, and easy to use Swagger interface, to test the project.

Enjoy!!




