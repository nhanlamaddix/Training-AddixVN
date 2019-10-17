/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise_weban;

/**
 *
 * @author ADDIX.01
 */
public class Weban {
    private int numTable;
    private int page;
    private String hourly;
    private String worktime;
    private String treatment;
    private String address;
    

    public Weban(){
        this.numTable = 0 ;
        this.page = 0;
        this.hourly = "";
        this.worktime = "";
        this.treatment = "";
        this.address = "";
        
    }

    public Weban(int numTable, int page, String hourly, String worktime, String treatment, String address) {
        this.numTable = numTable;
        this.page = page;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
    
    
}
