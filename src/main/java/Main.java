import component.RealMachine.CommandReader;
import component.RealMachine.InputDevice;

import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String fileName;
        List<String> program = new ArrayList<String>();
        System.out.println("Enter file name");
        InputDevice inputReader = new InputDevice();
        //fileName = inputReader.readingFileInput();
        fileName = "test1.txt";
        CommandReader commandReader = new CommandReader();
        commandReader.readingProgram(fileName);


    }
}
