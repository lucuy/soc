package com.soc.model.ScanTask;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ScanTask implements Serializable{
	//任务ID
	private long id ;  
	//任务名称
	private String taskName ; 
	//IP (多IP，以","号分隔)
	private String ipBunch ; 
	//开始IP
	private String startIP ; 
    //结束IP
	private String endIP ; 
	//描述
	private String description ; 
	// 创建时间
	private Date createDate ; 
	// 任务状态
	private int state ;//0：代表未扫描，1：代表正在扫描，2：代表扫描完成
	//读取文件标识
	private String falgFileName;
	private String userName;//创建者
	private List<String> ipList;//ip地址集合
	
	
	public String getFalgFileName() {
		return falgFileName;
	}
	public void setFalgFileName(String falgFileName) {
		this.falgFileName = falgFileName;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getIpBunch() {
		return ipBunch;
	}
	public void setIpBunch(String ipBunch) {
		this.ipBunch = ipBunch;
	}
	
	public String getStartIP() {
		return startIP;
	}
	public void setStartIP(String startIP) {
		this.startIP = startIP;
	}
	public String getEndIP() {
		return endIP;
	}
	public void setEndIP(String endIP) {
		this.endIP = endIP;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<String> getIpList() {
		return ipList;
	}
	public void setIpList(List<String> ipList) {
		this.ipList = ipList;
	}
	
}
