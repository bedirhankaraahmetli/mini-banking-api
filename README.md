# 🏦 Core Banking System (Microservices Architecture)

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-brightgreen?style=for-the-badge&logo=spring)
![gRPC](https://img.shields.io/badge/gRPC-1.60-blue?style=for-the-badge&logo=google)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)

This repository contains a modern, scalable core banking system built with **Spring Boot 3** and **Java 21**. The project is structured as a **Monorepo** consisting of a main REST API and an asynchronous Notification Service communicating via **gRPC**.

## 🏗️ System Architecture

```mermaid
graph TD
    Client([Mobile/Web Client]) -->|REST API (JSON/JWT)| CoreAPI[Core Banking API :8080]
    CoreAPI -->|ACID Transactions| DB[(PostgreSQL)]
    CoreAPI -->|gRPC (Binary/Protobuf)| NotifService[Notification Service :9090]
    NotifService -.->|SMS / Email| User([Customer])
```

## ✨ Key Features & Technical Stack

* **Clean Architecture:** Strict separation of concerns using DTOs, Mappers, and Fail-Fast Data Validation (`@Valid`).
* **Security First:** Stateless authentication utilizing **Spring Security** and **JWT (JSON Web Tokens)**. Passwords are cryptographically hashed using `BCrypt`.
* **Microservices Communication:** High-performance, low-latency inter-service communication using **gRPC** and **Protocol Buffers (.proto)**.
* **ACID Compliance:** Guaranteed transactional integrity during money transfers using `@Transactional`.
* **Robust Error Handling:** Centralized API error management using `@RestControllerAdvice` to prevent internal server stack traces from leaking.
* **Unit Testing:** Business logic validated mathematically without database connections using **JUnit 5** and **Mockito**.

## 📂 Monorepo Structure

* `/core-api`: The main banking brain. Handles users, accounts, security, and money transfers.
* `/notification-service`: A lightweight gRPC server listening on port 9090 to catch transfer events and simulate customer notifications.

## 🚀 Getting Started

### Prerequisites
* Java 21+
* Maven
* PostgreSQL (Running on `localhost:5432`)

### 1. Start the Notification Server
Open a terminal in the `/notification-service` directory and run:
```bash
./mvnw clean spring-boot:run
```

### 2. Start the Core API
Open a new terminal in the `/core-api` directory and run:
```bash
./mvnw clean spring-boot:run
```

### 3. Test the API
* Navigate to the Swagger UI: `http://localhost:8080/swagger-ui/index.html`
* Register a new user, log in to obtain your Bearer Token, and test the transfer endpoint.
* Watch the `notification-service` terminal catch the gRPC signal in real-time!

---
*Developed as a comprehensive engineering simulation showcasing enterprise-level backend practices.*
```
