package com.demo.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener{
	
	JTextField t;
	JButton [] numbers=  new JButton[10];
	JButton [] functions= new JButton[9];
	JButton add ,sub,mul ,div;
	JButton decimal,equals,delete,clr,neg;
	JPanel panel;
	
	Font myFont = new Font("Comic Sans MS", Font.PLAIN, 30);
	
	double num1=0,num2=0,res=0;
	char opertor;
	
	public Calculator() {
		JFrame frame= new JFrame("Calculator");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(420, 550);
		frame.setLayout(null);
		
		t=new JTextField();
		t.setBounds(50,25,300,50);
		t.setFont(myFont);
		t.setEditable(false);
		
		add= new JButton("+");
		sub= new JButton("-");
		mul= new JButton("*");
		div= new JButton("/");
		decimal= new JButton(".");
		equals= new JButton("=");
		delete= new JButton("del");
		clr = new JButton("clr");
		neg= new JButton("(-)");
		
		functions[0] =add;
		functions[1] =sub;
		functions[2] =mul;
		functions[3] =div;
		functions[4] =decimal;
		functions[5] =equals;
		functions[6] =delete;
		functions[7] =clr;
		functions[8] =neg;
		
		for(int i=0; i<9; i++)
		{
			functions[i].addActionListener(this);
			functions[i].setFont(myFont);
			functions[i].setFocusable(false);
		}
		
		for(int i=0; i<10; i++)
		{
			numbers[i]= new JButton(String.valueOf(i));
			numbers[i].addActionListener(this);
			numbers[i].setFont(myFont);
			numbers[i].setFocusable(false);
		}
		
		neg.setBounds(50,430,100,50);
		delete.setBounds(150,430,100,50);
		clr.setBounds(250,430,100,50);
		
		panel= new JPanel();
		panel.setBounds(50, 100, 300, 300);
		panel.setLayout(new GridLayout(4,4,10,10));
		
		
		panel.add(numbers[7]);
		panel.add(numbers[8]);
		panel.add(numbers[9]);
		panel.add(sub);
		panel.add(numbers[4]);
		panel.add(numbers[5]);
		panel.add(numbers[6]);
		panel.add(add);
		panel.add(numbers[1]);
		panel.add(numbers[2]);
		panel.add(numbers[3]);
		panel.add(div);
		panel.add(numbers[0]);
		panel.add(decimal);
		panel.add(equals);
		panel.add(mul);
		
		frame.add(panel);
		frame.add(neg);
		frame.add(delete);
		frame.add(clr);
		frame.add(t);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		Calculator c = new Calculator(); 
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	
		for(int i=0;i<10;i++) {
			if(e.getSource() == numbers[i]) {
				t.setText(t.getText().concat(String.valueOf(i)));
			}
		}
		if(e.getSource()==decimal)
			t.setText(t.getText().concat("."));
		if(e.getSource()== add) {
			num1= Double.parseDouble(t.getText());
			opertor= '+';
			t.setText("");
		}
		if(e.getSource()== sub) {
			num1= Double.parseDouble(t.getText());
			opertor= '-';
			t.setText("");
		}
		if(e.getSource()== mul) {
			num1= Double.parseDouble(t.getText());
			opertor= '*';
			t.setText("");
		}
		if(e.getSource()== div) {
			num1= Double.parseDouble(t.getText());
			opertor= '/';
			t.setText("");
		}
		if(e.getSource()==equals) {
			num2=Double.parseDouble(t.getText());
			switch (opertor){
			case'+':
				res=num1+num2;
				break;
			case'-':
				res=num1-num2;
				break;
			case'*':
				res=num1*num2;
				break;
			case'/':
				res=num1/num2;
				break;
			}
			t.setText(String.valueOf(res));
			num1=res;
		}
		if(e.getSource()==clr) {
			t.setText("");
		}
		if(e.getSource()==delete) {
			String str= t.getText();
			t.setText(str.substring(0, str.length() - 1));
		}
		if(e.getSource()== neg) {
			double temp= Double.parseDouble(t.getText());
			temp*=-1;
			t.setText(String.valueOf(temp));
		}
	}

}
