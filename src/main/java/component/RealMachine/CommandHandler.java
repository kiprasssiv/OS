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
                case "MTB":
                    for(int i=1;i<=1;i++)
                    {
                        parameterNumber++;
                    }
                case "ATM":
                    for(int i=1;i<=1;i++)
                    {
                        parameterNumber++;
                    }
                case "BTM":
                    for(int i=1;i<=1;i++)
                    {
                        parameterNumber++;
                    }
                 //Arithmetical commands
                case "AD":
                    System.out.println(allCommands.get(h)+" ");
                    for(int i=1;i<=2;i++)
                    {
                        System.out.println(allParameters.get(parameterNumber));
                        parameterNumber++;
                    }
                case "SB":
                    System.out.println(allCommands.get(h)+" ");
                    for(int i=1;i<=2;i++)
                    {
                        System.out.println(allParameters.get(parameterNumber));
                        parameterNumber++;
                    }
                case "CM":
                    for(int i=1;i<=2;i++)
                    {
                        parameterNumber++;
                    }
                //Control commands
                case "SJMP":
                    ;
                //Conditional control commands
                case "IJMP":
                    ;
                case "BJMP":
                    ;
                case "AJMP":
                    System.out.println(allCommands.get(h));
                    ;
                //Input commands
                case "GET":
                    parameterNumber++;
                    ;
                //Output commands
                case "WRT":
                    parameterNumber++;
                    ;
                //Program executing commands
                case "LOAD":
                    ;
                case "LD":
                    for(int i=1;i<=2;i++)
                    {
                        parameterNumber++;
                    }
                case "HALT":
                        ;
            }
        }

    }
}
