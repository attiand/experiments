package test;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Wrapper message so a list of integers can be used as a protobuf map value.
 */
public final class IntList {
    private final List<Integer> values;


    @ProtoFactory
    public IntList(List<Integer> values) {
        this.values = values;
    }

    @ProtoField(number = 1, collectionImplementation = ArrayList.class)
    public List<Integer> values() {
        return values;
    }


}
