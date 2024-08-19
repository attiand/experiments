package test;

import java.io.Serializable;

public class PersonClass implements Serializable {

	private final String firstName;
	private final String lastName;

	public PersonClass(String firstName, String lastName) {
		System.out.println("PersonClass constructor");
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}
}
