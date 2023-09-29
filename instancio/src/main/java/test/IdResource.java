package test;

import java.io.Serializable;

public class IdResource<K extends Serializable, T extends IdResource<K, ?>> {

    private K id;

    @SuppressWarnings("unchecked")
    public T setId(K id) {
        this.id = id;
        return (T) this;
    }

    public K getId() {
        return id;
    }

    static class LongResource extends IdResource<Long, LongResource> {
    }

    static class CompositeResource {
        public LongResource longResource;
    }
}
