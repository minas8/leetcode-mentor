# LeetCode Mentor

## Overview
**LeetCode Mentor** is a Spring Boot microservice that integrates with LeetCode’s unofficial GraphQL API, retrieves problem data, maps it into clean domain models, and exposes a structured REST API for client applications.

The service is designed as part of an upcoming mentor-driven coding practice system.  
**Work in progress.**

---

## ✨ Features (MVP)
- Fetch problems from LeetCode's GraphQL endpoint  
- Domain-layer modeling (Problem, DifficultyLevel, more coming soon)
- DTO → Domain mapping
- In-memory caching (prevents repeated external calls)
- Paging support `/paged?page=x&size=y`
- Difficulty filtering (query param + path param)
- Global exception handling with structured error responses

---

## 📡 REST Endpoints
| Method | Endpoint                   | Description                    |
|--------|---------------------------------------------|--------------------------------------------------|
| `GET` | `/leetcode/problems`                         | Returns all problems, optional difficulty filter |
| `GET` | `/leetcode/problems/difficulty/{difficulty}` | Returns problems filtered by difficulty          |
| `GET` | `/leetcode/problems/paged?page=&size=`       | Returns pageable list of problems                |

---

## 🧱 Tech Stack
- **Java 17**
- **Spring Boot WebFlux**
- **Lombok**
- **Reactive WebClient**
- **GraphQL over HTTP**
- **Maven**

---

## 🏗 Project Structure
- /controller → REST controllers
- /service → Business logic
- /domain → Domain models (clean architecture)
- /infrastructure → LeetCode API client
- /exception → Global exception handling

---

## 🚧 Roadmap (Coming Soon)
- Redis distributed caching  
- Fetch problem details by ID  
- Tag filtering  
- Integration with Judge0 for code execution  
- Multi-service architecture (CodeExecutor service)  
- CLI tool (MCP-based)

---

## 📌 Status
This project is actively developed as part of a personal portfolio and learning path.  
More features will be added gradually.

