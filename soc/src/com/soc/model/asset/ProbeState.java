package com.soc.model.asset;

import java.io.Serializable;
/**
 * 采集器状态实体类
 * @author 张浩磊
 *
 */
public class ProbeState implements Serializable {
	private String collectorMacs;//采集器mac地址
	private int error;//采集器运行状态
	private int result;//采集器返回结果
	private int taskId ; 
	
	public String getCollectorMacs() {
		return collectorMacs;
	}
	public void setCollectorMacs(String collectorMacs) {
		this.collectorMacs = collectorMacs;
	}
	public int getError() {
		return error;
	}
	public void setError(int error) {
		this.error = error;
	}
	public int getResult() {
		return result;
	}
	public void setResult(int result) {
		this.result = result;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}
	
	
}
