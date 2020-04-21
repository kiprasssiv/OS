package component.VirtualMachine;

import component.FileProcessor;
import model.Operation;

import java.util.List;


public class VirtualMachine {
    String fileName;
    CommandProcessor commandProcessor = new CommandProcessor();

    public VirtualMachine() {
        System.out.println("Enter file name");
        fileName = "test1.txt";

        FileProcessor reader = new FileProcessor();
        reader.readProgram(fileName);
        List<Operation> operations = reader.processProgramToCommands();
        commandProcessor.execute(operations);

    }
}
