# 🛒 Shopify E-Commerce Microservices Application

A production-style **E-Commerce Backend System** built using **Java, Spring Boot, Microservices Architecture, Kafka, JWT Security, Stripe Payment Gateway, Twilio Notifications, and API Gateway**.

This project demonstrates real-world enterprise backend development practices including:

* **Microservices Architecture**
* **API Gateway**
* **Service Discovery (Eureka)**
* **JWT Authentication & Authorization**
* **Kafka Event-Driven Communication**
* **Stripe Payment Integration**
* **Email, SMS & WhatsApp Notifications**
* **Centralized Routing**
* **Inter-Service Communication using OpenFeign**
* **Cloud-Native Design Principles**

---

# 🚀 Tech Stack

### Backend

* Java 21
* Spring Boot
* Spring Security + JWT
* Spring Cloud Gateway
* Spring WebFlux
* Spring Data JPA
* Hibernate
* Maven

### Microservices & Cloud

* Eureka Server
* OpenFeign Client
* API Gateway
* Centralized Routing

### Database

* MySQL

### Messaging Queue

* Apache Kafka

### Payment Integration

* Stripe Checkout

### Notifications

* Gmail SMTP (Email)
* Twilio SMS
* Twilio WhatsApp API

### Dev Tools

* IntelliJ IDEA
* Postman
* Git & GitHub
* Maven

---

# 🏗️ Microservices Architecture

The application follows **Microservices Architecture** where each service is independently deployable and communicates through REST APIs and Kafka events.

## Services Overview

### 1. Product Service

Manages products and catalog.

#### Features

* Add Product
* Update Product
* Delete Product
* Get Product Details
* Product Image Upload (AWS S3)

**Port:** `8081`

---

### 2. Cart Service

Handles guest and logged-in user carts.

#### Features

* Guest Cart using UUID
* Add to Cart
* Update Quantity
* Remove Product
* Get Cart Details
* Clear Cart after Order Placement

**Port:** `8082`

---

### 3. Order Service

Handles customer order processing.

#### Features

* Place Order
* Fetch Cart Data via Feign Client
* Create Order Items
* Calculate Total Price
* Publish Kafka Event after Order Creation

**Port:** `8083`

---

### 4. Payment Service

Handles online payments.

#### Features

* Stripe Hosted Checkout Integration
* Payment Success
* Payment Cancel
* Order Payment Handling

**Port:** `8084`

---

### 5. Notification Service

Handles event-based notifications.

#### Features

* Email Notifications
* SMS Notifications
* WhatsApp Notifications
* Kafka Consumer for Order Events

**Port:** `8086`

---

### 6. API Gateway

Single entry point for all microservices.

#### Features

* Centralized Routing
* JWT Authentication
* Token Validation
* Security Filters

**Port:** `8085`

---

### 7. Eureka Server

Service Registry for service discovery.

#### Features

* Service Registration
* Service Discovery

**Port:** `8761`

---

### 8. Admin Server

Used for monitoring microservices.

#### Features

* Health Monitoring
* Metrics Monitoring
* Service Status

**Port:** `9090`

---

# 🔐 Security Implementation

Implemented **JWT-based Authentication & Authorization**.

### Security Features

* JWT Token Generation
* JWT Token Validation
* Stateless Authentication
* API Gateway Security
* Route Protection

### Public APIs

```http
/api/v1/auth/**
```

### Protected APIs

```http
/api/v1/products/**
/api/v1/cart/**
/api/v1/orders/**
/api/v1/payment/**
```

---

# ⚡ Kafka Event Flow

The application uses **Apache Kafka** for asynchronous communication.

### Flow

1. User places order
2. Order Service publishes event
3. Notification Service consumes event
4. Email/SMS/WhatsApp notification sent

### Kafka Topic

```text
order-topic
```

---

# 💳 Stripe Payment Flow

1. Order Created
2. Payment Service creates Stripe Checkout Session
3. User redirected to Stripe Hosted Page
4. Payment Success / Cancel handled

---

# 📩 Notification Flow

After successful order placement:

### Email

Sent using Gmail SMTP.

### SMS

Sent using Twilio SMS API.

### WhatsApp

Sent using Twilio WhatsApp API.

---

# 🌐 API Gateway Routes

| Service         | Route                 |
| --------------- | --------------------- |
| Product Service | `/api/v1/products/**` |
| Cart Service    | `/api/v1/cart/**`     |
| Order Service   | `/api/v1/orders/**`   |
| Payment Service | `/api/v1/payment/**`  |

---

# 📂 Project Structure

```text
shopify_ecommerce/
│
├── admin-server/
├── api-gateway/
├── cart-service/
├── eureka-server/
├── notification-service/
├── order-service/
├── payment-service/
├── product-service/
│
└── README.md
```

---

# ⚙️ Setup Instructions

## 1. Clone Repository

```bash
git clone https://github.com/janardhan-akhil/shopify-ecommerce-app.git
```

---

## 2. Configure Environment Variables

Create `.env` file in root folder.

Example:

```env
AWS_ACCESS_KEY=your_access_key
AWS_SECRET_KEY=your_secret_key

STRIPE_SECRET_KEY=your_stripe_secret

MAIL_USERNAME=your_email
MAIL_PASSWORD=your_app_password

TWILIO_ACCOUNT_SID=your_sid
TWILIO_AUTH_TOKEN=your_token
TWILIO_FROM_PHONE=your_phone
TWILIO_WHATSAPP_NUMBER=whatsapp:+14155238886
```

---

## 3. Start Required Services

### Start MySQL

### Start Kafka

### Start Eureka Server

```bash
mvn spring-boot:run
```

---

## 4. Start Microservices Order

1. Eureka Server
2. API Gateway
3. Product Service
4. Cart Service
5. Order Service
6. Payment Service
7. Notification Service
8. Admin Server

---

# 🧪 API Testing

Use **Postman** to test APIs.

### Example Flow

#### Add Product

```http
POST /api/v1/products
```

#### Add To Cart

```http
POST /api/v1/cart/add
```

#### Place Order

```http
POST /api/v1/orders/place
```

#### Payment

```http
POST /api/v1/payment/create-session
```

---

# 📸 Future Enhancements

* Dockerize Microservices
* Kubernetes Deployment
* Redis Caching
* Elasticsearch
* CI/CD Pipeline
* Role-Based Authorization
* Order Tracking
* Inventory Management

---

# 👨‍💻 Author

**Akhil Janardhan**

Java Backend Developer | Spring Boot | Microservices | Kafka | AWS

GitHub:
https://github.com/janardhan-akhil

---

## ⭐ Support

If you found this project helpful, please give it a **Star ⭐** on GitHub.
