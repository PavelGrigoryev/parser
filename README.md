# Spring 2.7.4 Parser

## Author: [Grigoryev Pavel](https://pavelgrigoryev.github.io/GrigoryevPavel/)

# [Preview swagger](src/main/resources/static/preview1.png)

# [Preview project structure](src/main/resources/static/preview2.png)

# [Preview project logs](src/main/resources/static/preview3.png)

# [Video](https://youtu.be/SkCNhmGRQfw)

### Технологии, которые я использовал на проекте:

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

- Написал CRUD приложение, где продукты парсились с сайта магазина и сохранялись в Mongodb
- [MongodbCompass](src/main/resources/static/mongodbcompass.png),
- [Mongodb Intellij Idea](src/main/resources/static/mongoIdea.png)
- Созданы модель document Product, репозиторий ProductRepository, сервис ProductService,
  имплементация сервиса ProductServiceImpl, парсер ProductParser и контроллер ProductController
- Парсер первоначально работал через Scheduled
- Парсил через Jsoup, парсер пробегался по страницам магазина и сохранял в Mongodb
  все товары (имя, производитель, количество, цена) и дополнительно (id, LocalDateTime)
- Для всех методов прописал логи через Slf4j
- [Logs](src/main/resources/static/logs.png)
- Для Product создал ProductDto
- Для конвертации Product из Entity в Dto и обратно мною был написан ProductMapper
- Когда продукт не находился в бд по id, выбрасывалось исключение NoSuchProductException.
  Которое я прописал. Так же для отлова исключений мною был создан ProductExceptionHandler и IncorrectProductData
- Так же для автоинкремента Id для Mongodb написаны модель DatabaseSequence, сервис SequenceGeneratorService
  и листенер ProductModelListener
- [MongodbSequence](src/main/resources/static/mongodbsequence.png)

### 2.

- Добавил OpenApi в проект
- Добавил Swagger annotation на все контроллеры
- Столкнулся с проблемой вывода в swagger-ui (ендпоинты выводились не в том порядке как я хотел,
  регистрация была в самом низу), написал конфиг SwaggerSortConfig для вывода в отсортированном по тагам виде
- [Before](src/main/resources/static/beforeswaggerconfig.png),
- [After](src/main/resources/static/afterswaggerconfig.png)
- Добавил пагинацию для метода findAll() в ProductController
- [Pagination](src/main/resources/static/pagination.png)

### 3.

- Добавил Spring Security и Jsonwebtoken, где зарегистрированный юзер сохранялся
  во вторую базу данных MySQL
- Создал модель User, репозиторий UserRepository, сервис UserService, имплементацию сервиса
  UserServiceImpl и UserController
- Для аутентификации создал сервис JwtUserDetailsService, фильтр JwtRequestFilter, конфиг WebSecurityConfig,
  токен JwtTokenUtil, сервис AuthenticationService, имплементацию сервиса AuthenticationServiceImpl и
  контроллер AuthenticationController
- Через AuthenticationController юзер регистрировался либо логинился, password сохранялся в бд
  зашифрованным BCryptPasswordEncoder
- [BCryptPassword Intellij Idea](src/main/resources/static/bcryptpasswordidea.png),
- [BCryptPassword Workbench](src/main/resources/static/bcryptpasswordworkbeanch.png)
- Токен действовал 30 минута, дата истечения логировалась и выводилась в теле ответа
- [SwaggerToken](src/main/resources/static/swaggertoken.png),
- [LoggerToken](src/main/resources/static/loggertoken.png)
- Для разнообразия использую Postman
- [Register](src/main/resources/static/postmanregister.png)
- [FindById](src/main/resources/static/postmanfindbyid.png)
- [FindByName](src/main/resources/static/postmanfindbyname.png)
- Через UserController можно было проверить зарегистрированного юзера с токеном, так же удалить
  свои данные либо изменить их
- Если удалялись свои данные, то нужно было логиниться либо регистрироваться
- Если данные изменялись, то пользователь с изменёнными данными получал новый токен
- [Swagger User Update](src/main/resources/static/swaggeruserupdate.png),
- [Logs User Update](src/main/resources/static/ideauserupdate.png)
- В сваггере поставил ограничение допуска, без токена юзер не получал возможность пользоваться парсером и другими
  функциями. Только логин и регистрация
- [Swagger Defence](src/main/resources/static/swaggerdefence.png),
- [Bearer](src/main/resources/static/bearer.png)

### 4.

- Убрал Scheduled, добавил ParserController
- Добавил возможность парсить продукты со страниц каждого из 9 отделов через ендпоинт ParserController
- Был добавлен вспомогательный класс для парсера ProductDepartmentParser через него выбирался отдел для парсинга от 1 до
  9
- Вместо 18 Value, были созданы два POJO NumberOfParsablePages и ParsableUrl с ConfigurationProperties.
  Через них передавался url и количество страниц для парсинга в ProductDepartmentParser
- Добавил NoSuchDepartmentException, если вводилось другая цифра вместо 1 - 9.
  Так же добавил её в ProductExceptionHandler

### 5.

- Добавил валидацию для юзера
- Ник, имя и фамилия обрабатывались через аннотацию Pattern, который позволял вводить только буквы
  английского и русского алфавита в любом регистре без пробелов
- Емеил через аннотацию Email
- Добавил UserWithThisNickNameIsAlreadyExistsException, если юзер вводил ник уже зарегистрированного юзера.
  Так же добавил её в ProductExceptionHandler
- Для логирования, вывода в теле ответа и отлова валидационных исключений - написал
  ValidationExceptionHandler и два вспомогательных класса Violation и ValidationErrorResponse.
  В этот раз использовал рекорды
- [Violation](src/main/resources/static/swaggervalidation.png)

### 6.

- Добавил миграцию баз данных Liquibase для юзера в MySQL
- [Liquibase Logs](src/main/resources/static/liquibaselogs.png),
- [Liquibase db.changelog](src/main/resources/static/liqubasechangelog.png)
