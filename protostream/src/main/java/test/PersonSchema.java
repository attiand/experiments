package test;

import common.Gender;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.ProtoSchema;
import org.infinispan.protostream.annotations.ProtoSyntax;
import org.infinispan.protostream.types.java.CommonTypes;

/**
 * Compile-time generated schema and marshaller registration for {@link Person}.
 * The ProtoStream annotation processor produces {@code PersonSchemaImpl} from this interface.
 */
@ProtoSchema(
        includeClasses = {Person.class, Gender.class, ProtoStringSet.class},
        schemaFileName = "person.proto",
        schemaFilePath = "proto",
        schemaPackageName = "test",
        syntax = ProtoSyntax.PROTO3,
        dependsOn = CommonTypes.class
)
public interface PersonSchema extends GeneratedSchema {
}
