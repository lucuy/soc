package com.soc.model.systemsetting.rules;

import java.io.Serializable;

public class Device_category implements Serializable{
	//设备ID
	private int ID;
	//设备名称
	private String name;
	//设备的父级Id，0代表没有父级
	private int higherUpsId;
	//解析规则名称
	private String devname;
	//程序类型
	private String programtype;
	//是否显示
	private Integer customd1;
	//行ID
	private Integer rawid;
	//设备类型编号
	private String code;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHigherUpsId() {
		return higherUpsId;
	}
	public void setHigherUpsId(int higherUpsId) {
		this.higherUpsId = higherUpsId;
	}
	public String getDevname() {
		return devname;
	}
	public void setDevname(String devname) {
		this.devname = devname;
	}
	public String getProgramtype() {
		return programtype;
	}
	public void setProgramtype(String programtype) {
		this.programtype = programtype;
	}
	public Integer getCustomd1() {
		return customd1;
	}
	public void setCustomd1(Integer customd1) {
		this.customd1 = customd1;
	}
	public Integer getRawid() {
		return rawid;
	}
	public void setRawid(Integer rawid) {
		this.rawid = rawid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}
