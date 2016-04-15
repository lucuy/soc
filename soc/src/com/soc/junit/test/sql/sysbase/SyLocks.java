package com.soc.junit.test.sql.sysbase;
/**
 * 死锁数
 * @author changhong
 *
 */
public class SyLocks {
	private String spid;
	private String blocked;
	private String status;
	private String program_name;
	private String time_blocked;
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public String getBlocked() {
		return blocked;
	}
	public void setBlocked(String blocked) {
		this.blocked = blocked;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProgram_name() {
		return program_name;
	}
	public void setProgram_name(String program_name) {
		this.program_name = program_name;
	}
	public String getTime_blocked() {
		return time_blocked;
	}
	public void setTime_blocked(String time_blocked) {
		this.time_blocked = time_blocked;
	}
	
}
