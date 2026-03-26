# Todo Management REST API

## Overview
Todo Management API is a backend application built using Spring Boot that allows users to manage tasks through RESTful APIs.

## Features
- Create new todo
- Get all todos
- Get todo by id
- Update todo
- Delete todo

## Tech Stack
- Java 17
- Spring Boot
- Spring Data JPA
- H2 Database
- Maven

## Project Structure
Controller → Service → Repository → Database

## API Endpoints

GET /api/todos  
GET /api/todos/{id}  
POST /api/todos  
PUT /api/todos/{id}  
DELETE /api/todos/{id}

## Example Request

POST /api/todos

Body:

{
"title": "Learn Spring Boot",
"description": "Practice REST API",
"status": "PENDING"
}

## Author
Phan Cong Ngoc