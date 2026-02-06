# Car Booking  Release 2 (In Progress 🚀)
## Application Overview
Transactional Operations: creation / cancellation of car bookings.</br>
Inventory Management: listing all available cars by type.</br>
User Management: user accounts, manage profiles, and view all registered users.</br>

## Features
- Single Sign-On / Sign-up (SSO)
- Self service password recovery.
- Role-Based Access Control with JSON Web Tokens.
- Asynchronous Email Notifications.
- Payment Processing with Stripe API

## CRUD operations
- Create user accounts, manage profiles, and view all registered users.
- Initiate a new car booking. 
- Terminate an active booking. 
- List all available cars based by type. 
- Retrieve all cars booked by a specific user.

## Learning Outcomes
✅ **Spring Boot** to manage project's configurations.</br>
✅ **Spring Data JPA** with MySQL database for persistent storage and retrieval of application data.</br>
❌ **Spring Security with JSON Web Tokens** for user authentication, role based access control and single sign on.</br>
❌ **Spring Boot Mail Starter** for notifications and booking confirmations.</br>
❌ **Stripe API** for payment processing and real-time reservation updates. </br>
✅ **Streams** for functional programming.</br>
❌ **Generics** for type-safe API response mapping.</br>
❌ **Mocking** using Mockito for testing component interactions.</br>
✅ **Docker** for containerizing and optimizing build contexts for efficient deployment.</br>
❌ **Java Test Containers** to for isolated database environments for integration tests.</br>

## Important Files Release 2
| File path with clickable link | Purpose (1 line description) |
|------|------------|
|[../src/main/java/com/eimc/booking/BookingService.java](../../src/main/java/com/eimc/booking/BookingService.java) |Contains business logic related to bookings |
|[../src/main/java/com/eimc/car/CarService.java](../../src/main/java/com/eimc/car/CarService.java)|Contains business logic related to cars |
|[../src/main/java/com/eimc/user/UserService.java](../../src/main/java/com/eimc/user/UserService.java)| Contains business  logic related to users |


