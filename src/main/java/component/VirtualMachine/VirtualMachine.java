package component.VirtualMachine;

import component.RealMachine.InputDevice;
import component.FileProcessor;
import component.RealMachine.Processor;


public class VirtualMachine {
    String fileName;
    public VirtualMachine(){
        System.out.println("Enter file name");
        InputDevice inputReader = new InputDevice();
        //fileName = inputReader.readingFileInput();
        fileName = "test1.txt";
        
        FileProcessor reader = new FileProcessor();
        reader.readingProgram(fileName);
    }
}
