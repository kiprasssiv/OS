package component.VirtualMachine;

import model.Command;
import model.RegisterType;

import java.util.ArrayList;

import static model.Command.AD;
import static model.Command.AJMP;
import static model.Command.ATM;
import static model.Command.BJMP;
import static model.Command.BTM;
import static model.Command.CM;
import static model.Command.GET;
import static model.Command.HALT;
import static model.Command.IJMP;
import static model.Command.LD;
import static model.Command.LOAD;
import static model.Command.MTA;
import static model.Command.MTB;
import static model.Command.SB;
import static model.Command.SJMP;
import static model.Command.WRT;

public class CommandProcessor {
    public int parameterNumber;
    public String firstRegister, secondRegister;

    public CommandProcessor() {
        parameterNumber = 0;
    }

    public void separate(ArrayList<Command> allCommands, ArrayList<String> allParameters) {
        for (int h = 0; h < allCommands.size(); h++) {
            switch (allCommands.get(h)) {
                //Memory commands
                case MTA:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    MTA.handle();
                    break;
                case MTB:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    MTB.handle();
                    break;
                case ATM:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    ATM.handle();
                    break;
                case BTM:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    BTM.handle();
                    break;
                //Arithmetical commands
                case AD:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    secondRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    AD.handle(RegisterType.valueOf(firstRegister), RegisterType.valueOf(secondRegister));
                    break;
                case SB:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    secondRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    SB.handle(RegisterType.valueOf(firstRegister), RegisterType.valueOf(secondRegister));
                    break;
                case CM:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    secondRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    CM.handle(RegisterType.valueOf(firstRegister), RegisterType.valueOf(secondRegister));
                    break;
                //Control commands
                case SJMP:
                    SJMP.handle();
                    break;
                //Conditional control commands
                case IJMP:
                    IJMP.handle();
                    break;
                case BJMP:
                    BJMP.handle();
                    break;
                case AJMP:
                    AJMP.handle();
                    break;
                //Input commands
                case GET:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    GET.handle(RegisterType.valueOf(firstRegister));
                    break;
                //Output commands
                case WRT:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    WRT.handle(RegisterType.valueOf(firstRegister));
                    break;
                //Program executing commands
                case LOAD:
                    LOAD.handle();
                    break;
                case LD:
                    firstRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    secondRegister = allParameters.get(parameterNumber);
                    parameterNumber++;
                    LD.handle(RegisterType.valueOf(firstRegister), RegisterType.valueOf(secondRegister));
                    break;
                case HALT:
                    HALT.handle();
                    break;
            }

        }
    }

}
