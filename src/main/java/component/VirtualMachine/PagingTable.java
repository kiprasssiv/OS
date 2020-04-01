package component.VirtualMachine;

import java.util.HashMap;
import java.util.Map;

public class PagingTable {

    // Paging table. Key - page number of virtual memory, value - page number of real memory
    public Map<Integer, Integer> pageMap = new HashMap<>();
}
