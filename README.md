# LeetCode Mentor

## Overview
LeetCode Mentor is a Spring Boot microservice designed to retrieve, process, and serve LeetCode problem data.  
The service connects to LeetCode’s GraphQL API, maps the raw response into clean domain models, supports filtering, paging, and uses an in-memory cache to reduce external API calls.

This project is part of a larger system aimed at helping users practice algorithmic problem-solving in a structured, efficient, and mentor-guided way.
Work in progress.

Features:
- fetch problems from LeetCode GraphQL
- DTO mapping
- in-memory caching
- paging support
