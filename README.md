# Cycle Pricing Engine

A configurable cycle pricing engine built for the Hero Cycles fresher engineering assignment.

The application allows a salesperson to:

- Select cycle parts
- Choose a pricing date
- Generate component-wise pricing breakdown
- Calculate total cycle price using time-sensitive pricing

---

# Problem Statement

Hero Cycles sells multiple cycle configurations with different combinations of:

- Frames
- Handlebars & Brakes
- Seating
- Wheels
- Chain Assembly

The challenge is that part prices change over time.

---

# Architecture Note

The backend and frontend are intentionally kept independent for simplicity during the assessment.

- Backend focuses on pricing logic, validation, historical pricing, and configuration handling.
- Frontend focuses on configurator usability and pricing visualization.

The frontend implementation was AI-assisted for faster UI prototyping, while the application architecture, pricing engine design, validation flow, data modeling, and backend implementation were designed and implemented by me.

---

# Tech Stack

## Backend
- Java
- Spring Boot
- Maven
- MySQL
- Docker

## Frontend
- React
- HTML/CSS

---

# Data Handling

Part and pricing data are initialized using CSV files.

The CSV files act as lightweight seed data for:
- cycle parts
- component categories
- historical pricing entries

This approach keeps the setup simple for the assessment while still simulating real-world configurable pricing data.

CSV files are located inside:

```text
src/main/resources/data/
```

---

# Database

The application uses MySQL running inside a Docker container.

Docker was used to ensure:
- quick setup
- consistent environment
- easy local execution without manual MySQL installation

---

# Run Backend (Under 2 Minutes)

## Prerequisites

- Java 17+
- Maven
- Docker

## Steps

### 1. Clone repository

```bash
git clone <your-repository-url>
```

### 2. Navigate to backend folder

```bash
cd backend
```

### 3. Start MySQL container

```bash
docker run --name <container-name> \
-e MYSQL_ROOT_PASSWORD=<your-password> \
-e MYSQL_DATABASE=<your-database-name> \
-p 3306:3306 \
-d mysql:8
```

### 4. Configure database credentials

Update:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/<your-database-name>
spring.datasource.username=root
spring.datasource.password=<your-password>
```

### 5. Run Spring Boot application

```bash
mvn spring-boot:run
```

### 6. Backend starts at

```text
http://localhost:8080
```

---

# Run Frontend

## Steps

### 1. Navigate to frontend folder

```bash
cd frontend
```

### 2. Install dependencies

```bash
npm install
```

### 3. Start frontend

```bash
npm run dev
```

### 4. Frontend runs at

```text
http://localhost:5173
```

---

# Core Features

- Time-sensitive pricing
- Component-wise breakdown
- Historical price handling
- Configuration validation
- REST API based pricing engine

---

# Repository Structure

```text
backend/
frontend/
wireframes/
README.md
THINKING.md
UI_NOTES.md
```
