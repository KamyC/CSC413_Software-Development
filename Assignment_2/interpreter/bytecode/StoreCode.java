package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;

public class StoreCode extends ByteCode{
    
    private int offset;
    private String id;
    @Override
    public void init(ArrayList<String> arg){
        offset = Integer.parseInt(arg.get(0));
        id = arg.get(1);
    }
    @Override
    public void execute(VirtualMachine vm){
        vm.store(offset);
    }
    @Override
    public void printOut(){
        System.out.println("STORE "+ offset +" "+ id+" "+id+"="+offset);
    }
}
