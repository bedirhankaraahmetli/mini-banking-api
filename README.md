# 🏦 Core Banking System (Microservices Architecture)

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3-brightgreen?style=for-the-badge&logo=spring)
![gRPC](https://img.shields.io/badge/gRPC-1.60-blue?style=for-the-badge&logo=google)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-blue?style=for-the-badge&logo=postgresql)
![Docker](https://img.shields.io/badge/Docker-Container-2496ED?style=for-the-badge&logo=docker)

This repository contains a modern, scalable core banking system built with **Spring Boot 3** and **Java 21**. The project is structured as a **Monorepo** consisting of a main REST API and an asynchronous Notification Service communicating via **gRPC**. The entire system is fully containerized using **Docker**.

## 🏗️ System Architecture

```mermaid
graph TD
    Client([Mobile or Web Client]) -->|REST API| CoreAPI[Core Banking API :8080]
    CoreAPI -->|ACID Transactions| DB[(PostgreSQL)]
    CoreAPI -->|gRPC Protobuf| NotifService[Notification Service :9090]
    NotifService -.->|SMS or Email| User([Customer])
```

## ✨ Key Features & Technical Stack

* **Dockerized Environment:** One-click deployment for the entire microservices ecosystem and database using `docker-compose`.
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
* [Docker](https://www.docker.com/) and Docker Compose (For recommended setup)
* Java 21+ & Maven (For manual setup)

### 🐳 Run with Docker (Recommended & Easiest)
You don't need to install PostgreSQL or Java on your machine. Docker handles everything!
Open a terminal in the root directory and run:

```bash
docker-compose up -d --build
```
That's it! The isolated network will be created, the database will be initialized, and the microservices will start communicating.

### 🧪 Test the API
1. Navigate to the Swagger UI: `http://localhost:8080/swagger-ui/index.html`
2. Register a new user and log in to obtain your Bearer Token.
3. Test the transfer endpoint (`POST /api/v1/accounts/transfer`).
4. Watch the gRPC notification magic in real-time by checking the Docker logs:
   ```bash
   docker logs -f notification_service
   ```

---
*Developed as a comprehensive engineering simulation showcasing enterprise-level backend practices.*