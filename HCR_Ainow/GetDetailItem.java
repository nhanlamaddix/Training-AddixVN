/**
 * Created on Fri Dec 06 11:00:19 ICT 2019
 * HeartCore Robo Desktop v5.0.1 (Build No. 5.0.1-20190308.1)
 **/
package scripts;
import com.tplan.robot.scripting.*;
import com.tplan.robot.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.text.*;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Header; 
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
public class GetDetailItem extends DefaultJavaTestScript  {
    public ArrayList<String> arr_href;
    
   public void test() {
      try {
            String url_item = getContext().getVariableAsString("url_item");
            String class_href = getContext().getVariableAsString("class_href");
            String domain = getContext().getVariableAsString("domain");
            
            String amount_href = getContext().getVariableAsString("amount_href");
            String total_href_getted = getContext().getVariableAsString("total_href_getted");
            int total_getted = Integer.parseInt(total_href_getted);
            
            Document doc = Jsoup.connect(url_item).get();
            Elements hrefs = doc.select(class_href);
            
            arr_href = new ArrayList<String>();
            
            for(int i = 0; i < hrefs.size(); i++){
                String href = hrefs.get(i).attr("href");
                if(href.indexOf(domain) < 0){
                    href = domain + href;
                }
                arr_href.add(href);
            }
            
            ArrayList<String> arr_href_top = this.getTopRequest(arr_href);
            
           
            this.getDetailHrefItem(arr_href_top);
            
           
            getContext().setVariable("total_href_getted", total_getted+arr_href_top.size());
            
      } catch (StopRequestException ex) {
         throw ex;
      } catch (IOException ex) {
         ex.printStackTrace();
        throw new IllegalStateException(ex);
      }
   }
   public ArrayList<String> getTopRequest(ArrayList<String> arr_hrefs){
        
        ArrayList<String> arr_top = new ArrayList<String>();
        
        String amount_href = getContext().getVariableAsString("amount_href");
        String total_href_getted = getContext().getVariableAsString("total_href_getted");
        int total_req = Integer.parseInt(amount_href  );
        int total_getted = Integer.parseInt(total_href_getted);
        
        for(int i = 0; i < (total_req-total_getted) && i < arr_hrefs.size(); i++){
            arr_top.add(arr_hrefs.get(i));
            
        }
        return arr_top;
    }
   public void getDetailHrefItem(ArrayList<String> arr_hrefs){
        String no_num = getContext().getVariableAsString("no_num");
        int num = Integer.parseInt(no_num);
        
        for(int i = 0; i < arr_hrefs.size(); i++){
            num++;
            String str_href = arr_hrefs.get(i);
            getDetailContent(str_href, num);
        }
        getContext().setVariable("no_num", num);
    }
    public void getDetailContent(String str_url, int no_num){
        try {
            String class_title = getContext().getVariableAsString("class_title");
            String class_date_post = getContext().getVariableAsString("class_date_post");
            String class_source = getContext().getVariableAsString("class_source");
            String class_content = getContext().getVariableAsString("class_content");
            
            String title = "";
            String post_date = "";
            String source = "";
            String content = "";
            
            Document doc = Jsoup.connect(str_url).get();
            title = doc.select(class_title).text();
            post_date = doc.select(class_date_post).text();
            content = doc.select(class_content).text();
           
            this.writeDataToExcelFile(no_num, str_url, title, post_date, source, content);
        } catch (StopRequestException ex) {
            throw ex;
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new IllegalStateException(ex);
        }
    }
    public void writeDataToExcelFile(int no_num, String str_url, String str_title, String str_post_date, String str_source, String str_content){
        try {
            String web_name = getContext().getVariableAsString("web_name");
            String url_web = getContext().getVariableAsString("url_web");
            String keyword = getContext().getVariableAsString("keyword");
            String project_dir = getContext().getVariableAsString("project_dir");
            
            
            String out_file = getContext().getVariableAsString("out_file");
            String path_out_file = project_dir + "/" + out_file;
            
            File excelFile = new File(path_out_file);
            FileInputStream fis = new FileInputStream(excelFile);
            
            
            XSSFWorkbook workbook = new XSSFWorkbook(fis);

            
            XSSFSheet sheet = workbook.getSheetAt(0);

            int lastRow = sheet.getLastRowNum();
            Row row = sheet.createRow(++lastRow);
            row.createCell(0).setCellValue(no_num);
            row.createCell(1).setCellValue(web_name);
            row.createCell(2).setCellValue(url_web);
            row.createCell(3).setCellValue(keyword);
            row.createCell(4).setCellValue(str_url);
            row.createCell(5).setCellValue(str_title);
            row.createCell(6).setCellValue(str_post_date);
            row.createCell(7).setCellValue(str_source);
            row.createCell(8).setCellValue(str_content);

            fis.close();
            FileOutputStream output_file =new FileOutputStream(new File(path_out_file));
            
            workbook.write(output_file);
            output_file.close();

            getContext().setVariable("check_bug", "successful!");
        }catch(Exception e){
            getContext().setVariable("check_bug", "error. " + e.toString());
        }
    }
   
   public static void main(String args[]) {
      GetDetailItem script = new GetDetailItem();
      ApplicationSupport robot = new ApplicationSupport();
      AutomatedRunnable runnable = robot.createAutomatedRunnable(script, "GetDetailItem@" + Integer.toHexString(script.hashCode()), args, System.out, false);
      new Thread(runnable).start();
   }
}
