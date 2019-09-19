/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupwindow;

import java.awt.GridLayout;
import javafx.scene.control.Slider;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;

/**
 *
 * @author ADDIX.01
 */
public class TaskPop2 extends JFrame{
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
            System.out.println(i);
        }
    }
    public static void main(String[] args) {
        TaskPop2 task = new TaskPop2();
        task.showTaskPop2();
    }
}
