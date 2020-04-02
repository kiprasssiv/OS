package model;

public class Register {
    public int size;
    public byte  singleValue;
    public byte[] value;
    public String name;

    public Register(int size,String name) {
        this.size = size;
        this.value = new byte[size];
        singleValue = 0;
        this.name = name;
        for(int i=0;i<size;i++){
            value[i] = 0;
        }
    }
}
