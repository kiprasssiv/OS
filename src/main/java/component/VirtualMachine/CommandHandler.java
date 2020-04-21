package component.VirtualMachine;

import component.RealMachine.Processor;
import component.util.ByteArrayUtils;
import model.Register;
import model.RegisterType;

import java.nio.ByteBuffer;

public class CommandHandler {

    private static CommandHandler commandHandler = null;

    private Processor processor = null;

    public CommandHandler() {
        this.processor = Processor.getInstance();
    }

    public static CommandHandler getInstance() {
        if (commandHandler == null) {
            commandHandler = new CommandHandler();
        }
        return commandHandler;
    }

    public void handleVT(RegisterType reg, String value) {
        int decimal = Integer.parseInt(value, 16);
        processor.getRegister(reg).value = ByteBuffer.allocate(4).putInt(decimal).array();
        processor.updateCounts(-1);
    }

    // TODO: implement this stuff
    public void handleMTA() {
        processor.updateCounts(-1);
    }

    // TODO: implement this stuff
    public void handleMTB() {
        processor.updateCounts(-1);
    }

    // TODO: implement this stuff
    public void handleBTM() {
        processor.updateCounts(-1);
    }

    // TODO: implement this stuff
    public void handleATM() {
        processor.updateCounts(-1);
    }

    public void handleAD(RegisterType reg1, RegisterType reg2) {
        processor.getRegister(reg1).value = ByteArrayUtils.add(
            processor.getRegister(reg1).value,
            processor.getRegister(reg2).value
        );
        processor.updateCounts(-1);
    }

    public void handleSB(RegisterType reg1, RegisterType reg2) {
        processor.getRegister(reg1).value = ByteArrayUtils.sub(
            processor.getRegister(reg1).value,
            processor.getRegister(reg2).value
        );
        processor.updateCounts(-1);
    }

    public void handleCM(RegisterType reg1, RegisterType reg2) {
        processor.CF.value = ByteArrayUtils.intToBytes(
            ByteArrayUtils.compare(
                processor.getRegister(reg1).value,
                processor.getRegister(reg2).value
            ),
            1
        );
        processor.updateCounts(-2);
    }

    public void handleSJMP() {
        processor.CC.value = processor.SP.value;
        processor.updateCounts(-1);
    }

    public void handleIJMP() {
        if (processor.compareAXwithBX() == 2) {
            processor.CC.value = processor.SP.value;
        } else {
            ByteArrayUtils.add(processor.CC.value, 1);
        }
        processor.updateCounts(-1);
    }

    public void handleBJMP() {
        if (processor.compareAXwithBX() == 1) {
            processor.CC.value = processor.SP.value;
        } {
            ByteArrayUtils.add(processor.CC.value, 1);
        }
        processor.updateCounts(-1);
    }

    public void handleAJMP() {
        if (processor.compareAXwithBX() == 0) {
            processor.CC.value = processor.SP.value;
        } else {
            ByteArrayUtils.add(processor.CC.value, 1);
        }
        processor.updateCounts(-1);
    }

    // TODO: implement this stuff
    public void handleGET(RegisterType reg) {
        Register register = processor.getRegister(reg);
        processor.updateCounts(-3);
    }

    // TODO: implement this stuff
    public void handleWRT(RegisterType reg) {
        Register register = processor.getRegister(reg);
        processor.updateCounts(-3);
    }

    // TODO: implement this stuff
    public void handleLOAD() {
        processor.updateCounts(-2);
    }

    // TODO: implement this stuff
    public void handleLD(RegisterType reg1, RegisterType reg2) {
        Register register1 = processor.getRegister(reg1);
        Register register2 = processor.getRegister(reg2);
        processor.updateCounts(-2);
    }

    // TODO: implement this stuff
    public void handleHALT() {

    }
}
