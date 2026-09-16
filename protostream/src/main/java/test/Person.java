package test;

import common.Gender;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;

/**
 * A simple ProtoStream-marshalled value object.
 */
public class Person {

    @ProtoField(number = 1)
    final String name;

    @ProtoField(number = 2, defaultValue = "0")
    final int age;

    private Map<Gender, IntList> genders;

    public Person(String name, int age, Map<Gender, IntList> genders) {
        this.name = name;
        this.age = age;
        this.genders = genders;
    }

    @ProtoFactory
    static Person createProto(String name, int age, Map<String, IntList> genders) {
        return new Person(name, age, genders.entrySet().stream().collect(toMap(entry -> Gender.valueOf(entry.getKey()), Map.Entry::getValue)));
    }

    @ProtoField(number = 3)
    Map<String, IntList> getGenders() {
        return genders.entrySet().stream()
                .collect(toMap(entry -> entry.getKey().name(), Map.Entry::getValue));
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
