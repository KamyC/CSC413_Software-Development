package interpreter.bytecode;
import java.awt.*;
import java.util.ArrayList;
import interpreter.VirtualMachine;

public class ReturnCode extends ByteCode{
    
    protected String label;
    @Override
    public void init(ArrayList<String> arg){
        if(arg.isEmpty())
            label = null;
        else
            label = arg.get(0);
    }
    public String getLabelName(){
        String name[]=label.split("<<");
        return name[0];
    }
    public String getNumber(){
        int a=label.indexOf("<<");
        int b=label.indexOf(">>");
        String num=this.label.substring(a+2,b);
        return num;
    }
    @Override
    public void execute(VirtualMachine vm){
        vm.setPC(vm.popReturnAddrs());
        vm.popFrame();
    }
    @Override
    public void printOut(){
        if(label == null){
            System.out.println("RETURN ");
        }

        else{
            System.out.println("RETURN "+ this.getLabelName()+ " exit "+this.getLabelName()+": "+this.getNumber());
        }

    }
}
