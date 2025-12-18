# Car Booking System Release 1 

---

## Application Overview: </br> 
**Transactional Operations**: facilitates the creation / cancellation of car bookings. </br>
**Inventory Management**: listing all available cars for booking, filtered by type (Gasoline or Electric). </br>
**User Management**: displaying all registered users and cars booked for a specific user. </br>

## Demo (Coming soon 🚀)</br>
--- 
 
<img width="1067" height="667" alt="Screenshot 2025-12-03 at 2 52 09 PM" src="https://github.com/user-attachments/assets/2df1e683-423b-4d17-a5db-9a0d6a194fb5" />

# Car Booking System Documentation 

---

## Features </br>
- **Make a Car Booking** (By User ID and Car Registration Number)
- **Cancel a Car Booking** (By Booking ID)
- **Display All Available Cars**
- **To Display Available Gasoline Cars**
- **To Display Available Electric Cars**
- **Display Cars Booked by User** (By User ID)
- **Display All Registered Users**
- **View Main Menu**
  
---

## Learning Outcomes: </br>
✅ **Maven** to manage project's dependencies and build lifecycle.</br>
✅ **Dependency Injection** to ensure decoupled components.</br>
✅ **Exception Handling** for custom exceptions.</br>
✅ **Arrays** for storing all data.</br>
✅ **Defensive Copying** for all data access objects.</br>
✅ **Interfaces** for dependency inversion for data access objects.</br>
✅ **Unit testing using AssertJ** for correctness in domain classes and data access classes.</br>

---

## System Architecture </br>
Layered architecture using an array based approach to store data.</br>
  
---

## Important Files

### Top 5 Files

| File path with clickable link | Purpose (1 line description) |
|------|------------|
|[../src/main/java/com/eimc/app/CarBookingCLI.java](../src/main/java/com/eimc/app/CarBookingCLI.java)|Controller to create, cancel car bookings and view user booked cars|
|[../src/main/java/com/eimc/app/CLIDisplayUtility.java](../src/main/java/com/eimc/app/CLIDisplayUtility.java) |Data presentation layer for booking, car and user information |
|[../src/main/java/com/eimc/booking/BookingService.java](../src/main/java/com/eimc/booking/BookingService.java) |Contains business logic related to bookings |
|[../src/main/java/com/eimc/car/CarService.java](../src/main/java/com/eimc/car/CarService.java)|Contains business logic related to cars |
|[../src/main/java/com/eimc/user/UserService.java](../src/main/java/com/eimc/user/UserService.java)| Contains business  logic related to users |

### Top 5 Tests
| File path with clickable link | Purpose (1 line description) |
|------|------------|
|[../src/test/java/com/eimc/booking/ArrayBookingDAOTest.java](../src/test/java/com/eimc/booking/ArrayBookingDAOTest.java)|Booking Data Access Test Class|
|[../src/test/java/com/eimc/car/ArrayCarDAOTest.java](../src/test/java/com/eimc/car/ArrayCarDAOTest.java)|Car Data Access Test Class|
|[../src/test/java/com/eimc/user/ArrayUserDAOTest.java](../src/test/java/com/eimc/user/ArrayUserDAOTest.java)|User Data Access Test Class|
|[../src/test/java/com/eimc/booking/BookingTest.java](../src/test/java/com/eimc/booking/BookingTest.java)|Booking Domain Test Class|
|[../src/test/java/com/eimc/car/CarTest.java](../src/test/java/com/eimc/car/CarTest.java)|Car Domain Test Class|

### Test results
#### **Domain and DAO testing** is done using the **AssertJ** testing library.</br>
<img width="1412" height="583" alt="Screenshot 2025-12-18 at 4 28 42 PM" src="https://github.com/user-attachments/assets/6e2dd05a-482e-43ee-8035-348d7e4e03c3" /></br>
---

## How to Run

1. Navigate to the JAR Location

 ```bash 
    car-booking-cli.jar file.
```

1. Run the Application
 ```bash 
    Execute the JAR file using the java -jar car-booking-cli.jar
```
