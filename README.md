# PagePulse

PagePulse is a Spring Boot application that checks whether a website is reachable and returns its HTTP status and response time.

## Features

- URL Health Check
- REST API
- Response Time Measurement
- HTTP Status Code
- URL Validation
- Caching
- Configurable Cache Window
- Rate Limiting
- Recent Checks History
- Responsive UI

## Technologies Used

- Java 17
- Spring Boot
- HTML
- CSS
- JavaScript
- Maven

## API Endpoint

POST /api/audit

Request:

```json
{
  "url": "https://google.com"
}
```

Response:

```json
{
  "url": "https://google.com",
  "status": 200,
  "reachable": true,
  "responseTime": 120,
  "message": "Website is reachable"
}
```

## How to Run

1. Clone the repository.
2. Open in IntelliJ IDEA.
3. Run the Spring Boot application.
4. Open:

http://localhost:8080/

---

## Production Features

PagePulse includes production-oriented features to improve reliability, performance, and security.

- URL input validation
- Request timeout handling
- Concurrency control for audit requests
- Structured error responses
- URL audit result caching
- Configurable cache window
- Rate limiting per client
- Request ID based logging
- Recent audit history tracking


## Error Handling

The application provides structured error responses for invalid URLs, unreachable websites, and request failures.

Example:

```json
{
  "status": 400,
  "error": "Invalid URL",
  "message": "Please provide a valid URL"
}

## Testing

The project includes test cases for:

URL validation
API response correctness
Error scenarios
Audit service functionality

Run tests using:

mvn test

## Architecture Overview

The application follows a simple scalable architecture:
User
↓
REST API Controller
↓
Audit Service
↓
Cache Layer
↓
Website URL Checker
↓
Response Generation
↓
Audit History Storage

# Scalability Design

To handle high traffic and concurrent requests:
Queue-based processing can be used for handling large audit workloads.
Worker services can process URL checks independently.
Cache reduces repeated requests and improves performance.
Database stores audit history and results.
Rate limiting protects the service from excessive requests.
Continuous Integration
Automated testing can be integrated using GitHub Actions to run tests on every push and pull request.

# Deployment

GitHub Repository:

[PagePulse GitHub Repository](https://github.com/PriyankaKadam09/PagePulse)

# Built for Digital Heroes Training Task
