import java.io.IOException;

import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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


public class web_image {
    public static void main(String[] args) {
                String url="https://vnexpress.net";
		try {
			Document doc = Jsoup.connect(url).get();
			Elements linksDOM=doc.getElementsByTag("a");
			 
			Elements linksSelector=doc.select("a[href]");
			
			System.out.println("\nlinksDOM and linksSelector are the same? "+linksDOM.equals(linksSelector));
			
			 System.out.println("\nThe title and url of each link: ");
		        for(Element link:linksDOM)
		        {
		            System.out.println(link.text()+", "+link.attr("href"));
		        }
		        Elements images = doc.select("img[src~=\\.(gif|jpe?g)]");
		        for (Element image : images) {
		            System.out.println("\nThe image source : " + image.attr("src"));
		            System.out.println("Height : " + image.attr("height"));
		            System.out.println("Width : " + image.attr("width"));
		            //The alternative information of an image if a user for some reason cannot view it
		            System.out.println("The alternate text : " + image.attr("alt"));
		        }
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

}
}
