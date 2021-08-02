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
| GET       | `/api/products`                     |                                        | List of all products                 | Get all products                 |
| GET       | `/api/products/{id}`                |                                        | Representation of a single product   | Get product by ID                |
| POST      | `/api/products`                     | Representation of a product            | Representation of created product    | Create a new product             |
| PUT       | `/api/products`                     | Representation of a product            | Representation of updated product    | Update or replace a product      |
| DELETE    | `/api/products/{id}`                |                                        | Boolean - isDeleted                  | Delete a product                 |
| GET       | `/api/customers`                    |                                        | List of all customers                | Get all customers                |
| GET       | `/api/customers/{id}`               |                                        | Representation of a single customer  | Get customer by ID               |
| POST      | `/api/customers`                    | Representation of a customer           | Representation of created customer   | Create a new customer            |
| PUT       | `/api/customers`                    | Representation of a customer           | Representation of updated customer   | Update or replace a customer     |
| DELETE    | `/api/customers/{id}`               |                                        | Boolean - isDeleted                  | Delete a customer                |

## CRUD Operations Needed

## Customer:
* Find customer by First Name, Last Name keyword search
* Find customer by phone
* Find customer by email
* Find customer by address keyword search (Street, City, State, Zip)
