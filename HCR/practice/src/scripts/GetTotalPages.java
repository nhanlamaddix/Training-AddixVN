/**
 * Created on Wed Sep 25 04:13:50 ICT 2019
 * HeartCore Robo Desktop v5.0.3 (Build No. 5.0.3-20190618.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

import java.net.*;
import java.io.*;
import java.util.*;
import java.text.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GetTotalPages extends DefaultJavaTestScript  {

    public void test() {
        try {
            String url_item = getContext().getVariableAsString("url_item");
            String class_next_page = getContext().getVariableAsString("class_next_page");
            
            int totalpage = 0;
            Document doc = Jsoup.connect(url_item).get();
            Elements divTag = doc.select(class_next_page);
            
            if(divTag.size() != 0 ){
                totalpage = divTag.size();
            }
            //return totalpage;
            getContext().setVariable("number_pages", totalpage);
            
        } catch (StopRequestException ex) {
            throw ex;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }

    public static void main(String args[]) {
    GetTotalPages script = new GetTotalPages();
    ApplicationSupport robot = new ApplicationSupport();
    AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "GetTotalPages@" + Integer.toHexString(script.hashCode()), args, System.out, false);
    new Thread(runnable).start();
    }
}
