# GraphQL - The Big Picture

Pluralsight course by Adhithi Ravichandran

www.adhithiravichandran.com

GraphQL - a query for your API

* Requests allow specifying the fields you want to get back
* Get all the data you need in a single request
* Query language, programming language agnostic

Caching is not natively available like in REST

## Schema

Types

Scalars

* Int (signed 32-bit integer)
* Float
* String
* Boolean
* ID

Object - type with fields

enum - set of possible values

Schemas - consists of types, queries, mutations, subscriptions

type Query {
  allCourses: [Course]
}

type Mutation {
  createCourse(input: CourseInput): Course
}

Non-nullable fields - use the exclamation mark

type Course {
  id: ID!
  title: String!
}

## Queries

GraphiQL - in-browser IDE for writing, validating, and testing GraphQL queries.

Examples: used GitHub GraphQL API at https://developer.github.com/v4/explorer/

Aliases - to rename fields in queries - prefix with a colon



{
    viewer {
        login
        bio
        id
        name
        firstFollowers: followers (fist: 3) {
            nodes {
                id
                bio
            }
        }
        lastFollowers: followers (last: 3) {
            nodes {
                id
                bio
            }
        }
    }
}

Fragments - reusable pieces of query

build sets of fields and reuse them in multiple queries

frage userFields on User {
    id
    login
    bio
}

{
    viewer {
        ...userFields
        firstFollowers: followers (fist: 3) {
            nodes {
                ...userFields
            }
        }
        lastFollowers: followers (last: 3) {
            nodes {
                ...userFields
            }
        }
    }
}

operation name - name the query

query GetViewer {
    viewer {
        ...userFields
        firstFollowers: followers (fist: 3) {
            nodes {
                ...userFields
            }
        }
        lastFollowers: followers (last: 3) {
            nodes {
                ...userFields
            }
        }
    }
}

variables - pass arguments to queries

query GetViewer($first: Int = 3, $last: Int = 3) {
    viewer {
        ...userFields
        firstFollowers: followers (fist: $first) {
            nodes {
                ...userFields
            }
        }
        lastFollowers: followers (last: $last) {
            nodes {
                ...userFields
            }
        }
    }
}

{
    "first": 3,
    "last": 3
}

## Mutations

* Mutations are used to make changes to data
* GraphQL assumes side-effects after mutations and changes the dataset
after a mutation is executed
* While query fields are executed in parallel, mutation fields are executed in series

mutation NewStatus($input: ChangeUserStatusInput!) {
    changeUserStatus(input: $input) {
        clientMutationId
        status {
            message
        }
    }
}

{
    "input": {
        "clientMutationId" : "123",
        "message": "Hello from GraphQL"
    }
}

## Tools 

Clients

* Handle sending queries to the server receiving responses
* Integrates with your view components
* Caching query results
* Error handling and schema validation
* Local state management and caching
* Pagination

Popular Clients

* Apollo Client
* Relay

Server

* Schema and resolver functions (resolvers are functions that resolves
a value for a type/field in the schema)
* Network layer

GraphQL Execution Engine

* Can do optimization on the server side by batch resolving to avoid redundant calls
* Apollo Server
* Express GraphQL
* GraphQL Yoga

Database to GraphQL Server

* Prisma
    * Bridges the gap between the resolver and the database
    * Data access layer for your GraphQL server
    * Type-safe database access
    * GraphQL subscriptions

## IDE

GraphiQL
GraphQL voyager
GraphQL Faker

# Building a GraphQL API with Apollo Server

Jonathan Mills

Apollo - node js server for GraphQL

Apollo playgroud - in-browser IDE for writing, validating, and testing GraphQL queries

Introspection - query the schema

All interactions with the endpoint are POST requests


Resolving queries

* Resolver  - function for popoulating the data for a single field in your schema
* Resolver map - object that maps the schema to the resolver functions
* Executing queries
* Schema directives
* Implementing datasources
* Query filtering

Filtering

* Add it to the schema - expect something to be passed in, and return a filtered list

```graphql
query {
  sessionById(id:"84473") {
    id
    title
    room
    track
    level
  }
}
```
General filter pattern:

* Include all the attributes as query args
* Pass the args from the resolver to the data source
* Filter the data source based on the args

```graphql
query {
  sessionById(id:"84473") {
    title
    room
    track
    id
    level
    description
  }
  sessions(room:"Europa", track:".NET") {
    title
    room
    track
    id
    level
  }
}
```

Nesting Data

* You can use nested resolver maps

REST

* Apollo has a RESTDataSource class that you can extend to create a data source for your REST API

```javascript
import { RESTDataSource } from 'apollo-datasource-rest';
```

For a nesting example, think about the sessions example - we can get a list of the session - what if we want to nest some speaker data to
provide speaker details along with the session?

* Add a speakers array attribute to the session type
* Resolver has session as the parent

