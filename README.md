# Vending Machine REST Service
This Application has implement a REST Services in Java using JAX-RS and Jersey for a Vending Machine which :

 1. Initialise the vending machine to a known state, for use when the machine is set up.
 2. Register coins that have been deposited by a user
 3. Produce a collection of coins as returning change to the user)
 4. and remove the coins from the machine.

**Technology Used**

Java 8 with JERSEY

Jersey is the open source, production quality, JAX-RS (JSR 311) Reference Implementation for building RESTful Web services

**Prerequisites**
Eclipse IDE

**Software version**
Maven Project Dynamic Web Module v3.0
 - Eclipse IDE 
 - Apache Tomcat v7.0
 - jersey 1.19.4 library
 - genson 1.3 library

**Libraries used in this Application**
 - asm.jar
 - jersey-bundle.jar
 - json.jar
 - jersey-server.jar
 - jersey-core.jar
 - genson.jar

**Classes and their functionality**

 - APIController.java : This class implements all the HTTP methods 
 - Coin.java : This is our entity class where we define coins attributes to store them in Vending Machine
 - VendingMachine.java: This is our interface class
 - VendingMachineImp.java: This class implements all the CRUD functions to be done on Vending machine like add coins, remove coins, update Vending Machine. It implements VendingMachine interface
 - CoinsInResponse and APIResponse : These classes used to send the right response of the API called
 - Repository : This class implements a TreeMap data structure to store the coins available in Vending Machine. This class designed to be used for Database purpose in future
 - APIControllerTest.java : This is a unit test class.
 
 ## Aprroach

 - Build REST service in Java using JAX-RS and Jersey. Java API for RESTful Web Services (JAX-RS), is a set of APIs to develope REST service. JAX-RS is part of the JavaEE6, and make developers to develop REST web application easily.
 - Used Genson library to consume and produce JSON data. It automaticallys converts your request into JSON and so is your response. 
 - Utilise TreeMap datastructure to store coins and to do CRUD operation on vending machine. The reason behind using TreeMap as it provides a direct method called floorkey() to find the greatest key less than or equal to given key. I used this method while calculating the coins of remaining amount left after user payment. 
 - Apache Tomcat server used to run the REST service. 

## Assumptions

 - Vending Machine will only accept 5p, 10p, 20p, 50p , 1£ and 2£ coins
 - Vending Machine items costs are rounded according to the coins it accepts. Price can only be multiples of 5. 
 -  This service can be extended to accept one and two pence.
 - Vending Machine will only provide the change in 5p, 10p, 20p, 50p , 1£ or 2£ coins.

## Instructions to run this application

 - Import this project as a Maven project in Eclipse IDE
 - In case you have a different version of the Apache Tomcat server, go to Project Properties>Project Facets click Runtime and select your Tomcat server. Apply and close
 - To run this service on Tomcat server, follow below steps:
	 - Right click your project click Maven > Update Project
	 - Right click your project click Run As > Maven clean
	 - click Run As > Maven install
	 - click Run As > Run on Server 
 - By default, this service will run the http://localhost:8080/VendingMachine_RESTService/ API
-  Please follow the APIdocumentation.md file for detail information of APIs

Note: This application only implements the REST Service server side

## Test the REST Service

To test this service , run the test class "APIControllerTest.Java" in src >
test
