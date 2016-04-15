package com.util.reportForm.datadeal.model;


/**
 * Tableorder entity. @author MyEclipse Persistence Tools
 */

public class Tableorder  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer orderNum;
     private String name;
     private Integer paterOrder;


    // Constructors

    /** default constructor */
    public Tableorder() {
    }

	/** minimal constructor */
    public Tableorder(Integer orderNum) {
        this.orderNum = orderNum;
    }
    
    /** full constructor */
    public Tableorder(Integer orderNum, String name, Integer paterOrder) {
        this.orderNum = orderNum;
        this.name = name;
        this.paterOrder = paterOrder;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderNum() {
        return this.orderNum;
    }
    
    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Integer getPaterOrder() {
        return this.paterOrder;
    }
    
    public void setPaterOrder(Integer paterOrder) {
        this.paterOrder = paterOrder;
    }
   








}