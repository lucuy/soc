package com.soc.model.monitor.database.mysql;
/**
 * mysql告警
 * @author changhong
 *
 */
public class MySqlLastWarning {
	private	String level;
	private String code;
	private String message;
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
