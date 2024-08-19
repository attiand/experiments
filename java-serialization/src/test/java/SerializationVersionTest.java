import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.Test;
import test.Address;
import test.PersonRecord;

class SerializationVersionTest {

	private static final Path FILE = Paths.get(System.getProperty("java.io.tmpdir"), "tmp.ser");

	@Test
	void shouldWriteRecord() throws IOException {
		try (FileOutputStream stream = new FileOutputStream(FILE.toFile(), false);
				ObjectOutputStream out = new ObjectOutputStream(stream)) {

			var person = new PersonRecord("John", "Doe", new Address("bakerstreet", "london"));
			out.writeObject(person);
		}
	}

	@Test
	void shouldReadRecordV2() throws IOException, ClassNotFoundException {
		try (FileInputStream stream = new FileInputStream(FILE.toFile()); ObjectInputStream in = new ObjectInputStream(stream)) {
			var person = (PersonRecord) in.readObject();
			System.out.println(person);
		}
	}
}
