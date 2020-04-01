package component.RealMachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class CommandReader {
    ArrayList<String> allCommands = new ArrayList<String>();
    ArrayList<String> allParameters= new ArrayList<String>();
    CommandHandler handler = new CommandHandler();
    boolean readingCommand;
    String command;
    String parameter;
    public CommandReader(){
        command = "";
        parameter = "";
    }
    public void readingProgram(String fileName) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String lineOfCode;
            while ((lineOfCode = reader.readLine()) != null) {
                readingCommand = true;
                for(int i=0;i<lineOfCode.length();i++){
                        if(readingCommand) {
                            if(lineOfCode.charAt(i) != ' '){
                                command += lineOfCode.charAt(i);
                            }
                            else{
                                readingCommand = false;
                                allCommands.add(command);
                                command = "";
                            }
                            if(i == lineOfCode.length()-1){
                                allParameters.add(parameter);
                                command = "";
                            }
                        }
                        else{
                                if(lineOfCode.charAt(i) != ' '){
                                    parameter+=lineOfCode.charAt(i);
                                }

                                else{
                                    allParameters.add(parameter);
                                    parameter = "";
                                }
                                if(i == lineOfCode.length()-1){
                                    allParameters.add(parameter);
                                    parameter = "";
                                }

                            }
                        }

                }
                handler.commandSeparator(allCommands,allParameters);
                reader.close();
            }
         catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", fileName);
            e.printStackTrace();
        }
    }
}
