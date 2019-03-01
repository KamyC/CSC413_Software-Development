package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;

public class PopCode extends ByteCode{

    protected int num;
    @Override
    public void init(ArrayList<String> arg){
        num = Integer.parseInt(arg.get(0));
    }
    @Override
    public void execute(VirtualMachine vm) {
        int max=vm.getStakSize();
        if(num>max){
            vm.empty();
        }
        if(max-1>=num){
            for(int i=0;i<num-1;i++){
                vm.pop();
            }
        }
        else{
            for(int i=0;i<max-1;i++){
                vm.pop();
            }
        }
    }
    @Override
    public void printOut(){
        System.out.println("POP " + num) ;
    }

}
