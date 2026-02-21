# micro-investments

Small Spring Boot microservice for investment/expenses processing.

## Project layout
- `src/main/java/com/blackrock/micro/investments/micro_investments` - application code  
  - `controllers` - HTTP controllers (see `MicroInvestments.java`)  
  - `service` - service interfaces and implementations (`Investments`, `InvestmentsImpl`)  
  - `model` - domain models (`Expenses`, `EnrichedExpenses`, `Metrics`, `ExpensesObj`, `validateAmounts`)  
- `src/main/resources/application.yaml` - application configuration (server port, etc.)  
- `src/test` - unit tests

## Prerequisites
- Java 17+ (match project's configured Java version)  
- Maven 3.6+  
- IntelliJ IDEA (project opened with Maven support)  
- Windows OS (development environment)

## Configuration
Default configuration located at `src/main/resources/application.yaml`.  
Default server port:
- `server.port: 5477`

Adjust configuration values in `application.yaml` or provide external config via environment variables / `-D` JVM properties.

## Build
From project root, run:
- `mvn clean package`

## Run
- From IDE: run the main class `MicroInvestmentsApplication` (`src/main/java/.../MicroInvestmentsApplication.java`).  
- From CLI:  
  - `mvn spring-boot:run`  
  - or run the packaged jar: `java -jar target/<artifact>-<version>.jar`

Service will be available at `http://localhost:5477/` (unless port changed in config).

## API
Controller(s) are in `src/main/java/com/blackrock/micro/investments/micro_investments/controllers/MicroInvestments.java`. Inspect that file for available endpoints and request/response shapes.

Example (replace path with actual endpoint from the controller):
- List or health:
  - `curl http://localhost:5477/health`  
- Example POST (JSON):
  - `curl -X POST -H "Content-Type: application/json" -d @payload.json http://localhost:5477/expenses`

Models are in `src/main/java/com/blackrock/micro/investments/micro_investments/model/` and show expected JSON fields.

## Tests
Run unit tests:
- `mvn test`

Unit test entrypoint: `src/test/java/com/blackrock/micro/investments/micro_investments/MicroInvestmentsApplicationTests.java`

## Notes
- Review `MicroInvestments.java` controller to confirm exact endpoints and sample payloads.  
- If additional env-specific configuration is required, use Spring profiles (`application-{profile}.yaml`) or environment variables.
