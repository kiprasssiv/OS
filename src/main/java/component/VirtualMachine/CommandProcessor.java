package component.VirtualMachine;

import component.RealMachine.Processor;
import model.Operation;
import model.RegisterType;

import java.util.List;

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
import static model.Command.VT;
import static model.Command.WRT;

public class CommandProcessor {
    Processor processor;

    public CommandProcessor() {
        this.processor = Processor.getInstance();
    }

    public void separate(List<Operation> operations) throws Exception {
        while (processor.CC.getIntValue() < operations.size()) {
            Operation operation = operations.get(processor.CC.getIntValue());

            switch (operation.command) {
                //Putting values to registers
                case VT:
                    VT.handle(
                        RegisterType.valueOf(operation.parameters.get(0)),
                        operation.parameters.get(1)
                    );
                    processor.updateCounts(-1);
                    break;
                //Memory commands
                case MTA: // TODO: implement this stuff
//                    reg = operation.parameters.get(0);
//                    MTA.handle(value);
//                    processor.updateCounts(-1);
                    break;
                case MTB: // TODO: implement this stuff
//                    reg = operation.parameters.get(0);
//                    MTB.handle(value);
//                    processor.updateCounts(-1);
                    break;
                case ATM: // TODO: implement this stuff
//                    reg = operation.parameters.get(0);
//                    ATM.handle();
//                    processor.updateCounts(-1);
                    break;
                case BTM: // TODO: implement this stuff
//                    reg1 = operation.parameters.get(0);
//                    BTM.handle();
//                    processor.updateCounts(-1);
                    break;
                //Arithmetical commands
                case AD:
                    AD.handle(
                        RegisterType.valueOf(operation.parameters.get(0)),
                        RegisterType.valueOf(operation.parameters.get(1))
                    );
                    processor.updateCounts(-1);
                    break;
                case SB:
                    SB.handle(
                        RegisterType.valueOf(operation.parameters.get(0)),
                        RegisterType.valueOf(operation.parameters.get(0))
                    );
                    processor.updateCounts(-1);
                    break;
                case CM:
                    CM.handle(
                        RegisterType.valueOf(operation.parameters.get(0)),
                        RegisterType.valueOf(operation.parameters.get(1))
                    );
                    processor.updateCounts(-2);
                    break;
                //Control commands
                case SJMP:
                    SJMP.handle();
                    processor.updateCounts(-1);
                    break;
                //Conditional control commands
                case IJMP:
                    IJMP.handle();
                    processor.updateCounts(-1);
                    break;
                case BJMP:
                    BJMP.handle();
                    processor.updateCounts(-1);
                    break;
                case AJMP:
                    AJMP.handle();
                    processor.updateCounts(-1);
                    break;
                //Input commands
                case GET: // TODO: implement this stuff
                    GET.handle(RegisterType.valueOf(operation.parameters.get(0)));
                    processor.updateCounts(-3);
                    break;
                //Output commands
                case WRT: // TODO: implement this stuff
                    WRT.handle(RegisterType.valueOf(operation.parameters.get(1)));
                    processor.updateCounts(-3);
                    break;
                //Program executing commands
                case LOAD: // TODO: implement this stuff
                    LOAD.handle();
                    processor.updateCounts(-2);
                    break;
                case LD: // TODO: implement this stuff
                    LD.handle(
                        RegisterType.valueOf(operation.parameters.get(0)),
                        RegisterType.valueOf(operation.parameters.get(1))
                    );
                    processor.updateCounts(-2);
                    break;
                case HALT: // TODO: implement this stuff
                    HALT.handle();
                    break;
                default:
                    throw new Exception("No such command " + operation.command);
            }

        }
    }

}
