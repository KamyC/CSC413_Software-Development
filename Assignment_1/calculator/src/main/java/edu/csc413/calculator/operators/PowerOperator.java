package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator{
    @Override
    public int priority(){
        return 3;
    }
    @Override
    public Operand execute(Operand op1, Operand op2){
        int temp=1;
        if(op2.getValue()==0&&op2.getValue()==0){
            throw new IllegalArgumentException("Input error");
        }
        else if(op1.getValue()==1){
            temp=1;
        }
        else if(op2.getValue()==0){
            temp=1;
        }
        else{
            for(int i=0;i!=op2.getValue();i++){
                temp*=op1.getValue();
            }
        }
        return new Operand(temp);
    }
}