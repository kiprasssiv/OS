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
        Processor.TI.singleValue--;
        processor.getRegister(reg).value = bigInt.toByteArray();

    }


    public void handleMTA() {
    }

    public void handleMTB() {
    }

    public void handleBTM() {

    }

    public void handleATM() {

    }

    public void handleAD(RegisterType reg1, RegisterType reg2) {
        int register1 = new BigInteger(processor.getRegister(reg1).value).intValue();
        int register2 = new BigInteger(processor.getRegister(reg2).value).intValue();
        register1 = register1 + register2;
        BigInteger bigInt = BigInteger.valueOf(register1);

        processor.getRegister(reg1).value = bigInt.toByteArray();

    }

    public void handleSB(RegisterType reg1, RegisterType reg2) {
        int register1 = new BigInteger(processor.getRegister(reg1).value).intValue();
        int register2 = new BigInteger(processor.getRegister(reg2).value).intValue();
        register1 = register1 - register2;
        BigInteger bigInt = BigInteger.valueOf(register1);

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
        processor.CF.singleValue = (byte)result;
    }

    public void handleSJMP() {

    }

    public void handleIJMP() {

    }

    public void handleBJMP() {

    }

    public void handleAJMP() {

    }

    public void handleGET(RegisterType reg) {
        Register register = processor.getRegister(reg);
    }

    public void handleWRT(RegisterType reg) {
        Register register = processor.getRegister(reg);
    }

    public void handleLOAD() {

    }

    public void handleLD(RegisterType reg1, RegisterType reg2) {
        Register register1 = processor.getRegister(reg1);
        Register register2 = processor.getRegister(reg2);

    }

    public void handleHALT() {

    }
}
