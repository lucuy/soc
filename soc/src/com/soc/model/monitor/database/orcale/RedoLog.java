package com.soc.model.monitor.database.orcale;

public class RedoLog {
	private String group;
	private String type;
	private String member;
	private String bytes;
	private String status;
	private String first_change;
	private String first_time;
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMember() {
		return member;
	}
	public void setMember(String member) {
		this.member = member;
	}
	public String getBytes() {
		return bytes;
	}
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFirst_change() {
		return first_change;
	}
	public void setFirst_change(String first_change) {
		this.first_change = first_change;
	}
	public String getFirst_time() {
		return first_time;
	}
	public void setFirst_time(String first_time) {
		this.first_time = first_time;
	}
	
}
