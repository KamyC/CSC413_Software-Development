package interpreter.bytecode;
import java.util.ArrayList;
import interpreter.VirtualMachine;

public class BopCode extends ByteCode{

    private String operator;
    @Override
    public void init(ArrayList<String> arg){
        operator = arg.get(0);
    }
    @Override
    public void execute(VirtualMachine vm){
        int secondOperand=vm.pop();
        int firstOperand=vm.pop();
        int res=0;
        switch (operator){
            case "+":
                res=firstOperand+secondOperand;
                break;
            case "-":
                res=firstOperand-secondOperand;
                break;
            case "*":
                res=firstOperand*secondOperand;
                break;
            case "/":
                res=firstOperand/secondOperand;
            case "==":
                if(firstOperand==secondOperand){
                    res=1;
                }
                else{
                    res=0;
                }
                break;
            case "!=":
                if(firstOperand!=secondOperand){
                    res=1;
                }
                else{
                    res=0;
                }
                break;
            case "<=":
                if(firstOperand<=secondOperand){
                    res=1;
                }
                else{
                    res=0;
                }
                break;
            case ">":
                if(firstOperand>secondOperand){
                    res=1;
                }
                else {
                    res=0;
                }
                break;
            case ">=":
                if(firstOperand>=secondOperand){
                    res=1;
                }
                else {
                    res=0;
                }
                break;
            case "<":
                if(firstOperand<secondOperand){
                    res=1;
                }
                else {
                    res=0;
                }
                break;
            case "|":
                if(firstOperand==0&&secondOperand==0){
                    res=0;
                }
                else {
                    res=1;
                }
                break;
            case "&":
                if(firstOperand==1&&secondOperand==1){
                    res=1;
                }
                else {
                    res=1;
                }
                break;
        }
        vm.push(res);
    }
    @Override
    public void printOut(){
        System.out.println("BOP " + operator) ;
    }
}
