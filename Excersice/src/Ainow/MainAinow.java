/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ainow;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
public class MainAinow {
    private static CellStyle createStyleForTitle(Workbook workbook){
        Font font = workbook.createFont();
        font.setBold(true);
        CellStyle style = workbook.createCellStyle();
        style.setFont(font);
        
        return style;
    }
    public void showAinow(String myFile, String excel, Map<Integer, Ainow> data) throws IOException{
        Workbook workbook = null;
        Sheet sheet;
        String articleUrl1 = "";
        String title2 = "";
        String postDate2 = "";
        String allText2 = "";
        String domain4="";
        
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
        int rownum = 0;
        Cell cell;
        Row row;        
        CellStyle style = createStyleForTitle(workbook);
        sheet = workbook.createSheet("Ainow");
//        String[] headers = new String[] { "Hourly wage", "working hours", "Treatment","Address" };
//        for (int i=0; i<headers.length; i++) {
//         row = sheet.createRow(i);
//        row.createCell(0).setCellValue(headers[i]);
//        }
        try{
        
        row = sheet.createRow(rownum);
        //articleUrl
        cell = row.createCell(5,CellType.STRING);
        cell.setCellValue("articleUrl");
        cell.setCellStyle(style);
        
        //title
        cell =row.createCell(4,CellType.STRING);
        cell.setCellValue("title");
        cell.setCellStyle(style);
       
       //postDate
        cell =row.createCell(3,CellType.STRING);
        cell.setCellValue("postDate");
        cell.setCellStyle(style);
       
       //allText
        cell =row.createCell(2,CellType.STRING);
        cell.setCellValue("allText");
        cell.setCellStyle(style);
        //domain
        cell =row.createCell(1,CellType.STRING);
        cell.setCellValue("domain");
        cell.setCellStyle(style);
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        for(int i = 1; i < data.size()+1 ; i++){
                
            row = sheet.createRow(i);
            //ArticleUrl
            articleUrl1 = data.get(i-1).getArticleUrl();
            cell = row.createCell(5,CellType.STRING);
            cell.setCellValue(articleUrl1);
            //Title
            title2 = data.get(i-1).getTitle();
            cell = row.createCell(4,CellType.STRING);
            cell.setCellValue(title2);
            //PostDate
            postDate2 = data.get(i-1).getPostDate();
            cell = row.createCell(3,CellType.STRING);
            cell.setCellValue(postDate2);
            //AllText
            allText2 = data.get(i-1).getAllText();
            cell = row.createCell(2,CellType.STRING);
            cell.setCellValue(allText2);
            
            domain4 = data.get(i-1).getDomain();
            cell = row.createCell(1,CellType.STRING);
            cell.setCellValue(domain4);    
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
            }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    public void showSearch(int countWeban,String myUrl, Map<Integer, Ainow> data) throws IOException{
        
        
        
        
        try{
        
        String []key = new String[]{"AI","IoT","ハプティクス","ロボティクス","ロボット"
        ,"触覚","遠隔操作","遠隔会議","遠隔医療"};
        
        

        for(int i = 0; i < key.length ; i++){
//            for(int t = 0; key.length <= 5; t++){
            //System.out.println(key[i]);
            String name = "";
            String listTab = "";
            String articleUrl = "";
            String title = "";
            String postDate = "";
            String allText = "";
            String domain="";
            String webname = "";
            String keyword = "";
            
            //research URL
            String url="https://ainow.ai/?s=" + key[i];
            System.out.println("============" + url + "============");
            Document doc = Jsoup.connect(url).get();
            Elements links = doc.select("#main > div > article > a");
            
            ArrayList<String> arr_urlitem = new ArrayList<String>();
            
            for (Element link : links) {
                articleUrl =   link.attr("href"); 
                //articleUrl = links.text();
                System.out.println("articleUrl: " + articleUrl);
                arr_urlitem.add(articleUrl);
               
            }
            //System.out.println("size: " + arr_urlitem.size());
            ArrayList<String> title1 = new ArrayList<String>();
            ArrayList<String> dateposts = new ArrayList<String>();
            ArrayList<String> text = new ArrayList<String>();
            ArrayList<String> domain1 = new ArrayList<String>();
            ArrayList<String> webname1 = new ArrayList<String>();
            for(int j=0;j< arr_urlitem.size();j++){
                
                Document docurlitem = Jsoup.connect((arr_urlitem.get(j)).toString()).get();
                //title data
                title = docurlitem.title();
                System.out.println("title: " + title);
                title1.add(docurlitem.title());
                
                //domain
                Elements domain2 = docurlitem.select("#inner-footer > p > a");
                for( Element domain3 : domain2){
                    domain = domain3.attr("href");
                    System.out.println("domain: " + domain);
                    domain1.add(domain);
                }
                //webname
                
                //class_href
                
                
 
                //date date
                Elements date = docurlitem.select("#main > article > header > p > time");
                for( Element datepost : date){
                    postDate = datepost.text();
                    System.out.println("postDate: " + postDate);
                    dateposts.add(postDate);
                }
                //all text data
                Elements text3 = docurlitem.select("#main > article > section");
                for (Element text4 : text3){
                allText = text4.text();
                System.out.println("all Test: " + allText );
                text.add(allText);
                
                
                data.put(countWeban , new Ainow(countWeban,articleUrl,title,postDate,allText,domain, keyword, webname));
                countWeban ++;
            }
            // sum of elements
            System.out.println("Count Element:"+countWeban);
                
        }
        
    }
//        }
        }catch(Exception ex){
            ex.printStackTrace();
        }
}
    public static void main(String[] args) throws IOException {
        String myUrl ="" ;
        String myFile="D:\\Ainow.xls";
        String excel =" ";
        
        try{
        MainAinow main = new MainAinow();
        Map<Integer,Ainow> data = new HashMap<>();
        main.showSearch(0,myUrl, data);
        main.showAinow(myFile, excel, data);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
