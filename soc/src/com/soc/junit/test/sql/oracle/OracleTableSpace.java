package com.soc.junit.test.sql.oracle;
/**
 * 表空间状态
 * @author changhong
 *
 */
public class OracleTableSpace {
	private String file;
	private String status;
	private String change;
	private String time;
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getChange() {
		return change;
	}
	public void setChange(String change) {
		this.change = change;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
