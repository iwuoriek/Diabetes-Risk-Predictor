# Diabetes-Risk-Predictor
A decision support system to aid with type 2 diabetes risk prediction.
All code handling MVC features of this project are located in two folders.

# View
Programming for the UI of this system are contained in **.xhtml** files located in **web** folder. Design template is provided in **.css** file located in path **web/resources**

# Model and Controller
Both model and controller classes are located in the following file path **src/java/org/drp**. The **config** package contains classes tasked with the management of all web and database initialization and configuration. The **controller** package contains classes responsible for handling interactions and data transfer between application/business logic and the system UI. Classes and packages responsible for managing database sessions and connectivity, use of the **prediction model/algorithm** and located in the **handler** package. The **model** package holds all database entity classes and data transfer object (dto) classes which encapsulates data that is transfer through the system.

# Documentation
Intext documentations have been provided within code to explain the operations of the classes and methods within them.
