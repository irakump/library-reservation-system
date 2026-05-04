# MetBook – A Library Reservation System

MetBook is a library reservation system prototype developed for Metropolia University of Applied Sciences. The application solves the problem of managing e-book loans and reservations by providing a digital platform where students and teachers can browse, search, borrow, and reserve e-books without physical library cards. Books are returned automatically on the due date, and users receive email notifications about due dates and available reservations. The system supports three languages: English, Japanese, and Arabic.   

MetBook was developed over 8 sprints (16 weeks) across two courses: Software Engineering Project 1 (Sprints 1-4) and Software Engineering Project 2 (Sprints 5-8).  

**Technologies:** Java Spring Boot (backend), React with Tailwind CSS (frontend), MariaDB (database), Docker, Jenkins, SonarQube


>[!NOTE]
>This is a prototype that mocks a real library. Instead of real e-books, loaned books appear as cards in the user’s profile.


## Table of Contents
1. [Product Vision](#product-vision)
    - [Features](#features)
2. [Sprint Structure](#sprint-structure)
    - [Sprint Overview](#sprint-overview)
3. [Sprint Documentation](#sprint-documentation)
   - [Sprint 1 - Project Planning](#sprint-1---project-planning)
   - [Sprint 2 - Database Implementation](#sprint-2---database-implementation)
   - [Sprint 7 - Quality Assurance](#sprint-7---quality-assurance)
   - [Sprint 8 - Finalizing](#sprint-8---finalizing)
4. [Documents](#documents)
   - [ER Diagram](#er-diagram)
   - [Activity Diagram](#activity-diagram)
5. [How to run the project](#how-to-run-the-project)
   - [Database](#database)
   - [Frontend](#frontend)
   - [Backend](#backend)
   - [Jenkins and Docker](#jenkins-and-docker)
     - [Jenkins Requirements](#jenkins-requirements)
     - [Jenkins Pipeline](#jenkins-pipeline)
     - [Build and Run Docker Images Locally as a Container](#build-and-run-docker-images-locally-as-a-container)
     - [Run Published Docker Images](#run-published-docker-images)
   - [Localization](#localization)
6. [Repository Structure](#repository-structure)
7. [API endpoints](#api-endpoints)

## Product Vision
MetBook is designed to provide Metropolia's students and teachers with a simple, accessible way to reserve and borrow books digitally.

### Features
- Browse, search, and filter the library catalog by genre, language, and year.  
- Check availability, reserve, and loan books.  
- Receive email notifications for due dates and when a reserved book becomes loaned to the next user.   
- Register, login, favorite books, and check history of loaned books.
- Full localization in English, Japanese, and Arabic

**Definition of Success:**
The project is complete when all core features are implemented and tested, the application is localized in three languages, and the application builds and deploys successfully through the Jenkins pipeline.

## Sprint Structure
The project was developed using Agile methodology with Scrum framework. Each sprint lasted 2 weeks. Trello was used for backlog management and task tracking. The lecturer served as Product Owner.

### Sprint Overview

| Sprint   | Duration      | Goals                                                                                          | Course |
|----------|---------------|------------------------------------------------------------------------------------------------|--------|
| Sprint 1 | 15.01 - 29.01 | Project planning, project vision, Trello and GitHub setup, Figma prototype                     | SEP1   |
| Sprint 2 | 29.01 - 11.02 | Database implementation, UI development, Maven setup, unit testing, code coverage report       | SEP1   |
| Sprint 3 | 12.02 - 05.03 | Core feature implementation, Jenkins CI setup, JaCoCo report, initial Docker image             | SEP1   |
| Sprint 4 | 05.03 - 17.03 | Finalize functionality, UI, and responsive design, Docker containerization, final presentation | SEP1   |
| Sprint 5 | 17.03 - 30.03 | UI localization with react-i18next (English, Japanese, Arabic)                                 | SEP2   |
| Sprint 6 | 01.04 - 14.04 | Database localization, SonarQube, code cleanup, acceptance test planning                       | SEP2   |
| Sprint 7 | 14.04 - 28.04 | Functional and non-functional testing, SonarQube                                               | SEP2   |
| Sprint 8 | 28.04 - 05.05 | Documentation, final presentation                                                              | SEP2   |

# Sprint Documentation

## Sprint 1 - Project Planning
The main goals for Sprint 1 were to produce the [project plan](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/project_reports/project_plan.pdf) and [vision](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/project_reports/project_vision.pdf) documents, and to create the product backlog in [Trello](https://trello.com/w/sep1_melkeineturivintytot/).  
[Planning report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_1_planning_report.pdf)  
[Review report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_1_review_report_group2.pdf)

## Sprint 2 - Database implementation  
During Sprint 2, the database was created and the implementation of the user interface was initiated. In addition, Maven was integrated into the project, and code coverage reporting using JaCoCo was set up for unit testing. [Use Case Diagram](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/diagrams/use-case-diagram-sprint1.pdf) and [ER Diagram](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/diagrams/library_database_er_diagram.png) were created.  
[Planning report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_2_planning_report.pdf)  
[Review report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_2_review_report.pdf)

## Sprint 3 - CI/CD Integration, Feature Extension, and Basic Docker Image

Sprint 3 focused extending the fuctional prototype by implementing all core features. The unit tests were expanded and core functionality was validated through testing. Jenkins was integrated for CI/CD, with the pipeline configured to include automated unit tests and code coverage reporting using JaCoCo. Initial Docker images were created and tested locally.  
[Planning report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_3_planning_report.pdf)  
[Review report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/Sprint_3_Review_Report.pdf)

## Sprint 4 - Finalizing, Containerizing, and Sharing the Prototype

During Sprint 4, the product functionality was fully integrated and features tested. Docker images were created for all application components (frontend, backend, and database) and successfully pushed to Docker Hub for public access. The public images were containerized and the functionality tested.  
[Planning report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_4_planning_report.pdf)  
[Review report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_4_review_report.pdf)  

## Sprint 5 - UI Localization
The objective of Sprint 5 was to prepare the application for full multilingual support for Arabic, Japanese and English languages. The localization of the user interface was implemented using [i18next](https://www.npmjs.com/package/i18next) framework by separating the translatable content from core entities.
[Planning report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_5_planning_report.pdf)
[Review report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_5_review_report.pdf)

## Sprint 6 - Database Localization
The goal of the Sprint 6 was to extend localization into the database layer and improve overall code quality through static code analysis and refactoring. Database localization was implemented using field localization for text-based content (e.g., titles, descriptions). The regional formatting of dates and numbers is handled in the frontend.
[Planning report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_6%20_planning_report.pdf)
[Review report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_6_review_report.pdf)

## Sprint 7 - Quality Assurance
Sprint 7 focused on functional and non-functional testing. A [test plan](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/project_reports/test-plan-report.pdf) was created covering objective, resources, test environment and test tasks. Backend unit test target was set at 80%, and the final coverage reached 81.3%. SonarQube analysis achieved A grades across all categories. Non-functional testing included [user acceptance testing](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/project_reports/user-acceptance-test-report.pdf), [heuristic evaluation](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/project_reports/heuristic-evaluation-report.pdf), [accessibility testing](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/project_reports/Accessibility-tests-bug-report.pdf) and end-to-end testing with Playwright.
[Planning report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_7_planning_report.pdf)  
[Review report](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/sprint_reports/sprint_7_review_report.pdf)

## Sprint 8 - Finalizing
The project was finalized in Sprint 8, with a focus on updating the documentation. The README was refactored to include Sprint documentation, and relevant documents were added to the documents folder. Additionally, the [API documentation](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/project_reports/api_documentation.pdf) was moved from the README to the documents folder.  
[Planning report](https://github.com/Nurha20-24/library-reservation-system/tree/main/documents/sprint_reports)  
[Review report](https://github.com/Nurha20-24/library-reservation-system/tree/main/documents/sprint_reports)

### Documents
[Documents](https://github.com/Nurha20-24/library-reservation-system/tree/main/Documents) folder contains Diagrams, Project reports and Sprint reports.   
[Diagrams](https://github.com/Nurha20-24/library-reservation-system/tree/main/Documents/Diagrams) folder contains the following diagrams:
- [Activity diagram](https://github.com/Nurha20-24/library-reservation-system/blob/main/Documents/Diagrams/activity%20_diagram.pdf)
- [Class diagram](https://github.com/Nurha20-24/library-reservation-system/blob/main/Documents/Diagrams/class_diagram.pdf)
- [Relational Schema](https://github.com/Nurha20-24/library-reservation-system/blob/main/Documents/Diagrams/library_database_relational_schema.png)
- [Package diagram](http://github.com/Nurha20-24/library-reservation-system/blob/main/Documents/Diagrams/package_diagram.pdf)
- [Sequence diagram](https://github.com/Nurha20-24/library-reservation-system/blob/main/Documents/Diagrams/sequence_diagram.pdf)
- [Use case diagram](https://github.com/Nurha20-24/library-reservation-system/blob/main/Documents/Diagrams/use-case-diagram-sprint1.pdf)
- ER and activity diagram, which are shown below

#### ER Diagram
![Image of Database ER Diagram](/documents/diagrams/library_database_er_diagram.png)

#### Activity Diagram
<img width="542" height="675" alt="Activity diagram" src="https://github.com/user-attachments/assets/e884941b-b39c-4334-ba4e-ffd2f811e2ef" />

---

# How to run the project

## Database

Create user (use these as they're hardcoded to files)  
User: library_db_user  
Password: password

- CREATE USER 'library_db_user'@'localhost' IDENTIFIED BY 'password';

Create the database by running the `database.sql` script.  


> [!IMPORTANT]
> After creating the database, give permissions to user:  
>GRANT ALL PRIVILEGES ON metbook.\* TO 'library_db_user'@'localhost';  
>FLUSH PRIVILEGES;  

Next, run the following SQL files in this order:  
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

## Backend

Run `mvn clean install` to build, test and install.   
Start backend: `mvn spring-boot:run`      

**Test:**   
Run test files or test methods separately, or run `mvn test` / `mvn clean install` to execute all tests.

**JaCoCo report:**  
Run `mvn clean test` (compile and unit tests) and `mvn clean verify` (integration tests).   
Open index.html document from target/site/jacoco.   

Deploy the project to a remote repository: `mvn deploy` (when publishing the final product).

### Environment Variables

Copy `.env.sample` file located in root folder and create environment variables file `.env`. This includes secret variables. Modify them as needed.

## Jenkins and Docker

### Jenkins Requirements

Jenkinsfile requires Docker Desktop to be installed. Configure Jenkins settings (system, tools, and credentials) according to the tables below. Use the name in the second column or modify Jenkinsfile to fit yours.

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
[Database](https://hub.docker.com/r/sandrajuu/library-reservation-system-database)   
[Frontend](https://hub.docker.com/r/sandrajuu/library-reservation-system-frontend)   
[Backend](https://hub.docker.com/r/sandrajuu/library-reservation-system-backend)   

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

> [!IMPORTANT]
> Make sure you have set up environment variables as `.env` file in the folder Docker images are run from. Instructions [here](#environment-variables).

## Localization

### Localized Languages

The application has been localized into three languages: English, Japanese, and Arabic.

### Frontend

`react-i18next` is used for localization in the application frontend. The original language of the app is English. Translations into other languages were created by translating externalized English strings using AI.

To add a new localization:

1. Copy all files from `frontend/public/locales/en/`.
2. Create a new folder for the target language inside `frontend/public/locales/`.
3. Paste the copied files into the new folder.
4. Translate the strings either:
   - manually, one by one, or
   - in bulk using AI.
5. Add the new locale to:
   - `defineConfig.locales` in `frontend/i18next.config.js`,
   - `i18n.init.preload` in `frontend/src/i18n.js`,
   - `locales` in `frontend/src/utils/locales.js`. Also add native translation of language name here.

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

## Testing

**Frontend:**
Run `npm run test` in frontend folder

**End-to-end test (Playwright):**

Setup before running tests:
1. Create new local user (or use existing one)
2. Add `TEST_EMAIL` and `TEST_PASSWORD` to .env file (check .env.sample)
3. Make sure both backend and frontend are running.

If needed, install Playwright browsers locally: `npx playwright install`

Run `npm run test:e2e` in frontend folder to run Playwright end-to-end tests.

**Backend:**
Run `mvn test` / `mvn clean install` or alternatively run the test files or methods separately.

**JaCoCo report:**
Run `mvn clean test` and `mvn clean verify` to execute and receive a JaCoCo-report from the tests.
To view report go to target/site/jacoco and open index.html.

---

# Repository Structure

The repository contains three main folders:

- **/backend** – Backend code, tests, and database scripts
- **/documents** – Project documentation, diagrams, reports, and sprint reports
- **/frontend** – Frontend code and related tests

The project root also includes a Jenkinsfile for CI/CD pipeline configuration, Docker compose files for containerization, and a `.env.sample` file for environment variable setup example.

---

# API endpoints

The API endpoints are described in [API documentation](https://github.com/Nurha20-24/library-reservation-system/blob/main/documents/project_reports/api_documentation.pdf).

## Course Information
- Course: Software Engineering Project
- Semester: Spring 2026

- ## Team Members
- Anni Alanen - Full Stack Developer, Test Engineer, UI/UX designer, System Designer, Scrum Master
- Ira Kumpula - Full Stack Developer, Test Engineer, UI/UX designer, System Designer, Scrum Master
- Nur Hana - Full Stack Developer, Test Engineer, UI/UX designer, System Designer, Scrum Master
- Sandra Juursoo - Full Stack Developer, Test Engineer, UI/UX designer, System Designer, CI/CD Engineer, Scrum Master
