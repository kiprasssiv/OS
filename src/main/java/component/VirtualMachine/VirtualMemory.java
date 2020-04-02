package component.VirtualMachine;

import component.RealMachine.Processor;

import java.nio.ByteBuffer;

public class VirtualMemory {

    private Processor processor = null;

    private static int pageSize = 16;

    protected static int wordSize = 4;
    protected static int hexSize = 8;

    public static int pages = 16;
    public static int words = 16;

    public VirtualMemory() {
        processor = Processor.getInstance();
        processor.TI.value = ByteBuffer.allocate(2).putInt(20).array();
    }
}
