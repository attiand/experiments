package test.records;

import java.util.Objects;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import test.Address;

public record PersonOmit(@XStreamOmitField String firstName, String lastName, Address address)  {

    public PersonOmit {
        Objects.requireNonNull(firstName);
        Objects.requireNonNull(lastName);
        Objects.requireNonNull(address);
    }

    public static PersonOmit of(String firstName, String lastName, Address address) {
        return new PersonOmit(firstName, lastName, address);
    }
}
