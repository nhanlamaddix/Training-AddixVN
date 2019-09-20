/**
 * Created on Wed Aug 28 15:50:14 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Task162 extends DefaultJavaTestScript  {
    String path = "D:\\FileTxt.txt";
    void readTxt(String path){
        try {
         FileReader fr = new FileReader(path);
        BufferedReader bf = new BufferedReader(fr);
        String line="";
        
       while ((line = bf.readLine()) != null)
                {  
                getContext().setVariable("test",line);                                   
                 }
                }catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   public void test() {
      try {
          readTxt(path);
      } catch (StopRequestException ex) {
         throw ex;
      }
   }
   
   public static void main(String args[]) {
      Task162 script = new Task162();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "Task162@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
