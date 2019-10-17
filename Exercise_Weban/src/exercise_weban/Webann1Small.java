/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise_weban;

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

/**
 *
 * @author ADDIX.01
 */
public class Webann1Small {
    private static CellStyle createStyleForTitle(Workbook workbook){
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        
        return style;
    }
    
    public void showWebanBig(String myFile, String excel, Map<Integer, Weban> data) throws IOException{
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
        sheet = workbook.createSheet("bananaa");
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
            //hourly wage
            treatmentWeban = data.get(i-1).getTreatment();
            cell = row.createCell(3,CellType.STRING);
            cell.setCellValue(treatmentWeban);
            //hourly wage
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
    
    public void showWebanSmall(int countWeban, int pageNo, String myUrl, Map<Integer, Weban> data) throws IOException {
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
            System.out.println("Hourly: " + hourly);
            System.out.println("Worktime: " + worktime);
            System.out.println("Treatment: " + treatment);
            System.out.println("Address: " + address);
            
            
            data.put(countWeban, new Weban(countWeban, pageNo, hourly, worktime, treatment, address)); 
            countWeban ++;
        }
        System.out.println("Count Element:"+countWeban);
        
    }
    public static void main(String[] args) throws IOException{
        String myUrl="https://weban.jp/webapp/gen/list/itemSearchList/?A3=2716&CMD=300&FID=275&A1=06&V25=1&Z1=029&V1=16s";
        String myFile="D:\\Webann.xls";
        String excel =" ";
        try{
        Webann1Small wb = new Webann1Small();
        Map<Integer,Weban> dataa = new HashMap<>();
       
        wb.showWebanSmall(0, 0, myUrl, dataa);
        wb.showWebanBig(myFile, excel, dataa);
       //   tableData.get(0).getHourly();
       //System.out.println(dataa.remove(1));
//        System.out.println(dataa.get(9).getHourly());
//        System.out.println(dataa.get(9).getWorktime());
//        System.out.println(dataa.get(9).getTreatment());
//        System.out.println(dataa.get(9).getAddress());
        }catch (Exception ex){
            ex.printStackTrace();
        }
          
    }
}
