package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;


public class FalseBranchCode extends ByteCode{

    private int labelIndex;
    private String label;
    @Override
    public void init(ArrayList<String> arg){
        label = arg.get(0);
    }
    @Override
    public void execute(VirtualMachine vm){
        int val=vm.pop();
        if(val == 0){
            vm.setPC(labelIndex-1);//it should be labelIndex-1 so that the Label can be printed out
        }
    }

    public String getLabel(){
        return label;
    }

    public void setLabelIndex(int index){
        labelIndex = index;
    }

    public int getLabelIndex(){
        return labelIndex;
    }
    @Override
    public void printOut(){
        System.out.println("FALSEBRANCH " + this.label) ;
    }
    
    
}
