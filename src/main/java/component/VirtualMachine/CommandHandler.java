package component.VirtualMachine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class CommandHandler {
    public int parameterNumber;
    public String firstRegister, secondRegister;
    public CommandHandler(){
        parameterNumber = 0;
    }
    public void commandSeparator(ArrayList<String> allCommands, ArrayList<String> allParameters){
        for(int h=0;h<allCommands.size();h++){
            switch(allCommands.get(h)){
                //Memory commands
                case "MTA":
                    for(int i=1;i<=1;i++)
                    {
                        parameterNumber++;
                    }
                    break;
                case "MTB":
                    for(int i=1;i<=1;i++)
                    {
                        parameterNumber++;
                    }
                    break;
                case "ATM":
                    for(int i=1;i<=1;i++)
                    {
                        parameterNumber++;
                    }
                    break;
                case "BTM":
                    for(int i=1;i<=1;i++)
                    {
                        parameterNumber++;
                    }
                    break;
                 //Arithmetical commands
                case "AD":
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    secondRegister = allParameters.get(parameterNumber);
                    parameterNumber++;

                    break;
                case "SB":
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    secondRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    break;
                case "CM":
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    secondRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    break;
                //Control commands
                case "SJMP":
                    break;
                //Conditional control commands
                case "IJMP":
                    break;
                case "BJMP":
                    break;
                case "AJMP":

                    break;
                //Input commands
                case "GET":
                    parameterNumber++;
                    break;
                //Output commands
                case "WRT":
                    parameterNumber++;
                    break;
                //Program executing commands
                case "LOAD":
                    break;
                case "LD":
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    secondRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    break;
                case "HALT":
                    break;
            }
        }

    }

    public static class CommandReader {
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
                                    if(i == lineOfCode.length()-1){
                                        allCommands.add(command);
                                        command = "";
                                    }
                                }
                                else{
                                    readingCommand = false;
                                    allCommands.add(command);
                                    command = "";
                                }
                            }
                            else{
                                    if(lineOfCode.charAt(i) != ' '){
                                        parameter+=lineOfCode.charAt(i);
                                        if(i+1 == lineOfCode.length()){
                                            allParameters.add(parameter);
                                            parameter = "";
                                        }
                                    }
                                    else{
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
}
