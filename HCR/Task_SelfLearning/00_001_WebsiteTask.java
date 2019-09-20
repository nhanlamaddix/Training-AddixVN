/**
 * Created on Tue Sep 17 17:00:47 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class WebsiteTask extends DefaultJavaTestScript  {
      class Table{
                       private int numTable;
        private int page;
        private String companyName;
        private String titleElement;
        private String salary;
        private String timeWorking;
        private String traffic;

        public Table(){
            this.numTable = 0;
            this.page = 0;
            this.companyName = "";
            this.titleElement = "";
            this.salary = "";
            this.timeWorking = "";
            this.traffic = "";
        }

        public Table(int numTable, int page, String companyName, String titleElement, String salary, String timeWorking, String traffic){
            this.numTable = numTable;
            this.page = page;
            this.companyName = companyName;
            this.titleElement = titleElement;
            this.salary = salary;
            this.timeWorking = timeWorking;
            this.traffic = traffic;
        }
public int getNumTable() {
            return numTable;
        }

        public void setNumTable(int numTable) {
            this.numTable = numTable;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getTitleElement() {
            return titleElement;
        }

        public void setTitleElement(String titleElement) {
            this.titleElement = titleElement;
        }

        public String getSalary() {
            return salary;
        }

        public void setSalary(String salary) {
            this.salary = salary;
        }

        public String getTimeWorking() {
            return timeWorking;
        }

        public void setTimeWorking(String timeWorking) {
            this.timeWorking = timeWorking;
        }

        public String getTraffic() {
            return traffic;
        }

        public void setTraffic(String traffic) {
            this.traffic = traffic;
        }
}
private static CellStyle createStyleForTitle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    
    public void writeExcelFile(String fileName, String sheetName, Map<Integer, Table> myData){
        Workbook workbook = null;
        Sheet sheet;
        int size = myData.size();
        String elementTitle = "";
        String elementSalary="";
        String elementTime="";
        String elementTraffic="";
        try{
            File myFile = new File(fileName);
            if(myFile.exists()){
                workbook = WorkbookFactory.create(new FileInputStream(myFile));                        
            }else{
                if (myFile.toString().endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook();
                } else {
                    workbook = new HSSFWorkbook();
                }
            }
                
            
            sheet = null;
            int isExistSheet = 0; 
            int sheetNoCurr = workbook.getNumberOfSheets();
            //check sheet 
            if (sheetNoCurr != 0) {
                for (int i = 0; i < sheetNoCurr; i++) {
                    if (workbook.getSheetName(i).equals(sheetName)) {
                        sheet = workbook.getSheet(sheetName);
                        isExistSheet = 1;
                        break;
                    } 
                }
                
            } else {
                // Create new sheet  if empty
                sheet = workbook.createSheet(sheetName);
                isExistSheet = 0;
            }
            int rownum =0;
            if(isExistSheet == 1) 
                rownum = sheet.getLastRowNum();
            Cell cell;
            Row row;
             //Set style sheet
            //System.out.println("Sheet Last Row Num: " + rownum);    
            CellStyle style = createStyleForTitle(workbook);

            //Write Header
            if(rownum == 0){
                //Create row 1 header
                row = sheet.createRow(rownum); 

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Element Title");
                cell.setCellStyle(style);     
                
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue("Salary");
                cell.setCellStyle(style);
                
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue("Time Work");
                cell.setCellStyle(style);
                
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue("Traffic");
                cell.setCellStyle(style);
                 
            }
            
            //Write Content
            for(int i = 1; i < myData.size()+1;i++){
                 row = sheet.createRow(rownum);       
                //Title
                elementTitle = myData.get(i).getTitleElement();            
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(elementTitle);
                
                //Salary
                elementSalary = myData.get(i).getSalary();                    
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(elementSalary);
                
                //timeWorking
                elementTime = myData.get(i).getTimeWorking();                    
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(elementTime);
                
                //Traffic
                elementTraffic = myData.get(i).getTraffic();                    
                cell = row.createCell(3, CellType.STRING);
                cell.setCellValue(elementTraffic);
                
                rownum++;
            }
            
           
        }catch(Exception ex){
            ex.printStackTrace();
        }
        //Write file
        try{
            FileOutputStream fileOut = new FileOutputStream(new File(fileName));
            workbook.write(fileOut);
            
            fileOut.close();
            workbook.close();
        }catch(Exception ex){
            getContext().setVariable("errorElement", ex.toString());    
        }
    }
    public int getTotalPageUrl(String myUrl){
        int totalElement = 0;
        try{
            Document doc = Jsoup.connect(myUrl).get();

            Element totalElementNumber = doc.select("span.dispallcont").first();
            String strNumber = totalElementNumber.text();
            strNumber = strNumber.substring(0, strNumber.length() - 2);
            strNumber = strNumber.replace(",", "");
            totalElement = Integer.parseInt(strNumber);

              }catch(IOException ex){           
                              getContext().setVariable("Error", ex.toString());    
             }
        return totalElement;
    }
    public void getPageDetails(int countElement, int pageNo, String myUrl, Map<Integer, Table> myData){
        try{
                 Document doc = Jsoup.connect(myUrl).get();
            Elements parentTable = doc.select("div.shopSummaryTypeA01.shopSummaryStyle");            

            for(Element myTableParent : parentTable){
                String companyName = "";
                String elementTitle = "";
                String salary = "";
                String timeWorking = "";
                String traffic = "";
            
                companyName = myTableParent.select("div.W585 p").text();
                elementTitle = myTableParent.select("div.W585 h2").text();
                
                Elements myTable = myTableParent.select("table.tableTypeA01");
              
                Elements rows = myTable.select("tr");
                for (int i = 1; i < rows.size(); i++) { 
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    
                    if(i == 1){//Get Salary
                        salary = cols.text();
                    }else if(i == 2){//Get time 
                        timeWorking = cols.text();
                    }else if(i == 4){//Get Traffic
                        traffic = cols.text();
                    }
                    
                }                             
                countElement ++;
                //Save data 
                myData.put(countElement, new Table(countElement, pageNo, companyName, elementTitle, salary, timeWorking, traffic));    
            }  
            getContext().setVariable("countElement", countElement);
           
        }catch(IOException ex){
            ex.printStackTrace();
            getContext().setVariable("Error",ex.toString());
        }        
    }

   public void test() {
      try {
                             String myUrl=getContext().getVariableAsString("Url");
                            int noElementOfPage = Integer.parseInt(getContext().getVariableAsString("noElementOfPage"));
                            int totalElementUrl = 0;        
                                   totalElementUrl = getTotalPageUrl(myUrl);
                             int totalPageUrl = totalElementUrl / noElementOfPage;
                            if((totalElementUrl % noElementOfPage) > 0){
                                         totalPageUrl++;
                               }
                               getContext().setVariable("totalElement", totalElementUrl);
                               getContext().setVariable("totalPage", totalPageUrl);
                               
                               Map<Integer, Table> tableData = new HashMap<>();
                              int countElement = Integer.parseInt(getContext().getVariableAsString("countElement"));
                              int page = Integer.parseInt(getContext().getVariableAsString("page"));
                               
                                                                                       String excelFileName = getContext().getVariableAsString("ExcelPath");
                               String sheetName = getContext().getVariableAsString("strSearch");
                               writeExcelFile(excelFileName, sheetName, tableData);
                               
                               
      } catch (StopRequestException ex) {
         throw ex;
      }
      catch(Exception ex){
             
             getContext().setVariable("ErrorTest", ex.toString() );
          } 
   }
   
   public static void main(String args[]) {
      WebsiteTask script = new WebsiteTask();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "WebsiteTask@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
