package component;

import component.RealMachine.RealMemory;
import component.VirtualMachine.CommandProcessor;
import model.Command;
import model.Operation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public void readProgram(String fileName) {
        try {
            File file = getFileFromResources(fileName);
            byte[] program = Files.readAllBytes(file.toPath());
            RealMemory.getInstance().putIntoMemory(program);
        } catch (IOException ex) {
            System.out.println("Failed to read program from file, exeption: " + ex.getMessage());
        }
    }

    public List<Operation> processProgramToCommands() {
        String codeSegment = new String(Arrays.copyOfRange(RealMemory.getInstance().ram, 0, RealMemory.occupiedRam));
        return processCodeSegmentToOperations(codeSegment);
    }

    public List<Operation> processCodeSegmentToOperations(String codeSegment) {
        List<String> lines = Arrays.asList(codeSegment.split("\\r?\\n"));

        return lines.stream()
            .map(this::processLineOfCode)
            .collect(Collectors.toList());
    }

    public Operation processLineOfCode(String line) {
        System.out.println(line);
        Command operationCommand = null;
        List<String> parameters = new ArrayList<>();
        readingCommand = true;
        for (int i = 0; i < line.length(); i++) {
            if (readingCommand) {
                if (line.charAt(i) != ' ') {
                    command += line.charAt(i);
                    if (i == line.length() - 1) {
                        operationCommand = Command.valueOf(command);
                        command = "";
                    }
                } else {
                    readingCommand = false;
                    operationCommand = Command.valueOf(command);
                    command = "";
                }
            } else {
                if (line.charAt(i) != ' ') {
                    parameter += line.charAt(i);
                    if (i + 1 == line.length()) {
                        parameters.add(parameter);
                        parameter = "";
                    }
                } else {
                    parameters.add(parameter);
                    parameter = "";
                }

            }
        }
        return new Operation.Builder()
            .withCommand(operationCommand)
            .withParameters(parameters)
            .build();
    }

    private File getFileFromResources(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();

        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }
}
