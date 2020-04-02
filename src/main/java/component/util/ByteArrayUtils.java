package component.util;

import java.math.BigInteger;
import java.nio.ByteBuffer;

public class ByteArrayUtils {

    // sums arr1 and arr2, return add value in form of byte[]
    public static byte[] add(byte[] arr1, byte[] arr2) {
        return BigInteger.valueOf(
            byteToInt(arr1) + byteToInt(arr2)
        ).toByteArray();
    }

    // adds value to arr, returns updated arr
    public static byte[] add(byte[] arr, int value) {
        return  BigInteger.valueOf(
            byteToInt(arr) + value
        ).toByteArray();
    }

    // adds value to byte, returns updated arr
    public static byte add(byte byt, int value) {
        return (byte) ((byt & 0xFF) + value);
    }

    // substracts arr2 from arr1, return updated arr1
    public static byte[] sub(byte[] arr1, byte[] arr2) {
        return BigInteger.valueOf(
            byteToInt(arr1) - byteToInt(arr2)
        ).toByteArray();
    }

    // substracts value from arr, returns updated arr
    public static byte[] sub(byte[] arr, int value) {
        return BigInteger.valueOf(
            byteToInt(arr) - value
        ).toByteArray();
    }

    // compares arr1 with arr2, if arr1 bigger returns 1, if arr2 bigger returns 0, if equal returns 2
    public static int compare(byte[] arr1, byte[] arr2) {
        int sub = byteToInt(arr1) - byteToInt(arr2);
        if (sub > 0) return 1;
        if (sub < 0) return 0;
        return 2;
    }


    // converts int value to byte array
    public static byte[] intToBytes(int value, int arraySize) {
        return ByteBuffer.allocate(arraySize).putInt(value).array();
    }

    // converts byte array to integer value
    public static int byteToInt(byte[] bytes) {
        if (bytes.length == 4) {
            return ByteBuffer.wrap(bytes).getInt();
        }
        if (bytes.length == 2) {
            return (int) ByteBuffer.wrap(bytes).getShort();
        }
        return bytes[0] & 0xFF;
    }
}
