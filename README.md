# CRM Spring Boot Test Project

A simple **Customer Relationship Management (CRM)** system built with **Spring Boot** and **Java 25**. This project uses **Gradle** as the build tool and serves as a **testbed** for experimenting with Spring Boot and Java features.

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── de/
│   │       └── apollo7321/
│   │           └── crm/
│   │               ├── controller/     # REST Controllers
│   │               ├── dto/            # Data Transfer Objects
│   │               ├── exception/      # Global Exception Handling
│   │               ├── model/          # Entities
│   │               ├── repository/     # Repository Interfaces
│   │               └── service/        # Service Classes
│   └── resources/
│       └── application.properties   # Configuration
```

## Prerequisites

- Java 25
- Gradle
- An IDE of your choice (e.g., IntelliJ IDEA, VSCode)

## Installation

1. Clone the repository:
   ```bash
   git clone <repository-url>
   ```

2. Navigate to the project directory:
   ```bash
   cd <project-directory>
   ```

3. Build the project using Gradle:
   ```bash
   ./gradlew build
   ```

## Running the Application

Start the application with Gradle:
```bash
./gradlew bootRun
```

The application will be available at `http://localhost:8080`.

### Swagger UI

After starting the application, you can access the **Swagger UI** to explore and test the API endpoints:
- Open `http://localhost:8080/swagger-ui.html` in your browser.

## API Endpoints

### Customers

- **GET** `/customer` – Returns a list of all customers.
- **POST** `/customer` – Creates a new customer. Expects a JSON body:
  ```json
  {
    "firstName": "Max",
    "lastName": "Mustermann"
  }
  ```

## Validation

The API validates input for new customers. Missing or invalid fields result in a `400 Bad Request` error with a descriptive message.

## License

This project is licensed under the **MIT License**.
