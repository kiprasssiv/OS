package component.RealMachine;

import model.RealMachine.ProcessorMode;

import java.util.ArrayList;
import java.util.List;

public class Processor {

    private static Processor processor = getInstance();

    public ProcessorMode mode;

    public static List<Device> devices;

    public static Register AX,BX,CX,DX,PTR;
    public static Register SF, CF,CC, TI, WM, PI, SI;

    public static Processor getInstance() {
        if (processor == null) {
            return new Processor();
        }

        return processor;
    }

    /*private Processor() {
        this.devices = new ArrayList<>();
        this.mode = ProcessorMode.SUPERVISOR;
        initializeRegisters();
    }*/

    private Processor() {
        mode = ProcessorMode.SUPERVISOR;
    }

    public void initializeRegisters(){
        AX = new Register();
        BX = new Register();
        CX = new Register();
        DX = new Register();
        PTR = new Register();
        SF = new Register();
        CF = new Register();
        CC = new Register();
        TI = new Register();
        WM = new Register();
        PI = new Register();
        SI = new Register();
    }



}
