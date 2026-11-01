# HOSPITAL QUEUE MANAGEMENT SYSTEM BACKEND

## 1. Student Information

- **Full Name:** Ngabo Mulisa Kelly
- **Student ID:** 27145
- **Project Name:** Hospital Queue Management System Backend

## 2. Project Description

The Hospital Queue Management System Backend is a REST API developed using Spring Boot. It helps manage hospital operations, including hospital services, counters, nurses, outpatients, queue tickets and service records.

The system provides endpoints for creating, retrieving, updating, deleting and searching hospital records. It also supports queue management, ticket status updates, service-record management and dashboard statistics.

## 3. Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Maven
- PostgreSQL
- Hibernate
- Postman
- Git and GitHub

## 4. Main System Features

The system includes the following features:

1. CRUD operations for Services, Counters, Nurses, Outpatients and Queue Tickets.
2. Search outpatients by last name.
3. Search queue tickets by status.
4. Search queue tickets between two dates.
5. Find nurses according to hospital services.
6. Find available counters.
7. Find unused hospital services.
8. Retrieve outpatients assigned to a particular nurse.
9. Validate queue ticket creation.
10. Update queue ticket statuses according to allowed transitions.
11. Assign services to nurses.
12. Assign available counters to nurses.
13. Save service records for completed queue tickets.
14. Retrieve outpatient service history.
15. Delete service records.
16. Display ticket statistics by status.
17. Cancel tickets assigned to a nurse on a particular date.
18. Dashboard summary and queue ticket pagination.

## 5. Database Configuration

The application uses PostgreSQL as its database.

To configure the database:

1. Install and open PostgreSQL.
2. Create the project database.
3. Open `src/main/resources/application.properties`.
4. Configure the database URL, username and password according to your PostgreSQL installation.

Example configuration (replace the values with your actual database details):

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/hospital_management_db
spring.datasource.username=postgres
spring.datasource.password=admin

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8082
```

## 6. Requirements Before Running the Project

Make sure the following software is installed:

- Java JDK compatible with the project.
- PostgreSQL database.
- Maven.
- Visual Studio Code or IntelliJ IDEA.
- Postman for API testing.

## 7. Steps to Run the API

Follow these steps to run the application:

### Step 1: Clone the Repository

Open a terminal and execute:

```bash
git clone https://github.com/ngabo-kelly/hospital-queue-management-system-backend.git
```

### Step 2: Open the Project

Open the downloaded project folder in Visual Studio Code .

### Step 3: Configure the Database

Create the PostgreSQL database and update the database connection settings in:

```text
src/main/resources/application.properties
```

### Step 4: Open the Terminal

Open a terminal inside the project root directory, where the `pom.xml` file is located.

### Step 5: Run the Application

Execute:

```bash
mvn spring-boot:run
```

### Step 6: Confirm the Application Starts

Wait for Spring Boot to finish starting successfully.

The API runs at:

```text
http://localhost:8082
```

## 8. API Testing Using Postman

Postman is used to test the REST API endpoints.

The Postman collection is included in the root directory of this repository:

```text
hospital-queue-management-system-midterm.postman_collection.json
```

To test the API:

1. Open Postman.
2. Select Import.
3. Choose the Postman collection JSON file.
4. Open the imported collection.
5. Run the requests to test the system endpoints.

## 9. Project Repository

**GitHub Repository:**

https://github.com/ngabo-kelly/hospital-queue-management-system-backend

## 10. Conclusion

The Hospital Queue Management System Backend provides REST API functionality for managing hospital services, nurses, counters, outpatients, queue tickets and service records. It uses Spring Boot, Spring Data JPA and PostgreSQL to support hospital queue management operations.