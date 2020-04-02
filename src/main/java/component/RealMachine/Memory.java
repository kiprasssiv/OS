package component.RealMachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Memory {

    public static final int pageSize = 16;
    public static final int wordLen = 4;
    public static final int defaultMemorySize = 4096;

    private static Memory globalMemory = getInstance();

    private List<Integer> occupiedPageNumber = new ArrayList<>();
    private byte[] memory;

    public static Memory getInstance() {
        if (globalMemory == null)
            globalMemory = new Memory();
        return globalMemory;
    }

    private Memory() {
        memory = new byte[defaultMemorySize];
        Arrays.fill(memory, (byte) 0);
    }

}
