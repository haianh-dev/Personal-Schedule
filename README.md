# PersonalSchedule

PersonalSchedule is a personal web application for managing schedules,
events, notes, and categories.

The project is built as a learning project to practice real-world
backend development with Java and Spring Boot.

## Features

### Current
- Project setup
- Spring Boot backend
- Event management
- Category management
- REST API

### Planned
- Create, update, delete and view events
- Notes associated with events
- Event categories
- Detect overlapping events
- Recurring events
- Filter events by date
- Calendar interface
- User authentication
- PostgreSQL database
- Deployment

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate
- Maven

### Database
- PostgreSQL

### Frontend
- HTML
- CSS
- JavaScript

## Project Structure

```text
src/
├── main/
│   ├── java/
│   │   └── com/haianh/personalschedule/
│   │       ├── config/
│   │       ├── controller/
│   │       ├── dto/
│   │       ├── entity/
│   │       ├── exception/
│   │       ├── repository/
│   │       └── service/
│   │
│   └── resources/
│       ├── static/
│       └── application.properties/
│
└── test/
    └── java/