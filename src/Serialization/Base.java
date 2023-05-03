package Serialization;

public class Base {
    protected String name;
    protected short nameLength;
    protected int size = 2 + 4;

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
        size += name.length();
        nameLength = (short)name.length();
    }
}
