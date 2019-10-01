/**
 * Created on Tue Aug 20 10:14:35 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Web3 extends DefaultJavaTestScript  {

   public void Frmweb() {
       Document doc;
       try {
            doc= Jsoup.connect("https://vnexpress.net").get(); 
            
            
            System.out.println("Tieu de: "+doc.title());
            System.out.println("Body: ");
            System.out.println(doc.body().text());
            
            System.out.println("\nText of the content class: ");
            System.out.println(doc.getElementsByClass("CONTENT").text());
            
            System.out.println("First: ");
            System.out.println(doc.getElementsByTag("p").first().text());
            
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
   }
   public void test() {
       Frmweb();
   }
   public static void main(String args[]) {
      Web3 script = new Web3();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "Web3@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
