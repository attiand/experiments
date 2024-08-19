package test;

import java.io.Serializable;

public record PersonRecord(String firstName, String lastName , Address address) implements Serializable {

	public PersonRecord {
		System.out.println("PersonRecord constructor");
	}
}
