package com.soc.model.systemsetting.rules;

import java.io.Serializable;

public class QueryEvents_group implements Serializable{
	//组ID
	private int id;
	//解析规则名称
	private String name;
	//父级ID
	private int parent_id;
	//状态
	private Integer type;
	//参数
	private String conditions;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	
	
}
