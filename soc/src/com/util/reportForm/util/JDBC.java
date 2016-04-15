package com.util.reportForm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.struts2.ServletActionContext;

public class JDBC {

	/**
	 * @param args
	 */
	//private String userName = "";
	//private String password = "";
	//private String Driver = "";
	//private String URL = "";

	private Connection connection;
	private Statement statement;
	private PreparedStatement prestmt;
	private ResultSet resultSet;
	
	private InputStream inputStream = null;
	
	public JDBC() {
		// TODO Auto-generated constructor stub
		getConnection();
	}

	/**
	 * 修改为从配置文件读取连接
	 * @author Ren Zhongyuan
	 */
	public void getConnection() {
		//URL = "jdbc:mysql://127.0.0.1:3306/fort?useUnicode=true&amp;characterEncoding=UTF-8";
	/*	userName = "soc";
		password = "soc";
		Driver = "org.postgresql.Driver";
		URL = "jdbc:postgresql://192.168.1.72:5432/soc";
		
		try {
			Class.forName(Driver);
			try {
				connection = DriverManager.getConnection(URL, userName,
						password);
			} catch (SQLException e) {
				//System.out.println("com.util.reportForm.util.jdbc 文件");
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			//System.out.println("com.util.reportForm.util.jdbc 文件111");
			
			e.printStackTrace();
		}*/
		
		
		
		try {
			
			//数据库连接配置文件路径
			
		    inputStream = this.getClass().getClassLoader().getResourceAsStream("prop/jdbc.properties"); 

			Properties theProperties = new Properties();
			
			theProperties.load(inputStream);
			
			//加载jdbc驱动
			Class.forName(theProperties.getProperty("jdbc.driverClassName"));
			
			//System.out.println("驱动加载成功！！！");  
			
			try {  
				
				//建立连接
				connection = DriverManager.getConnection(theProperties.getProperty("jdbc.url"), theProperties.getProperty("jdbc.username"), theProperties.getProperty("jdbc.password"));  
	            
				//System.out.println("数据库连接成功！！！"); 
				
	        } catch (SQLException se) {  
	        	
	        	//System.out.println("不能连接到指定的数据库，请检查相应的基本信息！！！");  
	            
	        	se.printStackTrace();  
	        }  
	
		}catch (FileNotFoundException fe) {  
			
			//System.out.println("指定路径的文件不存在或者无权限访问！！！"); 
			
            fe.printStackTrace();  
            
        } catch (IOException ie) {  
        	
            ie.printStackTrace();  
            
        } catch (ClassNotFoundException ce) {  
        	
            //System.out.println("无法找到所加载的驱动，或是驱动文件不存在！！！");  
            
            ce.printStackTrace();  
            
        }finally{  
        	
        	if( inputStream != null) try{inputStream.close();}catch (Exception e4) {e4.printStackTrace();}
                
        }  

		
		
	}
	
	public Statement getStatement() {
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return statement;
	}

	public PreparedStatement getPreparedStatement(String sql) {
		try {
			prestmt = connection.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prestmt;
	}

	public ResultSet getResultSet(String sql) throws SQLException {
		
		resultSet = getStatement().executeQuery(sql);
		
		return resultSet;
	}

	public void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (prestmt != null) {
				prestmt.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeAll() {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (prestmt != null) {
				prestmt.close();
			}
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
