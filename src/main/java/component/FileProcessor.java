package component;

import component.VirtualMachine.CommandHandler;
import component.VirtualMachine.CommandProcessor;
import model.Command;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class FileProcessor {
    ArrayList<Command> allCommands = new ArrayList<Command>();
    ArrayList<String> allParameters = new ArrayList<String>();
    CommandProcessor commandProcessor = new CommandProcessor();
    boolean readingCommand;
    String command;
    String parameter;

    public FileProcessor() {
        command = "";
        parameter = "";
    }

    public void readingProgram(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String lineOfCode;
            while ((lineOfCode = reader.readLine()) != null) {
                readingCommand = true;

                for (int i = 0; i < lineOfCode.length(); i++) {
                    if (readingCommand) {
                        if (lineOfCode.charAt(i) != ' ') {
                            command += lineOfCode.charAt(i);
                            if (i == lineOfCode.length() - 1) {
                                allCommands.add(Command.valueOf(command));
                                command = "";
                            }
                        } else {
                            readingCommand = false;
                            allCommands.add(Command.valueOf(command));
                            command = "";
                        }
                    } else {
                        if (lineOfCode.charAt(i) != ' ') {
                            parameter += lineOfCode.charAt(i);
                            if (i + 1 == lineOfCode.length()) {
                                allParameters.add(parameter);
                                parameter = "";
                            }
                        } else {
                            allParameters.add(parameter);
                            parameter = "";
                        }

                    }
                }
            }
            commandProcessor.separate(allCommands, allParameters);
            reader.close();
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
    }
}
