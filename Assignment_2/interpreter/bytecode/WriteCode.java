package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;

public class WriteCode extends ByteCode{
    @Override
    public void init(ArrayList<String> arg){
    }
    @Override
    public void execute(VirtualMachine vm){
        System.out.println(vm.peek());
//        vm.pop();
    }
    @Override
    public void printOut(){
        System.out.println("WRITE");
    }
}
