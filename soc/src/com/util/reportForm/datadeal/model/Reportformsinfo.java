package com.util.reportForm.datadeal.model;




/**
 * Reportformsinfo entity. @author MyEclipse Persistence Tools
 */

public class Reportformsinfo  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Long reportFormId;
     private String colName;
     private String colWidth;
     private Integer alignType;
     private Integer correspondingTable;
     private Integer correspondingField;
     private String exportType;


    // Constructors

    /** default constructor */
    public Reportformsinfo() {
    }

    
    /** full constructor */
    public Reportformsinfo(Long reportFormId, String colName, String colWidth, Integer alignType, Integer correspondingTable, Integer correspondingField, String exportType) {
        this.reportFormId = reportFormId;
        this.colName = colName;
        this.colWidth = colWidth;
        this.alignType = alignType;
        this.correspondingTable = correspondingTable;
        this.correspondingField = correspondingField;
        this.exportType = exportType;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Long getReportFormId() {
        return this.reportFormId;
    }
    
    public void setReportFormId(Long reportFormId) {
        this.reportFormId = reportFormId;
    }

    public String getColName() {
        return this.colName;
    }
    
    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColWidth() {
        return this.colWidth;
    }
    
    public void setColWidth(String colWidth) {
        this.colWidth = colWidth;
    }

    public Integer getAlignType() {
        return this.alignType;
    }
    
    public void setAlignType(Integer alignType) {
        this.alignType = alignType;
    }

    public Integer getCorrespondingTable() {
        return this.correspondingTable;
    }
    
    public void setCorrespondingTable(Integer correspondingTable) {
        this.correspondingTable = correspondingTable;
    }

    public Integer getCorrespondingField() {
        return this.correspondingField;
    }
    
    public void setCorrespondingField(Integer correspondingField) {
        this.correspondingField = correspondingField;
    }

    public String getExportType() {
        return this.exportType;
    }
    
    public void setExportType(String exportType) {
        this.exportType = exportType;
    }
   








}