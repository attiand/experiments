package org.acme.serverside;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class GreetingService {

    private final List<Person> persons = List.of(new Person(1, "John", "Doe"), new Person(2, "Bruce", "Smith"));
    private final Map<Integer, Address> address = Map.of(1, new Address("First Street", "Smalltown"), 2, new Address("Maple Avenue", "new york"));

    public GreetingService() {
    }

    public List<Person> getAllPersons() {
        return persons;
    }

    public Person getPerson(int id) {
        return persons.stream().filter(p -> p.id() == id).findFirst().orElse(null);
    }

    public Address getAddressFromPerson(Person person) {
        return address.get(person.id());
    }

    public List<Address> getAddressFromPersonList(List<Person> persons) {
        return persons.stream().map(p -> address.get(p.id())).collect(Collectors.toList());
    }
}
