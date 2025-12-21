# LeetCode Mentor – MCP Server

A minimal MCP-compatible server that exposes tools for interacting with the LeetCode Mentor Java backend service.

> This is a lightweight proof-of-concept MCP server built for development and learning purposes.

---

## Purpose

This service acts as a thin adapter layer between MCP-compatible clients and the LeetCode Mentor backend, exposing backend capabilities as MCP tools.

---

## Available Tools

### health
Perform a minimal health check.

**Endpoint**
GET /health

**Description**
Perform a basic health check before using any other tool.

### get_problem_by_id

Fetch a single LeetCode problem by ID.

**Endpoint**
GET /tools/get_problem_by_id?problem_id=1

**Description**  
Calls the LeetCode Mentor backend service and returns the full problem details (content, difficulty, tags, etc.).

### get_problems_by_difficulty

Fetch a list of problems by Difficulty from the Java service

**Endpoint**
GET /tools/get_problems_by_difficulty?difficulty_id=EASY

**Description**  
Calls the LeetCode Mentor backend service and returns a list of problems by Difficulty ID (id, title, titleSlug, etc.).

Difficulty ID options: EASY | MEDIUM | HARD

---

## Running the Server

### Prerequisites
- Python 3.10+
- LeetCode Mentor backend running locally

The backend service is expected to be available at:
http://localhost:8080

### Steps

```bash
cd mcp-server
pip install -r requirements.txt
uvicorn server:app --reload --port 3333
```
Once running, the MCP server will be available at:
http://localhost:3333

Swagger UI:
http://localhost:3333/docs

### Status

This MCP server currently exposes basic read-only tools and is intended as a foundation for future MCP-based integrations.
