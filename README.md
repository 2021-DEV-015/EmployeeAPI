# EmployeeAPI

Employee API is a Springboot Restful web services used to store and retrieve Employee information. 
It should have two endpoints:
1) To store employee information such as employee id, name, date of birth, address and department details (such as department id and name).
2) To retrieve employee information based on employee id.

## Code commit guidelines

Inspired from Udacity

- feat: a new feature
- refactor: refactoring production code
- test: adding tests, refactoring test; no production code change
- chore: updating build tasks, package manager configs, etc; no production code change

## Prerequisites

- Java - Version 1.8 or higher
- Maven - Dependency management
- Junit - Version 5.7 or higher (added dependency in pom.xml)
- IDE - Eclipse or STS or any other IDE which supports Java

## Setup application

### IDE
```
1) Download project as zip file and unzip the same to a folder
2) Then in eclipse/sts IDE, go to below path
   File -> Import -> select Existing Maven Project option -> Next ->
   	Browse extracted folder and finish
3) Then, right click on the project in Project Explorer window and
	select Run as -> Maven install
```

### Command Prompt
```
1) Download project as zip file and unzip the same to a folder.
2) Open terminal at the project root folder location.
3) Run the command `mvn clean install`.
```

## Steps to run the test cases

### IDE
```
1) Once application setup is completed, Right click on the project in Project explorer window and
   select Run as --> Run Configurations.
2) Under configuration wizard from the left window, right click on Maven Build and
   select New Configuration.
3) Select Base directory as your project root folder and under Goals mention `clean test`
4) Apply and Run the same. This will execute all test cases.
```

### Command Prompt
```
1) Go to the directory where you have setup the application.
2) Run `mvn clean test` to execute test cases.
```

## Steps to run the application in command prompt
```
1) Setup application as per above instructions.
2) Once application setup is completed,
   open command prompt at location <ProjectDirectory>\target
   and execute below command
   java -jar EmployeeAPI-0.0.1-SNAPSHOT.jar
```

## Steps to connect to h2 in memory databse
```
1) Once application is started, go to any browser and connect to below url:
   http://localhost:8080/h2-ui
2) Login with username and password as specified in application.properties file 
   present at location <ProjectDirectory>\src\main\resources
```