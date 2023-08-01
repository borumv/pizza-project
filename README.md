# 🍕Pizza-shop: Full-Stack Spring Boot and React Application🍕

## 🔎 Overview 🔎
This full-stack project is an imitation of an online pizza shop, providing various filtering options and order placement functionality. The backend is developed using Spring Boot, while the frontend is built with React. The project aims to simulate a seamless pizza ordering experience with extensive filtering capabilities.

## 🗂️ Table of Contents 🗂️
- [Project Overview](#overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Project Roadmap and Issues](#Project-Roadmap-and-Issues)
  - [Current Issues](#Current-Issues)
- [Getting Started](#getting-started)
    - [Installation](#installation)
    - [Configuration](#configuration)
    - [Running the Application](#Running-the-Application)
- [API Documentation](#api-documentation)
- [User Interface Overview](#user-interface-overview)

## 🕹️ Features 🕹️

- Pizza Selection
- Filtering Options
- Order Placement
- Query-DSL Integration
- Redux-Toolkit State Management

## 👨‍🚀 Technologies Used 👨‍🚀
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

## 🗺️Project Roadmap and Issues🗺️

### Current Issues
Currently, the project covers the basic features of an online pizza shop. However, there are several potential areas of improvement and expansion, such as:

1. **User Authentication**: Implementing user authentication and accounts to handle personalized order history and preferences.
2. **Cart Page**: Improvement *`OrderPopUp`* element after payment processing


## 🏁 Getting Started 🏁
Follow these steps to get the project up and running on your local machine.

### Installation

1. Clone the repository: `https://github.com/borumv/pizza-project.git`

### Configuration

1. Backend Configuration:

    - Navigate to the backend directory: `cd backend`
    - Configure the database connection in `application.properties`.
    - Customize any other required configurations.

### Running the Application

1. Backend:
    - Build and run the Spring Boot application.
2. Frontend:

    - Install dependencies: `npm install`
    - Start the development server: `npm start`

Access the application by visiting `http://localhost:3000` in your web browser.


## 📝 API Documentation 📝
### Endpoints


#### Pizza Controller
| Endpoint                                  | Method | Parameters                                                                                                                                                                                   | Description                                                                                                                                      |
|-------------------------------------------|--------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------|
| `/api/pizza/model/all`                   | GET    | None                                                                                                                                                                                         | Retrieves a list of `PizzaModel` objects representing all available pizzas.                                                                     |
| `/api/pizza/types`                       | GET    | None                                                                                                                                                                                         | Retrieves a list of strings representing the descriptions of all pizza types.                                                                    |
| `/api/pizza/categories`                  | GET    | None                                                                                                                                                                                         | Retrieves a list of strings representing the names of all pizza categories.                                                                     |
| `/api/pizza/model/all_pizzes`            | GET    | - `category_id` (optional): The ID of the pizza category.<br>- `orderingValue` (optional): The value to order the pizzas by (e.g., price, rating).<br>- `ordering_type` (optional): The ordering type (ascending or descending).<br>- `search_value` (optional): The search value to filter pizzas by title. | Retrieves an `ExportDataPizzaModel` object containing pizza types, categories, and a list of pizza models.                                         |
| `/api/pizza/model/only_pizzes_page={page}limit={limit}` | GET    | - `category_id` (optional): The ID of the pizza category.<br>- `orderingValue` (optional): The value to order the pizzas by (e.g., price, rating).<br>- `ordering_type` (optional): The ordering type (ascending or descending).<br>- `search_value` (optional): The search value to filter pizzas by title.<br>- `page`: The page number for pagination.<br>- `limit`: The maximum number of items per page. | Retrieves a `PageableModel<PizzaModel, Pizza>` object containing pizza models with pagination information. |

#### Order Controller

| Endpoint                   | Method | Parameters                      | Description                                                                                                                                                              |
|----------------------------|--------|---------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `/api/order`               | POST   | - `orderPizza`: The OrderModel object containing the details of the order. | Register a new order with the provided order data. Returns a boolean value indicating the success or failure of the order registration.                           |

## User Interface Overview

#### Placing an order

https://github.com/borumv/pizza-project/assets/86356857/dd628273-357c-46ea-a42b-83a5a2348a92


#### Filtration


https://github.com/borumv/pizza-project/assets/86356857/1c4fddd6-ff55-4104-b73e-c9dc5f6aea68



