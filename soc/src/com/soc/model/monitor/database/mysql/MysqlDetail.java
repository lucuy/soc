package com.soc.model.monitor.database.mysql;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.util.StringUtil;

@SuppressWarnings("serial")
public class MysqlDetail implements Serializable{
	@SuppressWarnings("unused")
	private static  Connection conn=null;
	private String version;//版本
	
	private int falg;//是否连通;1,代表连通，0，代表未连通
	private List<MySqlLastWarning> lastWarning;//最后一次告警
	private List<MysqlDB> mysqlDB;//数据库
	private int threadsConnected;//打开连接数
	private int abortedConnects;//中止连接数
	private int abortedClients;//中止客户端数
	private int openTables;//当前打开的数据表总数
	private int queryTimes;//已执行查询总数
	private String os;//操作系统
	private String hostName;//主机名
	private String dataDIR;//数据目录
	private String dir;//基本目录
	private String key_Hit;//键命中率
	private String qctb;//请求缓存大小
	private String thread_stack;//线程缓冲大小
	private String key_blocks_used;//使用键缓冲
	private int runningThread;//当前活动线程数
	private String kbs;//使用键缓冲大小
	private String qCacheHit;//缓存命中率
	private String qcs;//查询缓存大小
	private String innodbBPH;//InnoDB Buffer Pool 的命中率
	private String innodbBPS;//InnoDB 数据和索引缓存
	@SuppressWarnings("unused")
	private MysqlDetail() {
	}
	@SuppressWarnings("static-access")
	public MysqlDetail(Connection conn) {
		this.conn=conn;
		if(checkCoon()){
		this.setVersion(version);
		this.setAbortedClients(abortedClients);
		this.setAbortedConnects(abortedConnects);
		this.setDataDIR(dataDIR);
		this.setDir(dataDIR);
		this.setFalg(1);
		this.setHostName(hostName);
		this.setInnodbBPH(innodbBPH);
		this.setInnodbBPS(innodbBPS);
		this.setKbs(kbs);
		this.setKey_blocks_used(key_blocks_used);
		this.setKey_Hit(key_Hit);
		this.setLastWarning(lastWarning);
		this.setMysqlDB(mysqlDB);
		this.setOpenTables(openTables);
		this.setOs(os);
		this.setqCacheHit(qCacheHit);
		this.setQctb(qctb);
		this.setRunningThread(runningThread);
		this.setQueryTimes(queryTimes);
		this.setThread_stack(thread_stack);
		this.setThreadsConnected(threadsConnected);
		this.setQcs(qcs);
		}
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
	
			this.version = querySql("show variables like 'version'");
		
	}
	public int getFalg() {
		return falg;
	}
	public void setFalg(int falg) {
		
		this.falg = falg;
	}
	public List<MySqlLastWarning> getLastWarning() {
		return lastWarning;
	}
	@SuppressWarnings("static-access")
	public void setLastWarning(List<MySqlLastWarning> lastWarning) {
		
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<MySqlLastWarning> list = new ArrayList<MySqlLastWarning>();
			try{
				ps = conn.prepareStatement("show warnings");
				rs = ps.executeQuery();
				while (rs.next()) {
					MySqlLastWarning mw = new MySqlLastWarning();
					mw.setLevel(rs.getString(1));
					mw.setCode(rs.getString(2));
					mw.setMessage(rs.getString(3));
					list.add(mw);
				}
			}catch (Exception e) {
				this.close(ps, rs);
			}
			
			this.lastWarning =list;
		
	}
	public List<MysqlDB> getMysqlDB() {
		return mysqlDB;
	}
	@SuppressWarnings("static-access")
	public void setMysqlDB(List<MysqlDB> mysqlDB) {
	
		List<MysqlDB> dbList = new ArrayList<MysqlDB>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count=0;
		try {
			ps = conn.prepareStatement("show databases");
			rs = ps.executeQuery();
			while (rs.next()) {
				MysqlDB md = new MysqlDB();
				md.setNum((count++)+"");
				md.setDbName(rs.getString(1));
				dbList.add(md);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.close(ps, rs);
		}
		this.mysqlDB = dbList;
		
	}
	public int getThreadsConnected() {
		return threadsConnected;
	}
	public void setThreadsConnected(int threadsConnected) {
		
			this.threadsConnected = quertCount("show status like 'Threads_connected'");
		
	}
	public int getAbortedConnects() {
		return abortedConnects;
	}
	public void setAbortedConnects(int abortedConnects) {
		
			this.abortedConnects =quertCount("show status like 'Aborted_connects'");
		
	}
	public int getAbortedClients() {
		return abortedClients;
	}
	public void setAbortedClients(int abortedClients) {
		
			this.abortedClients =quertCount("show status like 'Aborted_connects'");
		
	}
	public int getOpenTables() {
		return openTables;
	}
	public void setOpenTables(int openTables) {
			this.openTables =quertCount("SHOW STATUS LIKE 'Open_tables'");
	}
	public int getQueryTimes() {
		return queryTimes;
	}
	public void setQueryTimes(int queryTimes) {
			this.queryTimes =quertCount("show global status like 'Com_select'");
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
			this.os = querySql("show variables like 'version_compile_os'");
	}
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
			this.hostName = querySql("show variables like 'hostname'");
	}
	public String getDataDIR() {
		return dataDIR;
	}
	public void setDataDIR(String dataDIR) {
			this.dataDIR = querySql("show variables like 'datadir'");
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
			this.dir = querySql("show variables like 'basedir'");
	}
	public String getKey_Hit() {
		return key_Hit;
	}
	public void setKey_Hit(String key_Hit) {
			String key_reads="show status like'key_reads'";
			String key_requests="show status like 'key_read_requests'";
			int kr = quertCount(key_reads);
			int krs = quertCount(key_requests);
			try{
				this.key_Hit =(100 * kr)
					/ krs + "%";
			}catch (Exception e) {
				this.key_Hit ="0.0%";
			}
	}
	
	public String getQctb() {
		return qctb;
	}
	public void setQctb(String qctb) {
			this.qctb =querySql("show status like 'Qcache_total_blocks'");
	}
	public String getThread_stack() {
		return thread_stack;
	}
	public void setThread_stack(String thread_stack) {
			this.thread_stack =querySql("show variables like 'thread_stack'")+"Bytes";
	}
	public String getKey_blocks_used() {
		return key_blocks_used;
	}
	public void setKey_blocks_used(String key_blocks_used) {
			key_blocks_used =querySql("show status like 'Key_blocks_used'");
			if(StringUtil.isNotEmpty(key_blocks_used)){
				this.key_blocks_used =key_blocks_used+"Bytes";
			}else
				this.key_blocks_used = "0.0Bytes";
	}
	public int getRunningThread() {
		return runningThread;
	}
	public void setRunningThread(int runningThread) {
			this.runningThread = quertCount("SHOW STATUS LIKE '%threads_running%'");
	}
	public String getKbs() {
		return kbs;
	}
	public void setKbs(String kbs) {
			kbs = querySql("show variables like 'key_buffer_size'");
			if(StringUtil.isNotEmpty(kbs)){
				this.kbs = kbs+"Bytes";
			}else{
				this.kbs = "0.0Bytes";
			}
	}
	public String getqCacheHit() {
		return qCacheHit;
	}
	public void setqCacheHit(String qCacheHit) {
			String key_reads="show status like 'Qcache_hits'";
			String key_requests="show status like'Qcache_inserts'";
			int kr = quertCount(key_reads);
			int krs = quertCount(key_requests);
			try{
				this.qCacheHit =(100 * kr)
					/ krs + "%";
			}catch (Exception e) {
				this.qCacheHit ="0.0%";
			}
	}
	public String getInnodbBPH() {
		return innodbBPH;
	}
	public void setInnodbBPH(String innodbBPH) {
			int pool_reads=quertCount("SHOW STATUS like 'Innodb_buffer_pool_reads'");
			int pool_read_requests=quertCount("SHOW STATUS like 'Innodb_buffer_pool_read_requests'");
			try{
				this.innodbBPH =((pool_read_requests-pool_reads)*100/pool_read_requests)+"%";
			}catch (Exception e) {
				this.innodbBPH ="0.0%";
			}
	}
	public String getInnodbBPS() {
		return innodbBPS;
	}
	public void setInnodbBPS(String innodbBPS) {
			innodbBPS =querySql("SHOW VARIABLES like 'innodb_buffer_pool_size'");
			if(StringUtil.isNotEmpty(innodbBPS)){
				this.innodbBPS =innodbBPS+"Bytes";
			}else
				this.innodbBPS = "0.0Bytes";
	}
	
	public String getQcs() {
		return qcs;
	}
	public void setQcs(String qcs) {
			qcs =querySql("SHOW VARIABLES LIKE 'query_cache_size'");
			if(StringUtil.isNotEmpty(qcs)){
				this.qcs =qcs+"Bytes";
			}else
				this.qcs = "0.0Bytes";
	}
	//关闭数据库连接
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
	//根据传入的sql查询结果返回String
	public static String querySql(String sql) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String result="";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				result = rs.getString(2);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps, rs);
		}

		return result;
	}
	//根据传入的sql查询数量
	public static int quertCount(String sql){
		PreparedStatement ps = null;
		ResultSet rs = null;
		int result=0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				result=	Integer.valueOf(rs.getObject(2).toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(ps, rs);
		}
		return result;
	}
	
}
