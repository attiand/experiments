# GraphQL

GraphQL example in Quarkus. Uses a dynamic GraphQL client for test.

## GraphQL UI   

The GraphQL UI can be accessed from http://localhost:8080/q/graphql-ui

## Get the GraphQL schema

```bash
curl http://localhost:8080/graphql/schema.graphql
```

### Get all persons

```graphql
query all {
   persons {
      firstName
   }
}
``` 

### Specific person

```graphql
query no1 {
   person(personId: 1) {
      lastName
   }
}
```

### Address

```graphql
query street {
    p1: person(personId: 1) {
       address {
          street
       }
   }
}
```

### Address (Batch)

```graphql
query streets {
    persons {
       address {
          street
       }
   }
}
```

