package com.soc.model.monitor;

import java.io.Serializable;
import java.util.Date;


public class MonitorDatabaseTask implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long taskId;//任务编号
	private String taskName;//任务名称
	private String dbIp;//数据库ip地址
	private String dbCategory;//类别：数据库类型只是针对sqlserver分2000,2005,2008
	private String monType;//监控类型：jdbc
	private String dbUserName;//数据库用户名
	private String dbPWD;//数据库密码
	private String db2iupdt;//数据库服务实例名只是针对oracle
	private String dbContasts;//联系方式
	private String dbAddr;//数据库物理地址
	private String taskDESC;//任务描述
	private int dbOnline;//数据库是否能连通；0：连不上，1：连上
	private int taskStatus;//是否启动监控；0：不监控，1：监控,默认是1
	private int dbType; //1：mysql，2：oracle，3，sql server
	private String dbUrl;//数据库url
	private Date updateTime;//修改时间
	private int dbPort;//端口
	
	public int getDbPort() {
		return dbPort;
	}
	public void setDbPort(int dbPort) {
		this.dbPort = dbPort;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	public long getTaskId() {
		return taskId;
	}
	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getDbIp() {
		return dbIp;
	}
	public void setDbIp(String dbIp) {
		this.dbIp = dbIp;
	}
	public String getDbCategory() {
		return dbCategory;
	}
	public void setDbCategory(String dbCategory) {
		this.dbCategory = dbCategory;
	}
	public String getMonType() {
		return monType;
	}
	public void setMonType(String monType) {
		this.monType = monType;
	}
	public String getDbUserName() {
		return dbUserName;
	}
	public void setDbUserName(String dbUserName) {
		this.dbUserName = dbUserName;
	}
	public String getDbPWD() {
		return dbPWD;
	}
	public void setDbPWD(String dbPWD) {
		this.dbPWD = dbPWD;
	}
	public String getDb2iupdt() {
		return db2iupdt;
	}
	public void setDb2iupdt(String db2iupdt) {
		this.db2iupdt = db2iupdt;
	}
	public String getDbContasts() {
		return dbContasts;
	}
	public void setDbContasts(String dbContasts) {
		this.dbContasts = dbContasts;
	}
	public String getDbAddr() {
		return dbAddr;
	}
	public void setDbAddr(String dbAddr) {
		this.dbAddr = dbAddr;
	}
	public String getTaskDESC() {
		return taskDESC;
	}
	public void setTaskDESC(String taskDESC) {
		this.taskDESC = taskDESC;
	}
	public int getDbOnline() {
		return dbOnline;
	}
	public void setDbOnline(int dbOnline) {
		this.dbOnline = dbOnline;
	}
	public int getTaskStatus() {
		return taskStatus;
	}
	public void setTaskStatus(int taskStatus) {
		this.taskStatus = taskStatus;
	}
	public int getDbType() {
		return dbType;
	}
	public void setDbType(int dbType) {
		this.dbType = dbType;
	}
	
}
