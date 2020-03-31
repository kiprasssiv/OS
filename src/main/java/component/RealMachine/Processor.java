package component.RealMachine;

import model.RealMachine.ProcessorMode;

public class Processor {

    private static Processor processor = null;

    public ProcessorMode mode;

    public static Processor getInstance() {
        if (processor == null) {
            return new Processor();
        }

        return processor;
    }

    private Processor() {
        mode = ProcessorMode.SUPERVISOR;
    }


}
