package test;

import org.infinispan.protostream.ImmutableSerializationContext;
import org.infinispan.protostream.ProtobufUtil;
import org.infinispan.protostream.SerializationContext;

import java.io.IOException;
import java.io.StringReader;
import java.io.UncheckedIOException;

/**
 * Marshals {@link Person} instances to and from ProtoStream canonical JSON.
 */
public final class PersonMarshalling {

    private final ImmutableSerializationContext ctx;

    public PersonMarshalling() {
        SerializationContext ctx = ProtobufUtil.newSerializationContext();
        new PersonSchemaImpl().registerSchema(ctx);
        new PersonSchemaImpl().registerMarshallers(ctx);
        this.ctx = ctx;
    }

    public byte[] toByteArray(Person person) {
        try {
            return ProtobufUtil.toWrappedByteArray(ctx, person);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public String toString(Person person) {
        try {
            byte[] bytes = ProtobufUtil.toWrappedByteArray(ctx, person);
            return ProtobufUtil.toCanonicalJSON(ctx, bytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Person fromByteArray(byte [] bytes) {
        try {
            return (Person) ProtobufUtil.fromWrappedByteArray(ctx, bytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Person fromString(String json) {
        try {
            byte[] bytes = ProtobufUtil.fromCanonicalJSON(ctx, new StringReader(json));
            return (Person) ProtobufUtil.fromWrappedByteArray(ctx, bytes);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
