package edu.csc413.calculator.evaluator;



import edu.csc413.calculator.operators.Operator;

import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
  private Stack<Operand> operandStack;
  private Stack<Operator> operatorStack;
  private StringTokenizer tokenizer;
  private static final String DELIMITERS = "+-*^/()";

  public Evaluator() {
    operandStack = new Stack<>();
    operatorStack = new Stack<>();
  }
  public int eval( String expression ) {
    String token;
    // The 3rd argument is true to indicate that the delimiters should be used
    // as tokens, too. But, we'll need to remember to filter out spaces.
    this.tokenizer = new StringTokenizer( expression, DELIMITERS, true );

    // initialize operator stack - necessary with operator priority schema
    // the priority of any operator in the operator stack other than
    // the usual mathematical operators - "+-*/" - should be less than the priority
    // of the usual operators

    while ( this.tokenizer.hasMoreTokens() ) {
      // filter out spaces
      if ( !( token = this.tokenizer.nextToken() ).equals( " " )) {
        // check if token is an operand
        if ( Operand.check( token )) {
          operandStack.push( new Operand( token ));
        } else {
          if (!Operator.check(token)) {
            System.out.println("*****invalid token******");
            throw new RuntimeException("*****invalid token******");
          }
          // TODO Operator is abstract - these two lines will need to be fixed:

          Operator newOperator = Operator.getOperator(token);

          if (operatorStack.isEmpty()) {
            operatorStack.push(newOperator);
          }
          //if the operatorStack contains any tokens ")".equals(token)
          else {
            while (!operatorStack.isEmpty()
                    && newOperator.priority() <= operatorStack.peek().priority()
                    && !newOperator.equals(Operator.getOperator("("))) {
              //if we need to worry about the parenthesis and the input is ")",
              // so we need make sure all the calculations within () are done
              if (token.equals(")")) {
                //before we meet the operator "("
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals(Operator.getOperator("("))) {
                  Operator oldOpr = operatorStack.pop();
                  Operand op2 = operandStack.pop();
                  Operand op1 = operandStack.pop();
                  operandStack.push(oldOpr.execute(op1, op2));
                }
                //when we meet the operator "("
                if (!operatorStack.isEmpty() && operatorStack.peek().equals(Operator.getOperator("("))) {
                  operatorStack.pop();
                  break;
                }
              }
              //if there are no parenthesis to worry about
              else {
                Operator oldOpr = operatorStack.pop();
                Operand op2 = operandStack.pop();
                Operand op1 = operandStack.pop();
                operandStack.push(oldOpr.execute(op1, op2));
              }
            }
            //as long as we have not met the ")", just keep pushing new operators
            if (!token.equals(")")) {
              operatorStack.push(newOperator);
            }
          }
        }
      }
    }
    while (!operatorStack.isEmpty()) {
      Operand op2 = operandStack.pop();
      Operand op1 = operandStack.pop();
      operandStack.push(operatorStack.pop().execute(op1, op2));
    }
    return operandStack.peek().getValue();
  }

}
