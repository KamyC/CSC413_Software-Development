package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;


public class HaltCode extends ByteCode{

    @Override
    public void init( ArrayList<String> arg ){}
    @Override
    public void execute(VirtualMachine vm){
        vm.exit();
    }
    @Override
    public void printOut(){
        System.out.println("HALT") ;
    }
}
