/**
 * Created on Fri Dec 06 10:08:47 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class LoadListKeywords extends DefaultJavaTestScript  {

   public void test() {
      try {
            String web_name = getContext().getVariableAsString("web_name");
            String fileInput = getContext().getVariableAsString("input_mainn");
            String sheetInput = getContext().getVariableAsString("sheet_mainn");
            String path_project = getContext().getVariableAsString("project_dir");
            
            String path = path_project + "/" + fileInput;
            File fileExcel = new File(path);
            FileInputStream file1 = new FileInputStream(fileExcel);
            
            XSSFWorkbook workbook = new XSSFWorkbook(file1);
            XSSFSheet sheet = workbook.getSheet(sheetInput);
            getContext().setVariable("check_bug", "Loadlistkeywords successful. Keyword : " + sheetInput);
            
            Iterator<Row> rowIt = sheet.iterator();
            int i = 0;

            while(rowIt.hasNext()) {
                Row row = rowIt.next();
                Cell cell = row.getCell(0);
                if(web_name.indexOf("gigazine") >= 0){
                    if(cell.getStringCellValue().length() > 2){
                        getContext().setVariable("keyword"+i, cell.getStringCellValue());
                        i++;
                    }
                }else{
                    getContext().setVariable("keyword"+i, cell.getStringCellValue());
                    i++;
                }
            }
            getContext().setVariable("count_keyword", i);
            file1.close();
      } catch (StopRequestException ex) {
          getContext().setVariable("check_bug", "error: " + ex.toString());
         throw ex;
      }catch(IOException e){
            getContext().setVariable("check_bug", "error: " + e.toString());
            throw new RuntimeException(e);
        }
   }
   
   public static void main(String args[]) {
      LoadListKeywords script = new LoadListKeywords();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "LoadListKeywords@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
