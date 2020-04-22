package component.VirtualMachine;

import component.RealMachine.Exceptions.OutOfMemoryException;
import component.RealMachine.Memory;
import component.RealMachine.Processor;
import component.util.ByteUtil;
import model.Register;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

public class PagingTable {

    // Paging table. Key - page number of virtual memory, value - page number of real memory
    public Map<Integer, Integer> pageMap = new HashMap<>();

    private static PagingTable pagingTable = getInstance();
    Register PTR;

    // Uses single instance of memory across all virtual machines (should work?)
    private Memory memoryInstance = Memory.getInstance();

    public PagingTable() {


    }
    public static PagingTable getInstance() {
        if (pagingTable == null) {
            pagingTable = new PagingTable();
        }
        return pagingTable;
    }

    // Requests unused memory pages from real memory
    public void requestPages(int pageCount) {
        if (memoryInstance.getFreePagesCount() < pageCount)
            throw new OutOfMemoryException("No free memory");
        int newPageCount = pageMap.size() + pageCount;
        for (int i = pageMap.size(); i < newPageCount; i++) {
            int realMemoryPage = memoryInstance.requestPage();
            if (realMemoryPage != -1) {
                pageMap.put(i, realMemoryPage);
            }
        }
    }

    public void setPaging() {
        for (int wordNum = 0; wordNum < pageMap.size(); wordNum++) {
            byte[] realPage = ByteUtil.intToBytes(pageMap.get(wordNum));
            //memoryInstance.putWord(Processor.PTR.getValueOfSmallerTwoBytes(), wordNum, realPage);
        }
    }
}
