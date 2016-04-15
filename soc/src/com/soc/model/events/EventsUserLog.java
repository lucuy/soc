package com.soc.model.events;

/**
 * 
 * 上海格尔远程Oracle数据库的字段表对应Bean
 * @author HR
 *
 */

public class EventsUserLog {

	//流水号
	private long numID; 
	//用户标识/18位
	private long userID ;
	//单位名称
	private String organization ; 
	//用户名
	private String userName;
	//终端标识
	private String terminalID ;
	//操作类型
	private int operateType ;
	//操作时间
	private long operateTime ;
	//操作条件
	private String operateCondition ;
	//操作结果(1:成功|0：失败)
	private int operateResult ;
	
	
	
	public long getNumID() {
		return numID;
	}
	public void setNumID(long numID) {
		this.numID = numID;
	}
	public long getUserID() {
		return userID;
	}
	public void setUserID(long userID) {
		this.userID = userID;
	}
	public String getOrganization() {
		return organization;
	}
	public void setOrganization(String organization) {
		this.organization = organization;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTerminalID() {
		return terminalID;
	}
	public void setTerminalID(String terminalID) {
		this.terminalID = terminalID;
	}
	public int getOperateType() {
		return operateType;
	}
	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}
	public long getOperateTime() {
		return operateTime;
	}
	public void setOperateTime(long operateTime) {
		this.operateTime = operateTime;
	}
	public String getOperateCondition() {
		return operateCondition;
	}
	public void setOperateCondition(String operateCondition) {
		this.operateCondition = operateCondition;
	}
	public int getOperateResult() {
		return operateResult;
	}
	public void setOperateResult(int operateResult) {
		this.operateResult = operateResult;
	} 
	
	
	
	
	
}
