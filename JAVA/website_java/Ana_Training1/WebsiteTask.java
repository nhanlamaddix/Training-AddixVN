
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


public class WebsiteTask {
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
            System.out.println("Sheet Last Row Num: " + rownum);    
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
            ex.printStackTrace();
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
                    System.out.printf("ERROR", ex.toString());            
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
                for (int i = 1; i < rows.size(); i++) { //first row : so skip it.
                    Element row = rows.get(i);
                    Elements cols = row.select("td");
                    
                    if(i == 1){//Get Salary
                        salary = cols.text();
                    }else if(i == 2){//Get time work
                        timeWorking = cols.text();
                    }else if(i == 4){//Get Traffic
                        traffic = cols.text();
                    }
                    
                }              
                
                System.out.println("================== " + countElement + " ==================");
                System.out.println("Company Name: " + companyName );
                System.out.println("Element Title: " + elementTitle );
                System.out.println("Salary: " + salary );
                System.out.println("Time working: " + timeWorking );
                System.out.println("Traffice: " + traffic );
                                
                countElement ++;
                
                myData.put(countElement, new Table(countElement, pageNo, companyName, elementTitle, salary, timeWorking, traffic));                
            }  
                System.out.println("Count Element:"+countElement);
           
        }catch(IOException ex){
           System.out.printf("errTotalElement", ex.toString());
        }        
    }
     public static void main(String[] args) throws IOException  {
         
                    String myUrl="https://weban.jp/webapp/gen/list/itemSearchList/?A3=2716&CMD=300&FID=275&A1=06&V25=1&Z1=029&V1=16";
                    String excelPath="D:\\WebsiteGUI.xls";
                    String sheetName=" ";
                    int noElementOfPage =20;
                    int totalElementUrl;
                    int totalPageUrl;
                    int countElement=0;
                try {
                    WebsiteTask website = new WebsiteTask();

                    totalElementUrl= website.getTotalPageUrl(myUrl);
                    totalPageUrl = totalElementUrl/noElementOfPage;

                    if((totalElementUrl % noElementOfPage) > 0 ){
                            totalPageUrl++;
                    }
                    System.out.println("Total Element :"+totalElementUrl );
                    System.out.println("Total Page: "+totalPageUrl);

                    Map<Integer,Table> tableData = new HashMap<>();
                    int page=1;
                    website.getPageDetails(countElement, page, myUrl, tableData);
              
                    website.writeExcelFile(excelPath, sheetName, tableData);
                    
                    
               }catch (Exception e) {
                    e.printStackTrace();
            
               }
         
     }
}
