package interpreter;
import interpreter.bytecode.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private Program program;

    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }

    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     * Tokenize string to break it into parts.
     * Grab THE correct class name for the given ByteCode from CodeTable
     * Create an instance of the ByteCode class name returned from code table.
     * Parse any additional arguments for the given ByteCode and send them to
     * the newly created ByteCode instance via the init function.
     */
    public Program loadCodes() {
        this.program=new Program();
        String next;
        String key, val;
        ArrayList<String> tList = new ArrayList<>();
        try {
            next = byteSource.readLine();
            while (next != null) {//in each line,
                StringTokenizer tok = new StringTokenizer(next);//process the line

                //add this array to the bytecode
                key = tok.nextToken();//get raw code
                val = CodeTable.getClassName(key);//get the corresponding class code name
                Class c = Class.forName("interpreter.bytecode." + val);
                ByteCode bc = (ByteCode) c.getDeclaredConstructor().newInstance();//use reflection to create Bytecode object

                while (tok.hasMoreTokens()) {
                    tList.add(tok.nextToken());//add rest tokens to tList e.g. in LIT 2, add "2" to the tList
                }
                bc.init(tList);//initialize each bytecode
                program.addCode(bc);//add the bytecode instance to the program

                tList.clear();
                next = byteSource.readLine();//go to next line
            }
        } catch (Exception e) {
            System.out.println("Reading file errors: " + e);
        }

        //resolve address
        try{
            program.resolveAddrs(program);
        }catch(Exception e){
            System.out.println("ResolveAddress error: ");
        }
        return program;
    }
}
