/**
 * Created on Tue Oct 01 14:52:09 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.GridLayout;
import java.awt.TextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TaskPop3 extends DefaultJavaTestScript  {
     private int[] arr;
     public void TaskPop33(){
        JPanel pnelBig = new JPanel(new GridLayout(1,2,5,5));
        
        pnelBig.add(new JLabel("Input number field: "));
        JTextField txtInput = new JTextField(10);
        pnelBig.add(txtInput);
        Object[] button = {"Create","Cancel"};
        int x = JOptionPane.showOptionDialog(null, pnelBig, "TaskPop2", 
                JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null, button, button[0]);
        if(JOptionPane.YES_OPTION == x){
            getContext().setVariable("TaskPop3", txtInput.getText().trim());
            showTaskPop3(Integer.parseInt(txtInput.getText().trim()));
        }
    }
     public void showTaskPop3(int num){
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
            Object[] buttonn = {"Print","Cancel"};
        int y = JOptionPane.showOptionDialog(null, pnelSmall, "TaskPop3", 
                JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null, buttonn, buttonn[0]);
         }
     }
   public void test() {
      try {
                   TaskPop33();
                  } catch (StopRequestException ex) {
                   throw ex;
       }
   }
   
   public static void main(String args[]) {
      TaskPop3 script = new TaskPop3();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "TaskPop3@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
