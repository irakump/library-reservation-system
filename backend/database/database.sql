DROP DATABASE IF EXISTS metbook;
CREATE DATABASE metbook;
USE metbook;

CREATE TABLE users(
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(100) UNIQUE NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    role ENUM('user', 'admin') NOT NULL DEFAULT 'user'
);

CREATE TABLE genre(
    genre VARCHAR(50) NOT NULL,
    PRIMARY KEY (genre)
);

CREATE TABLE language(
    language VARCHAR(100) NOT NULL,
    PRIMARY KEY (language)
);

CREATE TABLE book(
    isbn VARCHAR(20) PRIMARY KEY,
    book_title VARCHAR(100) NOT NULL,
    publishing_year INT NOT NULL,
    image_name VARCHAR(100),
    description VARCHAR(5000) NOT NULL,
    genre VARCHAR(50) NOT NULL,
    language VARCHAR(100) NOT NULL,
    available BOOLEAN NOT NULL DEFAULT TRUE,
    FOREIGN KEY (genre) REFERENCES genre(genre),
    FOREIGN KEY (language) REFERENCES language(language)
);

CREATE TABLE author(
    author_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL
);

CREATE TABLE loan(
    loan_id INT AUTO_INCREMENT PRIMARY KEY,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    due_date DATETIME NOT NULL,
    return_date DATETIME NULL,
    user_id INT NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (isbn) REFERENCES book(isbn)
);

CREATE TABLE reservation(
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status ENUM('active', 'not_active') NOT NULL DEFAULT 'active',
    user_id INT NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (isbn) REFERENCES book(isbn)
);

CREATE TABLE writes(
    author_id INT NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    PRIMARY KEY (author_id, isbn),
    FOREIGN KEY (author_id) REFERENCES author(author_id),
    FOREIGN KEY (isbn) REFERENCES book(isbn)
);

CREATE TABLE favorite(
    user_id INT NOT NULL,
    isbn VARCHAR(20) NOT NULL,
    PRIMARY KEY (user_id, isbn),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (isbn) REFERENCES book(isbn)
);
