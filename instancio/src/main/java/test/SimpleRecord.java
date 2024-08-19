package test;

public record SimpleRecord(String firstName, String lastName) {

	public SimpleRecord {
		System.out.println("init");
	}
}
