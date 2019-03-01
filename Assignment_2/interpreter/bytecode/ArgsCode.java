package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class ArgsCode extends ByteCode{
    
    private int num;
    @Override
    public void init(ArrayList<String> arg){
        num = Integer.parseInt(arg.get(0));
    }
    @Override
    public void execute(VirtualMachine vm){
        vm.newFrameAt(num);
    }
    @Override
    public void printOut(){
        System.out.println("\nARGS " + num ) ;
    }
}
