package test;

import org.infinispan.protostream.annotations.ProtoField;

import java.util.HashSet;
import java.util.Set;

public record ProtoStringSet(@ProtoField(number = 1, collectionImplementation = HashSet.class) Set<String> values) {

}
