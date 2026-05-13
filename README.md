# **Quantity Measurement Application**

> Developed by **Srihari V**

---

# **Project Overview**

The Quantity Measurement Application is a **Spring Boot–based backend application** designed to perform quantity measurement operations such as:

* Unit Conversion
* Addition of Quantities
* Subtraction of Quantities
* Comparison of Quantities
* Division of Quantities

> **Note:**
> The complete source code for this project is available in the `dev` branch.
> The `main` branch contains only project overview and documentation.

The project was gradually enhanced across multiple Use Cases (UCs) to implement:

* REST APIs
* Database Integration
* Swagger Documentation
* JWT Authentication
* Google Authentication
* Spring Security
* Microservices Architecture
* Eureka Service Discovery
* API Gateway

The application follows a clean layered architecture and demonstrates real-world backend development practices using Spring Boot and Microservices.

---

# **Technologies Used**

## **Backend**

* Java 17
* Spring Boot 3
* Spring Security
* Spring Data JPA
* Spring Cloud
* Spring Cloud Gateway
* Eureka Discovery Server
* OpenFeign
* JWT Authentication
* OAuth2 / Google Authentication

## **Database**

* H2 Database

## **Documentation**

* Swagger OpenAPI

## **Build Tool**

* Maven

---

# **Project Structure**

```plaintext
Quantity_Measurement_App/
│
├── eureka-server/
├── api-gateway/
├── user-service/
├── measurement-service/
│
└── README.md
```

---

# **Microservices Included**

# **1. Eureka Server**

Acts as the **Service Registry**.

## **Port**

```plaintext
8761
```

## **Purpose**

* Registers all services
* Provides service discovery
* Helps inter-service communication

---

# **2. API Gateway**

Acts as the **central entry point**.

## **Port**

```plaintext
9090
```

## **Responsibilities**

* Route requests to services
* Centralized API handling
* Future authentication filtering

---

# **3. User Service**

Handles:

* User registration
* User login
* JWT generation
* Google Authentication

## **Port**

```plaintext
8080
```

---

# **4. Measurement Service**

Handles quantity operations.

## **Port**

```plaintext
8081
```

## **Features**

* Convert quantities
* Add quantities
* Subtract quantities
* Compare quantities
* Divide quantities

---

# **Implemented Use Cases**

# **UC1 – Basic Quantity Measurement**

## **Implemented**

* Length measurement operations
* Feet and Inch conversion
* Equality checking

---

# **UC2 – Volume Measurement**

## **Implemented**

* Gallon and Liter conversion
* Equality operations

---

# **UC3 – Weight Measurement**

## **Implemented**

* Kilogram and Gram conversion
* Arithmetic operations

---

# **UC4 – Temperature Measurement**

## **Implemented**

* Celsius and Fahrenheit conversion

---

# **UC5 – Generic Quantity Architecture**

## **Implemented**

* Generic quantity handling
* Interface-based design
* Reusable conversion structure

---

# **UC6 – REST API Development**

## **Implemented**

* Spring Boot REST Controllers
* API endpoints
* JSON request/response

---

# **UC7 – Swagger Documentation**

## **Implemented**

* Swagger OpenAPI integration
* API testing interface
* Endpoint documentation

## **Swagger URL**

```plaintext
http://localhost:8081/swagger-ui/index.html
```

---

# **UC8 – H2 Database Integration**

## **Implemented**

* H2 in-memory database
* JPA Entity mapping
* Repository layer

## **H2 Console**

```plaintext
http://localhost:8081/h2-console
```

---

# **UC9 – Service Layer Architecture**

## **Implemented**

* Service interfaces
* Service implementation layer
* Business logic separation

---

# **UC10 – Exception Handling**

## **Implemented**

* Global exception handling
* Custom exceptions
* API error responses

---

# **UC11 – Validation**

## **Implemented**

* DTO validation
* Request validation
* Invalid request handling

---

# **UC12 – DTO Layer**

## **Implemented**

* Request DTOs
* Response DTOs
* Layer separation

---

# **UC13 – Security Basics**

## **Implemented**

* Spring Security setup
* Authentication structure

---

# **UC14 – JWT Authentication**

## **Implemented**

* JWT token generation
* JWT validation
* Stateless authentication

---

# **UC15 – User Registration and Login**

## **Implemented**

* Register API
* Login API
* Password encryption

---

# **UC16 – Database Persistence**

## **Implemented**

* JPA persistence
* Entity storage
* Repository integration

---

# **UC17 – Full REST + JPA Quantity APIs**

## **Implemented**

* Quantity CRUD structure
* Complete API testing
* Swagger validation

---

# **UC18 – Google Authentication and User Management**

## **Implemented**

* Google Authentication
* JWT authentication
* Secure login system
* Spring Security filters
* Token-based authorization

## **Authentication APIs**

### **Register**

```http
POST /auth/register
```

### **Login**

```http
POST /auth/login
```

---

# **UC19 – Microservices Architecture**

## **Implemented**

* Eureka Server
* API Gateway
* User Service
* Measurement Service
* Service Discovery
* Inter-service architecture
* Centralized routing

---

# **API Endpoints**

# **Authentication APIs**

| Method | Endpoint         | Description                   |
| ------ | ---------------- | ----------------------------- |
| POST   | `/auth/register` | Register new user             |
| POST   | `/auth/login`    | User login and JWT generation |

---

# **Quantity APIs**

| Method | Endpoint                              | Description                     |
| ------ | ------------------------------------- | ------------------------------- |
| POST   | `/api/quantity/compare`               | Compare two quantities          |
| POST   | `/api/quantity/convert/{targetUnit}`  | Convert quantity to target unit |
| POST   | `/api/quantity/add/{targetUnit}`      | Add quantities                  |
| POST   | `/api/quantity/subtract/{targetUnit}` | Subtract quantities             |
| POST   | `/api/quantity/divide`                | Divide quantities               |

---

# **Running the Project**

# **Step 1 – Run Eureka Server**

Run:

```plaintext
EurekaServerApplication.java
```

Open:

```plaintext
http://localhost:8761
```

---

# **Step 2 – Run User Service**

Run:

```plaintext
UserServiceApplication.java
```

---

# **Step 3 – Run Measurement Service**

Run:

```plaintext
QuantityMeasurementApplication.java
```

---

# **Step 4 – Run API Gateway**

Run:

```plaintext
ApiGatewayApplication.java
```

---

# **Service URLs**

| Service             | URL                     |
| ------------------- | ----------------------- |
| Eureka Server       | `http://localhost:8761` |
| API Gateway         | `http://localhost:9090` |
| User Service        | `http://localhost:8080` |
| Measurement Service | `http://localhost:8081` |

---

# **Swagger Testing**

# **Measurement Service Swagger**

```plaintext
http://localhost:8081/swagger-ui/index.html
```

# **Through API Gateway**

```plaintext
http://localhost:9090/swagger-ui/index.html
```

---

# **H2 Database Console**

# **Measurement Service**

```plaintext
http://localhost:8081/h2-console
```

## **JDBC URL**

```plaintext
jdbc:h2:mem:measurementdb
```

---

# **User Service**

```plaintext
http://localhost:8080/h2-console
```

## **JDBC URL**

```plaintext
jdbc:h2:mem:userdb
```

---

# **Security Features**

## **Implemented**

* JWT Authentication
* Spring Security
* Password Encryption
* Stateless APIs
* Protected Endpoints
* Google OAuth Authentication

---

# **Design Principles Used**

* Layered Architecture
* Clean Code Structure
* Separation of Concerns
* DTO Pattern
* Repository Pattern
* Service-Oriented Architecture
* Microservices Architecture

---

# **Future Enhancements**

* Docker Integration
* Kubernetes Deployment
* Redis Caching
* Config Server
* RabbitMQ Integration
* Role-Based Authorization
* CI/CD Pipeline
* MySQL/PostgreSQL Support
* Monitoring with Prometheus & Grafana

---

# **Learning Outcomes**

This project demonstrates:

* Real-world Spring Boot backend development
* REST API development
* Microservices architecture
* JWT Authentication
* Google Authentication
* Database integration
* API documentation
* Service discovery
* API Gateway routing
* Secure application development

---

# **Author**

## **Developed by**

### **Srihari V**

---

# **Conclusion**

The Quantity Measurement Application successfully demonstrates a scalable and modular backend system using Spring Boot Microservices architecture.

The project integrates:

* REST APIs
* JWT Authentication
* Google Authentication
* Eureka Service Discovery
* API Gateway
* Swagger Documentation
* Database Persistence

It represents a complete real-world backend application architecture following modern enterprise development standards.
