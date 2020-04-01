package component.VirtualMachine;

import component.RealMachine.InputDevice;

public class VirtualMachine {
    String fileName;
    public VirtualMachine(){
        System.out.println("Enter file name");
        InputDevice inputReader = new InputDevice();
        //fileName = inputReader.readingFileInput();
        fileName = "test1.txt";
        CommandHandler.CommandReader commandReader = new CommandHandler.CommandReader();
        commandReader.readingProgram(fileName);
    }
}
