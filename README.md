Order Service – Microservices with OpenFeign, Eureka & PostgreSQL
1. Project Overview

This project demonstrates a basic microservices architecture using:

Spring Boot
Spring Cloud OpenFeign
Eureka Service Discovery
PostgreSQL
Spring Data JPA
REST APIs

The system contains three applications:

Client
   |
   v
Order Service
   |
   | OpenFeign
   v
Eureka Server
   |
   v
User Service

The Order Service stores order information in PostgreSQL and retrieves user information from the User Service through OpenFeign and Eureka.

2. Microservices
Service	Port	Responsibility
Eureka Server	8761	Service discovery
User Service	8081	Manage/retrieve users
Order Service	8082	Create/retrieve orders
3. Architecture
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

Order data:

Order Service
      |
      v
 PostgreSQL
4. Technologies Used
Java
Spring Boot
Spring Cloud
Spring Cloud Eureka
Spring Cloud OpenFeign
Spring Data JPA
PostgreSQL
Maven
REST API
5. Project Structure
microservices-project/
│
├── eureka-server/
│
├── user-service/
│
└── order-service/
