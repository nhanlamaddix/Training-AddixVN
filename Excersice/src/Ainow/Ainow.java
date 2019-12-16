/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ainow;

/**
 *
 * @author ADDIX.01
 */
public class Ainow {

    private int countWeban;

    public int getCountWeban() {
        return countWeban;
    }

    public void setCountWeban(int countWeban) {
        this.countWeban = countWeban;
    }
    private String articleUrl;
    private String title;
    private String postDate;
    private String allText;
    private String webname;
    private String domain;
    private String keyword;

    public String getWebname() {
        return webname;
    }

    public void setWebname(String webname) {
        this.webname = webname;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
  
    

    
    public Ainow(){
        
        this.countWeban = 0;
        this.articleUrl = "";
        this.title = "";
        this.postDate = "";
        this.allText = "";
        this.domain ="";
        this.webname ="";
        this.keyword ="";
        
    }
    

    public Ainow( int countWeban,String articleUrl, String title, String postDate, String allText, String domain,String webname,String keyword) {
//        this.name = name;
//        this.listTab = listTab;
        this.countWeban = countWeban;
        this.articleUrl = articleUrl;
        this.title = title;
        this.postDate = postDate;
        this.allText = allText;
        this.domain = domain;
        this.keyword = keyword;
        this.webname =  webname;
    }



    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostDate() {
        return postDate;
    }

    public void setPostDate(String postDate) {
        this.postDate = postDate;
    }

    public String getAllText() {
        return allText;
    }

    public void setAllText(String allText) {
        this.allText = allText;
    }
    
    
}
