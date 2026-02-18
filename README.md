# 🚀 Scan2Dine

<p align="center">
  <b>Cloud-Based QR Code Ordering & Online Payment Platform</b><br/>
  Full-Stack Production-Style Application
</p>

---

## 📌 Overview

Scan2Dine is a QR-code based ordering and online payment platform designed for small and medium-sized restaurants.

Customers scan a table QR code to:

- Browse menu
- Add items to cart
- Place orders
- Complete payment via PayPal
- Receive order confirmation instantly

This project demonstrates:

- Full-stack development
- Payment gateway integration
- Transaction-safe backend design
- Containerized deployment
- Production-style cloud hosting

---

## 🌐 Live Demo

### Customer Portal

👉 http://scan2dine.store/menu?tableNo=1

### 📱 Scan to Order

<p align="center">
  <img src="apps/customer-web/public/assets/img/qr-demo.png" width="420" style="margin-right:20px;" />
  <img src="https://api.qrserver.com/v1/create-qr-code/?size=220x220&data=http://scan2dine.store/menu?tableNo=1" width="220" />
</p>

Scan the QR code at your table to start ordering.

---

### 💳 PayPal Sandbox Test Card

```
Card Type: Visa
Card Number: 4032032897387889
Expiry: 10/2027
CVV: 166
```

⚠ Please switch PayPal region to **New Zealand**

---

## 🛠 Admin Portal

👉 http://scan2dine.store/admin/login.jsp

```
Username: admin
Password: 123456
```

Admin Features:

- Menu Management
- Order Monitoring
- Payment Status Tracking
- Basic System Configuration

---

## 🏗 System Architecture

```
Client (React)
       ↓
Nginx Reverse Proxy
       ↓
Spring Boot API
       ↓
MySQL Database
```

### Layered Design

```
Controller (API Layer)
    ↓
Application Layer
    ↓
Domain Layer
    ↓
Infrastructure Layer (Mybatis / Payment / DB)
```

---

# 📂 Project Structure

```
os/
│
├── services/api-service/                  # Spring Boot API
│   ├── src/main/java/
│   │   ├── api/              # REST Controllers
│   │   ├── application/      # Use cases (CreateOrder, PayOrder, etc.)
│   │   ├── common/           # Domain models & business rules
│   │   ├── infrastructure/channel   # PSP integration, such as PayPal
│   │   ├── mapper/           # MyBatis mapper interfaces
|   |   └── persistence/      # Database entity models (table mappings)
│   │
│   └── resources/
│       ├── application.yml
|       ├── mybatis.mapper    # MyBatis SQL mapping XML files
│       └── db/migration/     # Flyway migration scripts
│
├── apps
│   ├── customer-web/         # React customer frontend
│   │   ├──public/            # Static resources, such as pages, images, css files.
│   │   └── src/
│   │       ├── common/       # Shared constants, utilities, helpers
│   │       ├── components/   # Reusable UI components
│   │       ├── containers/   # Smart components connected to global state
│   │       ├── hooks/        # Custom React hooks (state, business logic)
│   │       ├── pages/        # Route-level pages
│   │       ├── router/       # Application routing configuration
│   │       └── services/     # API calls & backend communication layer
│   │
│   └── admin-web/            # Admin management frontend
│
├── infra/                   # Docker & Nginx config
│   ├── nginx/nginx.conf
│   ├── docker-compose.yml
│   └── .env
│
└── README.md
```

---

## 🧰 Tech Stack

### Backend
- Java 17
- Spring Boot
- Mybatis
- MySQL 8
- Flyway (Database Migration)
- RESTful API

### Frontend
- React
- Redux Toolkit
- Vite
- Responsive Mobile-First Design

### Payment Integration
- PayPal REST API
- Webhook Handling
- Idempotent Payment Callback Processing

### DevOps & Deployment
- Docker
- Docker Compose
- Nginx Reverse Proxy
- Tencent Cloud VPS
- Public Domain Configuration

---

## ⚙️ Local Deployment Guide

### 1️⃣ Clone Repository

```
git clone https://github.com/LucasWang1989/os.git
cd os/infra
```

### 2️⃣ Configure Environment Variables

Update `.env` file:

```
BACKEND_IMAGE=your_repository/scan2dine-api-service:latest
FRONTEND_IMAGE=your_repository/scan2dine-customer-web:latest
# database
MYSQL_ROOT_PASSWORD=your_password
# port
BACKEND_PORT=8041
FRONTEND_PORT=3000
MYSQL_PORT=3306
```

Update `nginx.conf` file:

```
server_name your_domain_or_ip_address;
```

### 3️⃣ Start Services

```
docker compose up -d
```

Services Included:

- MySQL
- Backend API
- Frontend
- Nginx

---

## 🔥 Technical Highlights

- Transaction-safe order workflow
- Idempotent PayPal webhook handling
- Clean layered architecture (Domain separation)
- Containerized production-style deployment
- Extensible payment gateway design

---

## 📈 Roadmap

### Phase 1 — Engineering Foundations
- Migrate MyBatis to Spring Data JPA
- Add JWT-based authentication + refresh token support
- Implement RBAC for admin operations

### Phase 2 — Product & Scalability
- Stripe integration
- CI/CD pipeline (GitHub Actions)
- Kubernetes deployment

### Phase 3 — Payments & Cloud-Native
- Refactor Admin Portal to React (modern UI + better maintainability)
- Multi-tenant restaurant support

---

## 🎯 Project Purpose

Scan2Dine showcases:

- Backend system design capability
- Payment system integration experience
- Transaction-heavy workflow handling
- Cloud deployment and DevOps practice
- Full-stack engineering competence

---

## 📄 License

This project is for demonstration and portfolio purposes.