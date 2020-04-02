package model;

import java.util.ArrayList;
import java.util.List;

public class Operation {

    public Command command;
    public List<String> parameters;

    public Operation(Command command, List<String> parameters) {
        this.command = command;
        this.parameters = parameters;
    }

    public Operation(Command command) {
        this.command = command;
        this.parameters = new ArrayList<>();
    }
}
