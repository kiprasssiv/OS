package model;

public class Register {
    public int size;
    public byte[] value;

    public Register(int size) {
        this.size = size;
        this.value = new byte[size];
    }
}
