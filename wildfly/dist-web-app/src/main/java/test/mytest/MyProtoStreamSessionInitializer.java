package test.mytest;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.ProtoSchema;

@ProtoSchema(includeClasses = { MyProtoStreamSession.class })
public interface MyProtoStreamSessionInitializer extends GeneratedSchema {
}
