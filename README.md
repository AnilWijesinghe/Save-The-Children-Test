# Read Me Template

![Project Image](project-image-url)

> This is a ReadMe template.

---

### Table of Contents
You're sections headers will be used to reference location of destination.

- [Description](#description)
- [How To Use](#how-to-use)
- [Author Info](#author-info)

---

## Description

Building and deployement instructions of practical test Java developer in Save The Childen UK.

#### Technologies

- Java 8
- Maven
- Spring boot 2.4.3
- Tomcat (Spring boot embedded)
- Hibernate
- H2 in Memory database
- JUnit4

[Back To The Top](#read-me-template)

---

## How To Use

#### Installation

Method 1 :

Run as Spring boot Project

Method 2 :

Generate JAR and run (java -jar filename.jar)


#### API Reference

```html    
    <p>
     Basic Auth included for both API:
           User Name - admin
           Password - password
    </p>

    <p>
     1st API : Register User - save user and returned encrypted details

            URL - http://localhost:8080/api/v1/user
            Method Type - POST
            Sample Request Body - 
            {
                "title" : "Mr",
                "firstName": "Anil",
              "lastName" : "Anjuna",
                "addressLine1": "No 07",
              "addressLine2" : "Rothwell Steet",
                "postCode": "BL3 6HY",
              "city" : "Bolton",
                "country": "United Kingdom",
                "identityNumber": "903153806V"
            }

            Sample Response Body -

            {
                "status": "Success",
                "code": "1001",
                "message": "User has been registered successfully.",
                "messageDescription": null,
                "user": {
                    "id": 1,
                    "title": "KLsztTihyN4Hvld17v424w==",
                    "firstName": "1Mxs3qS0pDq7HDdcjg6IMQ==",
                    "lastName": "7LMYJcplKAZAh5ODdQnBSA==",
                    "addressLine1": "JXC70oUoP5XyxwB0K/eYUA==",
                    "addressLine2": "ggjrrgAmGBHF2POLce3igQ==",
                    "postCode": "gd3MUWNg7qK4I1cTMVfkfA==",
                    "city": "d3ggK7NPVb0syXhlzE88tQ==",
                    "country": "k5MTpLGSUAfTuO4T/p8fvQ==",
                    "identityNumber": "KA7nJt50Jovg9U9RVnQGjw=="
                }
            }
    </p>

    <p>
     2nd API - Get user by id - Return decrypted User details

            URL - http://localhost:8080/api/v1/user/info
            Method Type - GET
            Request Parameters :
                name - id
                value - input saved customer id
            Request Body - empty

            Sample Response Body -

            {
                "status": "Success",
                "code": "1004",
                "message": "User information has been retrieved successfully",
                "messageDescription": null,
                "user": {
                    "id": 1,
                    "title": "Mr",
                    "firstName": "Anil",
                    "lastName": "Anjuna",
                    "addressLine1": "No 07",
                    "addressLine2": "Rothwell Steet",
                    "postCode": "BL3 6HY",
                    "city": "Bolton",
                    "country": "United Kingdom",
                    "identityNumber": "903153806V"
                }
            }
    </p>
```
[Back To The Top](#read-me-template)

---

## Author Info

- LinkedIn - [@anilanjuna](https://www.linkedin.com/in/anil-anjuna-wijesinghe-76325211a/)

[Back To The Top](#read-me-template)


