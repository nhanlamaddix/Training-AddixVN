/**
 * Created on Mon Sep 23 08:31:31 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;

//Import Jsoup for Website
//Import Apach Poi for Excel
//Import HashMap for Data
import java.util.HashMap;
import java.util.Map;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; 
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
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

public class AnaJava1 extends DefaultJavaTestScript  {
        class Website {
        private int numTable;
        private int page;
        private String companyName;
        private String elementTitle;
        private String salary;
        private String timeWorking;
        private String traffic;

        public Website(){
            this.numTable = 0;
            this.page = 0;
            this.companyName = "";
            this.elementTitle = "";
            this.salary = "";
            this.timeWorking = "";
            this.traffic = "";
        }

        public Website(int numTable, int page, String companyName, String elementTitle, String salary, String timeWorking, String traffic){
            this.numTable = numTable;
            this.page = page;
            this.companyName = companyName;
            this.elementTitle = elementTitle;
            this.salary = salary;
            this.timeWorking = timeWorking;
            this.traffic = traffic;
        }

        //Getter,setter data
        public int getnumTable() {
            return numTable;
        }

        public void setnumTable(int numTable) {
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

        public String gettitle() {
            return elementTitle;
        }

        public void settitle(String elementTitle) {
            this.elementTitle = elementTitle;
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
    private static CellStyle createTitle(Workbook workbook) {
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
      public void writeExcelFile(String fileName, String sheetName, Map<Integer, Website> myData){
        Workbook workbook = null;
        Sheet sheet;
        int size = myData.size();
        String elementTitle = "";
        
        //Open file 
        try{
            File myFile = new File(fileName);
            if(myFile.exists()){//File exist ->read file
                workbook = WorkbookFactory.create(new FileInputStream(myFile));                        
            }else{//File not exist -> new file
                if (myFile.toString().endsWith(".xlsx")) {
                    workbook = new XSSFWorkbook();
                } else {
                    workbook = new HSSFWorkbook();
                }
            }
                
            //create new sheet
             sheet = null;
            int isExistSheet = 0; 
            int sheetNoCurr = workbook.getNumberOfSheets();
            if (sheetNoCurr != 0) {
                for (int i = 0; i < sheetNoCurr; i++) {
                    if (workbook.getSheetName(i).equals(sheetName)) {
                        sheet = workbook.getSheet(sheetName);
                        isExistSheet  = 1;
                        break;
                    } 
                }
                if(isExistSheet == 0){
                    sheet = workbook.createSheet(sheetName);
                    isExistSheet = 0;
                }
            } else {
                // Create new sheet if empty
                        sheet = workbook.createSheet(sheetName);
                       isExistSheet = 0;
            }

            
            int rownum = 0;
            if(isExistSheet == 1) 
                rownum = sheet.getLastRowNum();            
            Cell cell;
            Row row;
            //System.out.println("Sheet Last Row Num: " + rownum);
            
            CellStyle style = createTitle(workbook);

            //Write Header 
            if(rownum == 0){
                //Create row  header
                row = sheet.createRow(rownum);

                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue("Data");
                cell.setCellStyle(style);
            }

            //Write Content
            for(int i = 1; i < myData.size() + 1; i++){
               
              elementTitle = myData.get(i).gettitle() ; 
                         rownum++;
                         row = sheet.createRow(rownum);
              cell = row.createCell(0, CellType.STRING);
               cell.setCellValue(elementTitle );
               
           
             }      
            
        }   catch(Exception ex){
            ex.printStackTrace();
        }
        //Write file
        try{
            FileOutputStream fileOut = new FileOutputStream(new File(fileName));
            workbook.write(fileOut);
            
            fileOut.close();
            workbook.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void getPageData(int countElement, int pageNo, String myUrl, Map<Integer, Website> myData){
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
                    
                    if(i == 1){// Salary
                        salary = cols.text();
                    }else if(i == 2){//time work
                        timeWorking = cols.text();
                    }else if(i == 4){//Traffic
                        traffic = cols.text();
                    }
                    
                }              
                                              
                countElement ++;
                //Save by Hashmap
                myData.put(countElement, new Website(countElement, pageNo, companyName, elementTitle, salary, timeWorking, traffic));                
            }  
            getContext().setVariable("countElement", countElement);
        }catch(IOException ex){
            ex.printStackTrace();
            getContext().setVariable("errTmp", myUrl);
        }        
    }
    public int getTotalPage(String myUrl){
        int totalElement = 0;
        try{
            Document doc = Jsoup.connect(myUrl).get();

            Element totalElementNumber = doc.select("span.dispallcont").first();
            String strNumber = totalElementNumber.text();
            strNumber = strNumber.substring(0, strNumber.length() - 2);
            strNumber = strNumber.replace(",", "");
            totalElement = Integer.parseInt(strNumber);

              }catch(IOException ex){           
                    getContext().setVariable("errTotalElement", ex.toString());            
             }
        return totalElement;
    }
   public void test() {
      try {
                String myUrl = getContext().getVariableAsString("itemUrl");
                int noElementOfPage = Integer.parseInt(getContext().getVariableAsString("noElementOfPage"));
                int totalElementUrl = 0;        
                 totalElementUrl = getTotalPage(myUrl);
       
               int totalPageUrl = totalElementUrl / noElementOfPage;
               if((totalElementUrl % noElementOfPage) > 0){
                 totalPageUrl++;
               }
              getContext().setVariable("totalElement", totalElementUrl);
              getContext().setVariable("totalPage", totalPageUrl);
              
               Map<Integer, Website> tableData = new HashMap<>();
              int countElement = Integer.parseInt(getContext().getVariableAsString("countElement"));
              int page = Integer.parseInt(getContext().getVariableAsString("page"));
              String excelFileName = getContext().getVariableAsString("exportExcelPath");
             String sheetName = getContext().getVariableAsString("strSearch");
        
             writeExcelFile(excelFileName, sheetName, tableData);
      } catch (StopRequestException ex) {
         throw ex;
      }
   }
   
   public static void main(String args[]) {
      AnaJava1 script = new AnaJava1();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "AnaJava1@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
