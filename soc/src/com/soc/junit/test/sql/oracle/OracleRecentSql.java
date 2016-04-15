package com.soc.junit.test.sql.oracle;
/*
 * 最近执行的sql
 */
public class OracleRecentSql {
	private String lastTime;
	private String sqlTest;
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	public String getSqlTest() {
		return sqlTest;
	}
	public void setSqlTest(String sqlTest) {
		this.sqlTest = sqlTest;
	}
	
}
