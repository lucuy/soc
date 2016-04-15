package com.compliance.model.cpManage.technology;

import java.io.Serializable;
import java.util.Date;

/**
 * Description: 技术差距分析 实体
 * @author 杜高杨
 * @Version 1.0
 * @Created at 2013-05-14
 * @Modified by
 * 
 */
public class Technology implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id; //id
	private String rankId;	//所属系统定级（参照定级表主键）
	private String name; //评估名称 如：广播电视系统20130203091323
	private String describe; //评估描述 如： 二次评估
	private String sysGrade; //系统级别
	private Date startTime; //开始时间
	private Date endTime; //结束时间
	private String currentState; //当前状态 0：未评估，1：评估中，2：已结束
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getRankId() {
		return rankId;
	}
	public void setRankId(String rankId) {
		this.rankId = rankId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getSysGrade() {
		return sysGrade;
	}
	public void setSysGrade(String sysGrade) {
		this.sysGrade = sysGrade;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	
}
