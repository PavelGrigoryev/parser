# Spring 2.7.4 Parser

## Author: [Grigoryev Pavel](https://pavelgrigoryev.github.io/GrigoryevPavel/)

# [Preview swagger](src/main/resources/static/preview1.png)

# [Preview project structure](src/main/resources/static/preview2.png)

# [Preview project logs](src/main/resources/static/preview3.png)

# [Video](https://youtu.be/SkCNhmGRQfw)

### Technologies that I used on the project:

* Maven
* Spring Web
* Spring Data Jpa
* Spring Security
* Spring Validation
* Jsonwebtoken
* Lombok
* MySQL
* Mongodb
* Liquibase
* Jsoup
* Openapi
* Spring Configuration Processor
* Junit Jupiter Tests

### 1.

- I wrote a CRUD application where products were parsed from the store's website and saved in Mongodb
- [MongodbCompass](src/main/resources/static/mongodbcompass.png),
- [Mongodb Intellij Idea](src/main/resources/static/mongoIdea.png)
- Created document model - Product,repository - ProductRepository, service - ProductService,
  service implementation - ProductServiceImpl, parser - ProductParser  and controller - ProductController 
- The parser originally worked via Scheduled
- Parsed through Jsoup, the parser ran through the pages of the store and saved in Mongodb
  all products (name, manufacturer, amount, price) and additionally (id, LocalDateTime)
- For all methods, I prescribed logs via Slf4j
- [Logs](src/main/resources/static/logs.png)
- For Product created ProductDto
- To convert Product from Entity to Dto and back, I wrote ProductMapper
- When the product was not found in the database by id, a NoSuchProductException exception was thrown.
  Which I prescribed. I also created ProductExceptionHandler and IncorrectProductData to catch exceptions
- Also, for the auto-increment of the ID for Mongodb, model - DatabaseSequence, service - SequenceGeneratorService 
  and listener - ProductModelListener were written
- [MongodbSequence](src/main/resources/static/mongodbsequence.png)

### 2.

- Added OpenAPI to the project
- Added Swagger annotation to all controllers
- Faced with the problem of output in swagger-ui (the endpoints were not output in the order I wanted,
  registration was at the very bottom), I wrote config - SwaggerSortConfig  for output sorted by tags
- [Before](src/main/resources/static/beforeswaggerconfig.png),
- [After](src/main/resources/static/afterswaggerconfig.png)
- Added pagination for the findAll() method in ProductController
- [Pagination](src/main/resources/static/pagination.png)

### 3.

- Added Spring Security and Jsonwebtoken, where the registered user was saved
  to the second database - MySQL
- Created model - User, repository - UserRepository, service - UserService, service implementation -
  UserServiceImpl and controller - UserController
- For authentication, I created service - JwtUserDetailsService, filter - JwtRequestFilter, config - WebSecurityConfig,
  token - JwtTokenUtil, service - AuthenticationService, service implementation - AuthenticationServiceImpl and
  controller - AuthenticationController 
- Through the AuthenticationController, the user was registered or logged in, the password was saved in the database
  encrypted by BCryptPasswordEncode
- [BCryptPassword Intellij Idea](src/main/resources/static/bcryptpasswordidea.png),
- [BCryptPassword Workbench](src/main/resources/static/bcryptpasswordworkbeanch.png)
- The token was valid for 30 minutes, the expiration date was logged and displayed in the response body
- [SwaggerToken](src/main/resources/static/swaggertoken.png),
- [LoggerToken](src/main/resources/static/loggertoken.png)
- [ExpiredToken](src/main/resources/static/expiredtoken.png)
- I use Postman for a change
- [Register](src/main/resources/static/postmanregister.png)
- [FindById](src/main/resources/static/postmanfindbyid.png)
- [FindByName](src/main/resources/static/postmanfindbyname.png)
- Through the UserController, it was possible to check a registered user with a token, as well as delete
  their data or change them
- If your data was deleted, then you had to log in or register
- If the data was changed, then the user with the changed data received a new token
- [Swagger User Update](src/main/resources/static/swaggeruserupdate.png),
- [Logs User Update](src/main/resources/static/ideauserupdate.png)
- In the swagger, I set a tolerance limit, without a token, the user did not get the opportunity to use the parser and other
  functions. Login and registration only
- [Swagger Defence](src/main/resources/static/swaggerdefence.png),
- [Bearer](src/main/resources/static/bearer.png)

### 4.

- Removed Scheduled, added controller - ParserController
- Added the ability to parse products from the pages of each of the 9 departments through the ParserController endpoint
- A supportive class was added for the ProductDepartmentParser parser through which the department for parsing from 1 to
  9 was selected
- Instead of 18 Value, two POJO NumberOfParsablePages and ParsableUrl with ConfigurationProperties were created.
  The url and the number of pages for parsing in ProductDepartmentParser were passed through them
- Added NoSuchDepartmentException if another digit was entered instead of 1-9.
  I also added it to ProductExceptionHandler

### 5.

- Added validation for the user
- Nickname, first name and last name were processed through the annotation Pattern, which allowed you to enter only letters
  of the English and Russian alphabet in any case without spaces
- Email via Email annotation
- Added a UserWithThisNickNameIsAlreadyExistsException if the user entered the nickname of an already registered user.
  I also added it to ProductExceptionHandler
- For logging, output in the response body and catching validation exceptions - I wrote
  ValidationExceptionHandler and two supportive classes Violation and ValidationErrorResponse.
  This time I used records
- [Violation](src/main/resources/static/swaggervalidation.png)

### 6.

- Added migration of Liquibase databases for the user to MySQL
- [Liquibase Logs](src/main/resources/static/liquibaselogs.png),
- [Liquibase db.changelog](src/main/resources/static/liqubasechangelog.png)
