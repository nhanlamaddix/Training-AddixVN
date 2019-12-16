/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractandinterface;

import java.text.MessageFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Scanner;

/**
 *
 * @author ADDIX.01
 */
public class Log {
    private String star;
    private int n = 120;
    public void writeHeader(String star){
        
        for (int i = 1 ; i < n  ; i++){
            System.out.format("%s",star);
        }
        System.out.println();
        
        String format = String.format("%sStart time:%60s%48s", star,new Date(),star);
        System.out.println(format);
        for(int i = 0; i< n; i++)
        {
            System.out.format("%s",star);
        }
        System.out.println("\n");
    }
  
    private String content;
    public void writeContent(String content){
//        Scanner scanner = new Scanner(System.in); 
//        //String string = new String();
//        String string = "";
//        System.out.println("Nhap:");
//        string = scanner.nextLine();
//        System.out.println(string.length());
//        
//        if(string.split("\\s").length <= n){
////            for (String w : string.split("\\s")) {
////            
////        }
//  
//        }
        String input = "";
        if(input == null){
        String ouput = input.substring(0, 120);
        System.out.println(ouput);
        }else 
        for (String w : input.split("\\s", 0)){
        System.out.println(w);
  }
    }
    public void writeFooter(String star){
        
        for (int i = 1 ; i < n  ; i++){
            System.out.format("%s",star);
        }
        System.out.println();
        
        String format = String.format("%sEnd time:%62s%48s", star,new Date(),star);
        System.out.println(format);
        for(int i = 0; i< n; i++)
        {
            System.out.format("%s",star);
        }
        
    }
    public static void main(String[] args) {
        String point="*";
        Log log = new Log();
        log.writeHeader(point);
        log.writeContent(point);
        log.writeFooter(point);
    }
    
}
