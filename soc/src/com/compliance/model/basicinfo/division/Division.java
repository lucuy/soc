package com.compliance.model.basicinfo.division;

import java.io.Serializable;
public class Division implements Serializable{
	
	private int id; //编号
	private String depName; //部门名称
	private String depDescription; //部门描述
	private String depEmp; //部门安全人员
	private String depHead; //部门领导
	private int parentId; //父部门id
	private String parentDepEmp; //上级部门安全员
	private String parentDepHead; //上级部门领导
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getDepDescription() {
		return depDescription;
	}
	public void setDepDescription(String depDescription) {
		this.depDescription = depDescription;
	}
	public String getDepEmp() {
		return depEmp;
	}
	public void setDepEmp(String depEmp) {
		this.depEmp = depEmp;
	}
	public String getDepHead() {
		return depHead;
	}
	public void setDepHead(String depHead) {
		this.depHead = depHead;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	public String getParentDepEmp() {
		return parentDepEmp;
	}
	public void setParentDepEmp(String parentDepEmp) {
		this.parentDepEmp = parentDepEmp;
	}
	public String getParentDepHead() {
		return parentDepHead;
	}
	public void setParentDepHead(String parentDepHead) {
		this.parentDepHead = parentDepHead;
	}
	
}
