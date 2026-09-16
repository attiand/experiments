package common;

import org.infinispan.protostream.annotations.ProtoAdapter;
import org.infinispan.protostream.annotations.ProtoEnumValue;

public enum Gender {
    @ProtoEnumValue(number = 1)
    MALE("m"),
    @ProtoEnumValue(number = 2)
    FEMALE("f");

    private final String value;

    Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
