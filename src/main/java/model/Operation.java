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

    public static class Builder {
        Command command;
        List<String> parameters;

        public Builder() {
        }

        public Builder withCommand(Command command) {
            this.command = command;
            return this;
        }

        public Builder withParameters(List<String> parameters) {
            this.parameters = parameters;
            return this;
        }

        public Operation build() {
            return new Operation(this);
        }
    }

    private Operation(Builder builder) {
        command = builder.command;
        parameters = builder.parameters;
    }
}
