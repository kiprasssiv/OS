package component.RealMachine;

import component.util.ByteArrayUtils;
import model.RealMachine.Device;
import model.RealMachine.ProcessorMode;
import model.Register;
import model.RegisterType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Processor {

    private static Processor processor = null;

    public ProcessorMode mode;

    public static List<Device> devices;

    public Register AX, BX, CX, DX, PTR, SF, CF, CC, TI, WM, PI, SI, SP;

    public static Processor getInstance() {
        if (processor == null) {
            processor = new Processor();
        }
        return processor;
    }

    private Processor() {
        devices = new ArrayList<>();
        mode = ProcessorMode.SUPERVISOR;
        initializeRegisters();
    }

    private Processor(ProcessorMode mode) {
        this.mode = mode;
    }

    public void initializeRegisters() {
        AX = new Register(4);
        BX = new Register(4);
        CX = new Register(4);
        DX = new Register(4);
        PTR = new Register(4); //nzn
        SF = new Register(1);
        CF = new Register(1);
        CC = new Register(4);
        TI = new Register(2);
        WM = new Register(1);
        PI = new Register(2);
        SI = new Register(2); // kanalu irenginys
        SP = new Register(4);
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


    public void updateCounts(int TI) {
        CC.value = ByteArrayUtils.add(CC.value, 1);
        this.TI.value = ByteArrayUtils.add(this.TI.value, TI);
    }

    public void updateCounts(int CC, int TI) {
        this.CC.value = ByteArrayUtils.add(this.CC.value, CC);
        this.TI.value = ByteArrayUtils.add(this.TI.value, TI);
    }

    public int compareAXwithBX() {
        return ByteArrayUtils.compare(AX.value, BX.value);
    }
}
