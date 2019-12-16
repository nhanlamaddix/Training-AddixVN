/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excersice;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
/**
 *
 * @author ADDIX.01
 */
public class MainLog {
    public static void main(String[] args) throws IOException 
    {
        int size=120;
        String point = "*";
        Log log= new Log(size);
        String header = "";
        String footer = "";
        String strcontent = "Researchers from the University of Glasgow reported the results in the New England Journal of Medicine. They compared the causes of death of 7,676 Scottish men who played professional soccer with 23,028 similar men from the general population. The men were all born between 1900 and 1976. Over 18 years of study, 1,180 players and 3,807 of the other group died. ";
        String cont = "";
        String nameError = "Error:";
        String errorContent = "Error Content";
        
        header= log.writeHeaderAndFooter(point, true);
        System.out.print(header);
       
        cont=log.writeContent(strcontent);
        System.out.print(cont);
        
        System.out.print(log.writeError(nameError, strcontent));
        
        footer = log.writeHeaderAndFooter(point, false);
        System.out.print(footer);
        
        System.out.println(log.getDateTime());
        
        
    }
}
