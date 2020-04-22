package model;

import java.nio.ByteBuffer;

public class Register {
    public int size;
    public byte[] value;

    public Register(int size) {
        this.size = size;
        this.value = new byte[size];
        for (int i = 0; i < size; i++) {
            if (i == size -1) {
                value[i] = 0;
                continue;
            }
            value[i] = 0;
        }
    }

    public int getIntValue() {
        if (value.length == 4) {
            return ByteBuffer.wrap(value).getInt();
        }
        if (value.length == 2) {
            return (int) ByteBuffer.wrap(value).getShort();
        }
        return value[0] & 0xFF;
    }
    public void setValue(byte[] value){
        this.value = value;
    }
}
