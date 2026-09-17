package test.mytest;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.ProtoSchema;
import org.infinispan.protostream.types.java.CommonTypes;

@ProtoSchema(includeClasses = { MyProtoStreamSession.class }, dependsOn = CommonTypes.class)
public interface MyProtoStreamSessionSchema extends GeneratedSchema {
}
