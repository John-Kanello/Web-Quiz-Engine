# Quiz Web Application

## Overview

The **Quiz Web Application** is a RESTful API built using **Spring Boot 3.2.0** and **Java 21**. It allows users to register, create quizzes, answer quizzes, and delete their own quizzes. Authentication and authorization are handled using **Spring Security 6.4.3** with **Basic Authentication**.

## Features

- **User Registration**: Unauthenticated users can register by providing their email and password.
- **Basic Authentication**: Registered users authenticate using their email and password.
- **Quiz Management**:
    - Users can create quizzes with a title, text, options, and answers.
    - Users can answer quizzes posted by themselves or other users.
    - Users can delete only the quizzes they have created.
- **H2 Database**: The application uses an in-memory H2 database for data storage.

## Technologies Used

- **Java 21**
- **Spring Boot 3.2.0**
- **Spring Security 6.4.3**
- **Spring Web**
- **Spring Data JPA**
- **H2 Database**

## API Endpoints

### Authentication

- **Register a new user**
  ```http
  POST /api/register
  ```
  **Request Body:**
  ```json
  {
    "email": "user@example.com",
    "password": "securepassword"
  }
  ```

### Quiz Management

- **Create a quiz** (Authenticated users only)

  ```http
  POST /api/quizzes
  ```

  **Request Body:**

  ```json
  {
    "title": "Sample Quiz",
    "text": "What is the capital of France?",
    "options": ["Paris", "Berlin", "Madrid", "Rome"],
    "answers": [0]
  }
  ```

- **Get all quizzes**

  ```http
  GET /api/quizzes
  ```

- **Answer a quiz**

  ```http
  POST /api/quizzes/{id}/solve
  ```

  **Request Body:**

  ```json
  {
    "answer": [0]
  }
  ```

- **Delete a quiz** (Only the quiz creator can delete it)

  ```http
  DELETE /api/quizzes/{id}
  ```

## Authentication

The application uses **Basic Authentication**. After registration, users must provide their email and password in the `Authorization` header to access protected endpoints.

### Example Basic Auth Header:

```
Authorization: Basic base64(email:password)
```

Developed with ❤️ using **Spring Boot**.
