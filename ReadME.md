# Portfolio Website
## Overview

This project demonstrates my personal portfolio website using Spring Boot Application. I used static HTML, CSS,
JS and Bootstrap Template for front-end. I've hosted my website on Railway.app platform integrating PostgresSQL to save the data into the database.
Utilizing Spring Data JPA to persist the data for CRUD operation into the database.Integrated common 
libraries like Project Lombok to reduce the boiler code. 

Used  Spring Thymeleaf to render the static page and UI. Implemented the Email SMTP to send email to the subscriber.
Validated input data using Jakarta-X for validation. Also, used Dev-tools to test/develop/deploy my website efficiently.

## Features

- Visible personal portfolio profile.
- Utilization of free Bootstrap Template.
- Access to my resume with a downloadable PDF.
- Exception handling for HTTP errors.
- Email functionality to send email to subscriber.
- Implemented Privacy, Terms of Use, and Copyright.
- Subscriber details are saved to PostgresSQL.
- Exception Handling to gracefully handle the exception.
- Spring Data JPA for CRUD operations.
- Logging and debugging of API requests and responses using SLF4J.

## Prerequisites

Ensure you have the following installed:
- Java 17 or higher
- Spring Boot v3.5.5
- Maven 3.8+ or Gradle 7+
- Java Mail Sender (Email SMTP)
- Project Lombok plugin
- PostgresSQL container running in Railway
- Project deployed in Railway.app
- An IDE of your choice (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Getting Started

### Clone the Repository

```bash
git clone https://github.com/awale88/ms-thymeleaf-portfolio.git
cd ms-thymeleaf-portfolio
mvn clean install
mvn spring-boot:run
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
### PSQL Configuration

```bash
Local URL: ${jdbc:postgresql://{LOCALHOST}:{PORT}/{YOUR_DATABASE}
Local Username: ${DATABASE_USERNAME}
Local Password: ${DATABASE_PASSWORD}

Prod URL: ${DATABASE_URL}
Prod USERNAME: ${PGUSER}
Prod PASSWORD: ${PGPASSWORD}
```

### Links

- Localhost: 🔗https://localhost:8080
- Project Link: 🔗https://github.com/awale88/ms-thymeleaf-portfolio.git
- Website Link: 🔗https://awale-portfolio.up.railway.app/contact
- Swagger Link: 🔗https://swagger-ui-production.up.railway.app/
- API Doc Link: 🔗https://awale-portfolio.up.railway.app/api-docs

### Contact

- LinkdedIn: 🔗https://www.linkedin.com/in/nayan-awale-933038241
- Author: awale88