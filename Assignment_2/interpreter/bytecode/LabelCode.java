package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;


public class LabelCode extends ByteCode{
    
    private String label;
    @Override
    public void init(ArrayList<String> arg){
        label = arg.get(0);
    }
    @Override
    public void execute(VirtualMachine vm){}
    
    public String getLabel(){
        return label;
    }
    @Override
    public void printOut(){
        System.out.println("LABEL "+label ) ;
    }
}
