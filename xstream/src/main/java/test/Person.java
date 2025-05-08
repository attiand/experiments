package test;

import java.util.Objects;

public record Person(String firstName, String lastName, Address address)  {

    public Person {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(address);
    }

    public static Person of(String firstName, String lastName, Address address) {
        return new Person(firstName, lastName, address);
    }
}
