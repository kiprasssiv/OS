package component.VirtualMachine;

import component.RealMachine.Processor;

public class VirtualMemory {

    private static int pageSize = 16;

    protected static int wordSize = 4;
    protected static int hexSize = 8;

    public static int pages = 16;
    public static int words = 16;

    public VirtualMemory() {
        Processor.TI.singleValue = (byte)20;
    }
}
