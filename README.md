# Event Tracker Project

### Full-Stack Spring/REST/JPA Project for Skill Distillery

## Project Overview
* TODO: description

## REST Endpoints
* Make a markdown table designing these in advance

| HTTP Verb | URI                                 | Request Body                           | Response Body                        | Purpose                          |
|-----------|-------------------------------------|----------------------------------------|--------------------------------------|----------------------------------|
| GET       | `/api/reqs`                         |                                        | List of all orders                   | Get all orders                   |
| GET       | `/api/reqs/{id}`                    |                                        | Representation of a single order     | Get order by ID                  |   
| GET       | `/api/products/{id}/reqs`           |                                        | List of orders                       | Get orders by product            |
| GET       | `/api/customers/{id}/reqs`          |                                        | List of orders                       | Get orders by customer           |
| POST      | `/api/reqs`                         | Representation of an order             | Representation of created order      | Create a new order               |
| PUT       | `/api/reqs`                         | Representation of an order             | Representation of updated order      | Update or replace an order       |
| DELETE    | `/api/reqs/{id}`                    |                                        | Boolean - isDeleted                  | Delete an order                  |


## CRUD Operations Needed

## Requisition:
* Get all orders
* Get order by ID
* Get orders by product
* Get orders by customer
* Req create
* Req update
* Req delete

## Product:
* Get all products
* Get product by ID
* Get products by price range
* Get products by name keyword search
* Product create
* Product update
* Product delete

## Customer:
* Get all customers
* Get customer by ID
* Find customer by First Name, Last Name keyword search
* Find customer by phone
* Find customer by email
* Find customer by address keyword search (Street, City, State, Zip)
* Customer create
* Customer update
* Customer delete
