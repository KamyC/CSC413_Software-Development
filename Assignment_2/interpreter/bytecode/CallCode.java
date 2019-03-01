package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;

public class CallCode extends ByteCode{

    private String label;
    private int labelIndex;
    private int paramter;
    @Override
    public void init(ArrayList<String> arg){
        label = arg.get(0);
    }
    @Override
    public void execute(VirtualMachine vm){
       /* push this address into returnAddr stack,
         when returnCode execute, pop the address and go backto this address*/
        vm.setReturnAddrs(vm.getPC());
        vm.setPC(labelIndex-1);////it should be labelIndex-1 so that the Label can be printed out
        if(vm.getStakSize()>0){
            paramter=vm.peek();
        }
    }
    
    public String getLabel(){
        return label;
    }
    public void setLabelIndex(int index){
        labelIndex = index;
    }
    public int getParameter(){
        return paramter;
    }
    public String getFunName(){
        String name[]=this.label.split("<<");
        return name[0];
    }
    @Override
    public void printOut(){
        if(this.label.equals("Read")){
            System.out.println("CALL " + this.label +"   "+this.getFunName()+"()") ;
        }
        else{
            System.out.println("CALL " + this.label +"   "+this.getFunName()+"("+this.getParameter()+")") ;
        }
    }
}
