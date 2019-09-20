/**
 * Created on Tue Aug 27 08:40:12 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;


public class Task12 extends DefaultJavaTestScript  {

     String csvFile = "D:\\countries.csv";
     void Write(String csvFile)
    {
        try{
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "" ;
            String[] tempArr;
            while ((line = br.readLine()) != null) {
                tempArr = line.split("," );
                for (String tempStr : tempArr) {
                    getContext().setVariable("test",  tempStr);                                       
                }                         
            }
            br.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
   public void test() {
       try {          
            Write( csvFile) ;            
        } catch (StopRequestException ex) {
            throw ex;
        }
   }
   
   public static void main(String args[]) {
      Task12 script = new Task12();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "Task12@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
