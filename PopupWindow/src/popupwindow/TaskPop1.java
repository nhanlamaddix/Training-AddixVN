/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupwindow;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ADDIX.01
 */
public class TaskPop1 {
    public void showTaskPop1(){
		JPanel pnelBig = new JPanel(new GridLayout(2,2,5,5));
		
		pnelBig.add(new JLabel("Number1: "));
		JTextField txtNum1 = new JTextField(10);
		pnelBig.add(txtNum1);
		
		pnelBig.add(new JLabel("Number2: "));
		JTextField txtNum2 = new JTextField(10);
		pnelBig.add(txtNum2);
		
                Object [] button = {"Sum","Cancel"};
                int x = JOptionPane.showOptionDialog(null, pnelBig, "TaskPop1", 
                        JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null, button, button[0]);
                if(JOptionPane.YES_OPTION == x){
                    String a = txtNum1.getText();
                    String b = txtNum2.getText();
                    double num , num1 =0, num2 =0;
                    num1 = Double.parseDouble(a);
                    num2 = Double.parseDouble(b);
                    num = num1 + num2;
                    System.out.println(num);                
                }
	}
	public static void main(String args[]){
		TaskPop1 task = new TaskPop1();
		task.showTaskPop1();
	}
}
