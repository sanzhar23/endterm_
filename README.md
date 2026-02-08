<img width="2060" height="2359" alt="image" src="https://github.com/user-attachments/assets/e7a7f72b-3bec-4815-9892-3d743d871ba5" />This project is a Spring Boot REST API for an online quiz platform. The application allows users to:create quizzes with different difficulty levels (EASY, HARD) ,view all quizzes, get a quiz by id,delete quizzes
The project follows a layered architecture:controller layer handles HTTP requests and responses,repository layer works with the database.
project also demonstrates solid principles

**B. REST API Documentation**
This project provides a RESTful API to manage quizzes and questions.All endpoints were tested using Postman
Get all quizzes: Endpoint: /api/quizzes. METHOD GET = Return all quiz
GET http://localhost:8080/api/quizzes
<img width="1024" height="431" alt="image" src="https://github.com/user-attachments/assets/de01acb7-4801-44d8-bbd6-0e6af687bef3" />
<img width="1364" height="627" alt="image" src="https://github.com/user-attachments/assets/fe525f08-4657-4502-a9ce-8bb63a91735e" />

Get quiz by ID
GET http://localhost:8080/api/quizzes/1
<img width="1383" height="597" alt="image" src="https://github.com/user-attachments/assets/7827ab91-63bd-4c10-8dde-c405580849d7" />
Returns a quiz by its ID.

Create a new quiz Method: POST
POST http://localhost:8080/api/quizzes
<img width="1400" height="570" alt="image" src="https://github.com/user-attachments/assets/3cbca0c0-53a9-47c3-91d8-704ddccd782b" />


Delete quiz by ID  Method: DELETE
DELETE http://localhost:8080/api/quizzes/13
<img width="1377" height="441" alt="image" src="https://github.com/user-attachments/assets/98562707-7daf-4970-a2bb-0ff4e4afc6e5" />

**C. Design Patterns Section**

This project uses several design patterns to make the code more structured, reusable, and easy to maintain
Singleton Pattern:DatabaseConnection class follows the Singleton pattern
It provides a single shared connection configuration for the database
Constructor is private and connection is accessed through a static method
Factory Pattern:is used to create objects without exposing the creation logic
Quizzes are created based on their difficulty level: EASY → EasyQuiz  HARD → HardQuiz
Builder Pattern:helps to create complex objects step by step.
DTO objects (such as request objects for API calls) follow the Builder idea by separating object creation from business logic
This makes the code easier to read and helps avoid large constructors with many parameters


**D. Component Principles Section**

This project follows basic component principles to keep the code clean, organized
Controller:Handles HTTP requests and returns responses
Service:Contains business logic and validation rules
Repository:Works with the database and executes SQL queries
Model:Represents application data (Quiz, Question)

High Cohesion(classes in the project are focused on one task)
QuizController works only with quiz-related endpoints
QuizRepository works only with quiz database operations
QuestionRepository works only with question database operations


**E. SOLID & OOP Summary**
Single Responsibility Principle (SRP):
Each class has one clear responsibility
Controllers handle HTTP requests, services contain business logic, and repositories work with the database

Open/Closed Principle (OCP):The base Quiz class can be extended by subclasses like EasyQuiz and HardQuiz without modifying existing code
Liskov Substitution Principle (LSP):Subclasses (EasyQuiz, HardQuiz) can be used wherever the base Quiz class is expected without breaking functionality
Interface Segregation Principle (ISP):Interfaces in the project are small and focused. Classes implement only the methods they need
Dependency Inversion Principle (DIP):High-level components (controllers) depend on abstractions (services), not on concrete implementations

**Object-Oriented Programming Concepts**
Abstraction:Abstract classes and interfaces hide implementation details and expose only necessary behavior
Inheritance:EasyQuiz and HardQuiz inherit from the abstract Quiz class
Polymorphism:Quiz objects are handled using the base Quiz type, while actual behavior depends on the subclass
Encapsulation:Fields are protected or private and accessed through methods

**F. Database Schema**
The database contains two main tables:
quizzes and questions
<img width="2060" height="2359" alt="image" src="https://github.com/user-attachments/assets/f7d4f7b8-dee9-450d-a1ab-b580a7cae257" />


Quizzes Table: id is a primary key
name must be unique and not null
level is limited to predefined values

Questions Table:quiz_id is a foreign key referencing quizzes(id)
points must be a positive number
Questions are deleted automatically when a quiz is deleted
Column	Type	Description
id	Primary key
quiz_id	INT	
text	TEXT	
correct_answer	TEXT
points	INT

Relationships:
One quiz can have many questions
Each question belongs to one quiz


**G. System Architecture Diagram**
Client (Postman)
        ↓
Controller Layer
        ↓
Service Layer
        ↓
Repository Layer
        ↓
Database (PostgreSQL)
Controller Layer:Receives HTTP requests from the client.
It maps endpoints such as GET, POST, and DELETE and returns JSON responses.
Service Layer:contains business logic and validation rules.
It decides how data should be processed before saving or returning it.
Repository Layer:works directly with the database.
It executes SQL queries and maps database rows to Java objects.
Database Layer:stores quizzes and questions in PostgreSQL tables.

**H. Instructions to Run the Spring Boot Application**
Create a PostgreSQL database: quiz_db
Open the terminal in the project root directory and run:
"mvn clean install"
This command downloads dependencies and builds the project.
To start the Spring Boot application, run:
mvn spring-boot:run
Or run the Application.java class directly from IntelliJ IDEA
After successful startup, the application runs on:
http://localhost:8080
