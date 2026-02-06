# Car Booking System Release 1 
## Application Overview 
Transactional Operations: the creation / cancellation of car bookings. </br>
Inventory Management: listing all available cars for booking, filtered by type (Gasoline or Electric). </br>
User Management: displaying all registered users and cars booked for a specific user. </br>

<img width="1148" height="716" alt="Screenshot 2025-12-20 at 4 32 11 PM" src="https://github.com/user-attachments/assets/7946aaad-8cce-4f23-b979-d9e8ad60d673" /></br>                                                                                      

# Car Booking System Documentation 
## Features
- **Make a Car Booking** (By User ID and Car Registration Number)
- **Cancel a Car Booking** (By Booking ID)
- **Display All Available Cars for booking**
- **Display All Available Gasoline Cars for booking**
- **Display All Available Electric Cars for booking**
- **Display All Cars Booked by User** (By User ID)
- **Display All Registered Users**
- **View Main Menu**
  
## Learning Outcomes
✅ **Maven** to manage project's dependencies and build lifecycle.</br>
✅ **Dependency Injection** to ensure decoupled components.</br>
✅ **Exception Handling** for custom exceptions.</br>
✅ **Arrays** for storing all data and simulating database interactions.</br>
✅ **Defensive Copying** for all data access objects to maintain immutability and protect domain layer objects internal state.</br>
✅ **Interfaces** for dependency inversion for data access objects and ensuring business logic is decoupled from storage implementation.</br>
✅ **Unit testing using AssertJ** for correctness in domain classes and data access classes.</br>

## System Architecture </br>
**Layered Architecture:** Organized into **Presentation (CLI)**, **Service (Business Logic)**, and **Data Access (DAO)** layers to ensure a clean separation of concerns and ease of refactoring. </br>
  
## [Important Files in Release 1 ](https://github.com/eimcharles/CarBookingSystem/releases/tag/v1.0.0)

### Top 5 Files
| File path | Purpose (1 line description) |
|------|------------|
|../src/main/java/com/eimc/app/CarBookingCLI.java|Controller to create, cancel car bookings and view user booked cars|
|../src/main/java/com/eimc/app/CLIDisplayUtility.java  |Data presentation layer for booking, car and user information |
|../src/main/java/com/eimc/booking/BookingService.java |Contains business logic related to bookings |
|../src/main/java/com/eimc/car/CarService.java |Contains business logic related to cars |
|../src/main/java/com/eimc/user/UserService.java | Contains business  logic related to users |

### Top 5 Tests
| File path | Purpose (1 line description) |
|------|------------|
|../src/test/java/com/eimc/booking/ArrayBookingDAOTest.java|Booking Data Access Test Class|
|../src/test/java/com/eimc/car/ArrayCarDAOTest.java |Car Data Access Test Class|
|../src/test/java/com/eimc/user/ArrayUserDAOTest.java |User Data Access Test Class|
|../src/test/java/com/eimc/booking/BookingTest.java |Booking Domain Test Class|
|../src/test/java/com/eimc/car/CarTest.java |Car Domain Test Class|

### Test results
#### **Domain and DAO testing** is done using the **AssertJ** testing library.</br>
<img width="1412" height="583" alt="Screenshot 2025-12-18 at 4 28 42 PM" src="https://github.com/user-attachments/assets/6e2dd05a-482e-43ee-8035-348d7e4e03c3" /></br>

---
