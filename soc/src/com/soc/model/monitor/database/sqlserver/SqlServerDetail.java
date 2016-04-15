package com.soc.model.monitor.database.sqlserver;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**SQL Server实体类
 * @author changhong
 *
 */
public class SqlServerDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private static  Connection conn=null;
	private String hostName;//主机名
	private String os;//操作系统
	private String version;//版本
	private Map<String,String> memory;//内存使用情况
	private int cntr_value;//数据文件大小
	private Map<String,String> accessMethods;//访问方法明细
	private Map<String,String> cacheObjectCounts;//高速缓存中高速缓存的对象数
	private Map<String,String> cachePages;//高速缓存对象所使用的（KB）页的数目
	private Map<String,String> staticManager;//SQLServerStaticManager
	private Map<String,String> dataFileSize;//数据文件大小
	private Map<String,String> logFlush;//日志刷新明细
	private Map<String,String> transactions;//事务明细
	private Map<String,String> logFileSize;//日志文件大小
	private int logins;//登录数
	private int logouts;//注销数
	private Map<String,String> sqlDetail;//Sql明细
	private Map<String,String> latchDetail;//latch明细
	private Map<String,String> lockDetail;//活动连接数
	private int activeConnect;//活动连接数
	private Map<String,String> bufferCacheDetail;//Buffer缓存明细
	private String bch; //Buffer缓存命中率
	private int falg;//是否连通;1,代表连通，0，代表未连通
	@SuppressWarnings("unused")
	private SqlServerDetail() {
	}
	@SuppressWarnings("static-access")
	public SqlServerDetail(Connection conn) {
		this.conn=conn;
		if(checkCoon()){
		this.setAccessMethods(accessMethods);
		this.setActiveConnect(activeConnect);
		this.setBch(bch);
		this.setBufferCacheDetail(bufferCacheDetail);
		this.setCacheObjectCounts(cacheObjectCounts);
		this.setCachePages(cachePages);
		this.setCntr_value(cntr_value);
		this.setDataFileSize(dataFileSize);
		this.setHostName(hostName);
		this.setLatchDetail(latchDetail);
		this.setLockDetail(lockDetail);
		this.setLogFileSize(logFileSize);
		this.setLogFlush(logFlush);
		this.setLogins(logins);
		this.setLogouts(logouts);
		this.setMemory(memory);
		this.setOs(os);
		this.setSqlDetail(sqlDetail);
		this.setStaticManager(staticManager);
		this.setTransactions(transactions);
		this.setVersion(version);
		this.setFalg(1);
		}
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		
			this.hostName =querySql("select @@servername");
		
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		
		this.os = os;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		
			this.version =querySql("select @@version");
	
	}
	public Map<String, String> getMemory() {
		return memory;
	}
	public void setMemory(Map<String, String> memory) {
		
			String sql="select counter_name,cntr_value from " +
					"master.sys.sysperfinfo where counter_name in " +
					"('Total Server Memory (KB)','SQL Cache Memory (KB)'," +
					"'Lock Memory (KB)','Optimizer Memory (KB)'," +
					"'Connection Memory (KB)','Granted Workspace Memory " +
					"(KB)','Memory Grants Pending','Memory Grants Outstanding')";
			this.memory = query(sql);
		
	}
	public int getCntr_value() {
		return cntr_value;
	}
	public void setCntr_value(int cntr_value) {
		
			
		String sql="select counter_name,cntr_value from " +
				"master.sys.sysperfinfo where object_name=" +
				"'SQLServer:Databases' and instance_name='_Total' " +
				"and counter_name='Data File(s) Size (KB)'";
		this.cntr_value = Integer.valueOf(query(sql).get("Data File(s) Size (KB)"));
		
	}
	public Map<String, String> getAccessMethods() {
		return accessMethods;
	}
	public void setAccessMethods(Map<String, String> accessMethods) {
		
			String sql ="select counter_name,cntr_value from " +
					"master.sys.sysperfinfo where counter_name in ('Full Scans/sec','Range Scans/sec','Probe Scans/sec')";
			this.accessMethods = query(sql);
		
	}
	public Map<String, String> getCacheObjectCounts() {
		return cacheObjectCounts;
	}
	public void setCacheObjectCounts(Map<String, String> cacheObjectCounts) {
		
			String sql ="select instance_name ,cntr_value from master.sys.sysperfinfo " +
					"where counter_name = 'Cache Object Counts'";
			this.cacheObjectCounts = query(sql);
		
	}
	public Map<String, String> getCachePages() {
		return cachePages;
	}
	public void setCachePages(Map<String, String> cachePages) {
		
			String sql ="select instance_name ,cntr_value from master.sys.sysperfinfo" +
					" where counter_name = 'Cache Pages'";
			this.cachePages = query(sql);
		
	}
	public Map<String, String> getStaticManager() {
		return staticManager;
	}
	public void setStaticManager(Map<String, String> staticManager) {
		
			String sql ="select counter_name,cntr_value from master.sys.sysperfinfo where " +
					"counter_name in ('Batch Requests/sec','Auto-Param Attempts/sec'," +
					"'SQL Compilations/sec','SQL Re-Compilations/sec')";
			this.staticManager = query(sql);
		
	}
	public Map<String, String> getDataFileSize() {
		return dataFileSize;
	}
	public void setDataFileSize(Map<String, String> dataFileSize) {
		
			String sql ="select instance_name,cntr_value from " +
					"master.sys.sysperfinfo where object_name='SQLServer:Databases' and " +
					"counter_name='Data File(s) Size (KB)' and instance_name !='_Total'";
			this.dataFileSize = query(sql);
		
	}
	public Map<String, String> getLogFlush() {
		return logFlush;
	}
	public void setLogFlush(Map<String, String> logFlush) {
		
			String sql ="select counter_name,cntr_value from " +
					"master.sys.sysperfinfo where object_name" +
					"='SQLServer:Databases' and instance_name=" +
					"'_Total' and counter_name in " +
					"('Log Flushes/sec','Log Flush Waits/sec'," +
					"'Log Flush Wait Time')";
			this.logFlush = query(sql);
		
	}
	public Map<String, String> getTransactions() {
		return transactions;
	}
	public void setTransactions(Map<String, String> transactions) {
		
			String sql ="select counter_name,cntr_value from master.sys.sysperfinfo " +
					"where object_name='SQLServer:Databases' and instance_name=" +
					"'_Total' and counter_name in ('Transactions/sec','Active Transactions')";
			this.transactions = query(sql);
		
	}
	public Map<String, String> getLogFileSize() {
		return logFileSize;
	}
	public void setLogFileSize(Map<String, String> logFileSize) {
	
			String sql ="select counter_name,cntr_value from " +
					"master.sys.sysperfinfo where object_name=" +
					"'SQLServer:Databases' and instance_name='_Total' " +
					"and counter_name='Log File(s) Size (KB)'";
			this.logFileSize = query(sql);
		
	}
	public int getLogins() {
		return logins;
	}
	public void setLogins(int logins) {
		
			
		String sql="select cntr_value from sysperfinfo " +
				"where object_name='SQLServer:General Statistics' " +
				"and counter_name='Logins/sec'";
		try {
			this.logins =Integer.valueOf(querySql(sql));
		} catch (Exception e) {
			this.logins = 0;
			e.printStackTrace();
		}
		
	}
	public int getLogouts() {
		return logouts;
	}
	public void setLogouts(int logouts) {
		
			
			String sql="select cntr_value from sysperfinfo " +
					"where object_name='SQLServer:General Statistics' " +
					"and counter_name='Logouts/sec'";
			try {
				this.logouts =Integer.valueOf(querySql(sql));
			} catch (Exception e) {
				this.logouts = 0;
				e.printStackTrace();
			}
			
	}
	public Map<String, String> getSqlDetail() {
		return sqlDetail;
	}
	public void setSqlDetail(Map<String, String> sqlDetail) {
		
			String sql ="select counter_name,cntr_value from " +
					"sysperfinfo where counter_name in " +
					"('Batch Requests/sec','SQL Compilations/sec'," +
					"'SQL Re-Compilations/sec','Auto-Param Attempts/sec'," +
					"'Failed Auto-Params/sec')";
			this.sqlDetail = query(sql);
		
	}
	public Map<String, String> getLatchDetail() {
		return latchDetail;
	}
	public void setLatchDetail(Map<String, String> latchDetail) {
		
			String sql ="select counter_name,cntr_value " +
					"from master.sys.sysperfinfo where counter_name " +
					"in ( 'Latch Waits/sec','Average Latch Wait Time (ms)')";
			this.latchDetail = query(sql);
		
	}
	public Map<String, String> getLockDetail() {
		return lockDetail;
	}
	public void setLockDetail(Map<String, String> lockDetail) {
		
			String sql ="select counter_name, cntr_value from master.sys.sysperfinfo where instance_name='_Total' and counter_name in" +
					" ('Lock Requests/sec','Lock Wait Time (ms)','Lock Timeouts/sec','Number of DeadLocks/sec','Average Wait Time (ms)')";
			this.lockDetail = query(sql);
		
	}
	public int getActiveConnect() {
		return activeConnect;
	}
	public void setActiveConnect(int activeConnect) {

			
			String sql="select cntr_value from master.sys.sysperfinfo where " +
					"object_name='SQLServer:General Statistics' and counter_name='User Connections'";
			try {
				this.activeConnect =Integer.valueOf(querySql(sql));
			} catch (Exception e) {
				this.activeConnect = 0;
				e.printStackTrace();
			}
			
		
	}
	public Map<String, String> getBufferCacheDetail() {
		return bufferCacheDetail;
	}
	public void setBufferCacheDetail(Map<String, String> bufferCacheDetail) {
		
			String sql ="select counter_name,cntr_value from master.sys.sysperfinfo " +
					"where object_name='SQLServer:Buffer Manager' and counter_name in " +
					"('Page lookups/sec','Page reads/sec','Page writes/sec'," +
					"'Total pages','Database pages','Free pages')";
			this.bufferCacheDetail = query(sql);
		
	}
	public String getBch() {
		return bch;
	}
	public void setBch(String bch) {
			String sql = "select counter_name,cntr_value from master.sys.sysperfinfo where object_name='SQLServer:Buffer Manager' and counter_name LIKE'%Buffer cache hit ratio%'";
			Map<String,String> map = query(sql);
			this.bch = (100*(Integer.valueOf(map.get("Buffer cache hit ratio"))))/Integer.valueOf(map.get("Buffer cache hit ratio base"))+"%";
	}
	private static void close(PreparedStatement ps1,ResultSet rs1){
		if(rs1!=null){
			try {
				rs1.close();
			} catch (SQLException e) {
				try {
					rs1.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		if(ps1!=null){
			try {
				ps1.close();
			} catch (SQLException e) {
				try {
					ps1.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}
	private static boolean checkCoon(){
		if(conn!=null)
			return true;
		else
			return false;
		
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
			close(ps, rs);
			
		}
		return detail;
	}
	// 执行sql语句
		public static Map<String, String> query(String sql) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			Map<String, String> map = new HashMap<String, String>();
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					map.put(rs.getString(1).trim(),
							rs.getString(2));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps, rs);
			}

			return map;
		}
		public int getFalg() {
			return falg;
		}
		public void setFalg(int falg) {
			this.falg = falg;
		}

}
