# MetBook – A Library Reservation System

MetBook is a library reservation system prototype designed for managing loans and reservations. Users can register and log in to browse, search, and filter the catalog of e-books by genre, language, and publication year. The system allows users to track their loan history, mark favourites, and receive notifications when loans are due or when reserved books become available.

>[!NOTE]
>This is a prototype that mocks a real library. Instead of real e-books, loaned books appear as cards in the user’s profile.


## Table of Contents
1. [Features](#features)
2. [Technologies](#technologies)
3. [Documents](#documents)
   - [Use Case Diagram](#use-case-diagram)
   - [ER Diagram](#er-diagram)
   - [Relational Schema](#relational-schema)
   - [Activity Diagram](#activity-diagram)
   - [Class Diagram](#class-diagram)
   - [Sequence Diagram](#sequence-diagram)
   - [Package Diagram](#package-diagram)
5. [Development](#development)
   - [Database](#database)
   - [Frontend](#frontend)
   - [Backend](#backend)
   - [Jenkins and Docker](#jenkins-and-docker)
     - [Jenkins Requirements](#jenkins-requirements)
     - [Jenkins Pipeline](#jenkins-pipeline)
     - [Build and Run Docker Images Locally as a Container](#build-and-run-docker-images-locally-as-a-container)
     - [Run Published Docker Images](#run-published-docker-images)
   - [Localization](#localization)
6. [API endpoints](#api-endpoints)
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
[Documents](https://github.com/Nurha20-24/library-reservation-system/tree/main/Documents) folder contains ER Diagrams and Sprint reports.   

#### Use Case Diagram
<img width="1217" height="1015" alt="use case diagram" src="https://github.com/user-attachments/assets/a0aba006-d85d-4600-a692-db70f193df0c" />

#### ER Diagram
![Image of Database ER Diagram](/Documents/Diagrams/library_database_er_diagram.png)

#### Relational Schema
![Image of Database Relational Schema](/Documents/Diagrams/library_database_relational_schema.png)

#### Activity Diagram
<img width="542" height="675" alt="Activity diagram" src="https://github.com/user-attachments/assets/e884941b-b39c-4334-ba4e-ffd2f811e2ef" />

#### Class Diagram
<img width="1272" height="725" alt="Image" src="https://github.com/user-attachments/assets/3a677cc9-ae0d-4386-b60c-517255445ccf" />

#### Sequence Diagram
<img width="819" height="738" alt="Sequence Diagram - book loan process" src="https://github.com/user-attachments/assets/95141988-bc87-4fc9-be70-bc9a823965c6" />

#### Package Diagram
<img width="874" height="612" alt="Image" src="https://github.com/user-attachments/assets/4a1ed2b3-046b-47c4-a579-55df1e6a400c" />

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

**End-to-end test (Playwright):**
Then run `npm run test:e2e` in frontend folder to run Playwright end-to-end tests.

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

Jenkinsfile requires Docker Desktop to be installed. Configure Jenkins settings (system, tools and credentials) according to the tables below. Use the name in the second column or modify Jenkinsfile to fit yours.

| System setting    | Name            |
| ----------------- | --------------- |
| SonarQube servers | SonarQubeServer |

| Tool              | Name         |
| ----------------- | ------------ |
| SonarQube Scanner | SonarScanner |

| Credential | Name       |
| ---------- | ---------- |
| DockerHub  | docker_hub |
| GitHub     | GitHub-pat |

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
docker compose -f compose.yml start
# or
docker compose -f compose.yml up -d

# Stop
docker compose -f compose.yml stop
# or stop and remove all data
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
docker compose -f compose.production.yml start
# or
docker compose -f compose.production.yml up -d

# Stop
docker compose -f compose.production.yml stop
# or stop and remove all data
docker compose -f compose.production.yml down -v
```

Once the container is running, open `http://localhost:3000` in the browser.

> [!IMPORTANT]
> Remember to pull changes every now and then if you want images to stay up to date.

## Localization

### Localized Languages

The application has been localized into three languages: English, Japanese, and Arabic.

### Frontend

`react-i18next` is used for localization in the application frontend. The original language of the app is English. Translations into other languages were created by translating externalized English strings using AI.

To add a new language:

1. Copy all files from `frontend/public/locales/en/`.
2. Create a new folder for the target language inside `frontend/public/locales/`.
3. Paste the copied files into the new folder.
4. Translate the strings either:
   - manually, one by one, or
   - in bulk using AI.

todo:
add info about what else to change, like navbar menus and footer menu, i18n.js, i18next.config.js

### Database

Database localization is done by translating all necessary attributes, and adding a new column for each containing the localized string. See below for list of all attributes in the database requiring localization.

Add the following fields in these tables when adding a new language:
- `first_name_{lang}` and `last_name_{lang}` in `Author` table
- `genre_{lang}` in `Genre` table
- `language_{lang}` in `Language` table
- `book_title_{lang}` and `description_{lang}` in `Book` table

Replace `{lang}` with locale code, e.g. `fr` for French.

### Backend

Backend localization is handled by `LocalizationUtil` helper class. It simplifies localization by centralizing all database localization methods.

To add a new language:

1. Add all necessary fields to Entity class(es) according to the database structure.
2. Update `LocalizationUtil` in `backend/src/main/java/com/library/backend/util/LocalizationUtil.java` to support the new language code and fields.

---

# API endpoints

Base URL: http://localhost:8081/api

## Genres

Get all genres:  
**endpoint:** '/genre/all/{lang}'

Get one genre:  
**endpoint:** '/genre/{englishName}/{lang}'  
**example:** '/genre/fantasy/en-US' or '/genre/fantasy/ja-JP'

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
**endpoint:** '/loans/user/{user_id}/{lang}'  
**example:** '/loans/user/1/en-US' or '/loans/user/1/ja-JP'

Get returned loans by user:  
**endpoint:** '/loans/user/{userId}/history/{lang}'  
**example:** '/loans/user/1/history/en-US' or '/loans/user/1/history/ja-JP'

Post new loans:  
**endpoint** '/loans/new/{lang}'  
**example:** '/loans/new/en-US'

Return loans:  
**endpoint** '/loans/return{lang}'  
**example:** '/loans/return/ja-JP'

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
**endpoint:** '/language/all/{lang}'  
**example:** '/language/all/en-US' or '/language/all/ja-JP'

Get language by name:  
**endpoint:** '/language/{name}/{lang}'  
**example:** '/language/english/en-US' or '/language/english/ja-JP'

## Authors

Get all authors and their books:  
**endpoint:** '/author/all/{lang}'  
**example:** '/author/all/en-US'  

Get authors and their books by id:  
**endpoint:** '/author/{id}/{lang}'  
**example:** '/author/1/en-US'  

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
