# Hotel Rating System - Microservices Architecture

A comprehensive hotel rating system built using Spring Boot microservices architecture with Eureka service discovery, PostgreSQL databases, and RESTful APIs.

## 🏗️ Architecture Overview

This project implements a microservices architecture with the following components:

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   User Service  │    │  Hotel Service  │    │ Rating Service  │
│   Port: 9090    │    │   Port: 8080    │    │   Port: 9091    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
         │                       │                       │
         └───────────────────────┼───────────────────────┘
                                 │
                    ┌─────────────────────┐
                    │  Service Registry   │
                    │  (Eureka Server)    │
                    │    Port: 8761       │
                    └─────────────────────┘
```

## 📋 Services Description

### 1. Service Registry (Eureka Server)
- **Port**: 8761
- **Purpose**: Service discovery and registration
- **Technology**: Spring Cloud Netflix Eureka Server
- **URL**: http://localhost:8761

### 2. User Service (MyUserService)
- **Port**: 9090
- **Purpose**: Manages user information and profiles
- **Database**: PostgreSQL (usersDB)
- **Features**:
  - Create new users
  - Retrieve user by ID
  - Get all users
  - User profile management

### 3. Hotel Service
- **Port**: 8080 (default)
- **Purpose**: Manages hotel information
- **Database**: PostgreSQL (HotelDB)
- **Features**:
  - Create new hotels
  - Retrieve hotel by ID
  - Get all hotels
  - Hotel information management

### 4. Rating Service
- **Port**: 9091
- **Purpose**: Manages ratings and feedback for hotels
- **Database**: PostgreSQL (ratingDB)
- **Features**:
  - Create ratings for hotels
  - Retrieve ratings by user ID
  - Retrieve ratings by hotel ID
  - Get all ratings

## 🛠️ Technology Stack

- **Framework**: Spring Boot 3.4.5
- **Language**: Java 17
- **Service Discovery**: Spring Cloud Netflix Eureka
- **Database**: PostgreSQL
- **ORM**: Spring Data JPA with Hibernate
- **Communication**: OpenFeign (for inter-service communication)
- **Documentation**: SpringDoc OpenAPI (Swagger)
- **Build Tool**: Maven
- **Additional Libraries**: Lombok, Spring Web

## 📊 Data Models

### User Entity
```java
- userId (String) - Primary Key
- name (String)
- email (String)
- about (String)
- ratings (List<Rating>) - Transient field
```

### Hotel Entity
```java
- hotelId (String) - Primary Key
- name (String)
- location (String)
- about (String)
```

### Rating Entity
```java
- ratingId (String) - Primary Key
- userId (String) - Foreign Key
- hotelId (String) - Foreign Key
- rating (int)
- feedback (String)
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 12+
- Git

### Database Setup

1. Install and start PostgreSQL
2. Create the following databases:
   ```sql
   CREATE DATABASE usersDB;
   CREATE DATABASE HotelDB;
   CREATE DATABASE ratingDB;
   ```
3. Update database credentials in application.properties files if needed (default: username=postgres, password=1234)

### Installation & Running

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd hotel-rating-system
   ```

2. **Start the Service Registry (Eureka Server) first**
   ```bash
   cd ServiceRegistry/ServiceRegistry
   mvn spring-boot:run
   ```
   Wait for the Eureka server to start (usually takes 30-60 seconds)

3. **Start the microservices** (in separate terminals)
   
   **User Service:**
   ```bash
   cd MyUserService/MyUserService
   mvn spring-boot:run
   ```
   
   **Hotel Service:**
   ```bash
   cd HotelService/HotelService
   mvn spring-boot:run
   ```
   
   **Rating Service:**
   ```bash
   cd RatingService/RatingService
   mvn spring-boot:run
   ```

4. **Verify the setup**
   - Eureka Dashboard: http://localhost:8761
   - All services should be registered and show as UP

## 📚 API Documentation

### User Service APIs (Port: 9090)

| Method | Endpoint              | Description           |
|--------|-----------------------|-----------------------|
| POST   | `/users/`             | Create a new user     |
| GET    | `/users/{userId}`     | Get user by ID        |
| GET    | `/users/`             | Get all users         |
| GET    | `/users/hello`        | Health check          |

### Hotel Service APIs (Port: 8080)

| Method | Endpoint              | Description           |
|--------|-----------------------|-----------------------|
| POST   | `/hotels/`            | Create a new hotel    |
| GET    | `/hotels/{hotelId}`   | Get hotel by ID       |
| GET    | `/hotels/`            | Get all hotels        |

### Rating Service APIs (Port: 9091)

| Method | Endpoint                    | Description                |
|--------|-----------------------------|----------------------------|
| POST   | `/ratings/`                 | Create a new rating        |
| GET    | `/ratings/`                 | Get all ratings            |
| GET    | `/ratings/users/{userId}`   | Get ratings by user ID     |
| GET    | `/ratings/hotels/{hotelId}` | Get ratings by hotel ID    |

## 🧪 Testing the APIs

### Example API Calls

1. **Create a User**
   ```bash
   curl -X POST http://localhost:9090/users/ \
   -H "Content-Type: application/json" \
   -d '{
     "userId": "user1",
     "name": "John Doe",
     "email": "john@example.com",
     "about": "Software Developer"
   }'
   ```

2. **Create a Hotel**
   ```bash
   curl -X POST http://localhost:8080/hotels/ \
   -H "Content-Type: application/json" \
   -d '{
     "hotelId": "hotel1",
     "name": "Grand Plaza",
     "location": "New York",
     "about": "Luxury hotel in downtown"
   }'
   ```

3. **Create a Rating**
   ```bash
   curl -X POST http://localhost:9091/ratings/ \
   -H "Content-Type: application/json" \
   -d '{
     "ratingId": "rating1",
     "userId": "user1",
     "hotelId": "hotel1",
     "rating": 5,
     "feedback": "Excellent service!"
   }'
   ```

## 🔧 Configuration

### Service Ports
- Service Registry: 8761
- User Service: 9090
- Hotel Service: 8080 (default Spring Boot port)
- Rating Service: 9091

### Database Configuration
Each service uses its own PostgreSQL database:
- User Service: `usersDB`
- Hotel Service: `HotelDB`
- Rating Service: `ratingDB`

## 📈 Monitoring

- **Eureka Dashboard**: http://localhost:8761
  - View registered services
  - Monitor service health
  - Service instance details

## 🛡️ Features

- **Service Discovery**: Automatic service registration and discovery using Eureka
- **Microservices Architecture**: Loosely coupled, independently deployable services
- **Database Per Service**: Each service has its own database
- **RESTful APIs**: Clean and consistent API design
- **Inter-Service Communication**: Services can communicate using OpenFeign
- **Auto-Configuration**: Spring Boot auto-configuration for rapid development

