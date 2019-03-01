package interpreter;
import interpreter.bytecode.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Program {

    private ArrayList<ByteCode> program;//this is an array in Program
    private HashMap<String, Integer> map;

    public int getSize() {
        return this.program.size();
    }

    public Program() {
        this.program = new ArrayList<>();
        this.map=new HashMap<>();
    }

    public ByteCode getCode(int pc) {
        return this.program.get(pc);
    }

    public void addCode(ByteCode bc){
        if(bc instanceof LabelCode){
            LabelCode label = (LabelCode)bc;
            //get the index of label in the program
            map.put(label.getLabel(), this.getSize());
        }
        program.add(bc);//add bytecode to the array
    }
    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LabelCode <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param program Program object that holds a list of ByteCodes
     */
    
    //bytecodes are stored in array list. label and its address store in a map object.
    public void resolveAddrs(Program program) {
        int address;
        int length=program.getSize();
        for(int i=0;i!=length;i++){
            if(program.getCode(i) instanceof GotoCode){
                GotoCode bc=(GotoCode) program.getCode(i);
                address= map.get(bc.getLabel());
                bc.setLabelIndex(address);
            }
            else if(program.getCode(i) instanceof FalseBranchCode){
                FalseBranchCode bc=(FalseBranchCode) program.getCode(i);
                address= map.get(bc.getLabel());
                bc.setLabelIndex(address);
            }
            else if( program.getCode(i) instanceof CallCode){
                CallCode bc=(CallCode) program.getCode(i);
                address= map.get(bc.getLabel());
                bc.setLabelIndex(address);
            }
        }
    }

}
