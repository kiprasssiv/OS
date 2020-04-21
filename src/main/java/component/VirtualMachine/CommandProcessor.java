package component.VirtualMachine;

import component.RealMachine.Processor;
import model.Operation;
import model.Register;

import java.util.List;

public class CommandProcessor {
    Processor processor;

    public CommandProcessor() {
        this.processor = Processor.getInstance();
    }

    public void execute(List<Operation> operations) {
        int testOperationCounter = 0; // operation counter for testing
        while (processor.CC.getIntValue() < operations.size()) {
            Operation operation = operations.get(processor.CC.getIntValue());
            operation.command.handle(operation.parameters);
            testOperationCounter++;
        }
        System.out.println(testOperationCounter);
    }

    private Register getRegister(char regChar) {
        return regChar == 'A' ? processor.AX : processor.BX;
    }

}
