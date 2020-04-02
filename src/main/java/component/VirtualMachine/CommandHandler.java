package component.VirtualMachine;

import component.RealMachine.Processor;
import model.Register;
import model.RegisterType;

import java.math.BigInteger;

public class CommandHandler {

    private static CommandHandler commandHandler = null;

    private Processor processor = null;

    public CommandHandler() {
        this.processor = Processor.getInstance();
    }

    public static CommandHandler getInstance() {
        if (commandHandler == null) {
            return new CommandHandler();
        }
        return commandHandler;
    }
    public void handleVT(RegisterType reg, String value){
        int decimal = Integer.parseInt(value, 16);
        BigInteger bigInt = BigInteger.valueOf(decimal);
        processor.TI.singleValue--;
        processor.getRegister(reg).value = bigInt.toByteArray();

    }


    public void handleMTA() {
        processor.TI.singleValue--;
    }

    public void handleMTB() {
        processor.TI.singleValue--;
    }

    public void handleBTM() {
        processor.TI.singleValue--;
    }

    public void handleATM() {
        processor.TI.singleValue--;
    }

    public void handleAD(RegisterType reg1, RegisterType reg2) {
        int register1 = new BigInteger(processor.getRegister(reg1).value).intValue();
        int register2 = new BigInteger(processor.getRegister(reg2).value).intValue();
        register1 = register1 + register2;
        BigInteger bigInt = BigInteger.valueOf(register1);
        processor.TI.singleValue--;
        processor.getRegister(reg1).value = bigInt.toByteArray();

    }

    public void handleSB(RegisterType reg1, RegisterType reg2) {
        int register1 = new BigInteger(processor.getRegister(reg1).value).intValue();
        int register2 = new BigInteger(processor.getRegister(reg2).value).intValue();
        register1 = register1 - register2;
        BigInteger bigInt = BigInteger.valueOf(register1);
        processor.TI.singleValue--;
        processor.getRegister(reg1).value = bigInt.toByteArray();
    }

    public void handleCM(RegisterType reg1, RegisterType reg2) {
        int register1 = new BigInteger(processor.getRegister(reg1).value).intValue();
        int register2 = new BigInteger(processor.getRegister(reg2).value).intValue();
        int result = -1;
        if (register1<register2){
            result = 0;
        }
        if(register1>register2){
            result = 1;
        }
        if(register1==register2){
            result = 2;
        }
        processor.TI.singleValue-=2;
        processor.CF.singleValue = (byte)result;
    }

    public void handleSJMP() {
        processor.TI.singleValue--;
    }

    public void handleIJMP() {
        processor.TI.singleValue--;
    }

    public void handleBJMP() {
        processor.TI.singleValue--;
    }

    public void handleAJMP() {
        processor.TI.singleValue--;
    }

    public void handleGET(RegisterType reg) {
        processor.TI.singleValue-=3;
        Register register = processor.getRegister(reg);
    }

    public void handleWRT(RegisterType reg) {
        processor.TI.singleValue-=3;
        Register register = processor.getRegister(reg);
    }

    public void handleLOAD() {
        processor.TI.singleValue-=2;
    }

    public void handleLD(RegisterType reg1, RegisterType reg2) {
        processor.TI.singleValue-=2;
        Register register1 = processor.getRegister(reg1);
        Register register2 = processor.getRegister(reg2);

    }

    public void handleHALT() {

    }
}
