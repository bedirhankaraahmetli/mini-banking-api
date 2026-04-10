# Mini Banking API

An enterprise-oriented, robust RESTful API for a banking application, built with modern Java and Spring Boot. 

## 🚀 Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.x
* **Database:** PostgreSQL (Dockerized)
* **ORM:** Spring Data JPA / Hibernate
* **Documentation:** Swagger / OpenAPI 3.0
* **Tools:** Maven, Lombok, Docker Compose

## ⚙️ Current Features

* **Customer Management:** Create and manage bank customers with strict data validation (Unique Identity Number and Email).
* *More features coming soon (Account Management, Transactions, Clean Architecture, and Security).*

## 🛠️ Getting Started

### Prerequisites

* [Java Development Kit (JDK) 17 or higher](https://www.oracle.com/tr/java/technologies/downloads/#java21)
* [Docker Desktop](https://www.docker.com/products/docker-desktop/)
a
### Installation & Run

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/bedirhankaraahmetli/mini-banking-api.git](https://github.com/bedirhankaraahmetli/mini-banking-api.git)
   cd mini-banking-api
    ````

2.  **Start the PostgreSQL database via Docker:**

    ```bash
    docker-compose up -d
    ```

3.  **Run the application using Maven Wrapper:**

    ```bash
    ./mvnw clean spring-boot:run
    ```

    *(For Windows PowerShell/CMD, use `.\mvnw clean spring-boot:run`)*

## 📖 API Documentation

This project uses Springdoc OpenAPI for self-documenting API endpoints. Once the application is running, you can explore and test the API directly via the Swagger UI:

👉 **[Swagger UI: http://localhost:8080/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui/index.html)**

-----

*Developed with enterprise architecture principles.*
