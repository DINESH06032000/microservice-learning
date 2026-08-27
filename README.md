# Microservices with OpenFeign, Eureka, API Gateway, Load Balancing, Circuit Breaker, Config Server & PostgreSQL

## 1. Project Overview

This project demonstrates a **production-style Microservices Architecture** using:

- Spring Boot
- Spring Cloud
- Spring Cloud Config Server
- Spring Cloud Config Client
- GitHub Config Repository
- Spring Cloud OpenFeign
- Eureka Service Discovery
- Spring Cloud Gateway
- Spring Cloud LoadBalancer
- Resilience4j Circuit Breaker
- Resilience4j Retry
- Resilience4j Rate Limiter
- Spring Boot Actuator
- PostgreSQL
- Spring Data JPA
- REST APIs
- Maven

The system contains the following applications:

- **Eureka Server**
- **Config Server**
- **API Gateway**
- **User Service**
- **Order Service**

The **Config Server** provides centralized configuration for the microservices by loading configuration properties from a **GitHub Config Repository**.

The **Order Service** stores order information in **PostgreSQL** and communicates with the User Service using **Spring Cloud OpenFeign**.

**Eureka** provides **service discovery**, **Spring Cloud LoadBalancer** distributes requests between multiple service instances, and **Resilience4j** provides fault tolerance using Circuit Breaker, Retry, and Rate Limiter.

---

## 2. Microservices

| Service | Port | Responsibility |
|---|---:|---|
| Eureka Server | `8761` | Service Discovery |
| config Server | `8888` | Centralized Configuration |
| API Gateway | `9090` | Single Entry Point |
| User Service | `8081` | Manage Users |
| Order Service | `8082` | Manage Orders |
| Order Service Instance 2 | `8083` | Load Balancing |
| Config Repository | `Github` | Store Configuration Files |

---

## 3. Architecture

```text
                              +----------------+
                              |     Client     |
                              +-------+--------+
                                      |
                                      | HTTP
                                      v
                            +--------------------+
                            |    API Gateway     |
                            |       :9090        |
                            +---------+----------+
                                      |
                         +------------+------------+
                         |                         |
                         | Routing                 |
                         v                         v
                +----------------+       +----------------+
                | Order Service  |       |  User Service  |
                |     :8082      |       |     :8081      |
                +-------+--------+       +--------+-------+
                        |                         ^
                        |                         |
                        | OpenFeign               |
                        +------------+------------+
                                     |
                                     v
                              +-------------+
                              |   Eureka    |
                              |   Server    |
                              |    :8761    |
                              +------+------+
                                     |
                         Service Discovery
                                     |
              +----------------------+----------------+
              |                                       |
              v                                       v
      +---------------+                       +---------------+
      | Order Service |                       | User Service  |
      |     :8082     |                       |     :8081     |
      +---------------+                       +---------------+
              |
              | Load Balancing
              v
      +---------------+
      | Order Service |
      |     :8083     |
      +---------------+

              Order Data
                   |
                   v
            +-------------+
            | PostgreSQL  |
            +-------------+

                 Centralized Configuration
                           |
                           v
                  +----------------+
                  |  Config Server |
                  |      :8888     |
                  +-------+--------+
                          |
                          | Reads configuration
                          v
                  +----------------+
                  | GitHub Config  |
                  |   Repository   |
                  +----------------+
```
---

## 4. Main Features

#  Service Discovery

- Eureka Server
- Dynamic service registration
- Service discovery
- Service instance management

#  Centralized Configuration

- Spring Cloud Config Server
- GitHub-based configuration repository
- Centralized application properties
- Environment-specific configuration
- Configuration version control

#  API Gateway

- Central entry point
- Request routing
- Service abstraction
- Gateway-based API access

#  Service Communication

- Spring Cloud OpenFeign
- Eureka-based service discovery
- Service-to-service communication
- Declarative REST client

#  Load Balancing

- Multiple service instances
- Request distribution
- Horizontal scaling
- Improved service availability

#  Fault Tolerance

- Circuit Breaker
- Retry mechanism
- Rate Limiter
- Fallback mechanism
- Failure handling

#  Monitoring

- Spring Boot Actuator
- Health checks
- Application metrics

#  Database

- PostgreSQL
- Spring Data JPA
- Hibernate
- Persistent data storage

---
## 5. Project Flow

```text
                     Microservices Architecture
                                  |
                                  v
                         GitHub Config Repo
                                  |
                                  v
                           Config Server
                                :8888
                                  |
                                  v
                      Centralized Configuration
                                  |
                                  v
                               Eureka
                               :8761
                                  |
                                  v
                          Service Discovery
                                  |
                                  v
                             OpenFeign
                                  |
                                  v
                     Service-to-Service Communication
                                  |
                                  v
                            API Gateway
                               :9090
                                  |
                                  v
                         Centralized Routing
                                  |
                                  v
                           Load Balancer
                                  |
                                  v
                       Multiple Service Instances
                                  |
                                  v
                          Circuit Breaker
                                  |
                                  v
                          Fault Tolerance
                                  |
                                  v
                                Retry
                                  |
                                  v
                     Temporary Failure Handling
                                  |
                                  v
                            Rate Limiter
                                  |
                                  v
                          Traffic Control
                                  |
                                  v
                              Actuator
                                  |
                                  v
                              PostgreSQL
                                  |
                                  v
                          Data Persistence
```

