package interpreter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class RunTimeStack {

    private ArrayList<Integer> runTimeStack;
    private Stack<Integer> framePointer;

    public RunTimeStack() {
        runTimeStack = new ArrayList();
        framePointer = new Stack<>();
        // Add initial Frame Pointer, main is the entry
        // point of our language, so its frame pointer is 0.
        framePointer.add(0);
    }
    public void dump(){
        Iterator<Integer> iter=framePointer.iterator();
        int current =iter.next();
        int next;
        for( int i=0;i!=framePointer.size();i++){
            if(!iter.hasNext()){
                next=this.stackSize();
            }
            else{
                next=iter.next();
            }
            //print out the elements between [current , next];
            System.out.print("[");
            for(int j=current;j!=next;j++){
                System.out.print(runTimeStack.get(j));
                if(j!=next-1){//in case the last element is followed by a ","
                    System.out.print(",");
                }
            }
            System.out.print("] ");
            current=next;
        }
        System.out.println();
    }
    public void empty(){
        this.runTimeStack.clear();
    }
    public int peek(){
        if(runTimeStack.size()==0){
            throw new IndexOutOfBoundsException("No Element in runTimeStack");
        }
        else{
            return runTimeStack.get(runTimeStack.size()-1);
        }
    }

    public int pop(){
        int top=runTimeStack.get(runTimeStack.size()-1);
        runTimeStack.remove(runTimeStack.size()-1);
        return top;
    }

    public int push(int i){
        runTimeStack.add(i);
        return this.peek();
    }
    /* Just in case the offset does not exceed the stack size
    throw an exception to warn*/
    public void newFrameAt(int offset){
        int frameVal=runTimeStack.size()-offset;
        if(frameVal>=0){
            framePointer.push(frameVal);
        }else{
            throw new IndexOutOfBoundsException();
        }
    }

    public void popFrame(){
        int popVal=this.peek();
        int popFra=framePointer.pop();
        for(int i=runTimeStack.size()-1;i!=popFra-1;i--){
            runTimeStack.remove(i);
        }
        runTimeStack.add(popVal);
    }

    public int store(int offset){
        int storeVal=this.pop();
        runTimeStack.set(framePointer.peek()+offset,storeVal);
        return storeVal;
    }

    public int load(int offset){
        int loadVal=runTimeStack.get(framePointer.peek()+offset);
        runTimeStack.add(loadVal);
        return loadVal;
    }

    public Integer push(Integer val){
        runTimeStack.add(val);
        return this.peek();
    }

    public int stackSize(){
        return this.runTimeStack.size();
    }
}
