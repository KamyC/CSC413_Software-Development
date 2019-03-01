package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;

public abstract class ByteCode {
    //initialize the bytecode along with the arguments each bytecode will deal with
    public abstract void init(ArrayList<String> arg);
    //request the virtual machine to manipulate the stack
    public abstract void execute(VirtualMachine vm);
    //For dumping the code
    public abstract void printOut();
}
