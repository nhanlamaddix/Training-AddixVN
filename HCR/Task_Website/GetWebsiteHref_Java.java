/**
 * Created on Thu Aug 15 16:20:30 ICT 2019
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

public class GetWebsite extends DefaultJavaTestScript  {
    public void FrmWeb() {
        Document doc;
        try {
            doc = Jsoup.connect("https://vnexpress.net").get();
            String title = doc.title();
            System.out.println("Title: "+ title);
            
            Elements links = doc.select("a[href]");
            for( Element link: links) {
                System.out.println("\nlink : " + link.attr("href"));
                            System.out.println("text : " + link.text());
            }
            
        } catch (IOException e) {
             
              e.printStackTrace();
          }
    }
    
   public void test() {
       FrmWeb();
   }
   
   public static void main(String args[]) {
      GetWebsite script = new GetWebsite();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "GetWebsite@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
