package test.mytest;

import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;

@SessionScoped
public class MySession implements Serializable {

    private int counter = 0;

    public void setRequestCount(int i) {
        this.counter = i;
    }

    public int getRequestCount() {
        return this.counter;
    }
}
