package com.util.monitorDatabase;

import java.sql.Connection;
/**
 * 数据库监控链接服务类
 * @author changhong
 *
 */
public class JdbcConnectionWrapper {
	private Connection conn;
	  private boolean falg ;
	  private String errorMsg;
	  protected JdbcConnectionWrapper(){}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public boolean isFalg() {
		return falg;
	}
	public void setFalg(boolean falg) {
		this.falg = falg;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public static JdbcConnectionWrapper init(String DRIVER,String url, String username, String pwd){
		return JDBCUtils.getInitialization(DRIVER, url, username, pwd).getConnection();
	}
	/*
	 * 关闭连接
	 */
	 public void closeConnection (){
		 try
		    {
		      if (conn != null)
		      {
		        conn.close();
		      }
		    }
		    catch (Exception e)
		    {
		      try
		      {
		        if (conn != null)
		        {
		          conn.close();
		        }
		      }
		      catch (Exception ex)
		      {
		      }
		    }
	 } 
}
