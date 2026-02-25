# GraphQl

## GraphQL UI   

The GraphQL UI can be accessed from http://localhost:8080/q/graphql-ui

## Get Schema

```bash
curl http://localhost:8080/graphql/schema.graphql
```

### Get all persons

```graoghql
query allPersons {
   allPersons {
      firstName
   }
}
``` 

### Specific person

```graoghql
query getPerson {
   person(personId: 1) {
      lastName
   }
}
```

### Address

```graoghql
query getAddressFromPerson {
    person(personId: 1) {
        street
    }
}
``` 
