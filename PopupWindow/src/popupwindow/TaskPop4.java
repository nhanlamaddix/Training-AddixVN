/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popupwindow;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.lang.model.util.Elements;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author ADDIX.01
 */
public class TaskPop4 {
    
    public  TaskPop4(){
        JPanel pnelInput = new JPanel();
        
        pnelInput.add(new JLabel("Input URL: "));
        JTextField txtInput = new JTextField(12);
        //txtInput.setText("https://vnexpress.net");
        pnelInput.add(txtInput);
        
        Object[] button = {"Get Modified Date", "Cancel"};
        
        int x = JOptionPane.showOptionDialog(null, pnelInput, "TaskPop4", 
                JOptionPane.YES_NO_OPTION, JOptionPane.NO_OPTION, null , button, 
                button[0]);
        if(x==JOptionPane.YES_OPTION){
            showTaskPop4(txtInput.getText().trim());
        }
    }
    
    public void showTaskPop4(String url) {
        
        try{
            Connection.Response rs2 = Jsoup.connect("https://news.zing.vn")
                .ignoreContentType(true)
                .execute(); 
            System.out.println("Header = " + rs2.header("Last-Modified"));

            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
            Date date = new Date(System.currentTimeMillis());
            System.out.println("date now: " + formatter.format(date));
        }
        catch (IOException e){
            System.err.println(e.toString());
        }
        
    }
    public static void main(String[] args) {
        TaskPop4 task = new TaskPop4();
        
    }
}
