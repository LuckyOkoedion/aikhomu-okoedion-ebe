# Bookstore Application

This is a demonstration of a modern, scalable bookstore application built with **Java 21**, **Spring Boot 3**, and the latest development best practices. The system showcases key features such as book inventory management, order checkout, payment simulation, and purchase history.

## Features
- **Book Inventory**: Manage a collection of books with attributes like title, author, genre, and ISBN.
- **Search Functionality**: Search for books based on title, author, year, and genre.
- **Shopping Cart**: Add books to your cart and view them.
- **Checkout Simulation**: Simulate checkout using Web, USSD, and Transfer payment options.
- **Purchase History**: View past orders with payment success status.
- **Unit Testing**: Comprehensive unit tests for business logic and interactions.
- **Mocks**: Simulated external service interactions using **mock-payment-service**.

## Prerequisites
- **Java 21**
- **Spring Boot 3**
- **Docker (optional for using docker-compose)**

## Setting Up the Application

### 1. Clone the repository
```git clone https://github.com/your-repository/bookstore-app.git```
```cd OnlineBookStore```

### 2. Build the application
```Make sure you have Java 21 and Gradle installed on your machine.```

```./gradlew build```

### 3. Running with Docker Compose
```Ensure Docker and Docker Compose are installed on your machine..```

```docker-compose up -d```

### 4. Unit Testing
```You can run the unit tests by using:```

```./gradlew test```


### Interacting with the API
- **GET**: /books/search: Search for books by title, author, genre, or year.
- **POST**: /books: Add a new book to the inventory.
- **GET**: /orders/history/{userId}: View purchase history for a specific user.
- **POST**: /orders/checkout: Simulate a checkout process with options WEB, USSD, or TRANSFER.

### Building and Running with Docker

1. **Ensure Docker and Docker Compose are Installed**
    - Docker: [Install Docker](https://docs.docker.com/get-docker/)
    - Docker Compose: [Install Docker Compose](https://docs.docker.com/compose/install/)

2. **Build and Run the Application**
   ```bash
   docker-compose up --build


### Swagger Docs Path:

http://localhost:8080/swagger-ui/index.html

### Test user credentials:

username: demoUser
password: demoPassword