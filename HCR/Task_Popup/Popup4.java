/**
 * Created on Mon Sep 09 10:23:20 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.net.HttpURLConnection;
import java.net.URL;
import  java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.SpringLayout;

public class Pop4 extends DefaultJavaTestScript  {
    
    private String myValue;

    public String getMyValue() {
        return myValue;
    }

    public void setMyValue(String myValue) {
        this.myValue = myValue;
    }
    
    public void showPopup4() {
        JPanel panel = new JPanel();
        JLabel lblUrl = new JLabel();
        JTextField tfUrl= new JTextField();
        tfUrl.setPreferredSize(new Dimension(150, 20));
        
        lblUrl.setText("Input url:");
        tfUrl.setText("https://vnexpress.net/");
        
        SpringLayout layout = new SpringLayout(); 
        panel.setLayout(layout);
        
        panel.add(lblUrl);
        panel.add(tfUrl);
        
        layout.putConstraint(SpringLayout.WEST, lblUrl, 5, SpringLayout.WEST, panel);
        layout.putConstraint(SpringLayout.NORTH, lblUrl, 5, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.NORTH, tfUrl, 5, SpringLayout.NORTH, panel);
        layout.putConstraint(SpringLayout.WEST, tfUrl, 15, SpringLayout.EAST, lblUrl);
        
        
        Image image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        
        int result = JOptionPane.showOptionDialog(null,     
                            panel,                          
                            "Popup4: Input URL",           
                            JOptionPane.DEFAULT_OPTION,             
                            JOptionPane.QUESTION_MESSAGE,           
                            new ImageIcon(image),                   
                            new String[] {"Get Modified Date", "Cancel"},
                            "Cancel");
        
        if(result == JOptionPane.YES_OPTION){
           getLastModified(tfUrl.getText().trim());
        }
    }
    private void getLastModified(String myUrl) {
        Date myDate;
        String myResult = "";
        String myStrDate = "";
        try {
            URL url = new URL(myUrl);  
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            
            myResult = connection.getHeaderField("Last-Modified");        
            SimpleDateFormat myFormatDate = new SimpleDateFormat("dd-MM-yyyy");        
            
            if(myResult == null){                
                myDate = new Date();
            }
            else{
                SimpleDateFormat formatDateFromWebsite = new SimpleDateFormat("E, dd MMM yyyy");
                myDate = formatDateFromWebsite.parse(myResult);                              
            }
            myStrDate = myFormatDate.format(myDate);
            
            connection.disconnect();
            
            myValue = myStrDate;
        } catch(ParseException ex){
          
     }catch(IOException ex){
         
     }
            getContext().setVariable("test",myValue);
    }
   public void test() {
      try {
                            showPopup4() ;
      } catch (StopRequestException ex) {
         throw ex;
      }
   }
   
   public static void main(String args[]) {
      Pop4 script = new Pop4();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "Pop4@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
