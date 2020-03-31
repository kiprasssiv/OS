package model.RealMachine;

public enum ProcessorMode {
    SUPERVISOR, USER;

    public int getValue() {
        switch (this) {
            case SUPERVISOR:
                return 1;
            case USER:
                return 0;
            default: return 0;
        }
    }
}
