package component.RealMachine;

import java.util.ArrayList;

public class CommandHandler {
    public int parameterNumber;
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
                    System.out.println(allCommands.get(h)+" ");
                    for(int i=1;i<=2;i++)
                    {
                        System.out.println(allParameters.get(parameterNumber));
                        parameterNumber++;
                    }
                    break;
                case "SB":
                    System.out.println(allCommands.get(h)+" ");
                    for(int i=1;i<=2;i++)
                    {
                        System.out.println(allParameters.get(parameterNumber));
                        parameterNumber++;
                    }
                    break;
                case "CM":
                    for(int i=1;i<=2;i++)
                    {
                        parameterNumber++;
                    }
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
                    System.out.println(allCommands.get(h));
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
                    for(int i=1;i<=2;i++)
                    {
                        parameterNumber++;
                    }
                    break;
                case "HALT":
                    break;
            }
        }

    }
}
