package test;

public class Pojo {

	private final String givenName;
	private final String lastName;

	public Pojo(String givenName, String lastName) {
		this.givenName = givenName;
		this.lastName = lastName;
	}

	public String getGivenName() {
		return givenName;
	}

	public String getLastName() {
		return lastName;
	}
}
