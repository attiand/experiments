package test;

public class Mixed {

	private Person person;

	private PersonClazz personClazz;

	public Mixed(Person person, PersonClazz personClazz) {
		this.person = person;
		this.personClazz = personClazz;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public PersonClazz getPersonClazz() {
		return personClazz;
	}

	public void setPersonClazz(PersonClazz personClazz) {
		this.personClazz = personClazz;
	}
}
