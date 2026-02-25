package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

@GraphQLApi
public class GreetingResource {

    @Inject
    GreetingService service;

    @Query("allPersons")
    @Description("Get all persons")
    public List<Person> getAllPersons() {
        return service.getAllPersons();
    }

    @Query
    @Description("Get a specific person")
    public Person getPerson(@Name("personId") int id) {
        return service.getPerson(id);
    }

    public Address address(@Source Person person) {
        return service.getAddressFromPerson(person);
    }

}
