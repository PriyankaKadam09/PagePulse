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

http://localhost:8080

## Built for Digital Heroes Training Task