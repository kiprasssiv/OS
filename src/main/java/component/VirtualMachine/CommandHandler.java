package component.VirtualMachine;

import component.RealMachine.Processor;
import model.Register;
import model.RegisterType;

public class CommandHandler {

    private static CommandHandler commandHandler = null;

    private Processor processor = null;

    public CommandHandler() {
        processor = Processor.getInstance();
    }

    public static CommandHandler getInstance() {
        if (commandHandler == null) {
            return new CommandHandler();
        }
        return commandHandler;
    }
    public void handleVT(RegisterType reg, String value) {
        processor.getRegister(reg).value[0] = (byte)Integer.parseInt(value);
        System.out.println(processor.getRegister(reg).name + "   " + processor.getRegister(reg).value[0]);

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
       // processor.getRegister(reg1).value[] = processor.getRegister(reg1).value + processor.getRegister(reg2);
        int sum;
        byte b1;
        byte b2;
        b1 = processor.getRegister(reg1).value[0];
        b2 = processor.getRegister(reg2).value[0];
        sum = b1 + b2;
        //sum = processor.getRegister(reg1).value[0];
        //sum = (byte)(sum + processor.getRegister(reg2).value[0]);
        //processor.getRegister(reg1).value[0]= (byte)processor.getRegister(reg1).value[0] + (byte)processor.getRegister(reg2).value[0];
        System.out.println(processor.getRegister(reg2).value[0]);

    }

    public void handleSB(RegisterType reg1, RegisterType reg2) {
      //  processor.getRegister(reg1).value = processor.getRegister(reg1).value - processor.getRegister(reg2);
    }

    public void handleCM(RegisterType reg1, RegisterType reg2) {
        Register register1 = processor.getRegister(reg1);
        Register register2 = processor.getRegister(reg2);
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
