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

public class Popup4 {
    private String myValue;

    public String getMyValue() {
        return myValue;
    }

    public void setMyValue(String myValue) {
        this.myValue = myValue;
    }
    
    public Popup4()
    {
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
    private void getLastModified(String myUrl){
        Date myDate;
        String myResult = "";
        String myStrDate = "";
       
        try{            
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
        }catch(IOException ex){
            System.out.println(ex.toString());
        }catch(ParseException ex){
            System.out.println(ex.toString());
        } 
        System.out.println("Last modified date: " + getMyValue()); 
}
    public static void main(String []args){
       new Popup4();
              
    }
}
