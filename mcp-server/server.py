from fastapi import FastAPI
import requests

app = FastAPI(title="LeetCode Mentor MCP Server")

LEETCODE_SERVICE_URL = "http://localhost:8080"

@app.get("/health")
def health():
    return {"status": "ok"}

@app.get("/tools/get_problem_by_id")
def get_problem_by_id(problem_id: str):
    """
    MCP Tool:
    Fetch a single LeetCode problem by ID from the Java service
    """
    response = requests.get(
        f"{LEETCODE_SERVICE_URL}/leetcode/problems/{problem_id}"
    )

    if response.status_code != 200:
        return {
            "error": "Failed to fetch problem",
            "status_code": response.status_code
        }

    return response.json()

@app.get("/tools/get_problems_by_difficulty")
def get_problems_by_difficulty(difficulty_id: str):
    """
    MCP Tool:
    Fetch a list of problems by Difficulty from the Java service
    
    difficulty_id: EASY | MEDIUM | HARD
    """
    response = requests.get(
        f"{LEETCODE_SERVICE_URL}/leetcode/problems?difficulty={difficulty_id}"
        )
        
    if response.status_code != 200:
        return {
            "error": "Failed to fetch problem list",
            "status_code": response.status_code
        }
    
    return response.json()