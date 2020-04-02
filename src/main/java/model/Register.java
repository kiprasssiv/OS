package model;

public class Register {
    public int size;
    //public int[] value;
    public int value;
    public String name;

    public Register(int size,String name) {
        this.size = size;
        //this.value = new int[size];
        this.value = 0;
        this.name = name;
        /*for(int i=0;i<size;i++){
            value[i] = 0;
        }*/
    }
}
