package com.soc.model.monitor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class CustomTrend implements Serializable {
	private long trendId;//id
	private String trendName;//名称
	private long parentId;//父类id
	private int days;
	private String startTime;
	private String endTime;
	private String trendIp;
	private Date trendCreateDateTime;//创建时间
	private Date trendUpdateDateTime;//修改时间
	private int trendDelete;//是否被删除
	private String trendCreateUser;//创建者
	private String trendUpdateUser;//修改者
	//自定义字段
	private String trendCustom1;
	private String trendCustom2;
	private String trendCustom3;
	private String trendCustom4;
	private String trendCustom5;
	
	private List<String> ipList;
	public long getTrendId() {
		return trendId;
	}
	public void setTrendId(long trendId) {
		this.trendId = trendId;
	}
	public String getTrendName() {
		return trendName;
	}
	public void setTrendName(String trendName) {
		this.trendName = trendName;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	public Date getTrendCreateDateTime() {
		return trendCreateDateTime;
	}
	public void setTrendCreateDateTime(Date trendCreateDateTime) {
		this.trendCreateDateTime = trendCreateDateTime;
	}
	public Date getTrendUpdateDateTime() {
		return trendUpdateDateTime;
	}
	public void setTrendUpdateDateTime(Date trendUpdateDateTime) {
		this.trendUpdateDateTime = trendUpdateDateTime;
	}
	public int getTrendDelete() {
		return trendDelete;
	}
	public void setTrendDelete(int trendDelete) {
		this.trendDelete = trendDelete;
	}
	public String getTrendCreateUser() {
		return trendCreateUser;
	}
	public void setTrendCreateUser(String trendCreateUser) {
		this.trendCreateUser = trendCreateUser;
	}
	public String getTrendUpdateUser() {
		return trendUpdateUser;
	}
	public void setTrendUpdateUser(String trendUpdateUser) {
		this.trendUpdateUser = trendUpdateUser;
	}
	public String getTrendCustom1() {
		return trendCustom1;
	}
	public void setTrendCustom1(String trendCustom1) {
		this.trendCustom1 = trendCustom1;
	}
	public String getTrendCustom2() {
		return trendCustom2;
	}
	public void setTrendCustom2(String trendCustom2) {
		this.trendCustom2 = trendCustom2;
	}
	public String getTrendCustom3() {
		return trendCustom3;
	}
	public void setTrendCustom3(String trendCustom3) {
		this.trendCustom3 = trendCustom3;
	}
	public String getTrendCustom4() {
		return trendCustom4;
	}
	public void setTrendCustom4(String trendCustom4) {
		this.trendCustom4 = trendCustom4;
	}
	public String getTrendCustom5() {
		return trendCustom5;
	}
	public void setTrendCustom5(String trendCustom5) {
		this.trendCustom5 = trendCustom5;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTrendIp() {
		return trendIp;
	}
	public void setTrendIp(String trendIp) {
		this.trendIp = trendIp;
	}
	public List<String> getIpList() {
		return ipList;
	}
	public void setIpList(List<String> ipList) {
		this.ipList = ipList;
	}
	
	
	
	
	
}
