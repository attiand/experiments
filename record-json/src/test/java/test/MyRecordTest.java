package test;

import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import org.junit.jupiter.api.Test;

class MyRecordTest {

	@Test
	void recordTest() {
		var config = new JsonbConfig().withNullValues(true);
		try(var jsonb = JsonbBuilder.create(config)) {
			MyRecord myRecord = new MyRecord(null, "name");
			String json = jsonb.toJson(myRecord);
			System.out.println(json);

			var my = jsonb.fromJson(json, MyRecord.class);

			System.out.println(my.name());
			System.out.println(my.value());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	void classTest() {
		var config = new JsonbConfig();//.withNullValues(true);
		try(var jsonb = JsonbBuilder.create(config)) {
			MyClass myClass = new MyClass("name", null);
			String json = jsonb.toJson(myClass);
			System.out.println(json);

			var my = jsonb.fromJson(json, MyClass.class);

			System.out.println(my.getName());
			System.out.println(my.getValue());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
