/**
 * Created on Wed Jan 23 13:38:48 ICT 2019
 * HeartCore Robo Desktop v4.4.1 (Build No. 4.4.1-20180330.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;
import java.io.*;
import javax.script.*;
import java.net.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LastUpdate extends DefaultJavaTestScript  {

   public void test() {
      try {
          System.out.println("date: bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
          
          
          URL url = null;
          try {
              url = new URL("https://stackoverflow.com/questions/3095775/get-modified-date-of-web-resource-in-java");
          }catch (MalformedURLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          HttpURLConnection httpCon = null;
          try {
              httpCon = (HttpURLConnection) url.openConnection();
          }catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
          long date = httpCon.getLastModified();
          getContext().setVariable("last_date", date);
      } catch (StopRequestException ex) {
         throw ex;
      } catch (IOException ex) {
         ex.printStackTrace();
        throw new IllegalStateException(ex);
      }
   }
   
   public static void main(String args[]) {
       System.out.println("dateaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa: ");
      LastUpdate script = new LastUpdate();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "LastUpdate@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
      String result = getLastUpdate("https://sotoene.chuden.jp/pc/calendar.html");
      System.out.println("date: " + result);
   }
   
   public static String getLastUpdate(String url_input){
       URL url = null;
       try {
           url = new URL(url_input);
       }catch (MalformedURLException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       HttpURLConnection httpCon = null;
       try {
           httpCon = (HttpURLConnection) url.openConnection();
       }catch (IOException e) {
           // TODO Auto-generated catch block
           e.printStackTrace();
       }
       long last_date = httpCon.getLastModified();
       DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
       String reportDate = df.format(last_date);
       return reportDate;
   }
}
