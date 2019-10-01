/**
 * Created on Tue Oct 01 15:06:03 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
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
public class TaskPop4 extends DefaultJavaTestScript  {
     public  void TaskPop44(){
        JPanel pnelInput = new JPanel();
        
        pnelInput.add(new JLabel("Input URL: "));
        JTextField txtInput = new JTextField(12);
        txtInput.setText("http://www.nfib.com/Portals/0/PDF/sbet/sbet201402.pdf");
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
            //Connection.Response rs2 = Jsoup.connect("https://news.zing.vn")
            Connection.Response rs2 = Jsoup.connect(url)
                .ignoreContentType(true)
                .execute();
                String tmp = rs2.header("Last-Modified");
                if( tmp == ""){ 
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
                    Date date = new Date(System.currentTimeMillis());
                    tmp=formatter.format(date);
                }
                 getContext().setVariable("TaskPop4","date now" + tmp );
            }
        catch (IOException e){
            
        }
        
    }
   public void test() {
      try {
                        TaskPop44();
             } catch (StopRequestException ex) {
                   throw ex;
        }
   }
   
   public static void main(String args[]) {
      TaskPop4 script = new TaskPop4();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "TaskPop4@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
