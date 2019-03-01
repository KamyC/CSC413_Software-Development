package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;


public class DumpCode extends ByteCode{
    
    protected String label;
    @Override
    public void init(ArrayList<String> arg){
        label = arg.get(0);
    }
    @Override
    public void execute(VirtualMachine vm){
        vm.setDump(label);
    }
    @Override
    public void printOut(){}//no need to print dump
    
}
