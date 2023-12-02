# MyFit Application

## Overview

MyFit is an application for managing workout programs, exercises, and user information. The application is built using the Spring Boot framework with Spring Data JPA, Thymeleaf for templating, and PostgreSQL as the database.

## Configuration

### `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/myfit
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.properties.hibernate.format_sql=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.cache=false

spring.mvc.hiddenmethod.filter.enabled=true

logging.level.org.hibernate.SQL=debug

# JWT Secret
jwt.secret=9A8E15241DDD69BC4EDAD3BEFF89B

# JWT Token Lifetime
jwt.lifeTime=PT1H

##Admin user
admin.user.name=AdminUser
admin.user.email=admin@example.com
admin.user.password=adminPassword
admin.user.roles=ADMIN

spring.thymeleaf.security-authorization-enabled=true

##Project Structure
The project adheres to the MVC (Model-View-Controller) architecture.

##Project Status
The project is currently in progress, with the following milestones:

Done: Boot Spring application using MVC architecture, Thymeleaf is used to display information. Using DTO pattern. Make Access Tokens, JWT requst filter and security configuration.
In Progress:  Work with errors. Catch exceptions. Create unit tests. Wrap in container(docker) and customize the deploy.
Future: Add API architecture to communicate with the application. Follow up on Kotlin and make an Android app.

##Getting Started
Clone the Repository:

Open a terminal or command prompt.
Run the following command to clone the repository:
bash
Copy code
git clone <repository-url>
Replace <repository-url> with the actual URL of the MyFit repository.
Configure PostgreSQL Database:


Ensure you have Java and Maven installed on your machine.
Navigate to the root directory of the cloned repository in the terminal.
Run the following command to build the application:
bash
mvn clean install

Once the build is successful, run the following command to start the Spring Boot application:
bash
java -jar target/myfit-<version>.jar
Replace <version> with the actual version of the application.
Access the Application:

Open a web browser.
Navigate to http://localhost:8080 (or the configured port) to access the MyFit application.