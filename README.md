# Prepit backend application

## Table of Contents  
[Project Description](#project-description)  
[Technology Stack](#technology-stack)  
[Documentation](#documentation)



## Project Description
A full-stack web application written in Springboot, React.js, and MySQL (Hibernate) created for my bachelor's thesis.
Based on user parameters (such as gender, age, weight, height, and the goal of either losing or gaining weight) the application will suggest food recipes for each part of the day. This algorithm uses a priority queue implemented as a min heap to generate the suggestion list and the accuracy or the diversity of results can be tuned in the user's settings of the application.

Besides algorithm tuning, creating an account comes with the benefit of being able to create custom collections of recipes and saving them for later reading. In order for an user to create an account, they have to provide a valid email address which will be used to verify the account later.

## Technology stack
* The application was developed with Springboot framework, MySQL and Hibernate
* Spring Security for authentication and security
* Maven for project management
* Swagger-ui for API documentation
* Lombok in order to reduce boilerplate code

## Documentation
The documentation contains various information about how the application was designed and it's architecture

<details>
  <summary>Expand</summary>
  
  ### Users and use cases
  
There is only one type of user that can create an account, log into application, and use the meal-suggestion algorithm. The application is structured     in such a way that a user can interact with the most of the application, there is no need for creating an account. However, creating an account comes with the benefit of storing the information about the user for later use. 

With the user permission granted, the application can store the metrics used by the user in order to make it easier for them to generate meals, the stored fields being automatically taken into account. Another benefit of having an account is saving meal recipes for later reading.
  ![Imgur Image](https://imgur.com/MFZd6Mx.jpg)

  ### Class diagram: Domain
  ![Imgu Image](https://imgur.com/ydiVmdE.jpg)

  
  #### User
The user entity represents the basic actor of the application. This class includes attributes like age, weight, height, gender, that are stored with the user’s approval in order to make the process of meal generation easier. The user also contains an attribute called authority used to identify what type of privileges a user has.

  #### Authority
  The authority class implements the Springboot Security interface GrantedAuthority and it is used during the authentication phase in order to decide what privileges the user has
  
  #### Meal
  Most of the time recipes in the application are stored into a JSON dataset which we will talk about later. However, when a user decides he wants to save a recipe for later reading, the meal is stored inside the database and inside a given collection.
  
  #### Collection
  The collection entity is used to associate recipes when the user saves them. This allows the user to categorize saved recipes based on their preference, and in order to help them, the collection also stores a collection name and a description

  #### Verification token
  This entity is used solely for verification of the email address. This is generated when a new account is created and contains information about the user and the actual token
  

  ### Some Code
  ```js
  function logSomething(something) {
    console.log('Something', something);
  }
  ```
</details>
