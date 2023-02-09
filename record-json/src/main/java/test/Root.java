package test;

import java.util.Map;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Root {

    //@JsonProperty("map")
    //@JsonDeserialize(keyUsing = MapKeyDeserializer.class)
    Map<MyKey, String> map;

    public Map<MyKey, String> getMap() {
        return map;
    }


    public void setMap(Map<MyKey, String> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "Root{" +
                "map=" + map +
                '}';
    }
}
