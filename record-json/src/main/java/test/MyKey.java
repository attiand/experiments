package test;

public class MyKey {
    int i;
    String name;

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MyKey{" +
                "i=" + i +
                ", name='" + name + '\'' +
                '}';
    }
}
