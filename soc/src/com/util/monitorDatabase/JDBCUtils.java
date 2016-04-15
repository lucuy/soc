package com.util.monitorDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * JDBC服务类
 * @author changhong
 *
 */
public class JDBCUtils {
	private String url;//数据库连接地址
	private String username;//用户名
	private String pwd;//密码
	private String DRIVER;//驱动
	private JDBCUtils() {
	}
	private JDBCUtils(String DRIVER,String url, String username, String pwd) {
		this.DRIVER=DRIVER;
		this.url = url;
		this.username = username;
		this.pwd = pwd;
	}
	public static JDBCUtils  getInitialization(String DRIVER,String url, String username, String pwd){
		//加载驱动
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return new JDBCUtils(DRIVER, url, username, pwd);
	}
	// 获得连接
		public JdbcConnectionWrapper getConnection()  {
			JdbcConnectionWrapper wrapper= new JdbcConnectionWrapper();
			try {
				if(url!=null&&username!=null&&pwd!=null){
					Connection conn=DriverManager.getConnection(url, username, pwd);
					wrapper.setConn(conn);
					wrapper.setFalg(true);
				}
				return wrapper;
			} catch (SQLException e) {
				if (e.getMessage().indexOf("invalid username/password;") > 0)
			        wrapper.setErrorMsg("用户名或密码错误，请重新输入.");
			      else if (e.getMessage().indexOf("The Connection descriptor used by the client was") > 0)
			        wrapper.setErrorMsg("实例名错误，请重新输入.");
			      else
			        wrapper.setErrorMsg("连接出现异常，无法连接数据库.");
				wrapper.setFalg(false);
				return wrapper;
			    }
		}

		// 关闭连接
		public void close(ResultSet rs, Statement st) {
			try {
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {

				try {
					if (st != null)
						st.close();
				} catch (SQLException e) {
					e.printStackTrace();
				} 
			}
		}
		public static void closeConnection(Connection conn)
		  {
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
