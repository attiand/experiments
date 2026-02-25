package org.acme.serverside;

import jakarta.inject.Inject;
import org.eclipse.microprofile.graphql.*;

import java.util.List;

@GraphQLApi
public class GreetingResource {

    @Inject
    GreetingService service;

    @Query("persons")
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

    public List<Address> address(@Source List<Person> persons) {
        return service.getAddressFromPersonList(persons);
    }
}
