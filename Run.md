# Steps to Run Spring Boot User App
## 1. Maven Build the Project
If you have installed Maven on your machine then use the below command:
```
mvn clean package
```
If you haven't insatlled Maven on your machine then use below command:
```
./mvnw clean package
 ```
 Note: Go to root directory of the project and execute above command.
 ## 2. Create a Database
 Before running Spring boot user application, you need to create the MySQL database.
 
 Use the below SQL database to create the MySQL database:
 ```sql
 create database myuser
 ```
 Database name - myuser
 ## 3. Run Spring Boot Project
 Use below command to run Spring boot application:
 ```
 mvn spring-boot:run
 ```
 Once you run Spring boot application, Hibernate will create the database tables autimatically.
 However, you can refer to DDL scritp for all tables here:
 https://github.com/RameshMF/springboot-user-rest-api/blob/main/myuser-ddl-script.sql
 ## 4. Insert Data
User below Insert SQL statements to insert records into roles table:
```sql
INSERT INTO `intuit.roles` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER'),(3,'ROLE_GUEST');
```
Now, Spring boot user application is ready to use.
