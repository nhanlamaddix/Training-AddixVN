
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SliderExample  {
    public SliderExample() {  
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
                
                System.out.println(i);
            }
                
            
}  
public static void main(String s[]) {  
            SliderExample frame=new SliderExample();  
             
    }  
}
