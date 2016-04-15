package com.compliance.model.cpManage.demand;

import java.io.Serializable;

public class Arithmetic implements Serializable {
	private int id;	//id
	private String sort; //排序字段
	private String jsAlg; //js算法
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getJsAlg() {
		return jsAlg;
	}
	public void setJsAlg(String jsAlg) {
		this.jsAlg = jsAlg;
	}
	
}
