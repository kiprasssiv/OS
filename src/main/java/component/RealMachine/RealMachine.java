package component.RealMachine;

import component.VirtualMachine.VirtualMachine;

public class RealMachine {
    private static RealMachine realMachine = null;
    public Processor processor;
    public Memory memory;

    public RealMachine(){
        this.processor = Processor.getInstance();
        this.memory = Memory.getInstance();
        VirtualMachine vm = new VirtualMachine();
    }

}
