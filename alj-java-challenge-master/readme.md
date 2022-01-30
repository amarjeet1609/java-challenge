### How to use this spring-boot project

- Install packages with `mvn package`
- Run `mvn spring-boot:run` for starting the application (or use your IDE)

Application (with the embedded H2 database) is ready to be used ! You can access the url below for testing it :

- Swagger UI : http://localhost:8082/swagger-ui.html
- H2 UI : http://localhost:8082/h2-console

> Don't forget to set the `JDBC URL` value as `jdbc:h2:mem:testdb` for H2 UI.



### Instructions

- download the zip file of this project
- create a repository in your own github named 'java-challenge'
- clone your repository in a folder on your machine
- extract the zip file in this folder
- commit and push

- Enhance the code in any ways you can see, you are free! Some possibilities:
  - Add tests
  - Change syntax
  - Protect controller end points
  - Add caching logic for database calls
  - Improve doc and comments
  - Fix any bug you might find
- Edit readme.md and add any comments. It can be about what you did, what you would have done if you had more time, etc.
- Send us the link of your repository.

#### Restrictions
- use java 8


#### What we will look for
- Readability of your code
- Documentation
- Comments in your code 
- Appropriate usage of spring boot
- Appropriate usage of packages
- Is the application running as expected
- No performance issues

#### Your experience in Java

Please let us know more about your Java experience in a few sentences.
- I have over 4 years experience in Java and I started to use Spring Boot from past 2 years
- I have hands on experience in java technologies like Rest API, Soap Services, MicroServices, JMS, etc. 


### Updated Source code by Amarjeet ###
- Test has been implemented using Junit and Mockito
- Syntax has been modified as required
- JWT Bearer Token has been implemented
- Caching logic has been added and cached the data for better performance.
- Readme.md file has been modified and Comments/Logs has been added in the Source code.
- Bugs has been fixed when found.

### Instruction to start the Application ###
- Tomcat Port has been changed to 8082, So please change the port while starting Swagger/H2 while testing.
- Use User/pass => testuser/testpassword as authentication credential to generate Bearer Token. 
  Authorize your endpoints by clicking Authenticate button in Swagger UI and pass the API Key : Bearer <generated token>

 