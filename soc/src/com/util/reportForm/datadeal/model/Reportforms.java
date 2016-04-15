package com.util.reportForm.datadeal.model;




/**
 * Reportforms entity. @author MyEclipse Persistence Tools
 */

public class Reportforms  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Long reportFormId;
     private String reportFormName;
     private String reportFormDescription;
     private Long createDate;
     private Integer reportFormSort;
     private Integer reportFormType;
     private String tables;
     private String selTerm;
     private String reportFormSql;
     private String coordx;
     private String coordy;
     private String groupby;
     private String orderby;
    // Constructors

    public String getGroupby() {
		return groupby;
	}

	public void setGroupby(String groupby) {
		this.groupby = groupby;
	}

	/** default constructor */
    public Reportforms() {
    }

	/** minimal constructor */
    public Reportforms(Long reportFormId, String reportFormName, Long createDate, Integer reportFormSort, Integer reportFormType) {
        this.reportFormId = reportFormId;
        this.reportFormName = reportFormName;
        this.createDate = createDate;
        this.reportFormSort = reportFormSort;
        this.reportFormType = reportFormType;
    }
    
    /** full constructor */
    public Reportforms(Long reportFormId, String reportFormName, String reportFormDescription, Long createDate, Integer reportFormSort, Integer reportFormType, String tables, String selTerm, String reportFormSql) {
        this.reportFormId = reportFormId;
        this.reportFormName = reportFormName;
        this.reportFormDescription = reportFormDescription;
        this.createDate = createDate;
        this.reportFormSort = reportFormSort;
        this.reportFormType = reportFormType;
        this.tables = tables;
        this.selTerm = selTerm;
        this.reportFormSql = reportFormSql;
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

    public String getReportFormName() {
        return this.reportFormName;
    }
    
    public void setReportFormName(String reportFormName) {
        this.reportFormName = reportFormName;
    }

    public String getReportFormDescription() {
        return this.reportFormDescription;
    }
    
    public void setReportFormDescription(String reportFormDescription) {
        this.reportFormDescription = reportFormDescription;
    }

    public Long getCreateDate() {
        return this.createDate;
    }
    
    public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }

    public Integer getReportFormSort() {
        return this.reportFormSort;
    }
    
    public void setReportFormSort(Integer reportFormSort) {
        this.reportFormSort = reportFormSort;
    }

    public Integer getReportFormType() {
        return this.reportFormType;
    }
    
    public void setReportFormType(Integer reportFormType) {
        this.reportFormType = reportFormType;
    }

    public String getTables() {
        return this.tables;
    }
    
    public void setTables(String tables) {
        this.tables = tables;
    }

    public String getSelTerm() {
        return this.selTerm;
    }
    
    public void setSelTerm(String selTerm) {
        this.selTerm = selTerm;
    }

    public String getReportFormSql() {
        return this.reportFormSql;
    }
    
    public void setReportFormSql(String reportFormSql) {
        this.reportFormSql = reportFormSql;
    }

	public String getCoordx() {
		return coordx;
	}

	public void setCoordx(String coordx) {
		this.coordx = coordx;
	}

	public String getCoordy() {
		return coordy;
	}

	public void setCoordy(String coordy) {
		this.coordy = coordy;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
   
	
}