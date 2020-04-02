package component.RealMachine;

import model.RealMachine.ProcessorMode;
import model.Register;
import model.RegisterType;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Processor {

    private static Processor processor = null;

    public ProcessorMode mode;

    public static List<Device> devices;

    public static Register AX, BX, CX, DX, PTR, SF, CF, CC, TI, WM, PI, SI;

    public static Processor getInstance() {
        if (processor == null) {
            return new Processor();
        }
        return processor;
    }

    private Processor() {
        this.devices = new ArrayList<>();
        this.mode = ProcessorMode.SUPERVISOR;
        initializeRegisters();
    }

    private Processor(ProcessorMode mode) { this.mode = mode; }

    public void initializeRegisters() {
        this.AX = new Register(4, "AX");
        this.BX = new Register(4,"BX");
        this.CX = new Register(4,"CX");
        this.DX = new Register(4,"DX");
        this.PTR = new Register(4,"PTR"); //nzn
        this.SF = new Register(1,"SF");
        this.CF = new Register(1,"CF");
        this.CC = new Register(2,"CC");
        this.TI = new Register(2,"TI");
        this.WM = new Register(1,"WM");
        this.PI = new Register(2,"PI");
        this.SI = new Register(2,"SI"); // kanalu irenginys
    }
    public Register getRegister(RegisterType type) {
        try {
            Field field = Processor.class.getDeclaredField(type.toString());
            field.setAccessible(true);
            return (Register) field.get(processor);
        } catch (NoSuchFieldException e) {
            throw new IllegalStateException("Bad field name: " + type, e);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Failed to access field " + type + " after making it accessible", e);
        }
    }
    public String getRegisterValues(){
        return "AX: "+AX.value+ " " + "BX: "+BX.value+ " " + "CX: "+ CX.value+ " " +"DX: "+DX.value;
    }
}
