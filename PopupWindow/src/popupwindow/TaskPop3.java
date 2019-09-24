/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupwindow;

import java.awt.GridLayout;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author ADDIX.01
 */
public class TaskPop3 {
    private int[] arr;
    
    public TaskPop3(){
        JPanel pnelBig = new JPanel(new GridLayout(1,2,5,5));
        
        pnelBig.add(new JLabel("Input number field: "));
        JTextField txtInput = new JTextField(10);
        pnelBig.add(txtInput);
        Object[] button = {"Create","Cancel"};
        int x = JOptionPane.showOptionDialog(null, pnelBig, "TaskPop2", 
                JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null, button, button[0]);
        if(JOptionPane.YES_OPTION == x){
            System.out.println(txtInput.getText().trim());
            showTaskPop32(Integer.parseInt(txtInput.getText().trim()));
        }
    }
    public void showTaskPop32(int num){
        JPanel pnelSmall = new JPanel(new GridLayout(num,1,5,5));
        arr = new int[num];
        JLabel[] lbNum = new JLabel[num];
        JTextField[] txtNum = new JTextField[num];
        for(int i = 0; i < num ;i ++  ){
            lbNum[i]=new JLabel();
            lbNum[i].setText("Num "+i);
            txtNum[i] = new JTextField();
            pnelSmall.add(lbNum[i]);
            pnelSmall.add(txtNum[i]);
        }
        
        Object[] buttonn = {"Print","Cancel"};
        int y = JOptionPane.showOptionDialog(null, pnelSmall, "TaskPop2", 
                JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null, buttonn, buttonn[0]);
        
    }
    
    
    public static void main(String[] args) {
        TaskPop3 task = new TaskPop3();
        
    }
}
