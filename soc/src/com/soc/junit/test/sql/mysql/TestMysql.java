package com.soc.junit.test.sql.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.soc.junit.test.sql.base.JDBCUtils;
import com.soc.junit.test.sql.base.JdbcConnectionWrapper;

public class TestMysql {
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://198.9.9.9:3306/?useUnicode=true&amp;characterEncoding=UTF-8";// 默认的数据库
	private static final String USERNAME = "root";
	private static final String PWD = "root";
	static JDBCUtils jdbc = JDBCUtils.getInitialization(DRIVER, URL, USERNAME,
			PWD);
	static Connection conn=null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;
		static{
			long time = System.currentTimeMillis();
			JdbcConnectionWrapper warp=jdbc.getConnection();
			conn = warp.getConn();
			long end = System.currentTimeMillis();
			//System.out.println(warp.getErrorMsg());
			System.out.println("连接时间："+(end -time)+"ms");
		}
		//拼接sql
		public static Map<String, Integer> query(String sql) {
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
//					System.out.println(rs.getObject(1).toString().trim() + "---"
//							+ rs.getObject(2));
					map.put(rs.getObject(1).toString().trim(),
							Integer.valueOf(rs.getObject(2).toString()));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbc.close(rs, ps);
			}

			return map;
		}
		//对map进行迭代输出
		public static void SET(String sql){
			Map<String,Integer> map = query(sql);
			Set<Entry<String, Integer>> set = map.entrySet();
			Iterator<Entry<String, Integer>> it = set.iterator();
			while(it.hasNext()){
				Entry<String, Integer> entry=it.next();
				System.out.println(entry.getKey()+"="+entry.getValue());
			}
		}
		//打开连接数
		public static void MySqlThreadsConnected(){
			String sql="show status like 'Threads_connected'";
			System.err.println("连接数----");
			SET(sql);
		}
		//中止连接数,中止客户端连接数
		public static void MySqlAbortedConnects(){
			String sql = "show status like 'Aborted_connects'";
			System.err.println("中止连接数");
			SET(sql);
		}
		//已执行查询总数
		public static void MySqlQueryTimes(){
			String sql="show global status like 'Com_select'";
			System.err.println("已执行查询总数");
			SET(sql);
		}
		//当前打开的数据表总数
		public static void MySqlOpenTables(){
			String sql="SHOW STATUS LIKE 'Open_tables'";
			System.err.println("当前打开的数据表总数");
			SET(sql);
		}
		//线程缓存大小
		public static void MySqlThrandStack(){
			String sql="show variables like 'thread_stack'";
			System.err.println("线程缓冲大小--");
			SET(sql);
		}
		//使用键缓冲
		public static void MySqlKeyBlocksUsed(){
			String sql="show status like 'Key_blocks_used'";
			System.err.println("使用键缓冲--");
			SET(sql);
		}
		//使用键缓冲大小
		public static void MySqlKeyBufferSize(){
			String sql="show variables like 'key_buffer_size'";
			System.err.println("使用键缓冲大小--");
			SET(sql);
		}
		//请求缓存大小
		public static void MySqlQCacheTotalBlocks(){
			String sql="show status like 'Qcache_total_blocks'";
			System.err.println("请求缓存大小--");
			SET(sql);
		}
		//键命中率
		public static void MySqlKeyHit(){
			String key_reads="show status like'key_reads'";
			String key_requests="show status like 'key_read_requests'";
			System.err.println("键命中率");
			Map<String, Integer> map = query(key_reads);
			Map<String, Integer> map1 = query(key_requests);
			try{
			System.out.println((100 * map.get("key_reads"))
					/ map1.get("key_read_requests") + "%");
			}catch (Exception e) {
				System.out.println("0.0%");
			}
		}
		//缓存命中率
		public static void MySqlQCacheHit(){
			String key_reads="show status like 'Qcache_hits'";
			String key_requests="show status like'Qcache_inserts'";
			System.err.println("缓存命中率");
			Map<String, Integer> map = query(key_reads);
			Map<String, Integer> map1 = query(key_requests);
			if(map1.get("Qcache_inserts")!=0){
				System.out.println((100 * map.get("Qcache_hits"))
						/ map1.get("Qcache_inserts") + "%");
			}else {
				System.out.println("0.0%");
			}
		}
		//当前活动线程数
		public static void MySqlRunningThread(){
			String sql="SHOW STATUS LIKE '%threads_running%'";
			System.err.println("当前活动线程数--");
			SET(sql);
		}
		public static String querySql(String sql) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			String result="";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
//					System.out.println(rs.getObject(1).toString().trim() + "---"
//							+ rs.getObject(2));
					result = rs.getString(2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbc.close(rs, ps);
			}

			return result;
		}
		//Mysql版本
		public static void MySqlVersion(){
			String sql="show variables like 'version'";
			System.err.println("Mysql版本--");
			System.out.println(querySql(sql));
		}
		//操作系统
		public static void OS(){
			String sql="show variables like 'version_compile_os'";
			System.err.println("操作系统--");
			System.out.println(querySql(sql));
		}
		//主机名
		public static void HostName(){
			String sql="show variables like 'hostname'";
			System.err.println("主机名--");
			System.out.println(querySql(sql));
		}
		//基本目录
		public static void BaseDir(){
			String sql="show variables like'basedir'";
			System.err.println("基本目录--");
			System.out.println(querySql(sql));
		}
		//数据目录
		public static void DataDir(){
			String sql="show variables like'datadir'";
			System.err.println("数据目录--");
			System.out.println(querySql(sql));
		}
		//数据库
		public static void DBList(){
			String sql="show databases";
			System.err.println("数据库--");
			for (String str : queryDB(sql)) {
				System.out.println(str);
			}
		}
		//最后一次警告
		public static void LastWarning(){
			String sql="show warnings";
			System.err.println("最后一次警告");
			for (String str : queryWarning(sql)) {
				System.out.println(str);
			}
		}
		//查询告警
		public static List<String> queryWarning(String sql) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<String> dbList = new ArrayList<String>();
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
//					System.out.println(rs.getObject(1).toString().trim() + "---"
//							+ rs.getObject(2));
					dbList.add(rs.getString(1)+"$$"+rs.getString(2)+"$$"+rs.getString(3));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbc.close(rs, ps);
			}

			return dbList;
		}
		//查询数据库
		public static List<String> queryDB(String sql) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<String> dbList = new ArrayList<String>();
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
//					System.out.println(rs.getObject(1).toString().trim() + "---"
//							+ rs.getObject(2));
					dbList.add(rs.getString(1));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbc.close(rs, ps);
			}

			return dbList;
		}
		//查询缓存大小
		public static void MySqlQueryCacheSize(){
			String sql="SHOW VARIABLES LIKE 'query_cache_size'";
			System.out.println("查询缓存大小");
			SET(sql);
		}
		//InnoDB 数据和索引缓存
		public static void MySqlInnodbBufferPoolSize(){
			String sql="SHOW VARIABLES like 'innodb_buffer_pool_size'";
			System.err.println("InnoDB 数据和索引缓存");
			SET(sql);
		}
		//InnoDB Buffer Pool 的命中率
		public static void MySqlInnodbBufferPoolHit(){
			String reads = "SHOW STATUS like 'Innodb_buffer_pool_reads'";
			String requests = "SHOW STATUS like 'Innodb_buffer_pool_read_requests'";
			int pool_reads=query(reads).get("Innodb_buffer_pool_reads");
			int pool_read_requests=query(requests).get("Innodb_buffer_pool_read_requests");
			System.out.println("InnoDB Buffer Pool 的命中率");
			System.out.println(((pool_read_requests-pool_reads)*100/pool_read_requests)+"%");
		}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MySqlThreadsConnected();
		MySqlAbortedConnects();
		MySqlQueryTimes();
		MySqlOpenTables();
		MySqlThrandStack();
		MySqlKeyBlocksUsed();
		MySqlKeyBufferSize();
		MySqlQCacheTotalBlocks();
		MySqlKeyHit();
		MySqlQCacheHit();
		MySqlRunningThread();
		MySqlVersion();
		OS();
		HostName();
		DataDir();
		DBList();
		LastWarning();
		MySqlQueryCacheSize();
		MySqlInnodbBufferPoolSize();
		MySqlInnodbBufferPoolHit();
		
	}

}
