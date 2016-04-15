package com.util.monitorDatabase;

import com.soc.model.monitor.MonitorDatabaseTask;
/**
 * 数据库监控工具类
 * @author changhong
 *
 */
public class MonitorDatabaseUtils {
	private static final String mySqlDRIVER="com.mysql.jdbc.Driver";//mysql驱动
	private static final String oracleDRIVER="oracle.jdbc.driver.OracleDriver";//oracle驱动
	private static final String sqlServerDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";//sqlserver驱动
	private static String mySqlUrl="jdbc:mysql://$ip$:$port$/?useUnicode=true&amp;characterEncoding=UTF-8";//mysqlUrl
	private static String oracleUrl="jdbc:oracle:thin:@$ip$:$port$:$service$";//oracleUrl
	private static String sqlServerUrl="jdbc:sqlserver://$ip$:$port$";//sqlServerUrl
	//查询是否在线
	public static boolean checkOnline (MonitorDatabaseTask mdt){
		JdbcConnectionWrapper wrapper = null;
		switch (mdt.getDbType()) {
		case 1://mysql
			wrapper = JdbcConnectionWrapper.init(mySqlDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		case 2://sqlserver
			wrapper = JdbcConnectionWrapper.init(sqlServerDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		case 3://orcale
			wrapper = JdbcConnectionWrapper.init(oracleDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		}
		wrapper.closeConnection();
		return wrapper.isFalg();
	}
	//获取Connection控制器
	public static JdbcConnectionWrapper creatConnection(MonitorDatabaseTask mdt){
		JdbcConnectionWrapper wrapper = null;
		switch (mdt.getDbType()) {
		case 1://mysql
			wrapper = JdbcConnectionWrapper.init(mySqlDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		case 2://sqlserver
			wrapper = JdbcConnectionWrapper.init(sqlServerDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		case 3://orcale
			wrapper = JdbcConnectionWrapper.init(oracleDRIVER, mdt.getDbUrl(), mdt.getDbUserName(), mdt.getDbPWD());
			break;
		}
		return wrapper;
	}
	/**
	 * 转换数据库连接地址
	 * @param dbType
	 * @param MonitorDatabaseTask mondbt
	 * @return URL
	 */
	public static String changURL(int dbType,MonitorDatabaseTask mondbt){
		String url ="";
		switch (dbType) {
		case 1://mysql
			url=(mySqlUrl.replace("$ip$", mondbt.getDbIp())).replace("$port$", mondbt.getDbPort()+"");
			break;
		case 2://sqlserver
			url=(sqlServerUrl.replace("$ip$", mondbt.getDbIp())).replace("$port$", mondbt.getDbPort()+"");
			break;
		case 3://oracle
			url=((oracleUrl.replace("$ip$", mondbt.getDbIp())).replace("$port$", mondbt.getDbPort()+"")).replace("$service$", mondbt.getDb2iupdt());
			break;
		}
		return url;
	}
}
