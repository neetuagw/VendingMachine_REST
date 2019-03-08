# API Documentation
### Allowed HTTP Requests :
GET     : Get a task or list of Tasks
POST    : Initialise Vending Machine and deposite User's coins

### Description Of Usual Server Responses:
-   200  `OK`  - the request was successful.
    
-   201  `Created`  - the request was successful and a resource was created.
    
-   400  `Bad Request`  - the request could not be understood or was missing required parameters
-   406  `Not Acceptable`  - resource was not found

### Home API

    http://localhost:8080/VendingMachine_RESTService/

## Reference

## Coin
**CoinAttributes:**
- fiveP`(Number)` : Store number of 5 pence coins
- tenP`(Number)` : Store number of 10 pence coins
- twentyP`(Number)` : Store number of 20 pence coins
- fiftyP`(Number)` : Store number of 50 pence coins
- one`(Number)` : Store number of 1 pound coins
- two`(Number)` : Store number of 2 pound coins
- itemCost`(Number)` : Cost of item bought by User (example if the cost of item is 1.25 pound then itemCost : 125)
- paidAmount`(Number)` : Amount paid by User (example if User pay 2 pound then paidAmount : 200)
- change`(Number)` : Change left after user payment

## Coin Collection

Retrieve coins available in Vending Machine
> GET  `http://localhost:8080/VendingMachine_RESTService/api`

## Initialise Vending Machine

> POST `http://localhost:8080/VendingMachine_RESTService/api/initialise`
> Parameters `null`
> Request Body

     `{
        	"fiveP":5,
		    "tenP":5,
		    "twentyP":5,
		    "fiftyP":5,
		    "one":5,
		    "two":5
        }`
> Response `201`

     `{
        	"coins":
	        	{
		        	"fiveP":5,
		        	"tenP":5,
		        	"twentyP":5,
		        	"fiftyP":5,
		        	"one":5,
		        	"two":5
	        	},
	        "message":"Successfully initialised"
        }`

## Deposite User coins

> POST `http://localhost:8080/VendingMachine_RESTService/api/deposite`
> Parameters `null`

> Request Body

     `{
		    "itemCost": 125,
		    "paidAmount": 200,
		    "two":1
        }`

> Response `200`

     `{
        	"coins":
	        	{
		        	"fiveP":1,
		        	"tenP":0,
		        	"twentyP":1,
		        	"fiftyP":1,
		        	"one":0,
		        	"two":0
	        	},
	        "message":"Successfull transaction! Here is your change"
        }`

> Response `400`
API will send the following response in case itemCost or PaidAmount is missing

     `{
        "error": "ItemCost and PaidAmount are mandatory and their values should be greater than 0"
      }`

> Response `406`
API will send the following response in case itemCost value is greater than paidAmount

     `{
        "error": "PaidAmount canot be less than Item's cost"
      }`
