import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    JFrame frame;
    JTextField textfield;
    JButton[] numbtn = new JButton[10];
    JButton[] actionbtn = new JButton[9]; 
    JButton addbtn, subbtn, mulbtn, divbtn, decButton, equalbtn, deletebtn, clrbtn, negatebtn; 
    JPanel panel;
	

    Font myFont = new Font("Serif", Font.BOLD, 30);

    double num1 = 0, num2 = 0, result = 0;
    char operator;

    boolean operatorClicked = false;
    boolean negateFlag = false; // Added a flag to track whether the number is negated

    Calculator() {

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setBackground(new Color(48, 67, 0));
        textfield.setEditable(false);

        addbtn = new JButton("+");
        subbtn = new JButton("-");
        mulbtn = new JButton("*");
        divbtn = new JButton("/");
        decButton = new JButton(".");
        equalbtn = new JButton("=");
        deletebtn = new JButton("Del");
        clrbtn = new JButton("Clr");
        negatebtn = new JButton("Neg"); // Added the negate button

        actionbtn[0] = addbtn;
        actionbtn[1] = subbtn;
        actionbtn[2] = mulbtn;
        actionbtn[3] = divbtn;
        actionbtn[4] = decButton;
        actionbtn[5] = equalbtn;
        actionbtn[6] = deletebtn;
        actionbtn[7] = clrbtn;
        actionbtn[8] = negatebtn; // Added the negate button to the action buttons array

        for (int i = 0; i < 9; i++) {
            actionbtn[i].addActionListener(this);
            actionbtn[i].setFont(myFont);
            actionbtn[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numbtn[i] = new JButton(String.valueOf(i));
            numbtn[i].addActionListener(this);
            numbtn[i].setFont(myFont);
            numbtn[i].setFocusable(false);
        }

        deletebtn.setBounds(150, 430, 100, 50);
        clrbtn.setBounds(250, 430, 100, 50);
        negatebtn.setBounds(50, 430, 100, 50); // Set the position of the negate button

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(new Color(78, 38, 0));

        panel.add(numbtn[1]);
        panel.add(numbtn[2]);
        panel.add(numbtn[3]);
        panel.add(addbtn);
        panel.add(numbtn[4]);
        panel.add(numbtn[5]);
        panel.add(numbtn[6]);
        panel.add(subbtn);
        panel.add(numbtn[7]);
        panel.add(numbtn[8]);
        panel.add(numbtn[9]);
        panel.add(mulbtn);
        panel.add(decButton);
        panel.add(numbtn[0]);
        panel.add(equalbtn);
        panel.add(divbtn);

        frame.add(panel);
        frame.add(deletebtn);
        frame.add(clrbtn);
        frame.add(negatebtn); // Add the negate button to the frame
        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    
    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numbtn[i]) {
                if (negateFlag) {
                    textfield.setText("-" + textfield.getText() + String.valueOf(i));
                    negateFlag = false;
                } else {
                    textfield.setText(textfield.getText() + String.valueOf(i));
                }
                operatorClicked = false;
            }
        }
        if (e.getSource() == decButton) {
            textfield.setText(textfield.getText() + ".");
            operatorClicked = false;
        }
        if (e.getSource() == addbtn || e.getSource() == subbtn || e.getSource() == mulbtn || e.getSource() == divbtn) {
            if (!operatorClicked) {
                num1 = Double.parseDouble(textfield.getText());
                operator = e.getActionCommand().charAt(0);
                textfield.setText(textfield.getText() + " " + operator + " ");
                operatorClicked = true;
            }
        }
        if (e.getSource() == equalbtn) {
            if (!operatorClicked) {
                String[] parts = textfield.getText().split(" ");
                if (parts.length == 3) {
                    num2 = Double.parseDouble(parts[2]);
                    switch (operator) {
                        case '+':
                            result = num1 + num2;
                            break;
                        case '-':
                            result = num1 - num2;
                            break;
                        case '*':
                            result = num1 * num2;
                            break;
                        case '/':
                            if (num2 != 0) {
                                result = num1 / num2;
                            } else {
                                textfield.setText("Error");
                                return;
                            }
                            break;
                    }
                    textfield.setText(String.valueOf(result));
                    num1 = result;
                    operatorClicked = false;
                }
            }
        }
        if (e.getSource() == clrbtn) {
            textfield.setText("");
            operatorClicked = false;
        }
        if (e.getSource() == deletebtn) {
            String string = textfield.getText();
            textfield.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textfield.setText(textfield.getText() + string.charAt(i));
            }
        }
       if(e.getSource()==negatebtn) {
			double temp = Double.parseDouble(textfield.getText());
			temp*=-1;
			textfield.setText(String.valueOf(temp));
		}
    }
}
