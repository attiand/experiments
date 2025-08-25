package test.records;

import java.util.Objects;

import test.Address;

public record PersonConverter(String firstName, String lastName, Address address)  {

    public PersonConverter {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(address);
    }

    public static PersonConverter of(String firstName, String lastName, Address address) {
        return new PersonConverter(firstName, lastName, address);
    }
}
