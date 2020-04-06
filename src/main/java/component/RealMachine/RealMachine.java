package component.RealMachine;

import component.RealMachine.Exceptions.OutOfMemoryException;
import component.VirtualMachine.VirtualMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RealMachine {
    private static RealMachine realMachine = null;
    public Processor processor;
    public RealMemory realMemory;

    public RealMachine(){
        this.processor = Processor.getInstance();
        this.realMemory = RealMemory.getInstance();
        System.out.println("Enter file name");
        Scanner scanner = new Scanner(System.in);
        initializeVirtualMachine(scanner.nextLine());
    }

    private boolean initializeVirtualMachine(String command) {
        String[] splitCommand = command.split(" ");
        List<String> params = getParams(splitCommand);
        String programFileName = splitCommand[0];

        VirtualMachine virtualMachine = null;
        try {
            virtualMachine = new VirtualMachine("test1.txt", params);
        } catch (OutOfMemoryException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Parameters should be integers");
            return false;
        }

        return true;
    }

    private List<String> getParams(String[] splitCommand) {
        List<String> params = new ArrayList<>();
        if (splitCommand.length > 1) {
            for (int i = 1; i < splitCommand.length; i++) {
                params.add(splitCommand[i]);
            }
        }
        return params;
    }

    public static void printAttributes() {

    }

}
