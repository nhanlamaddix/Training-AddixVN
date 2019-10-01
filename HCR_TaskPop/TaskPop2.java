/**
 * Created on Tue Oct 01 14:20:17 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;


import javafx.scene.control.Slider;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class TaskPop2 extends DefaultJavaTestScript  {
     public void showTaskPop2(){
                JPanel pnelSlider = new JPanel();
                JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
                slider.setMajorTickSpacing(25);
                slider.setMinorTickSpacing(5);
                slider.setPaintTicks(true);
                slider.setPaintLabels(true);
                pnelSlider.add(slider);
        
                Object []button = {"Get Value","Cancel"};
                int x = JOptionPane.showOptionDialog(null, pnelSlider, "TaskPop2", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null, button, button[0]);
                if(JOptionPane.YES_OPTION == x){
                      int i = slider.getValue();
                      getContext().setVariable("TaskPop2", i);
         }     
     }
  
   public void test() {
      try {
                    showTaskPop2();
           } catch (StopRequestException ex) {
         throw ex;
       
       }
   }
   
public static void main(String args[]) {
      TaskPop2 script = new TaskPop2();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "TaskPop2@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}