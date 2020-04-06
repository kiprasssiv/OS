package component.VirtualMachine;

import component.RealMachine.Exceptions.OutOfMemoryException;
import component.RealMachine.Processor;
import component.RealMachine.RealMemory;
import component.util.ByteUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PagingTable {

    // Paging table. Key - page number of virtual realMemory, value - page number of real realMemory
    public Map<Integer, Integer> pageMap = new HashMap<>();

    private static PagingTable pagingTable = getInstance();

    // Uses single instance of realMemory across all virtual machines (should work?)
    private RealMemory realMemoryInstance = RealMemory.getInstance();

    private Processor processor = Processor.getInstance();

    public PagingTable() {
        processor.PTR.setValue(realMemoryInstance.requestPage());
        processor.SP.setValue(new byte[]{0, 0, VirtualMemory.EXTRASEG_START_PAGE - 1, 15});
    }

    public static PagingTable getInstance() {
        if (pagingTable == null) {
            pagingTable = new PagingTable();
        }
        return pagingTable;
    }

    // Requests unused realMemory pages from real realMemory
    public void requestPages(int pageCount) {
        if (realMemoryInstance.getFreePagesCount() < pageCount)
            throw new OutOfMemoryException("No free realMemory");
        int newPageCount = pageMap.size() + pageCount;
        for (int i = pageMap.size(); i < newPageCount; i++) {
            int realMemoryPage = realMemoryInstance.requestPage();
            if (realMemoryPage != -1) {
                pageMap.put(i, realMemoryPage);
            }
        }
    }

    public void setPaging() {
        for (int wordNum = 0; wordNum < pageMap.size(); wordNum++) {
            byte[] realPage = ByteUtil.intToBytes(pageMap.get(wordNum));
            realMemoryInstance.putWord(processor.PTR.getValueOfSmallerTwoBytes(), wordNum, realPage);
        }
    }

    public byte[] getWordFromMemory(int page, int word) {
        int pageInMemory = ByteUtil.byteToInt(realMemoryInstance.getWord(processor.PTR.getValueOfSmallerTwoBytes(), page));
        return realMemoryInstance.getWord(pageInMemory, word);
    }

    public byte[] getBytesFromMemory(int page, int word, int byteCount) {
        int pageInMemory = ByteUtil.byteToInt(realMemoryInstance.getWord(processor.PTR.getValueOfSmallerTwoBytes(), page));
        return realMemoryInstance.getBytes(pageInMemory, word, byteCount);
    }

    public void putWordToMemory(int pageNum, int wordNum, byte[] word) {
        int pageInMemory = ByteUtil.byteToInt(realMemoryInstance.getWord(processor.PTR.getValueOfSmallerTwoBytes(), pageNum));
        realMemoryInstance.putWord(pageInMemory, wordNum, word);
    }

    public void putBytesToMemory(int pageNum, int wordNum, byte[] words, int byteCount) {
        int pageInMemory = ByteUtil.byteToInt(realMemoryInstance.getWord(processor.PTR.getValueOfSmallerTwoBytes(), pageNum));
        realMemoryInstance.putBytes(pageInMemory, wordNum, words, byteCount);    }

    public void freeMemory() {
        realMemoryInstance.freeVMMemory(pageMap, processor.PTR.getValueOfSmallerTwoBytes());
    }

    public void saveVMRegisters(List<Integer> registerValues) {
        int vMRegisterAddress = realMemoryInstance.setVmRegistersAddress();
        realMemoryInstance.saveVMRegisters(vMRegisterAddress, registerValues);
    }

    public List<Integer> loadVMRegisters() {
        return realMemoryInstance.loadVMRegisters();
    }
}
