package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;

public class LoadCode extends ByteCode{
    
    protected String id;
    protected int offset;
    @Override
    public void init(ArrayList<String> arg){
        offset = Integer.parseInt(arg.get(0));
        if(arg.size()>1)
            id = arg.get(1);
    }
    @Override
    public void execute(VirtualMachine vm){
        vm.load(offset);
    }
    @Override
    public void printOut(){
        if(!id.equals("")){
            System.out.println("LOAD "+offset+" "+id);
        }
        else{
            System.out.println("LOAD "+ offset);
        }
    }
}
