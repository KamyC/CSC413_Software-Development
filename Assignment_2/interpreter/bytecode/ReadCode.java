package interpreter.bytecode;
import java.util.ArrayList;
import java.util.Scanner;
import interpreter.VirtualMachine;

public class ReadCode extends ByteCode{
    
    private int input;
    @Override
    public void init(ArrayList<String> arg){}
    @Override
    public void execute(VirtualMachine vm){
        try{
            System.out.print("Enter an integer: ");
            Scanner sc=new Scanner(System.in);
            input=sc.nextInt();
            vm.push(input);
        }catch (Exception e){
            System.out.println("Input Error: "+ e);
        }
    }
    @Override
    public void printOut(){
        System.out.println("READ");
    }
}
