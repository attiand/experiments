package test.records;

import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamAlias;

import test.Address;

public record PersonAlias(@XStreamAlias("givenName") String firstName, String lastName, Address address)  {

    public PersonAlias {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(address);
    }

    public static PersonAlias of(String firstName, String lastName, Address address) {
        return new PersonAlias(firstName, lastName, address);
    }
}
