package component.RealMachine;

public class Memory {

    private static Memory memory = null;

    public static Memory getInstance() {
        if (memory == null) {
            return new Memory();
        }

        return memory;
    }

    private Memory() {}

}
