/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package excersice;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.TimeZone;
/**
 *
 * @author ADDIX.01
 */
public class Log {
    private int widthSize = 0;
    private String strLineError = "=";
    private int numSpace = 3;    
    /*Contructer Log class with parameter: witdthSize */
    public Log(int widthSize) {
        this.widthSize = widthSize;
    }
    /**
     * writeHeaderAndFooter(): Set True for Header content, False for Footer content
     * @param charStar : symbol 
     * @param isHeader : T/F 
     * @return 
     */
    public String writeHeaderAndFooter(String charStar, boolean isHeader){
        String myStr = "";
        String strStar = "";
        String myFormat = "";
        String outDate = "";
        String strText = "";
        //using join, collection.nCopies for "*"
        strStar = String.join("", Collections.nCopies(this.widthSize, "*"));
       int lengthDate = 0;
       //set Hearder and Footer: length of Start/End with Date occur
       if(isHeader){//Header
           strText = "ADDIX-VN -Start time:";
           lengthDate = (int)Math.floor(this.widthSize/2);
           // myFormat: Caculate of content.lenght, not over size
           myFormat = "%s" +  strText + "%"+ lengthDate +"s%" + (this.widthSize - strText.length() - lengthDate - 1) + "s";
           //outDate: print date with format from getDateTime
           outDate = String.format(myFormat, charStar, getDateTime(), charStar);
       }else{//Footer
           
            strText = "ADDIX-VN -End time:";
           lengthDate = (int)(Math.floor(this.widthSize/2)+2);
           myFormat = "%s" + strText + "%" + lengthDate + "s%" + (this.widthSize - strText.length() - lengthDate - 1) + "s";
           outDate = String.format(myFormat, charStar, getDateTime(), charStar);
       }
       myStr = myStr + "\n" + strStar + "\n";
       myStr = myStr + outDate + "\n";
       myStr = myStr + strStar  + "\n";
             
       return myStr;
   }
    
   /**
    *  writeContent(): Get content limited length for content 
    * @param content: text of content
    * @return 
    */
    public String writeContent(String content){
       int strLength = content.length();
       String myStr = "";
       if(strLength > this.widthSize){//lenght content over size default
           String strTemp = "";
           int postStart = 0, postEnd = this.widthSize - 1 , iter = 0;
           char myChar;
           //While : search ' ' text, then Enter content 
           while(postEnd <= strLength){
               strTemp = content.substring(postStart, postEnd);
               iter = postEnd + 1;
               myChar = content.charAt(iter);
               while(myChar != ' '){
                   iter --;
                   myChar = content.charAt(iter);
               }               
               postEnd = iter;
               strTemp = content.substring(postStart, postEnd);
               
               myStr = myStr + "\n" + strTemp;
               
               postStart = postEnd + 1;
               postEnd += this.widthSize - 1;
               if(postEnd > strLength){
                   myStr = myStr + "\n" + content.substring(postStart, strLength);
               }
           }
       }else{//lenght content smaller size default
           return content;
       }
       return myStr;
   }
 /**
  * writeError: Get content error
  * @param nameError: name of Error
  * @param errorContent: text of Error
  * @return 
  */  
    public String writeError(String nameError, String errorContent)
   {
       
        String myStr = "";
        String myErrorLine = "";
        String strSpace = " ";
        String mySpace = "";
        int strLength = 0;
        //The same content beside, but change length of Date,text Error
        strLength = errorContent.length();
        myErrorLine = String.join("", Collections.nCopies(this.widthSize, "="));
        myStr = "\n" + myErrorLine + "\n";
        myStr += "Error " + nameError + " Date: " + new Date() ;
        mySpace = String.join("", Collections.nCopies(this.numSpace, strSpace));
        if((strLength + this.numSpace) > this.widthSize){
            String strTemp = "";
            int postStart = 0, postEnd = this.widthSize - 1 - this.numSpace , iter = 0;
            char myChar;
            while(postEnd <= strLength){
               strTemp = errorContent.substring(postStart, postEnd);
               iter = postEnd + 1;
               myChar = errorContent.charAt(iter);
               while(myChar != ' '){
                   iter --;
                   myChar = errorContent.charAt(iter);
               }               
               postEnd = iter;
               strTemp = mySpace + errorContent.substring(postStart, postEnd);

               myStr = myStr + "\n" + strTemp;

               postStart = postEnd + 1;
               postEnd += this.widthSize - 1 - this.numSpace;
               if(postEnd > strLength){
                   myStr = myStr + "\n" + mySpace + errorContent.substring(postStart, strLength);
               }
            }
        }else{
            myStr += mySpace + errorContent + "\n";
        }
        myStr +=  "\n" + myErrorLine + "\n";
        return myStr;       
   }
    
    /**
     * getDateTime(): Format date, then return by String.
     * @return 
     */
    public String getDateTime()
    {
        Date date = new Date();
        DateFormat gmtFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");
        TimeZone gmtTime = TimeZone.getTimeZone("GMT");
        gmtFormat.setTimeZone(gmtTime);

        return gmtFormat.format(date);
    }
    
}
