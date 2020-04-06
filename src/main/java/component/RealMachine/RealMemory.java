package component.RealMachine;

import component.util.ByteUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class RealMemory {

    public static final int pageSize = 16;
    public static final int wordLen = 4;
    public static final int defaultMemorySize = 4096;
    public static final int pageCount = defaultMemorySize / (pageSize * wordLen);
    public static int occupiedRam = 0;

    private static RealMemory realMemory = getInstance();

    private List<Integer> occupiedPageNumber = new ArrayList<>();
    public byte[] ram;

    public static RealMemory getInstance() {
        if (realMemory == null) {
            realMemory = new RealMemory();
        }
        return realMemory;
    }

    private RealMemory() {
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

    // TODO should be moved to virtual realMemory
    public void putWord(int pageNum, int wordNum, byte[] word) {
        int byteIndex = 0;
        for (int i = pageNum * pageSize * wordLen + wordNum * wordLen; i < pageNum * pageSize * wordLen + wordNum * wordLen + wordLen; i++) {
            ram[i] = word[byteIndex++];
        }
    }

    //TODO should be moved to virtual realMemory
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
    // Puts loaded program from file to realMemory
    public void putIntoMemory(byte[] byteCode) {
        for (int i = 0; i < byteCode.length; i++) {
            ram[i] = byteCode[i];
        }
        occupiedRam = byteCode.length;
    }

    public void freeVMMemory(Map<Integer, Integer> pageMap, int ptrValue) {
        for (Map.Entry<Integer, Integer> page: pageMap.entrySet()) {
            occupiedPageNumber.remove(occupiedPageNumber.indexOf(page.getValue()));
            for (int i = page.getValue() * pageSize * wordLen; i < (page.getValue() + 1) * pageSize * wordLen; i++) {
                ram[i] = 0;
            }
        }
        occupiedPageNumber.remove(occupiedPageNumber.indexOf(ptrValue));
        for (int i =  ptrValue * pageSize * wordLen; i < (ptrValue + 1) * pageSize * wordLen; i++) {
            ram[i] = 0;
        }
    }

    private int getVMCounter() {
        int counter = 0;
        byte[] bytes;
        while (true) {
            bytes = Arrays.copyOfRange(ram, (counter * wordLen), (counter * wordLen) + wordLen);
            boolean allZeros = true;
            for (byte b : bytes) {
                if (b != 0)
                    allZeros = false;
            }
            if (allZeros) {
                break;
            } else counter++;
        }
        return counter;
    }

    public int setVmRegistersAddress() {
        int counter = getVMCounter();
        byte[] vMAddress = ByteUtil.intToBytes(requestPage());
        for (int i = 0; i < vMAddress.length; i++) {
            ram[counter * wordLen + i] = vMAddress[i];
        }
        return ByteUtil.byteToInt(vMAddress);
    }

    public void saveVMRegisters(int vMRegisterAddress, List<Integer> registerValues) {
        int registerCounter = 0;
        for (Integer registerValue: registerValues) {
            byte[] registerValueInBytes = ByteUtil.intToBytes(registerValue);
            for (int i = 0; i < registerValueInBytes.length; i++) {
                ram[vMRegisterAddress * wordLen * pageSize + registerCounter * wordLen + i] = registerValueInBytes[i];
            }
            registerCounter++;
        }
    }

    public List<Integer> loadVMRegisters() {
        List<Integer> vmRegisters = new ArrayList<>();
        int counter = getVMCounter();
        int oldVMAddress = ByteUtil.byteToInt(Arrays.copyOfRange(ram, (counter - 1) * wordLen, (counter - 1) * wordLen + wordLen));
        for (int i = 0; i < wordLen; i++) {
            ram[(counter - 1) * wordLen + i] = 0;
        }
        occupiedPageNumber.remove(occupiedPageNumber.indexOf(oldVMAddress));
        while (true) {
            if (vmRegisters.size() < 13) {
                byte[] bytes = Arrays.copyOfRange(ram, oldVMAddress * pageSize * wordLen + vmRegisters.size() * wordLen,
                    oldVMAddress * pageSize * wordLen + vmRegisters.size() * wordLen + wordLen);
                for (int i = 0; i < wordLen; i++) {
                    ram[oldVMAddress * pageSize * wordLen + vmRegisters.size() * wordLen + i] = 0;
                }
                vmRegisters.add(ByteUtil.byteToInt(bytes));
            } else break;
        }
        return vmRegisters;
    }
}
