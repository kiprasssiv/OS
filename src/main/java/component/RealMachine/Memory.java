package component.RealMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Memory {

    public static final int pageSize = 16;
    public static final int wordLen = 4;
    public static final int defaultMemorySize = 4096;
    public static final int pageCount = defaultMemorySize / (pageSize * wordLen);
    public static int occupiedRam = 0;

    private static Memory memory = getInstance();

    private List<Integer> occupiedPageNumber = new ArrayList<>();
    public byte[] ram;

    public static Memory getInstance() {
        if (memory == null) {
            memory = new Memory();
        }
        return memory;
    }

    private Memory() {
        ram = new byte[defaultMemorySize];
        Arrays.fill(ram, (byte) 0);
    }

    public int requestPage() {
        Random r = new Random();
        while (true) {
            int randomPage = r.nextInt(ram.length / (pageSize * wordLen));
            if (!occupiedPageNumber.contains(randomPage)) {
                occupiedPageNumber.add(randomPage);
                return randomPage;
            }
        }
    }

    public byte[] getWord(int page, int word) {
        return Arrays.copyOfRange(ram, page * pageSize * wordLen + word * wordLen, page * pageSize * wordLen + word * wordLen + wordLen);
    }

    public byte[] getBytes(int page, int word, int byteCount) {
        return Arrays.copyOfRange(ram, page * pageSize * wordLen + word * wordLen, page * pageSize * wordLen + word * wordLen + byteCount);
    }

    // TODO should be moved to virtual memory
    public void putWord(int pageNum, int wordNum, byte[] word) {
        int byteIndex = 0;
        for (int i = pageNum * pageSize * wordLen + wordNum * wordLen; i < pageNum * pageSize * wordLen + wordNum * wordLen + wordLen; i++) {
            ram[i] = word[byteIndex++];
        }
    }

    //TODO should be moved to virtual memory
    public void putBytes(int pageNum, int wordNum, byte[] words, int byteCount) {
        int byteIndex = 0;
        for (int i = pageNum * pageSize * wordLen + wordNum * wordLen; i < pageNum * pageSize * wordLen + wordNum * wordLen + byteCount; i++) {
            ram[i] = words[byteIndex++];
        }
    }

    public int getFreePagesCount() {
        return pageCount - occupiedPageNumber.size();
    }

    // TODO refactor ugly syntax
    // Puts loaded program from file to memory
    public void putIntoMemory(byte[] byteCode) {
        for (int i = 0; i < byteCode.length; i++) {
            ram[i] = byteCode[i];
        }
        occupiedRam = byteCode.length;
    }
}
