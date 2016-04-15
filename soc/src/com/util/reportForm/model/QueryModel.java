package com.util.reportForm.model;


import java.util.HashMap;
import java.util.Map;

public class QueryModel {
	//封装sql语句
	private String queryhql ;
	
	//封装参数
	private Map params = new HashMap();
	
	public QueryModel(){
		
	}

	public String getQueryhql() {
		return queryhql;
	}

	public void setQueryhql(String queryhql) {
		this.queryhql = queryhql;
	}

	public Map getParams() {
		return params;
	}

	public void setParams(Map params) {
		this.params = params;
	}
}
