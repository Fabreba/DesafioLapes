# DesafioLapes - BACKEND
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Postgres](https://img.shields.io/badge/postgres-%23316192.svg?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)


## Table of Contents
- [ERD DIAGRAM](#ERDDIAGRAM)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Authentication](#authentication)
- [Database](#database)

## ERD DIAGRAM
![DIAGRAMA ER](https://github.com/Fabreba/DesafioLapes/assets/92182806/ea5d7042-428a-4153-94d3-743b5230a174)
## Installation

1. Clone the repository:

```bash
git clone https://github.com/Fabreba/DesafioLapes.git
```

2. Install dependencies with Maven

3. Install [PostgresSQL](https://www.postgresql.org/)
4. Change Database URL, User and Password in application.properties to yours

## Usage

1. Start the application with Maven
2. The API will be accessible at http://localhost:8080


## API Endpoints
The API provides the following endpoints:

```markdown
GET /product - Retrieve a list of all products. (all authenticated users)

POST /product - Register a new product (ADMIN access required).

DELETE /product - Delete a product (ADMIN access required).

POST /category - Register a new category (ADMIN access required).

POST /category - Delete a category (ADMIN access required).

POST /auth/login - Login into the App

POST /auth/register - Register a new user into the App
```
3. Swagger
   ```
    http://localhost:8080/swagger-ui/index.html
   ```

## Authentication
The API uses Spring Security for authentication control. The following roles are available:

```
COMMMOM -> Standard user role for logged-in users.
ADMIN -> Admin role for managing partners (registering new partners).
```
To access protected endpoints as an ADMIN user, provide the appropriate authentication credentials in the request header.

## Database
The project utilizes [PostgresSQL](https://www.postgresql.org/) as the database. The necessary database migrations are managed using Flyway.
