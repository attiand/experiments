package test;

import common.Gender;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

/**
 * A simple ProtoStream-marshalled value object.
 */
public class Person {

    @ProtoField(number = 1)
    final String name;

    @ProtoField(number = 2, defaultValue = "0")
    final int age;

    @ProtoField(number = 4)
    final LocalDateTime timeStamp;

    private Map<Gender, Set<String>> genders;

    public Person(String name, int age, LocalDateTime timeStamp, Map<Gender, Set<String>> genders) {
        this.name = name;
        this.age = age;
        this.timeStamp = timeStamp;
        this.genders = genders;
    }

    @ProtoFactory
    static Person createProto(String name, int age, LocalDateTime timeStamp, Map<String, ProtoStringSet> gendersProto) {
        return new Person(name, age, timeStamp, gendersProto.entrySet().stream().collect(toMap(entry -> Gender.valueOf(entry.getKey()), entry -> entry.getValue().values())));
    }

    @ProtoField(number = 3, name = "genders")
    Map<String, ProtoStringSet> getGendersProto() {
        return genders.entrySet().stream()
                .collect(toMap(entry -> entry.getKey().name(), entry -> new ProtoStringSet(entry.getValue())));
    }

    public Map<Gender, Set<String>> getGenders() {
        return genders;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }
}
