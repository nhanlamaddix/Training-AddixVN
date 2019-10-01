/**
 * Created on Tue Oct 01 13:40:45 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class TaskPop1 extends DefaultJavaTestScript  {
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
                    getContext().setVariable("TaskPop1", num);
        }
    }
   public void test() {
           try{
                    showTaskPop1();
               }
               catch (StopRequestException ex) {
         throw ex;
      } 
   }

   public static void main(String args[]) {
      TaskPop1 script = new TaskPop1();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "TaskPop1@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
