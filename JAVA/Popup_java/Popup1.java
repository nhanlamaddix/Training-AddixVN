import java.awt.GridLayout;
import javax.swing.JButton;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class TaskFinal {
    
    
    public TaskFinal()
    {             
        JPanel panel= new JPanel(new GridLayout(3,2,5,5));
        JLabel lbnum1 = new JLabel("Number 1: ");
        JLabel lbnum2 = new JLabel("Number 2: ");
        JTextField tfnum1 = new JTextField();
       JTextField tfnum2 = new JTextField();
       
        panel.add(lbnum1);
        panel.add(tfnum1 );
        panel.add(lbnum2);
        panel.add(tfnum2 );
                    
        Object[] button = {"Sum","Cancel"};
       
        int result =  JOptionPane.showOptionDialog(null,panel,"Design",JOptionPane.YES_NO_OPTION,JOptionPane.NO_OPTION,null,button,button[0]);
        
        if(JOptionPane.YES_OPTION == result)
        {
            String text1 = tfnum1.getText();
            String text2 = tfnum2.getText();
            double num1=0,num2=0,num=0;
        
            num1=Double.parseDouble(text1);
            num2=Double.parseDouble(text2);
            num=num1+num2;
            System.out.println(num);
        }      
    }
    public static void main(String[] args) {
        new TaskFinal();
    }
    
}
