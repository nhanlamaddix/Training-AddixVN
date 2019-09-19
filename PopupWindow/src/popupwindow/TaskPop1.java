/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupwindow;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ADDIX.01
 */
public class TaskPop1 {
    public void showTaskPop1(){
		JPanel pnelBig = new JPanel(new GridLayout(2,1,5,5));
		
		pnelBig.add(new JLabel("Number1: "));
		JTextField num1 = new JTextField(20);
		pnelBig.add(num1);
		
		pnelBig.add(new JLabel("Number2: "));
		JTextField num2 = new JTextField(20);
		pnelBig.add(num2);
		
		
	}
	public static void main(String args[]){
		TaskPop1 task = new TaskPop1();
		task.showTaskPop1();
	}
}
