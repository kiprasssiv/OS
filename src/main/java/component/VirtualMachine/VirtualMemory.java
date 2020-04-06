package component.VirtualMachine;

import component.RealMachine.Processor;

import java.nio.ByteBuffer;

public class VirtualMemory {

    private Processor processor = null;

    public static final int PARAMSEG_START_PAGE = 0;
    public static final int DATASEG_START_PAGE = 1;
    public static final int EXTRASEG_START_PAGE = 4;
    public static final int CODESEG_START_PAGE = 5;

    private static int pageSize = 16;
    private static int wordSize = 4;
    private static int hexSize = 8;

    private static VirtualMemory virtualMemory = getInstance();

    public static PagingTable pagingTable;


    public VirtualMemory() {
        processor = Processor.getInstance();
//        processor.TI.value = ByteBuffer.allocate(2).putInt(20).array();
        pagingTable = PagingTable.getInstance();

    }

    public static VirtualMemory getInstance() {
        if (virtualMemory == null) {
            virtualMemory = new VirtualMemory();
        }
        return virtualMemory;
    }
}
