package com.soc.junit.test.sql.oracle;

public class OracleHighCpuUsedUser extends OracleUser {
	private String spid;
	private String prog;
	private String termonal;
	private String status;
	public String getSpid() {
		return spid;
	}
	public void setSpid(String spid) {
		this.spid = spid;
	}
	public String getProg() {
		return prog;
	}
	public void setProg(String prog) {
		this.prog = prog;
	}
	public String getTermonal() {
		return termonal;
	}
	public void setTermonal(String termonal) {
		this.termonal = termonal;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
