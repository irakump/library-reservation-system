# Library Book Reservation System

### Description:

A system where students and teachers can reserve books from the campus library, check availability, and get notifications when books are due.

### Features:

Browse, search and filter the library catalog.  
Check availability, reserve and loan e-books.  
Get reminders for due dates.

### Technologies:

Backend: Java, Spring Boot  
Frontend: React, Tailwind CSS  
MariaDB, Docker

# Development

### Frontend

npm install  
npm run dev

**Test:**  
Run `npm run test` in frontend folder

### Backend

`mvn clean install` (build + test + install)  
`mvn spring-boot:run` (start backend)

**Test:**
Run test files or test methods separately

JaCoCo report:  
Run `mvn clean test` (compile and unit tests) and `mvn clean verify` (integration tests). Open index.html document from target/site/jacoco.

Deploy the project: `mvn deploy` (when publishing the final product).

### Database

Create user (use these as they're hardcoded to files)  
User: library_db_user  
Password: password

- CREATE USER 'library_db_user'@'localhost' IDENTIFIED BY 'password';

Create database and add data in the following order (.sql files):

1. database (+ give permissions to user:)

- GRANT ALL PRIVILEGES ON metbook.\* TO 'library_db_user'@'localhost';
- FLUSH PRIVILEGES;

2. genre
3. language
4. author
5. book
6. writes

Add mock user data (only for development):

1. mock-users
2. loan
3. reservation
4. favorite

### Spring Boot + database + database to frontend

1. Run "mvn spring-boot:run" or "mvn clean spring-boot:run".
2. Optionally run BackendApplication class in backend/src/main/java/com/library/backend.

One of the above needs to be running for frontend to get the data.
Open console in http://localhost:5173, it should have printed genre list.

#### How to add more database endpoints?

1. Navigate to backend/src/main/java/com/library/backend.
2. Look at examples from Genre.java, GenreController.java, and GenreRepository.java.

## API endpoints

Base URL: http://localhost:8081/api

#### Genres

Get all genres:  
**endpoint:** '/genre'

Get one genre:  
**endpoint:** '/genre/{name}'  
**example:** '/genre/fantasy'

#### Users

Get all users:  
**endpoint:** '/users'

Get user by id:  
**endpoint:** '/users/{id}'  
**example:** '/users/1'

Get user by email:  
**endpoint:** '/users/email/{email}'  
**example:** '/users/email/hello@fakemail.com'

#### Loans

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

#### Reservations

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

#### Languages

Get all languages:  
**endpoint:** '/language'

Get language by name:  
**endpoint:** '/language/{name}'  
**example:** '/language/english'

#### Authors

Get all authors and their books:  
**endpoint:** '/author'

Get authors and their books by id:  
**endpoint:** '/author/{id}'  
**example:** '/author/1'

#### Books

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

#### Years

Get years used by books:  
**endpoint:** '/book/years'  
**example:** '/book/years'

#### Favorites

Get favorites by userId:
**endpoint** '/users/{userId}/favorites'

Post favorites by isbn:
**endpoint** '/users/{userId}/favorites/{isbn}'

Remove favorites by isbn:
**endpoint** '/users/{userId}/favorites/{isbn}'

#### Authorization (register, login)

Register endpoint (register form sends data there and backend uses it):
**endpoint:** '/auth/register'

Use this endpoint with login:
**endpoint:** '/auth/login'

### Jenkins and Docker

#### Jenkins Requirements

Configure credentials in Jenkins settings:  
docker_hub (DockerHub), GitHub

Install plugins:

- Docker
- Docker pipeline
- JaCoCo
- Pipeline: Stage View

Ensure that Docker Desktop is installed.

#### Jenkins Pipeline

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

#### Run Docker Locally

The following commands use configurations from `compose.yml` file and are run like this:

```shell
# Build
docker compose build

# Start
docker compose up -d

# Stop and remove
docker compose down -v

# View logs
docker compose logs [image-name]
```

Once container is running, open `http://localhost:3000` in the browser.
