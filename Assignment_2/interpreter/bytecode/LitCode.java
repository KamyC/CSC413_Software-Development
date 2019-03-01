package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;


public class LitCode extends ByteCode{
    
    protected String variable="";
    protected int num;
    @Override
    public void init(ArrayList<String> arg){
        num=Integer.parseInt(arg.get(0));
        if(arg.size()>1){
            variable = arg.get(1);
        }
    }
    @Override
    public void execute(VirtualMachine vm){
        if(variable.equals("")){
            vm.push(num);
        }
        else {
            vm.push(0);
        }
    }
    @Override
    public void printOut(){
        if(!variable.equals("")){
            System.out.println("LIT "+num+" "+variable+" "+"int "+ variable);
        }
        else{
            System.out.println("LIT "+num);
        }
    }
}
