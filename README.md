# Pizza-shop: Full-Stack Spring Boot and React Application

## Overview
This full-stack project is an imitation of an online pizza shop, providing various filtering options and order placement functionality. The backend is developed using Spring Boot, while the frontend is built with React. The project aims to simulate a seamless pizza ordering experience with extensive filtering capabilities.

## Table of Contents
- [Project Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies used)
- [Project Roadmap and Issues](#Project Roadmap and Issues)
- [Getting Started](#getting started)
    - [Installation](#installation)
    - [Configuration](#configuration)
    - [Running the Application](#Running the Application)
- [API Documentation](#api-documentation)
- [User Interface Overview](#user interface overview)

## Features

- Pizza Selection
- Filtering Options
- Order Placement
- Query-DSL Integration
- Redux-Toolkit State Management

## Technologies Used
- List the technologies and tools used in your project. For example:
    - Backend:
        - Spring-boot
        - Query-DSL (with Blazebit)
        - Hibernate
        - liquibase migration
        - H2 (for tests)
    - Frontend:
        - React (TypeScript)
        - Redux-Toolkit
        - Debounce

## Project Roadmap and Issues

### Current Issues
Currently, the project covers the basic features of an online pizza shop. However, there are several potential areas of improvement and expansion, such as:

1. **User Authentication**: Implementing user authentication and accounts to handle personalized order history and preferences.
2. **Cart Page**: Improvement *`OrderPopUp`* element after payment processing


## Getting Started
Follow these steps to get the project up and running on your local machine.

## Installation

1. Clone the repository: `https://github.com/borumv/pizza-project.git`

## Configuration

1. Backend Configuration:

    - Navigate to the backend directory: `cd backend`
    - Configure the database connection in `application.properties`.
    - Customize any other required configurations.
2. Frontend Configuration:

    - Navigate to the frontend directory: `cd frontend`
    - Update the API endpoint in the configuration file if needed.

## Running the Application

1. Backend:
    - Build and run the Spring Boot application.
2. Frontend:

    - Install dependencies: `npm install`
    - Start the development server: `npm start`

Access the application by visiting `http://localhost:3000` in your web browser.

### Running the Application

Now you're all set to run the full-stack application:

1. Start the Spring Boot backend server (if you haven't already):

`./mvnw spring-boot:run`

The backend server will be running at `http://localhost:8090.

2. Start the React frontend server:
   `cd frontend npm start`

The frontend server will be running at `http://localhost:3000`.

## API Documentation
### Endpoints

**Person**

|Endpoint|Method|Description|
|---|---|---|
|`/api/persons/all{page}`|GET|Get a list of persons with pagination.|
|`/api/persons`|GET|Get a list of all persons.|
|`/api/persons/pattern={startWith}`|GET|Get a list of persons whose name starts with the specified pattern.|
|`/api/persons/{userId}`|GET|Get the person with the specified user ID.|
|`/api/persons`|POST|Add a new person.|
|`/api/persons/{id}`|DELETE|Delete the person with the specified ID.|
|`/api/persons/{userId}/doc_member`|GET|Get the document members associated with the person of the given user ID.|
|`/api/persons/{userId}/doc_payment`|GET|Get the document payments associated with the person of the given user ID.|
|`/api/persons/{userId}/class_education`|GET|Get the education information of the person with the given user ID.|
|`/api/persons/{userId}/workplace`|GET|Get the work places associated with the person of the given user ID.|
|`/api/persons/{id}`|PUT|Update the person with the specified ID.|

**Trade union**

|Endpoint|Method|Description|
|---|---|---|
|`/api/union/{unionId}`|GET|Get the trade union with the specified union ID.|
|`/api/union/{unionId}/docpayments`|GET|Get all document payments associated with the trade union of the given union ID.|
|`/api/union/{unionId}/members{pageNumber}`|GET|Get all active members associated with the trade union of the given union ID and page number.|
|`/api/union/{unionId}/members`|GET|Get all active members associated with the trade union of the given union ID.|
|`/api/union`|GET|Get a list of all trade unions.|
|`/api/union/{unionId}`|DELETE|Delete the trade union with the specified union ID.|
|`/api/union`|POST|Add a new trade union.|
|`/api/union/{id}`|PUT|Update the trade union with the specified ID.|

**Class education**

|Endpoint|Method|Description|
|---|---|---|
|`/api/education/{typeEducationId}/allpersons`|GET|Get a list of persons who are taking the specified type of education.|
|`/api/education`|GET|Get a list of all class education records.|
|`/api/education/{id}`|GET|Get the class education record with the specified ID.|

**Doc member**

| Endpoint                     | Method | Description                                                            |
| ---------------------------- | ------ | ---------------------------------------------------------------------- |
| `/api/union/docmember/all`   | GET    | Get a list of document members who have not finished their membership. |
| `/api/union/docmember/{num}` | GET    | Get the document member with the specified document number.            |

**Doc Payment**

| Endpoint                           | Method | Description                                                         |
| ---------------------------------- | ------ | ------------------------------------------------------------------- |
| `/api/docpayments`                 | GET    | Get a list of all document payments.                                |
| `/api/docpayments/{payId}`         | GET    | Get the document payment with the specified payment ID.             |
| `/api/docpayments/{payId}`         | DELETE | Delete the document payment with the specified payment ID.          |
| `/api/docpayments/{payId}`         | PUT    | Update the document payment with the specified payment ID.          |
| `/api/docpayments/count`           | GET    | Get the quantity of payments per trade union.                       |
| `/api/docpayments/count_statistic` | GET    | Get the quantity of payments that are not yet made per trade union. |


**Work Place**

|Endpoint|Method|Description|
|---|---|---|
|`/api/union/workplace/all`|GET|Get all work places.|
|`/api/union/workplace/{workPlaceId}`|GET|Get the work place with the specified ID.|


**User**

|Endpoint|Method|Description|
|---|---|---|
|`/api/user/findEmail`|GET|Get the user with the specified email.|
|`/api/user`|GET|Get the authenticated user with authorities.|
|`/api/user/permissions`|GET|Get the list of permissions for the specified role.|
|`/api/user/change_password`|POST|Change the password for the user with the specified email.|

**Authentication**

|Endpoint|Method|Description|
|---|---|---|
|`/api/auth/login`|POST|Authenticates a user with the provided credentials.|
|`/api/auth/logout`|POST|Logs out the currently authenticated user.|
|`/api/auth/register`|POST|Registers a new user with the provided registration details.|
|`/api/auth/refreshtoken`|POST|Refreshes an access token using a valid refresh token.|
|`/api/auth/validate`|POST|Validates if the given token is still valid for the user.|

## User Interface Overview


In this section, I provide an overview of the user interface of our application. Below are some screenshots and videos showcasing different pages and functionalities.

### Authorization

#### Login page with validation


https://github.com/borumv/Trade-Union-Application/assets/86356857/fc1170dd-a536-44aa-8be2-fa2084613fbd

#### Register page


https://github.com/borumv/Trade-Union-Application/assets/86356857/ef535d4b-da69-4427-ac6d-73b8d6288e61


### Working with tables

#### Different view with different roles

**Admin**

https://github.com/borumv/Trade-Union-Application/assets/86356857/1ca64a47-31a6-40c5-9b98-6915e427f707

**User**

https://github.com/borumv/Trade-Union-Application/assets/86356857/3e51d4ba-407c-4b50-90ea-bf4c9c06c85c

#### CRUD operations & Validation

https://github.com/borumv/Trade-Union-Application/assets/86356857/39f6c327-7d6f-4a68-ae7f-3d70cfb3bf2e




