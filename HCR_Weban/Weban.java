/**
 * Created on Fri Oct 18 08:44:17 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
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

public class Weban extends DefaultJavaTestScript  {
    class Webann {
    private int numTable;
   // private int page;
    private String hourly;
    private String worktime;
    private String treatment;
    private String address;
    

    public Webann(){
        this.numTable = 0 ;
        //this.page = 0;
        this.hourly = "";
        this.worktime = "";
        this.treatment = "";
        this.address = "";
        
    }

    public Webann(int numTable, String hourly, String worktime, String treatment, String address) {
        this.numTable = numTable;
        //this.page = page;
        this.hourly = hourly;
        this.worktime = worktime;
        this.treatment = treatment;
        this.address = address;
        
    }
    
     public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
    }

    public String getWorktime() {
        return worktime;
    }

    public void setWorktime(String worktime) {
        this.worktime = worktime;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getNumTable() {
        return numTable;
    }

    public void setNumTable(int numTable) {
        this.numTable = numTable;
    }

    //public int getPage() {
        //return page;
    //}//

    //public void setPage(int page) {
        //this.page = page;
    //}
}
    private static CellStyle createStyleForTitle(Workbook workbook){
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        
        return style;
    }
    
    public void showWebanBig(String myFile, String excel, Map<Integer, Webann> data) throws IOException{
        Workbook workbook = null;
        Sheet sheet;
        String hourlyWage = "";
        String workingTime = "";
        String treatmentWeban = "";
        String addresss = "";
        
        try{
           
        File file = new File(myFile);
        if (file.exists()) {
            workbook = WorkbookFactory.create(new FileInputStream(file));
            
        }else {
            if(file.toString().endsWith(".xlsx")){
                workbook = new XSSFWorkbook();
                }
            else {
               workbook = new HSSFWorkbook();
             }
        }
        int rownum = 1;
        Cell cell;
        Row row;        
        CellStyle style = createStyleForTitle(workbook);
        sheet = workbook.createSheet("WEBAN");
//        String[] headers = new String[] { "Hourly wage", "working hours", "Treatment","Address" };
//        for (int i=0; i<headers.length; i++) {
//         row = sheet.createRow(i);
//        row.createCell(0).setCellValue(headers[i]);
//        }
        try{
        
        row = sheet.createRow(rownum);
        //Hourly wage
        cell = row.createCell(1,CellType.STRING);
        cell.setCellValue("Hourly wage");
        cell.setCellStyle(style);
        
        //working hours
        cell =row.createCell(2,CellType.STRING);
        cell.setCellValue("working hours");
        cell.setCellStyle(style);
       
       //treatment
        cell =row.createCell(3,CellType.STRING);
        cell.setCellValue("Treatment");
        cell.setCellStyle(style);
       
       //Address
        cell =row.createCell(4,CellType.STRING);
        cell.setCellValue("Address");
        cell.setCellStyle(style);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        for(int i = 2; i < data.size()+1 ; i++){
                
            row = sheet.createRow(i);
            //hourly wage
            hourlyWage = data.get(i-1).getHourly();
            cell = row.createCell(1,CellType.STRING);
            cell.setCellValue(hourlyWage);
            //working time
            workingTime = data.get(i-1).getWorktime();
            cell = row.createCell(2,CellType.STRING);
            cell.setCellValue(workingTime);
			//treatment
			treatmentWeban = data.get(i-1).getTreatment();
			cell = row.createCell(3,CellType.STRING);
            cell.setCellValue(treatmentWeban);
			//Address
			addresss = data.get(i-1).getAddress();
			cell = row.createCell(4,CellType.STRING);
            cell.setCellValue(addresss);

            rownum++;
        }
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
        try{
            
            FileOutputStream outFile = new FileOutputStream(new File(myFile));
            workbook.write(outFile);
            outFile.close();
            workbook.close();
//        String[] headers = new String[] { "Hourly wage", "working hours", "Treatment","Address" };
//        for (int i=0; i<headers.length; i++) {
//        Row row = sheet.createRow(i);
//        row.createCell(0).setCellValue(headers[i]);
//        }
            }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void showWebanSmall(int countWeban, String myUrl, Map<Integer, Webann> data) throws IOException {
        //Weban wb = new Weban();
        
        Document doc = Jsoup.connect(myUrl).get();
        Elements TableBig = doc.select("div.shopSummaryTypeA01.shopSummaryStyle");
//          String title = doc.select("#paydtl").text();
//          System.out.println(title);
//          
//          String title1 = doc.select("#offihrdtllst").text();
//          System.out.println(title1);
//          
//          String title2 = doc.select("#pickUpItems > div > div > div > div.imageBoxTypeB01 > div.box > table > tbody > tr:nth-child(4) > td").text();
//          System.out.println(title2);
//          
//          String title3 = doc.select("#mainContents > div:nth-child(3) > div > div.imageBoxTypeB01 > div > table > tbody > tr:nth-child(5) > td > span").text();
//          System.out.println(title3);
        for(Element myTableBig : TableBig){
                
            String hourly = "";
            String worktime = "";
            String treatment = "";
            String address = "";
               
        treatment = myTableBig.select("div.headingBox p").text();
        Elements myTable = myTableBig.select("table.tableTypeA01");
        Elements rows = myTable.select("tr");
        for(int i = 1; i < rows.size(); i++){
            Element row = rows.get(i);
            Elements cols = row.select("td");
            
            if( i == 1){
                hourly = cols.text();
            }else if(i == 2){
                worktime = cols.text();
            }else if(i == 4){
                address = cols.text();
            }
        }
            //System.out.println("----" +countWeban+ "----");
            //System.out.println("Hourly: " + hourly);
            //System.out.println("Worktime: " + worktime);
            //System.out.println("Treatment: " + treatment);
            //System.out.println("Address: " + address);
            
            //getContext().setVariable("Hourly", hourly);
            //getContext().setVariable("Worktime", worktime);
            //getContext().setVariable("Treatment", treatment);
            //getContext().setVariable("Address", address);
              data.put(countWeban, new Webann(countWeban, hourly, worktime, treatment, address)); 
              countWeban ++;
        }
             getContext().setVariable("countWeban", countWeban);
        //getContext().setVariable("urll", myUrl);
       
    }
    
    

   public void test() {
       try {
        
        String myUrl = getContext().getVariableAsString("myUrl");
        //String naa= myUrl;
        //getContext().setVariable("mymy", naa);
        Map<Integer, Webann> data = new HashMap<>();
        showWebanSmall(0, myUrl, data);
        int countWeban = Integer.parseInt(getContext().getVariableAsString("countWeban"));
        String myFile = getContext().getVariableAsString("file");
        String excel = getContext().getVariableAsString("excel");
        showWebanBig(myFile, excel,  data);
        
       } catch (StopRequestException ex) {
         throw ex;
      } 
      catch(Exception ex){
             
             getContext().setVariable("ERROR", ex.toString() );
          } 
       
   }
   
   
   public static void main(String args[]) {
      Weban script = new Weban();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "Weban@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
