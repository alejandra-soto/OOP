# OOP
Author: Alejandra Soto
Class: Fall 2019 COMP586
App: To-do List

Hi Professor Rabinovich,

I created a To-Do List Application that lets users create different lists and tasks within those lists. For example, a user can create a "Grocery" list and, within this list, create "Purchase a quart of oatmilk", "Pick 10 apples from the neighbor's tree", "Buy chicken nuggets from Trader Joe's" tasks.

I used Spring Boot, JDK, Hibernate, Spring Framework, and Spring Data JPA for the web server. For the frontend, I used Angular 8, Bootstrap, npm, and JQuery.

I deployed my application using AWS Beanstalk on an EC2 server.


Requirements: 

-Single Page Application (SPA): The Angular frontend takes care of the data-to-HTML conversion process.

-Model-View-Controller (MVC) architecture: Models are Task.java and TaskList.java, View is the Angular frontend,
Controllers are TaskController.java and TaskListController.java

-Object-relational mapping (ORM): Hibernate ORM framework handles converting data between the MYSQL relational database and Java. There are two tables, "Task-List" and "Task", that have a one-to-many relationship. Task-List can have multiple tasks but tasks cannot have multiple task-lists.

-Dependency injection design pattern: Spring Framework and Angular use dependency injection. In Spring Framework, objects can be injected into other objects with the Spring core container. In the Task and TaskList controllers, the @Autowired annotation allows the injection of the Task and TaskList repository interfaces. In the Angular frontend, the task.service.ts file is an @Injectable service class that contains get and post methods. The components (task-list, create-task, category-list, create-list) inject the TaskService as a contructor parameter.

-Use tokens for authentication: I integrated Google Sign-In in my application, which manages the token lifecycle and uses Google APIs with OAuth 2.0 authorization. 

