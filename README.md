# my-shopping
A Malaysian e-commerce website

# How to setup:
Clone the project in your favorite IDE.

Run Application.java as Spring Boot App, for ease embedded Tomcat server has been used. To run the application on Jboss server, do a mvn clean install and copy the war file file <source directory>/targer/my-shopping.war into JBOSS deployments folder (change MySQL credentials accordingly)

Open swagger and browse through

http://localhost:8080/my-shopping/api/swagger-ui.html

user name : syedirfa33
password : admin@123


# What we will accomplish:

User login

Add new product

Edit exisitng product by ID

Delete a product by ID

Search multiple products by product name

Spring Security

JWT based authentication

spring-data-jpa and validations

# Future enhancements

Pagination support

Lucene and Hiberante Search impl for faster Product searching

User management endpoints

# class diagram

![alt text](https://github.com/syedirfan33/my-shopping/blob/master/classDiagram.png)

# Swagger sample

![alt text](https://github.com/syedirfan33/my-shopping/blob/master/swagger.png)



