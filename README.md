![](https://github.com/kivilev/pizzeria-app/workflows/tests/badge.svg)

# Pizzeria application
This is a test task for Coherentsolutions.com (Spring &amp; Kotlin)

Description
Problem: The business has decided that it wants to expand into other industries and to open a pizzeria. After collaborating with multiple food supplier services in the area, we realized that we need to figure out which toppings customers would want so that we know what to order.

<details>
  <summary>Task details</summary>
Requirements:

1. Create an endpoint that allows for customers to submit their email address along with the list of toppings that they would be interested in.
2. Create an endpoint that allows for the front end team to grab the list of toppings currently submitted and the number of unique customers that have requested that topping.
3. Only the customer’s latest topping submission will be recorded.
4. Implementation must happen using Kotlin & Spring Boot.

Bonus points will be awarded for:
- Persisting data between runs
- Creativity for adding additional features
- A special endpoint for listing your personal topping choice!

</details>

## Task solution

This is a standard RestApi application based on the Spring framework that uses an RDBMS and Swagger documentation.

Technical stack: Kotlin, Spring Boot 3 (MVC, Data JPA, Validation, etc.), PostgreSQL 14, JUnit 5, Testcontainers, Docker-Compose.

