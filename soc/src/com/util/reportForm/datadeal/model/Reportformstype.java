package com.util.reportForm.datadeal.model;

/**
 * Reportformstype entity. @author MyEclipse Persistence Tools
 */

public class Reportformstype  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String reportType;


    // Constructors

    /** default constructor */
    public Reportformstype() {
    }

    
    /** full constructor */
    public Reportformstype(String reportType) {
        this.reportType = reportType;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportType() {
        return this.reportType;
    }
    
    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
   








}