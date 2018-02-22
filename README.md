Overview
--------

The user management web api Service is a microservice that can execute all CRUD methods to interact and maintain a simple user management
service. You can view users in a paginated manner, create user, update user and delete user. This can also
be deployed as a docker image and run in a docker container.


 
Technologies
------------
- Spring Boot - To launch the application in tomcat.
- Spring Data - Java Persistence to communicate with MYSQL.
- Spring Boot RS - To create REST End points.
- Spring Profiles - only the default profile is use but could have more to handle different environments
- Docker - A docker image has been included to run this service in a docker container that can be ship in the cloud

Source url
------------

    1. Source codes are stored in https://github.com/dondontamayo/userManagement

Local Development Setup
-----------------------
1. unzip file to your folder OR clone git@github.com:dondontamayo/userManagement.git
    - install mysql if you have not done so
    - modify your mysql url to point to your schema/database
    - create your table and modify your entity to map to it. 
    - Table columns should be exactly the same base on the POJO @Column annotation
2. Build the project: "mvn package"
3. Run the application:
    
    From Maven:
   
        mvn spring-boot:run

    From Intellij:
    
        Right click on Application class and "Run Application"
       
4. Using SOAP UI to test

   Create a SOAPUI Rest project, using the following endpoints:
   
       Method: GET
       Endpoint: http://localhost:8080/users?&page=0&size=2
       Resource: none
       
   The response will come back as per below:
	{
        "content": [
            {
                "id": 1,
                "userName": "userName1",
                "password": "pass1",
                "email": "name1@yahoo.com",
                "name": "name1"
            },
            {
                "id": 2,
                "userName": "userName1",
                "password": "pass1",
                "email": "name1@yahoo.com",
                "name": "name1"
            }
        ],
        "last": false,
        "totalPages": 7,
        "totalElements": 14,
        "size": 2,
        "number": 0,
        "sort": null,
        "first": true,
        "numberOfElements": 2
    }
    
        Method: GET
        Endpoint: http://localhost:8080/users/1
        Resource: {id}1
           
    The response will come back as per below:
        {
            "id": 1,
            "userName": "userName1",
            "password": "pass1",
            "email": "name1@yahoo.com",
            "name": "name1"
        }
        
        Method: PUT
        Endpoint: http://localhost:8080/users
        Resource: none

    The request will be as per below: This will create a new record
    alloting the next available id sequence:
        {
            "userName": "userName1",
            "password": "pass1",
            "email": "name1@yahoo.com",
            "name": "name1"
        }

    The response will come back as per below:
        {
            "id": 2,
            "userName": "userName1",
            "password": "pass1",
            "email": "name1@yahoo.com",
            "name": "name1"
        }
            
        Method: POST
        Endpoint: http://localhost:8080/users/2
        Resource: {id}2

    The request will be as per below: This will modify the specified resource
    base on the request body
        {
            "userName": "userName2",
            "password": "pass2",
            "email": "name2@yahoo.com",
            "name": "name2"
        }

    The response will come back as per below:
        {
            "id": 2,
            "userName": "userName2",
            "password": "pass2",
            "email": "name2@yahoo.com",
            "name": "name2"
        }

        Method: DELETE
        Endpoint: http://localhost:8080/users/2
        Resource: {id}2
    
    This will delete the resource specified by the id.
