package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap
    // This map will use keys as the tokens we're interested in,
    // and values will be instances of the Operators.
    // ALL subclasses of operator MUST be in their own file.
    // Example:
    // Where does this declaration go? What should its access level be?
    // Class or instance variable? Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    private static HashMap<String, Operator> operators = new HashMap<>();

    static{
        Operator.operators.put( "+", new AddOperator() );
        Operator.operators.put( "-", new SubtractOperator() );
        Operator.operators.put( "*", new MultiplyOperator() );
        Operator.operators.put( "/", new DivideOperator() );
        Operator.operators.put( "^", new PowerOperator() );
        Operator.operators.put( "(", new LeftParenthesisOperator() );
        Operator.operators.put( ")", new RightParenthesisOperator() );
    }

    public abstract int priority();
    public abstract Operand execute( Operand op1, Operand op2 );

    public static boolean check( String token ) {
        return operators.containsKey(token);
    }
    //return an operator object
    public static Operator getOperator(String token){
        return operators.get(token);
    }
}


