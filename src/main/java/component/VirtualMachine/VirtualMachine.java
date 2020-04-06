package component.VirtualMachine;

import component.FileProcessor;
import model.Operation;

import java.util.List;


public class VirtualMachine {
    String fileName;
    CommandProcessor commandProcessor = new CommandProcessor();
    VirtualMemory virtualMemory;
    List<String> parameters;

    public VirtualMachine(String fileName, List<String> parameters) {
        this.fileName = fileName;
        this.parameters = parameters;
        virtualMemory = VirtualMemory.getInstance();
        FileProcessor reader = new FileProcessor();
        reader.readProgram(fileName);
        List<Operation> operations = reader.processProgramToCommands();
        commandProcessor.execute(operations);
    }

}
