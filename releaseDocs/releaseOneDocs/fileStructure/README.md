# Car Booking System Release 1 

--- 

## File Structure
```
CarBookingSystem/
├── src/
│   └── main/                                      
│       ├── java/                                  
│       │   ├── com/
│       │   │    └── eimc/ 				
│       │   │           ├── app/                                          (Presentation Layer)
│       │   │           │    ├── CarBookingApp.java
│       │   │           │    ├── CarBookingCLI.java
│       │   │           │    ├── CLIDisplayUtility.java
│       │   │           │    ├── CLIFormatUtility.java
│       │   │           │    └── CLIInputUtility.java 
│       │   │           │ 
│       │   │           │
│       │   │           ├── booking/                                      (Booking Domain Component)
│       │   │           │       ├── Booking.java
│       │   │           │       ├── BookingService.java 
│       │   │           │       └── dao/                                  (Data Access Layer for Booking)
│       │   │           │            ├── ArrayBookingDAO.java             (Concrete Array Implementation)
│       │   │           │            └── BookingDAO.java                  (Interface / Contract for DAO)
│       │   │           │ 
│       │   │           ├── car/                                          (Car Domain Component)
│       │   │           │     ├── Brand.java
│       │   │           │     ├── FuelType.java
│       │   │           │     ├── Car.java
│       │   │           │     ├── CarService.java
│       │   │           │     └── dao/                                    (Data Access Layer for Car)
│       │   │           │          ├── ArrayCarDAO.java                   (Concrete Array Implementation) 
│       │   │           │          └── CarDAO.java                        (Interface / Contract for DAO)
│       │   │           │ 
│       │   │           ├── configuration/
│       │   │           │         └── Configuration.java                  (Initializes and links all services/DAOs)
│       │   │           │ 
│       │   │           │ 
│       │   │           ├── exception/
│       │   │           │       ├── BookingNotFoundException.java
│       │   │           │       ├── CarUnavailableException.Java
│       │   │           │       ├── CarNotFoundException.java
│       │   │           │       └── UserNotFoundException.java
│       │   │           │       
│       │   │           │
│       │   │           └── user/  
│       │   │                 ├── User.java 
│       │   │                 └── UserService.java      
│       │   │                           └── dao/                          (Data Access Layer for User)
│       │   │                                 ├── ArrayUserDAO.java       (Concrete Array Implementation)
│       │   │                                 └── UserDAO.java            (Interface / Contract for DAO)
│       │   │ 
│       │   └── resources/
│       │            └── application.properties
│       └── test/                                                         (Testing Layer)                                  
│             ├── java/
│             │     └── com/
│             │         └── eimc/  
│             │              ├── booking/
│             │              │        ├── BookingTest.Java                (Booking Domain Test Class)
│             │              |        └── ArrayBookingDAOTest.Java        (Booking Data Access Test Class)
│             │              |
│             |              ├── car/
│             │              |    ├── CarTest.Java                        (Car Domain Test Class)
│             │              |    └── ArrayCarDAOTest.java                (Car Data Access Test Class)
│             │              |
│             │              |
│             │              └── user/
│             │                    ├── UserTest.java                      (User Domain Test Class)
│             │                    └── ArrayUserDAOTest.java              (User Data Access Test Class)
│             │       
│             │       
│             └── resources/ 
│                     └── application-test.properties
├── .gitignore          
├── pom.xml          
└── README.md                               
```
----
