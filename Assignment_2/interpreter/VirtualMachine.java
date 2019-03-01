package interpreter;
import interpreter.bytecode.ByteCode;
import interpreter.bytecode.DumpCode;
import interpreter.bytecode.WriteCode;

import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack returnAddrs;
    private Program program;
    private int pc;
    private boolean dump;
    private boolean isRunning;

    protected VirtualMachine(Program program) {
        this.program = program;
    }
    public void executeProgram(){
        pc=0;
        runStack=new RunTimeStack();
        returnAddrs=new Stack<Integer>();
        isRunning=true;
        this.dump =false;

        while(isRunning){
            ByteCode code=program.getCode(pc);
            code.execute(this   );
            if(dump==true&&!(code instanceof DumpCode)){
                code.printOut();
                runStack.dump();
            }
            pc++;
        }
    }

    public void setDump(String state){
        if(state.equals("ON")){
            dump=true;
        }
        else if(state.equals("OFF")){
            dump=false;
        }
    }
    public void exit(){
        isRunning=false;
    }
    public void setPC(int labelIndex){
        pc=labelIndex;
    }
    public int getPC(){
        return this.pc;
    }
    public void setReturnAddrs(int address){
        this.returnAddrs.push(address);
    }
    public int popReturnAddrs(){
        return (Integer)this.returnAddrs.pop();
    }

    //below methods are the manipulation of runStack
    public int peek(){
        return this.runStack.peek();
    }
    public int pop(){
        return this.runStack.pop();
    }
    public void load(int offset){
        runStack.load(offset);
    }
    public void store(int offset){
        runStack.store(offset);
    }
    public void push(int val){
        runStack.push(val);
    }
    public void newFrameAt(int offset){
        runStack.newFrameAt(offset);
    }
    public void popFrame(){
        runStack.popFrame();
    }
    public int getStakSize(){
        return runStack.stackSize();
    }
    public void empty(){
        runStack.empty();
    }
}
