package com.soc.junit.test.sql.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.soc.junit.test.sql.base.JDBCUtils;
import com.soc.junit.test.sql.base.JdbcConnectionWrapper;

public class TestSqlServer {
	private static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static final String URL = "jdbc:sqlserver://198.9.9.8:1433";// 默认的数据库
	private static final String USERNAME = "sa";
	private static final String PWD = "1qaz@WSX";
	static JDBCUtils jdbc = JDBCUtils.getInitialization(DRIVER, URL, USERNAME,
			PWD);
	static Connection conn=null;
		static{
			long time = System.currentTimeMillis();
			JdbcConnectionWrapper warp=jdbc.getConnection();
			conn = warp.getConn();
			long end = System.currentTimeMillis();
			System.out.println("连接时间："+(end -time)+"ms");
		}
	/**
	 * @param args
	 */
	// 测试连接
	public static boolean Connexity() {
		if (conn != null){
			return true;
		}else{
			return false;
		}
	}

	// Buffer缓存命中率
	public static void SqlServerBufferCacheHit() {
		String sql = "select counter_name,cntr_value from master.sys.sysperfinfo where object_name='SQLServer:Buffer Manager' and counter_name LIKE'%Buffer cache hit ratio%'";
		if(Connexity()){
			Map<String, Double> map = query(sql);
			System.out.println((100 * map.get("Buffer cache hit ratio"))
					/ map.get("Buffer cache hit ratio base") + "%");
		}
	}

	// Buffer缓存明细
	public static void SqlServerBufferCacheDetail() {
		String sql = "select counter_name,cntr_value from master.sys.sysperfinfo " +
				"where object_name='SQLServer:Buffer Manager' and counter_name in " +
				"('Page lookups/sec','Page reads/sec','Page writes/sec'," +
				"'Total pages','Database pages','Free pages')";
		SET(sql);
		/*Map<String,Double> map = query(sql);
		Set<Entry<String, Double>> set = map.entrySet();
		Iterator<Entry<String, Double>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, Double> entry=it.next();
			System.out.println(entry.getKey()+"="+entry.getValue());
		}*/
	}
	// 缓存明细只针对sqlserver2000
	public static void SqlServerCacheDetail() {
		String sql = "select counter_name,cntr_value from sysperfinfo where" +
				" object_name='SQLServer:Cache Manager' and instance_name='_Total' and counter_name in " +
				"('Cache Pages','Cache Object Counts','Cache Use Counts/sec')";
		SET(sql);
		/*Map<String,Double> map = query(sql);
		Set<Entry<String, Double>> set = map.entrySet();
		Iterator<Entry<String, Double>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, Double> entry=it.next();
			System.out.println(entry.getKey()+"="+entry.getValue());
		}*/
	}
	//活动连接数
	public static void SqlServerActiveConnect(){
		String sql="select cntr_value from master.sys.sysperfinfo where " +
				"object_name='SQLServer:General Statistics' and counter_name='User Connections'";
		System.out.println(querySql(sql));
	}
	//锁明细
	public static void SqlServerLockDetail(){
		String sql="select counter_name, cntr_value from master.sys.sysperfinfo where instance_name='_Total' and counter_name in" +
				" ('Lock Requests/sec','Lock Wait Time (ms)','Lock Timeouts/sec','Number of DeadLocks/sec','Average Wait Time (ms)')";
		//Map<String,Double> map = query(sql);
		SET(sql);
	}
	//对map进行迭代输出
	public static void SET(String sql){
		Map<String,Double> map = query(sql);
		Set<Entry<String, Double>> set = map.entrySet();
		Iterator<Entry<String, Double>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, Double> entry=it.next();
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	}
	//访问方法明细
	public static void SqlServerAccessMethods(){
		String sql ="select counter_name,cntr_value from " +
				"master.sys.sysperfinfo where counter_name in ('Full Scans/sec','Range Scans/sec','Probe Scans/sec')";
		//Map<String,Double> map = query(sql);
		SET(sql);
	}
	//latch明细
	public static void SqlServerLatchDetail(){
		String sql ="select counter_name,cntr_value " +
				"from master.sys.sysperfinfo where counter_name " +
				"in ( 'Latch Waits/sec','Average Latch Wait Time (ms)')";
		//Map<String,Double> map = query(sql);
		SET(sql);
	}
	//Sql明细
	public static void SqlServerSqlDetail(){
		String sql="select counter_name,cntr_value from " +
				"sysperfinfo where counter_name in " +
				"('Batch Requests/sec','SQL Compilations/sec'," +
				"'SQL Re-Compilations/sec','Auto-Param Attempts/sec'," +
				"'Failed Auto-Params/sec')";
		//Map<String,Double> map = query(sql);
		SET(sql);
	}
	//登录数
	public static void SqlServerLogins(){
		String sql="select cntr_value from sysperfinfo " +
				"where object_name='SQLServer:General Statistics' " +
				"and counter_name='Logins/sec'";
		System.out.println(querySql(sql));
	}
	//注销数
	public static void SqlServerLogouts(){
		String sql="select cntr_value from sysperfinfo " +
				"where object_name='SQLServer:General Statistics' " +
				"and counter_name='Logouts/sec'";
		System.out.println(querySql(sql));
	}
	//内存使用情况
	public static void SqlServerMemory(){
		String sql="select counter_name,cntr_value from " +
				"master.sys.sysperfinfo where counter_name in " +
				"('Total Server Memory (KB)','SQL Cache Memory (KB)'," +
				"'Lock Memory (KB)','Optimizer Memory (KB)'," +
				"'Connection Memory (KB)','Granted Workspace Memory " +
				"(KB)','Memory Grants Pending','Memory Grants Outstanding')";
		System.err.println("内存使用情况-----");
		SET(sql);
	}
	//数据文件大小
	public static void SqlServerTotalDataFileSize(){
		String sql="select counter_name,cntr_value from " +
				"master.sys.sysperfinfo where object_name=" +
				"'SQLServer:Databases' and instance_name='_Total' " +
				"and counter_name='Data File(s) Size (KB)'";
		System.err.println("数据库文件大小-----");
		SET(sql);
	}
	//日志文件大小
	public static void SqlServerLogFileSize(){
		String sql="select counter_name,cntr_value from " +
				"master.sys.sysperfinfo where object_name=" +
				"'SQLServer:Databases' and instance_name='_Total' " +
				"and counter_name='Log File(s) Size (KB)'";
		System.err.println("日志文件大小----");
		SET(sql);
	}
	// 执行sql语句
	public static Map<String, Double> query(String sql) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
//				System.out.println(rs.getObject(1).toString().trim() + "---"
//						+ rs.getObject(2));
				map.put(rs.getObject(1).toString().trim(),
						Double.valueOf(rs.getObject(2).toString()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs, ps);
		}

		return map;
	}

	// 获取数据库具体信息
	public static void queryVersion() {
		 String sql = "select @@version";//版本信息
		//String sql = "select @@servername";// 主机名称

		System.out.println(querySql(sql));
	}

	// 获取数据库主机名称
	public static void queryHostName() {
		String sql = "select @@servername";// 主机名称
		System.out.println(querySql(sql));
	}

	public static String querySql(String sql) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String detail = "";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				detail = rs.getObject(1).toString().trim();
			}
		} catch (Exception e) {
		} finally {
			jdbc.close(rs, ps);
			
		}
		return detail;
	}

	// 获取数据库连接
//事务明细
	public static void SqlServerTransactions(){
		String sql="select counter_name,cntr_value from master.sys.sysperfinfo " +
				"where object_name='SQLServer:Databases' and instance_name=" +
				"'_Total' and counter_name in ('Transactions/sec','Active Transactions')";
		System.err.println("事务明细----");
		SET(sql);
	}
	//日志刷新明细
	public static void SqlServerLogFlush(){
		String sql="select counter_name,cntr_value from " +
				"master.sys.sysperfinfo where object_name" +
				"='SQLServer:Databases' and instance_name=" +
				"'_Total' and counter_name in " +
				"('Log Flushes/sec','Log Flush Waits/sec'," +
				"'Log Flush Wait Time')";
	System.err.println("日志刷新明细---");
	SET(sql);
	}
	//数据库文件大小
	public static void SqlServerDataFileSize(){
		String sql="select instance_name,cntr_value from " +
				"master.sys.sysperfinfo where object_name='SQLServer:Databases' and " +
				"counter_name='Data File(s) Size (KB)' and instance_name !='_Total'";
		Map<String,Double> map = query(sql);
		Set<Entry<String, Double>> set = map.entrySet();
		Iterator<Entry<String, Double>> it = set.iterator();
		while(it.hasNext()){
			Entry<String, Double> entry=it.next();
			System.out.println(entry.getKey()+"(MB)="+entry.getValue()/1024);
		}
	}
	//高速缓存中高速缓存的对象数
	public static void CacheObjectCounts(){
		String sql ="select instance_name ,cntr_value from master.sys.sysperfinfo " +
				"where counter_name = 'Cache Object Counts'";
		System.err.println("高速缓存中高速缓存的对象数--");
		SET(sql);
	}
	//高速缓存对象所使用的（KB）页的数目
	public static void CachePages(){
		String sql ="select instance_name ,cntr_value from master.sys.sysperfinfo" +
				" where counter_name = 'Cache Pages'";
		System.out.println("高速缓存对象所使用的（KB）页的数目---");
		SET(sql);
	}
	public static void SQLServerStaticManager(){
		String sql ="select counter_name,cntr_value from master.sys.sysperfinfo where " +
				"counter_name in ('Batch Requests/sec','Auto-Param Attempts/sec'," +
				"'SQL Compilations/sec','SQL Re-Compilations/sec')";
		System.out.println("SQL Server Static Manager---");
		SET(sql);
	}
	public static void main(String[] args) {
//		queryVersion();
//		SqlServerBufferCacheHit();
//		SqlServerActiveConnect();
//		SqlServerLockDetail();
//		SqlServerAccessMethods();
//		SqlServerLatchDetail();
//		SqlServerLogins();
//		SqlServerLogouts();
//		SqlServerMemory();
//		SqlServerTotalDataFileSize();
//		SqlServerLogFileSize();
//		SqlServerTransactions();
//		SqlServerLogFlush();
//		SqlServerDataFileSize();
		CacheObjectCounts();
		CachePages();
		SQLServerStaticManager();
	}

}
