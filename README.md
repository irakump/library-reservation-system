# MetBook – A Library Reservation System

MetBook is a library reservation system prototype designed for managing loans and reservations. Users can register and log in to browse, search, and filter the catalog of e-books by genre, language, and publication year. The system allows users to track their loan history, mark favourites, and receive notifications when loans are due or when reserved books become available.

>[!NOTE]
>This is a prototype that mocks a real library. Instead of real e-books, loaned books appear as cards in the user’s profile.


## Table of Contents
1. [Features](#features)
2. [Technologies](#technologies)
3. [Documents](#documents)
4. [Development](#development)
   - [Database](#database)
   - [Frontend](#frontend)
   - [Backend](#backend)
   - [Jenkins and Docker](#jenkins-and-docker)
     - [Jenkins Requirements](#jenkins-requirements)
     - [Jenkins Pipeline](#jenkins-pipeline)
     - [Build and Run Docker Images Locally as a Container](#build-and-run-docker-images-locally-as-a-container)
     - [Run Published Docker Images](#run-published-docker-images)
5. [API endpoints](#api-endpoints)
   - [Genres](#genres)
   - [Users](#users)
   - [Loans](#loans)
   - [Reservations](#reservations)
   - [Languages](#languages)
   - [Authors](#authors)
   - [Books](#books)
   - [Years](#years)
   - [Favorites](#favorites)
   - [Authorization](#authorization)

### Features

- Browse, search and filter the library catalog by genre, language and year.  
- Check availability, reserve and loan books.  
- Receive email notifications for due dates and when a reserved book becomes loaned to the next user.   
- Register, login, favorite books and check history of loaned books. 

### Technologies

Backend: Java, Spring Boot  
Frontend: React, Tailwind CSS  
Database & tools: MariaDB, Docker, Jenkins


### Documents
[Documents](https://github.com/Nurha20-24/library-reservation-system/tree/main/Documents) folder contains ER-diagrams and Sprint reports.   

---

# Development

## Database

Create user (use these as they're hardcoded to files)  
User: library_db_user  
Password: password

- CREATE USER 'library_db_user'@'localhost' IDENTIFIED BY 'password';

Create database:  
Use **database.sql** file.   

> [!IMPORTANT]
> After creating the database, give permissions to user:  
>GRANT ALL PRIVILEGES ON metbook.\* TO 'library_db_user'@'localhost';  
>FLUSH PRIVILEGES;  

Next, add the data in the following order of .sql files:  
1. genre
2. language  
3. author  
4. book  
5. writes  

Optional: add mock user data (only for development):
1. mock-users
2. loan
3. reservation
4. favorite

## Frontend

Install dependencies: run `npm install` in frontend folder   
Start: run `npm run dev`

**Test:**  
Run `npm run test` in frontend folder to run frontend tests.

## Backend

Run `mvn clean install` to build, test and install.   
Start backend: `mvn spring-boot:run`      

**Test:**   
Run test files or test methods separately, or run `mvn test` / `mvn clean install` to execute all tests.

**JaCoCo report:**  
Run `mvn clean test` (compile and unit tests) and `mvn clean verify` (integration tests).   
Open index.html document from target/site/jacoco.   

Deploy the project to a remote repository: `mvn deploy` (when publishing the final product).

## Jenkins and Docker

### Jenkins Requirements

Jenkinsfile requires Docker Desktop to be installed. Configure credentials in Jenkins settings:  
docker_hub (DockerHub), GitHub

Install plugins:

- Docker
- Docker pipeline
- JaCoCo
- Pipeline: Stage View

Ensure that Docker Desktop is installed.

### Jenkins Pipeline

Configure Jenkins pipeline:

1. Make new pipeline. Name it.
2. Navigate to pipeline settings.
3. Select 'Pipeline script from SCM'.
4. Select 'Git' as SCM.
5. Under repository paste `https://github.com/Nurha20-24/library-reservation-system.git` to Repository URL, and select your GitHub credentials as this repository is private.
6. Modify branches to build to be `*/main`.
7. Apply and Save.
8. Select 'Build Now' from left side menu. View live build progress in Stage View. Click on a build to view more info about it.

Add automatic trigger:

1. Navigate to Triggers in pipeline settings.
2. Check 'Poll SCM'
3. Paste `H/5 * * * *` into 'Schedule' text field to poll for repository changes every 5 minutes.

### Build and Run Docker Images Locally as a Container

The following commands use configurations from `compose.yml` file and are run like this:

```shell
# Build
docker compose -f compose.yml build

# Start
docker compose -f compose.yml up -d

# Stop and remove
docker compose -f compose.yml down -v

# View logs
docker compose -f compose.yml logs [image-name]
```

Once container is running, open `http://localhost:3000` in the browser.

### Run Published Docker Images
Images are published in Docker Hub:
Database: https://hub.docker.com/r/sandrajuu/library-reservation-system-database   
Frontend: https://hub.docker.com/r/sandrajuu/library-reservation-system-frontend   
Backend: https://hub.docker.com/r/sandrajuu/library-reservation-system-backend   

File `compose.production.yml` in root directory contains everything needed to get published images up and running together as a container. Navigate to the directory `compose.production.yml` file is in and then run the following through terminal:

```shell
# Pull changes
docker compose -f compose.production.yml pull

# Start
docker compose -f compose.production.yml up -d

# Stop
docker compose -f compose.production.yml down -v
```

Once the container is running, open `http://localhost:3000` in the browser.

> [!IMPORTANT]
> Remember to pull changes every now and then if you want images to stay up to date.

---

# API endpoints

Base URL: http://localhost:8081/api

## Genres

Get all genres:  
**endpoint:** '/genre'

Get one genre:  
**endpoint:** '/genre/{name}'  
**example:** '/genre/fantasy'

## Users

Get all users:  
**endpoint:** '/users'

Get user by id:  
**endpoint:** '/users/{id}'  
**example:** '/users/1'

Get user by email:  
**endpoint:** '/users/email/{email}'  
**example:** '/users/email/fakeuser@example.com'

## Loans

Get all loans:  
**endpoint:** '/loans'

Get loan by id:  
**endpoint:** '/loans/{id}'  
**example:** '/loans/2'

Get active loans by user:  
**endpoint:** '/loans/user/{user_id}'  
**example:** '/loans/user/1'

Get returned loans by user:  
**endpoint:** '/loans/user/{userId}/history'  
**example:** '/loans/user/1/history'

Post new loans:  
**endpoint** '/loans/new'

Return loans:  
**endpoint** '/loans/return'

## Reservations

Get all reservations:  
**endpoint:** '/reservations'

Get reservation by id:  
**endpoint:** '/reservations/{id}'  
**example:** '/reservations/2'

Get reservations by user:  
**endpoint:** '/reservations/user/{user_id}'  
**example:** '/reservations/user/1'

Get reservations by book:  
**endpoint:** '/reservations/book/{isbn}'  
**example:** '/reservations/book/9780241600948'  

Post new reservation:  
**endpoint:** '/reservations/new'  

Cancel reservation:  
**endpoint** '/reservations/cancel'  

Get reservation queue:  
**endpoint** '/book/{isbn}/queue-length'  

## Languages

Get all languages:  
**endpoint:** '/language'

Get language by name:  
**endpoint:** '/language/{name}'  
**example:** '/language/english'

## Authors

Get all authors and their books:  
**endpoint:** '/author'

Get authors and their books by id:  
**endpoint:** '/author/{id}'  
**example:** '/author/1'

## Books

Get book by ISBN:  
**endpoint:** '/book/{isbn}'  
**example:** '/book/9780241600948'

Get books by genre:  
**endpoint:** '/book/genre/{genre}'  
**example:** '/book/genre/fantasy'

Get books by year:  
**endpoint:** '/book/year/{year}'  
**example:** '/book/year/2024'

Get books by language:    
**endpoint:** '/book/language/{language}'  
**example:** '/book/language/english'

Get books by filters (any combination, max one language and one genre, multiple years allowed):  
**endpoint:** '/book/filter?genre={genre}&years={year}&language={language}'  
**example:** '/book/filter?genre=fantasy&years=2024&language=english'

## Years

Get years used by books:  
**endpoint:** '/book/years'  
**example:** '/book/years'

## Favorites

Get favorites by userId:   
**endpoint:** '/users/{userId}/favorites'

Post favorites by isbn:   
**endpoint:** '/users/{userId}/favorites/{isbn}'

Remove favorites by isbn:   
**endpoint:** '/users/{userId}/favorites/{isbn}'

## Authorization

Register endpoint (register form sends data there and backend uses it):   
**endpoint:** '/auth/register'

Login:   
**endpoint:** '/auth/login'
