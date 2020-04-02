package component;

import component.VirtualMachine.CommandProcessor;
import model.Command;
import model.Operation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileProcessor {
    List<Operation> operationList;
    CommandProcessor commandProcessor = new CommandProcessor();
    boolean readingCommand;
    String command;
    String parameter;

    public FileProcessor() {
        command = "";
        parameter = "";
        operationList = new ArrayList<>();
    }

    public void readingProgram(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                    this.getClass().getResourceAsStream("/" + fileName)
                )
            );
            int operationCounter = 0;
            String lineOfCode;
            while ((lineOfCode = reader.readLine()) != null) {
                readingCommand = true;
                for (int i = 0; i < lineOfCode.length(); i++) {
                    if (readingCommand) {
                        if (lineOfCode.charAt(i) != ' ') {
                            command += lineOfCode.charAt(i);
                            if (i == lineOfCode.length() - 1) {
                                operationList.add(operationCounter, new Operation(Command.valueOf(command)));
                                command = "";
                            }
                        } else {
                            readingCommand = false;
                            operationList.add(operationCounter, new Operation(Command.valueOf(command)));
                            command = "";
                        }
                    } else {
                        if (lineOfCode.charAt(i) != ' ') {
                            parameter += lineOfCode.charAt(i);
                            if (i + 1 == lineOfCode.length()) {
                                operationList.get(operationCounter).parameters.add(parameter);
                                parameter = "";
                            }
                        } else {
                            operationList.get(operationCounter).parameters.add(parameter);
                            parameter = "";
                        }

                    }
                }
                operationCounter++;
            }
            commandProcessor.separate(operationList);
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
    }
}
