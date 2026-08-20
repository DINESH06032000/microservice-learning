# Order Service – Microservices with OpenFeign, Eureka & PostgreSQL

## 1. Project Overview

This project demonstrates a basic **Microservices Architecture** using:

- Spring Boot
- Spring Cloud OpenFeign
- Eureka Service Discovery
- PostgreSQL
- Spring Data JPA
- REST APIs
- Maven

The system contains three applications:

- **Eureka Server**
- **User Service**
- **Order Service**

The **Order Service** stores order information in PostgreSQL and retrieves user information from the **User Service** through **Spring Cloud OpenFeign** and **Eureka Service Discovery**.

---

## 2. Microservices

| Service | Port | Responsibility |
|---|---:|---|
| Eureka Server | `8761` | Service Discovery |
| User Service | `8081` | Manage and retrieve users |
| Order Service | `8082` | Create and retrieve orders |

---

## 3. Architecture

```text
                    +------------------+
                    |      Client      |
                    +--------+---------+
                             |
                             | HTTP
                             v
                    +------------------+
                    |  Order Service   |
                    |     :8082        |
                    +--------+---------+
                             |
                             | OpenFeign
                             v
                    +------------------+
                    |  Eureka Server   |
                    |     :8761        |
                    +--------+---------+
                             |
                             | Service Discovery
                             v
                    +------------------+
                    |   User Service   |
                    |     :8081        |
                    +------------------+
