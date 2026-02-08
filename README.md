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

### Backend

### Database
Create user (use these as they're hardcoded to files)  
User: library_db_user  
Password: password  
  - CREATE USER 'library_db_user'@'localhost' IDENTIFIED BY 'password';  

Create database and add data in the following order (.sql files):
1. database (+ give permissions to user:)
- GRANT ALL PRIVILEGES ON metbook.* TO 'library_db_user'@'localhost';
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
