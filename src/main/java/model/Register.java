package model;

public class Register {
    public int size;
    public byte[] value;
    public String name;

    public Register(int size,String name) {
        this.size = size;
        this.value = new byte[size];
        this.name = name;
        for(int i=0;i<size;i++){
            value[i] = 0;
        }
    }

}
