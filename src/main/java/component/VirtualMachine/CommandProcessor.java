package component.VirtualMachine;

import component.RealMachine.Processor;
import model.Command;
import model.Register;
import model.RegisterType;

import java.util.ArrayList;
import static model.Command.VT;
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
    Processor processor;
    public int parameterNumber;
    public String reg1,reg2,reg,value;
    Register registers = null;

    public CommandProcessor() {
        parameterNumber = 0;
        this.processor = Processor.getInstance();
    }

    public void separate(ArrayList<Command> allCommands, ArrayList<String> allParameters) {
        for (int h = 0; h < allCommands.size(); h++) {
            switch (allCommands.get(h)) {
                //Putting values to registers
                case VT:
                    reg = allParameters.get(parameterNumber);
                    parameterNumber++;
                    value = allParameters.get(parameterNumber);
                    parameterNumber++;
                    VT.handle(RegisterType.valueOf(reg), value);
                    break;

                //Memory commands
                case MTA:
                    reg = allParameters.get(parameterNumber);
                    parameterNumber++;
                    MTA.handle();
                    break;
                case MTB:
                    reg = allParameters.get(parameterNumber);
                    parameterNumber++;
                    MTB.handle();
                    break;
                case ATM:
                    reg = allParameters.get(parameterNumber);
                    parameterNumber++;
                    ATM.handle();
                    break;
                case BTM:
                    reg1 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    BTM.handle();
                    break;
                //Arithmetical commands
                case AD:
                    reg1 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    reg2 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    AD.handle(RegisterType.valueOf(reg1), RegisterType.valueOf(reg2));
                    break;
                case SB:
                    reg1 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    reg2 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    SB.handle(RegisterType.valueOf(reg1), RegisterType.valueOf(reg2));
                    break;
                case CM:
                    reg1 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    reg2 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    CM.handle(RegisterType.valueOf(reg1), RegisterType.valueOf(reg2));
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
                    reg = allParameters.get(parameterNumber);
                    parameterNumber++;
                    GET.handle(RegisterType.valueOf(reg));
                    break;
                //Output commands
                case WRT:
                    reg = allParameters.get(parameterNumber);
                    parameterNumber++;
                    WRT.handle(RegisterType.valueOf(reg));
                    break;
                //Program executing commands
                case LOAD:
                    LOAD.handle();
                    break;
                case LD:
                    reg1 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    reg2 = allParameters.get(parameterNumber);
                    parameterNumber++;
                    LD.handle(RegisterType.valueOf(reg1), RegisterType.valueOf(reg2));
                    break;
                case HALT:
                    HALT.handle();
                    break;
            }

        }
    }

}
