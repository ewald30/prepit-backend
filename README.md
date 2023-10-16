# Prepit backend application

## Table of Contents  
[Project Description](#project-description)  

[Technology Stack](#technology-stack)  

[Documentation](#documentation)

[Contributing](#contributing)



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

  #### Class diagrams
  
  ###Domain
  ![Imgur Image](https://imgur.com/ydiVmdE.jpg)
  
  ### Login and Register
  ![Imgur Image](https://imgur.com/cVOQka6.jpg)

  
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
  
  ### Sequence diagrams
  
  #### Sequence diagram: Login
  ![Imgur Image](https://imgur.com/lc23jTG.jpg)
  
  #### Sequence diagram: Register
  ![Imgur Image](https://imgur.com/ka5gjXO.jpg)
  
  #### Sequence diagram: Email verification
  ![Imgur Image](https://imgur.com/N9npoZl.jpg)
  
  #### Sequence diagram: Meal plan generation
  ![Imgur Image](https://imgur.com/U8kUxuf.jpg)
  
  #### Sequence diagram: Create collection
  ![Imgur Image](https://imgur.com/dmOimgd.jpg)
  
  #### Sequence diagram: Save meal to collection
  ![Imgur Image](https://imgur.com/SjgNows.jpg)
  
  
  ### Deisgn patterns
  
  #### Adapter deisgn pattern
  Starting off with one of the most popular structural design patterns, the Adapter pattern is a common solution for communication between objects with incompatible interfaces. In this application, this is exactly the case, since the dataset of meal recipes stores data in JSON format, and since the algorithm used to suggest recipes needs objects of type MealDTO to operate, there is a need for an adapter class which takes the JSON data retrieved by the meal repository and converts it into an list of MealDTO objects
The adapter class in this case is named MealJsonDTOAdapter and it exposes only one public method: convert which takes an array of JSON objects and returns a list of converted MealDTO objects
  
  ![Imgur Image](https://imgur.com/bwT0mlz.jpg)
  
  #### Builder design pattern
  The Builder pattern is one of the creational design patterns which helps in constructing complex objects step-by-step. Using the same code for construction, this pattern allows the production of different types of representation of an entity.
In the case of this application, there are a number of ways to calculate the Total Daily Energy Expenditure, each approach has its ups and downs and the differences are not that great.
Take for example the Harris-Benedict formula and the revised one, both are using the same principles but the multipliers are different. In this case, the code for the TDEE calculator was extracted into a separate interface and classes which have to implement it, an interface for the calculator builder was defined and each type of formula will have to implement its own concrete builder. For example: Harris- BenedictBuilder and HarrisBenedictRevisedBuilder which will build an object of type CaloricCalculatorInterface with their own multipliers. On top of that, another object named Director is created in order to establish what builder to use and start the building process.
![Imgur image](https://imgur.com/jDAvh3p.jpg)
  
  ### Algorithm performance
  The algorithm takes into account three different values:
  * The price rating of a meal recipe
  * The time rating of a meal recipe
  * The number of kilocalories of the recipes
  
  These three values are combined into a single one which is taken into account when comparing the recipes to see which one fits the criteria the best.
  Because of this, we can measure the algorithm performance in two main ways:
  * The diversity of the results (how many different meals are returned in N iterations)
  * The accuracy of the results (how close the nb of calories of the meal results are to the number of calories that we search for)
  
  Since there is a tradeoff between those two, I decided that it's best to let the user decide which one is most important to him (getting more diversified results ore getting more accurate ones). This can be done by tuning a multiplier that is used when computing the value described above for each meal. The range of the multiplier is between 5 to 10 and modifying it results in very different result sets. You can see below for each case how the results compare.
  
  #### 10000 iterations (each one with 10 suggestions) and 10x multiplier
  ![Imgur Image](https://imgur.com/SQhIi0y.jpg)
  
  #### 10000 iterations (each one with 10 suggestions) and 5x multiplier
  ![Imgur Image](https://imgur.com/xUyawpN.jpg)
  
    
  ### Read more...
  #### You can read more information about the project in the included .pdf file

</details>

## Contributing
First off all, thank you for your intiative!
For your local setup to work, you need to create a file "secrets.properties" in the same directory as "application.properties" and include the following values:
 * application.verification.url = for developing this would be the application context (localhost:8080/PrepIt/)
 * jwt.auth.secret.key = secret key used to encode/decode jwt token (for developing you can set it to whatever you want)
 * spring.email.username = an email address used to send the verification token when creating an account
 * spring.email.password = the password of the provided email address

The email address doesn't have to be a valid one if you're not planning on testing sending emails.
Also please feel free to comment on the issue or contact me if you're asking for support.

