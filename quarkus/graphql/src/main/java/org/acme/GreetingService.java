package org.acme;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class GreetingService {

    private final List<Person> persons = List.of(new Person(1, "John", "Doe"), new Person(2, "Bruce", "Smith"));
    private final List<Address> address = List.of(new Address(1,"First Street", "Smalltown"), new Address(2,"Maple Avenue", "new york"));

    public GreetingService() {
    }

    public List<Person> getAllPersons() {
        return persons;
    }

    public Person getPerson(int id) {
        return persons.stream().filter(p -> p.id() == id).findFirst().orElse(null);
    }

    public Address getAddressFromPerson(Person person) {
        return address.stream().filter(a -> a.personId() == person.id()).findFirst().orElse(null);
    }
}
