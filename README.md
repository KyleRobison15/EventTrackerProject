# Event Tracker Project

### Full-Stack Spring/REST/JPA Project for Skill Distillery

## Project Overview
This application is meant to help a baking business called "Mama Gates Bakes" keep track of orders. Mama Gates Bakes is independently owned by Susan Gates, who churns out delicious baked goods and delivers them from her house! Given that Susan does everything herself, she needed a way of keeping track of orders on a daily basis so she can stay organized and deliver items on time, to the right place. This application will help her do just that by keeping track of what products Susan has to offer, what customers are purchasing those products, and when and where those orders need to be delivered. As a bonus, the application will be a great way of tracking financial data for the business as well!

## REST Endpoints

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
| GET       | `/api/customers/name/search/{name}` |                                        | List of customers                    | Search customers by name         |
| GET       | `/api/customers/email/search/{email}` |                                      | List of customers                    | Search customers by email        |
| GET       | `/api/customers/phone/search/{phone}` |                                      | List of customers                    | Search customers by phone        |
| GET       | `/api/customers/address/search/{keyword}`|                                   | List of customers                    | Search customers by address      |
| POST      | `/api/customers`                    | Representation of a customer           | Representation of created customer   | Create a new customer            |
| PUT       | `/api/customers`                    | Representation of a customer           | Representation of updated customer   | Update or replace a customer     |
| DELETE    | `/api/customers/{id}`               |                                        | Boolean - isDeleted                  | Delete a customer                |
