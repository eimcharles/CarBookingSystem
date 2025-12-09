# Car Booking System

## Release 1  

<img width="1067" height="667" alt="Screenshot 2025-12-03 at 2 52 09 PM" src="https://github.com/user-attachments/assets/2df1e683-423b-4d17-a5db-9a0d6a194fb5" />

## Application Overview
This project is a Maven application that serves as a system for managing the car booking lifecycle, it includes:
- **Transactional Operations**: facilitating the creation / cancellation of car bookings.
- **Inventory Management**: listing all available cars, filtered by type (Gasoline or Electric).
- **User Management**: displaying all registered users and displaying cars booked for a specific user.


---

## Learning Outcomes
- 🚫 **Maven** to manage project's dependencies and build lifecycle.
- 🚫 **SOLID principles** to ensure maintainable, and flexible object-oriented design.
- ✅ **Chained Filtering** for algorithmic control and procedural efficiency.
- ✅ **Dependency Injection** to ensure decoupled components.
- ✅ **Exception Handling** for custom exceptions.
- ✅ **Arrays** for storing all data.
- ✅ **Defensive Copying** for all data access objects.
- ✅ **Interfaces** for dependency inversion for data access objects.
- 🚫 **Unit Testing** for correctness.

---

## Features
- Make a Car Booking (By User ID and Car Registration Number)
- Cancel a Car Booking (By Booking ID)
- Display All Available Cars
- To Display Available Gasoline Cars
- To Display Available Electric Cars
- Display Cars Booked by User (By User ID)
- Display All Registered Users
- Display All Registered Users
- View Main Menu
---

## System Architecture
- Layered architecture using an array based approach to store data.
---

## File Structure
```
CarBookingSystem/
├── .idea/ 
├── src/
│     ├── app/                              (Presentation Layer)
│     │   ├── CarBookingApp.java
│     │   ├── CarBookingCLI.java
│     │   ├── CLIDisplayUtility.java
│     │   ├── CLIFormatUtility.java
│     │   └── CLIInputUtility.java
│     │
│     ├── booking/                          (Booking Domain Component)
│     │   ├── Booking.java                  
│     │   ├── BookingService.java           
│     │   └── dao/                          (Data Access Layer for Booking)
│     │       ├── ArrayBookingDAO.java      (Concrete Array Implementation)
│     │       └── BookingDAO.java           (Interface / Contract)
│     │
│     ├── car/                              (Car Domain Component)
│     │   ├── Brand.java                    
│     │   ├── Car.java                      
│     │   ├── CarService.java               
│     │   ├── FuelType.java                 
│     │   └── dao/                          (Data Access Layer for Car)
│     │       ├── ArrayCarDAO.java          (Concrete Array Implementation)
│     │       └── CarDAO.java               (Interface / Contract)
│     │
│     ├── configuration/                    (Dependency Injection Setup)
│     │   └── Configuration.java            (Initializes and links all services/DAOs)
│     │
│     ├── exception/ 
│     │   ├── BookingNotActiveException.java
│     │   ├── BookingNotFoundException.java
│     │   ├── CarNotFoundException.java
│     │   ├── CarUnavailableException.java
│     │   └── UserNotFoundException.java
│     │ 
│     └──  user/                            (User Domain Component)
│         ├── User.java                     
│         ├── UserService.java              
│         └── dao/                          (Data Access Layer for User)
│             ├── ArrayUserDAO.java         (Concrete Array Implementation)
│             └── UserDAO.java              (Interface / Contract)
│           
├── CarBookingSystem.iml 
└── README.md                               
```

## How to Run

1. Navigate to the JAR Location

 ```bash 
    car-booking-cli.jar file.
```

1. Run the Application

 ```bash 
    Execute the JAR file using the java -jar car-booking-cli.jar
```
