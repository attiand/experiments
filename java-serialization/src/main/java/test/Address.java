package test;

import java.io.Serializable;

public record Address(String street, String city) implements Serializable {
	public Address {
		System.out.println("Address constructor");
	}
}
