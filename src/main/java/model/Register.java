package model;

import component.util.ByteUtil;

import java.nio.ByteBuffer;

public class Register {
    private static final int defaultSize = 4;
    public byte[] value;
    public int size;

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

    public Register() {
        this.size = defaultSize;
        this.value = new byte[defaultSize];
        for (int i = 0; i < defaultSize; i++) {
            if (i == defaultSize -1) {
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

    public int getSize() {
        return size;
    }

    public int getValue() {
        return ByteBuffer.wrap(this.value).getInt();
    }

    public byte[] getByteValue() { return this.value; }

    public void setValue(int value) {
        this.value = ByteBuffer.allocate(this.size).putInt(value).array();
    }

    public void incrementValue(int by) {
        int value = this.getValue();
        value += by;
        this.setValue(value);
    }

    public void incrementValue() {
        int value = this.getValue();
        this.setValue(++value);
    }

    public String getHexValue() {
        return ByteUtil.bytesToHex(value);
    }

    public int getValueOfSmallerTwoBytes() {
        return ByteUtil.byteToInt(new byte[] {0, 0, value[2], value[3]});
    }
}
