package component.VirtualMachine;

import component.RealMachine.InputDevice;
import component.Tools.CodeFileReader;

public class VirtualMachine {
    String fileName;
    public VirtualMachine(){
        System.out.println("Enter file name");
        InputDevice inputReader = new InputDevice();
        //fileName = inputReader.readingFileInput();
        fileName = "test1.txt";
        CodeFileReader reader = new CodeFileReader();
        reader.readingProgram(fileName);
    }
}
