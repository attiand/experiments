import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import test.Address;
import test.PersonClass;
import test.PersonRecord;

class SerializationTest {

	@Test
	void shouldSerializeClass() throws IOException, ClassNotFoundException {
		Path fileName = Files.createTempFile("test", ".ser");
		try (FileOutputStream stream = new FileOutputStream(fileName.toFile(), false);
				ObjectOutputStream out = new ObjectOutputStream(stream)) {

			var person = new PersonClass("John", "Doe");
			out.writeObject(person);
		}

		try (FileInputStream stream = new FileInputStream(fileName.toFile()); ObjectInputStream in = new ObjectInputStream(stream)) {

			var person = (PersonClass) in.readObject();

			System.out.println(person.getFirstName());
		}
	}

	@Test
	void shouldSerializeRecord() throws IOException, ClassNotFoundException {
		Path fileName = Files.createTempFile("test", ".ser");
		try (FileOutputStream stream = new FileOutputStream(fileName.toFile(), false);
				ObjectOutputStream out = new ObjectOutputStream(stream)) {

			var person = new PersonRecord("John", "Doe", new Address("bakerstreet", "london"));
			out.writeObject(person);
		}

		try (FileInputStream stream = new FileInputStream(fileName.toFile()); ObjectInputStream in = new ObjectInputStream(stream)) {
			var person = (PersonRecord) in.readObject();
			System.out.println(person.firstName());
		}
	}
}
