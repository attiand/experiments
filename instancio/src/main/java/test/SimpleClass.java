package test;

import java.util.Objects;

public class SimpleClass {

	private String firstName;
	private String lastName;

	public SimpleClass(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public String getLastName() {
		return this.lastName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SimpleClass that = (SimpleClass) o;
		return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName);
	}

	@Override
	public String toString() {
		return "SimpleClass{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + '}';
	}
}
