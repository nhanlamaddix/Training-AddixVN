/**
 * Created on Fri Sep 06 16:42:15 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Popup2 extends DefaultJavaTestScript  {
    public void popUp2()
    {
            JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 100, 25);  
            slider.setMinorTickSpacing(5);  
            slider.setMajorTickSpacing(25);  
            slider.setPaintTicks(true);  
            slider.setPaintLabels(true);  
               
            JPanel panel=new JPanel(new GridLayout(2,2,5,5));  
            panel.add(slider);  
          
            Object[] button = {"Get Value","Cancel"};
           
            
            int x= JOptionPane.showOptionDialog(null, panel, "Slider", JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null, button,button[0]);
            if(JOptionPane.YES_OPTION==x)
            {
                int i=slider.getValue();
                
                getContext().setVariable("test",Double.toString(i));
            }
    }
   public void test() {
      try {
                   popUp2();
      } catch (StopRequestException ex) {
         throw ex;
      }
   }
   
   public static void main(String args[]) {
      Popup2 script = new Popup2();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "Popup2@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
