package component.RealMachine;

import component.VirtualMachine.VirtualMachine;

public class RealMachine {
    Processor processor;
    Memory memory;
    public RealMachine(){
        this.processor = Processor.getInstance();
        this.memory = Memory.getInstance();
        VirtualMachine vm = new VirtualMachine();
    }
}
