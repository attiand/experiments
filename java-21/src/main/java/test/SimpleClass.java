package test;

public class SimpleClass {

	private final String myString;
	private final Short admissionNumber;

	public SimpleClass(String selElementDescription, Short admissionNumber) {
		this.myString = selElementDescription;
		this.admissionNumber = admissionNumber;
	}

	public String getMyString() {
		return myString;
	}

	public Short getAdmissionNumber() {
		return admissionNumber;
	}
}
