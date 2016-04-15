package com.soc.junit.test.sql.base;

import java.sql.Connection;

public class JdbcConnectionWrapper {
	private Connection conn;
	  private boolean ok = true;
	  private String errorMsg;
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public boolean isOk() {
		return ok;
	}
	public void setOk(boolean ok) {
		this.ok = ok;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	  
}
