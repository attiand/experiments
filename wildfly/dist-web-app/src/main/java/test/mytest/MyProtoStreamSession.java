package test.mytest;

import jakarta.enterprise.context.SessionScoped;
import org.infinispan.protostream.annotations.ProtoField;
import org.infinispan.protostream.descriptors.Type;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;

@SessionScoped
public class MyProtoStreamSession implements Serializable {

    @ProtoField(number = 1, type = Type.UINT32, defaultValue = "0")
    int counter = 0;

    @ProtoField(number = 2)
    LocalDateTime timestamp;

    public void setRequestCount(int n) {
        this.counter = n;
    }

    public int getRequestCount() {
        return this.counter;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    // prof that java serialization is not used
    private void writeObject(ObjectOutputStream oos)
            throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(counter);
        LoggerFactory.getLogger(MyProtoStreamSession.class).warn("writeObject use Serializable");
    }

    // prof that java serialization is not used
    private void readObject(ObjectInputStream ois)
            throws ClassNotFoundException, IOException {
        LoggerFactory.getLogger(MyProtoStreamSession.class).warn("readObject use Serializable");
        ois.defaultReadObject();
        Integer counter = (Integer) ois.readObject();
        this.setRequestCount(counter);
    }
}
