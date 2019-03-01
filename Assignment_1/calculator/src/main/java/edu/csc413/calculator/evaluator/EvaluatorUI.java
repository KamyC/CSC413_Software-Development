package edu.csc413.calculator.evaluator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
        "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3",
        "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression, clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private String input="";
    private String output="";
    public void actionPerformed(ActionEvent arg0) {
        // You need to fill in this fuction
        //buttons[14] is “=”,buttons[18] is "C" which means to delete one token
        // buttons[19] is CE which means to clear all the input and displayed expressions.
        String temp=arg0.getActionCommand();
        if(arg0.getSource()!=buttons[14]&&arg0.getSource()!=buttons[18]&&arg0.getSource()!=buttons[19]){
            this.input+=temp;
            txField.setText(input);
        }
        else if(arg0.getSource()==buttons[14]){
            Evaluator eva=new Evaluator();
            int res=eva.eval(txField.getText());
            output=Integer.toString(res);
            txField.setText(output);
        }
        else if(arg0.getSource()==buttons[18]){
            txField.setText(txField.getText().substring(0,txField.getText().length()-1));
        }
        else if(arg0.getSource()==buttons[19]){
            output="";
            input="";
            txField.setText(output);
        }
    }
}
