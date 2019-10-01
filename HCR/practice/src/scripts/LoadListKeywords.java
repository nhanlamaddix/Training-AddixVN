/**
 * Created on Wed Sep 25 02:39:03 ICT 2019
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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LoadListKeywords extends DefaultJavaTestScript  {

    public void test() {
        try {
            //get variable from main.tpr
            String web_name = getContext().getVariableAsString("web_name");
            String fileName = getContext().getVariableAsString("input_file");
            String sheetName = getContext().getVariableAsString("sheet_keyword");
            String path_project = getContext().getVariableAsString("project_dir");
            
            //get current path of project
            String path = path_project + "/" + fileName;
            
            //open excel file with sheet name is keywords and get all keywords
            File excelFile = new File(path);
            FileInputStream fis = new FileInputStream(excelFile);

            // we create an XSSF Workbook object for our XLSX Excel File
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            // choose sheet name
            XSSFSheet sheet = workbook.getSheet(sheetName);
            getContext().setVariable("check_detail", "Load list keywords success. Keyword of sheet: " + sheetName);

            //we iterate on rows
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
            getContext().setVariable("total_keyword", i);
            fis.close();
            
        } catch (StopRequestException ex) {
            getContext().setVariable("check_detail", "error: " + ex.toString());
            throw ex;
        } catch(IOException e){
            getContext().setVariable("check_detail", "error: " + e.toString());
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
