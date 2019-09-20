/**
 * Created on Wed Aug 28 15:35:04 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Task161 extends DefaultJavaTestScript  {
    String str ="AAAAAA";
         String path ="D:\\FileTxt.txt";
    void writeTxt(String Str,String text){
        try {
          
        FileOutputStream fs = new  FileOutputStream( path , true);
        PrintWriter pw = new  PrintWriter(fs);
        pw.printf(str);
        pw.close();
        pw.flush();
        fs.close();
    } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   public void test() {
       try {          
                   writeTxt(path,str);
        } catch (StopRequestException ex) {
            throw ex;
        }
   }
   
   public static void main(String args[]) {
      Task161 script = new Task161();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "Task161@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
