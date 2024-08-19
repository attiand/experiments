package test;

public class Address {

	public String street;
	public int number;
	public String areaCode;

	public Address(String mystreet, int i, String code) {
		this.street = mystreet;
		this. number = i;
		this.areaCode = code;
	}

	public String street() {return street;}

	public int number() {return number;}

	public String areaCode() {return areaCode;}
}
