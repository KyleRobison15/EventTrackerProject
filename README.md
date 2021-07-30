# Event Tracker Project

### Full-Stack Spring/REST/JPA Project for Skill Distillery

## Project Overview
* TODO: description

## REST Endpoints
* Make a markdown table designing these in advance

| HTTP Verb | URI                  | Request Body | Response Body | Purpose |
|-----------|----------------------|--------------|---------------|---------|
| GET       | `/api/v1/books`      |              | Collection of representations of all _book_ resources | **List** or **collection** endpoint |
| GET       | `/api/v1/books/17`   |              | Representation of _book_ `17` | **Retrieve** endpoint |
| POST      | `/api/v1/books`      | Representation of a new _book_ resource | Description of the result of the operation | **Create** endpoint |
| PUT       | `/api/v1/books/17`   | Representation of a new version of _book_ `17` | | **Replace** endpoint |
| PATCH     | `/api/v1/books/17`   | Description of changes to make to _book_ `17` | | **Update** endpoint |
| DELETE    | `/api/v1/books/17`   |              | | **Delete** route |
| GET       | `/api/v1`            |              | Description of the API and its endpoints | **Index** endpoint |
