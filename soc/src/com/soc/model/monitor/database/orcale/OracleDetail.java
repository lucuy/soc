package com.soc.model.monitor.database.orcale;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.util.StringUtil;


@SuppressWarnings("serial")
public class OracleDetail implements Serializable{
	
	private static  Connection conn=null;
	private String hostName;//主机名
	private String creatTime;//创建时间
	private String version;//版本
	private String startTime;//启动时间
	private int latchCount;//Latch争用总等待次数
	private int latchTime;//Latch争用总等待时间
	private int openCursoors;//当前游标数
	private List<OracleCurrentUser> currentUser;//当前登录用户
	private List<OracleHighCpuUsedUser> highCpuUsedUser;//最消耗系统资源的用户和进程
	private List<OracleRecentSql> recentSql;//最近执行的sql语句
	private List<RedoLog> oracleRedoLog;//RedoLog使用情况
	private List<String> oracleTop10Sq;//最消耗系统资源的sql信息Top10
	private  List<OracleTableSpace> oracleTableSpace;//表空间状态
	private List<OracleCustom> oracleFreeSpace;//剩余空间
	private List<OracleCustom> oracleTotalSpace;//总容量
	private List<OracleCustom> oracleConfigInfo;//配置参数
	private int oracleConnect;//连接数
	private String oracleDBMemUsed;//数据库内存使用
	//缓存命中率
	private String lch;
	private String bch;
	//等待事件
	private String totalTimeouts;
	private String averageWait;
	//内存排序占总排序的比例
	private String  oracleInMemorySort;
	//获得锁的进程数
	private String oracleObtainLockCourse;
	//等待锁的进程数
	private String oracleWaitLockCourse;
	private int falg;//是否连通;1,代表连通，0，代表未连通
	
	@SuppressWarnings("unused")
	private OracleDetail() {
		
	}
	@SuppressWarnings("static-access")
	public OracleDetail(Connection conn) {
		this.conn=conn;
		if(checkCoon()){
		this.setAverageWait(averageWait);
		this.setBch(bch);
		this.setCreatTime(creatTime);
		this.setCurrentUser(currentUser);
		this.setCurrentUser(currentUser);
		this.setFalg(1);
		this.setHighCpuUsedUser(highCpuUsedUser);
		this.setHostName(hostName);
		this.setLatchCount(latchCount);
		this.setLatchTime(latchTime);
		this.setLch(lch);
		this.setOpenCursoors(openCursoors);
		this.setOracleConfigInfo(oracleConfigInfo);
		this.setOracleConnect(oracleConnect);
		this.setOracleDBMemUsed(oracleDBMemUsed);
		this.setOracleFreeSpace(oracleFreeSpace);
		this.setOracleInMemorySort(oracleInMemorySort);
		this.setOracleObtainLockCourse(oracleObtainLockCourse);
		this.setOracleRedoLog(oracleRedoLog);
		this.setStartTime(startTime);
		this.setOracleTop10Sq(oracleTop10Sq);
		this.setOracleTotalSpace(oracleTotalSpace);
		this.setOracleWaitLockCourse(oracleWaitLockCourse);
		this.setRecentSql(recentSql);
		this.setTotalTimeouts(totalTimeouts);
		this.setVersion(version);
		this.setOracleTableSpace(oracleTableSpace);
		}
	}
	
	public String getHostName() {
		return hostName;
	}
	public  void setHostName(String hostName) {
			this.hostName = querySql("select host_name from v$instance");
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
			this.creatTime = querySql("select created from v$database");
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
			this.version = querySql("select version from v$instance");
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
			this.startTime = querySql("select startup_time from v$instance");
	}
	public int getLatchCount() {
		return latchCount;
	}
	public void setLatchCount(int latchCount) {
			this.latchCount = Integer.valueOf(querySql("select total_waits from v$system_event where event = 'latch free'"));
	}
	public int getLatchTime() {
		return latchTime;
	}
	public void setLatchTime(int latchTime) {
			this.latchTime = Integer.valueOf(querySql("select time_waited from v$system_event where event = 'latch free'"));
	}
	public int getOpenCursoors() {
		return openCursoors;
	}
	public void setOpenCursoors(int openCursoors) {
			this.openCursoors = Integer.valueOf(querySql("select count(*) open_cursor from v$open_cursor"));
	}
	public List<OracleCurrentUser> getCurrentUser() {
		
		return currentUser;
	}
	public void setCurrentUser(List<OracleCurrentUser> currentUser) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<OracleCurrentUser> list = new ArrayList<OracleCurrentUser>();
			String sql="select sid, username,osuser,machine from v$session " +
					"where username is not null";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					OracleCurrentUser user = new OracleCurrentUser();
					user.setSid(rs.getString(1));
					user.setUsername(rs.getString(2));
					user.setOsuser(rs.getString(3));
					user.setMschine(rs.getString(4));
					list.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps, rs);
			}
			this.currentUser = list;
	}
	public List<OracleHighCpuUsedUser> getHighCpuUsedUser() {
		return highCpuUsedUser;
	}
	public void setHighCpuUsedUser(List<OracleHighCpuUsedUser> highCpuUsedUser) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<OracleHighCpuUsedUser> list = new ArrayList<OracleHighCpuUsedUser>();
			String sql="select a.sid,a.username,spid,status,substr(a.program,1,40) " +
					"prog,a.terminal,osuser from v$session a,v$process b,v$sesstat c " +
					"where c.statistic#=12 and c.sid=a.sid and a.paddr=b.addr and a.username is " +
					"not null order by value desc";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					OracleHighCpuUsedUser user = new OracleHighCpuUsedUser();
					user.setSid(rs.getString(1));
					user.setUsername(rs.getString(2));
					user.setSpid(rs.getString(3));
					user.setStatus(rs.getString(4));
					user.setProg(rs.getString(5));
					user.setTermonal(rs.getString(6));
					user.setOsuser(rs.getString(7));
					list.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps, rs);
			}
			
			this.highCpuUsedUser = list;
	}
	public List<OracleRecentSql> getRecentSql() {
		return recentSql;
	}
	public void setRecentSql(List<OracleRecentSql> recentSql) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<OracleRecentSql> list = new ArrayList<OracleRecentSql>();
			String sql="select s.LAST_LOAD_TIME,st.SQL_TEXT from v$sql s,v$sqltext st where s.ADDRESS=st.ADDRESS and rownum<=20 order by s.LAST_LOAD_TIME desc";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					OracleRecentSql rsql = new OracleRecentSql();
					rsql.setLastTime(rs.getString(1));
					rsql.setSqlTest(rs.getString(2).trim());
					
					list.add(rsql);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps, rs);
			}
			
			this.recentSql = list;
	}
	public List<RedoLog> getOracleRedoLog() {
		return oracleRedoLog;
	}
	public void setOracleRedoLog(List<RedoLog> oracleRedoLog) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<RedoLog> list = new ArrayList<RedoLog>();
			String sql="select a.group#,a.type,a.member,b.bytes,b.status,b.first_change#,first_time from v$logfile a,v$log b where a.group#=b.group#";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					RedoLog rl = new RedoLog();
					rl.setGroup(rs.getString(1));
					rl.setType(rs.getString(2));
					rl.setMember(rs.getString(3));
					rl.setBytes(rs.getString(4));
					rl.setStatus(rs.getString(5));
					rl.setFirst_change(rs.getString(6));
					rl.setFirst_time(rs.getString(7));
					list.add(rl);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps, rs);
			}
			this.oracleRedoLog = list;
	}
	public List<String> getOracleTop10Sq() {
		return oracleTop10Sq;
	}
	public void setOracleTop10Sq(List<String> oracleTop10Sq) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<String> list = new ArrayList<String>();
			String sql="SELECT sql_text  FROM  v$sqlarea where rownum<11 ORDER BY disk_reads DESC";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					list.add(rs.getString(1).trim());
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps, rs);
			}
			for (String str : list) {
				System.err.println(str);
			}
			this.oracleTop10Sq = list;
	}
	public List<OracleTableSpace> getOracleTableSpace() {
		return oracleTableSpace;
	}
	public void setOracleTableSpace(List<OracleTableSpace> oracleTableSpace) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<OracleTableSpace> list = new ArrayList<OracleTableSpace>();
			String sql="select * from v$backup";
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					OracleTableSpace ts = new OracleTableSpace();
					ts.setFile(rs.getString(1));
					ts.setStatus(rs.getString(2));
					ts.setChange(rs.getString(3));
					ts.setTime(rs.getString(4));
					list.add(ts);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(ps, rs);
			}
			this.oracleTableSpace = list;
	}
	public List<OracleCustom> getOracleFreeSpace() {
		return oracleFreeSpace;
	}
	public void setOracleFreeSpace(List<OracleCustom> oracleFreeSpace) {
			String sql="SELECT tablespace_name 表空间,sum(blocks*8192/1000000) " +
					"剩余空间M FROM dba_free_space GROUP BY tablespace_name";
			this.oracleFreeSpace = OracleSpace(sql,1);
	}
	public List<OracleCustom> getOracleTotalSpace() {
		return oracleTotalSpace;
	}
	public void setOracleTotalSpace(List<OracleCustom> oracleTotalSpace) {
			String sql="select b.name,sum(a.bytes/1000000)总空间 from " +
					"v$datafile a,v$tablespace b where a.ts#=b.ts# group by b.name";
			this.oracleTotalSpace = OracleSpace(sql,1);
	}
	public List<OracleCustom> getOracleConfigInfo() {
		return oracleConfigInfo;
	}
	public void setOracleConfigInfo(List<OracleCustom> oracleConfigInfo) {
			String sql="select name,value from v$parameter";
			this.oracleConfigInfo = OracleSpace(sql,0);
	}
	public int getOracleConnect() {
		return oracleConnect;
	}
	public void setOracleConnect(int oracleConnect) {
			this.oracleConnect = Integer.valueOf(querySql("select count(1) from v$session"));
	}
	public String getOracleDBMemUsed() {
		return oracleDBMemUsed;
	}
	public void setOracleDBMemUsed(String oracleDBMemUsed) {
			String sql="select ((select sum(bytes) from v$sgastat )+" +
					"(select value from v$PGASTAT where name='total PGA allocated' ))/1024/1024 as mem_use from dual";
			this.oracleDBMemUsed = String.format("%.2f", Double.valueOf(querySql(sql)))+"M";
	}
	public String getLch() {
		return lch;
	}
	public void setLch(String lch) {
			this.lch= String.format("%.2f", Double.valueOf(querySql("select round(sum(pinhits)/sum(pins) * 100,2) as librarycachehit from v$librarycache")))+"%";
	}
	public String getBch() {
		return bch;
	}
	public void setBch(String bch) {
			this.bch = String.format("%.2f", Double.valueOf(querySql("select trunc((1-(sum(decode(name,'physical reads',value,0))/(sum(decode(name,'db clock gets',value,0)) + (sum(decode(name,'consistent gets',value,0)))))) * 100) as buffercachehit from v$sysstat")))+"%";
	}
	public String getTotalTimeouts() {
		return totalTimeouts;
	}
	public void setTotalTimeouts(String totalTimeouts) {
			totalTimeouts =querySql("select total_timeouts from v$system_event where event = 'buffer busy waits'");
		if(StringUtil.isNotEmpty(totalTimeouts)){
			this.totalTimeouts = totalTimeouts+"ms";
		}
		else{
			this.totalTimeouts = "0.0ms";
		}
	}
	public String getAverageWait() {
		return averageWait;
	}
	public void setAverageWait(String averageWait) {
			averageWait=querySql("select average_wait/10 as average_wait from v$system_event where event = 'buffer busy waits'");
			if(StringUtil.isNotEmpty(averageWait)){
				this.averageWait = averageWait+"ms";
			}else{
				this.averageWait = "0.0ms";
				
			}
	}
	public String getOracleInMemorySort() {
		return oracleInMemorySort;
	}
	public void setOracleInMemorySort(String oracleInMemorySort) {
			this.oracleInMemorySort = String.format("%.2f", Double.valueOf(querySql("select round((100*b.value)/decode((a.value+b.value), 0, 1, (a.value+b.value)), 2) as InMemorySort from v$sysstat a, v$sysstat b where a.name='sorts (disk)' and b.name='sorts (memory)'")))+"%";
	}
	public String getOracleObtainLockCourse() {
		return oracleObtainLockCourse;
	}
	public void setOracleObtainLockCourse(String oracleObtainLockCourse) {
			this.oracleObtainLockCourse = (querySql("select count(*) from v$lock where lmode!=0 and lmode!=1"));
	}
	public String getOracleWaitLockCourse() {
		return oracleWaitLockCourse;
	}
	public void setOracleWaitLockCourse(String oracleWaitLockCourse) {
			this.oracleWaitLockCourse = (querySql("select count(*)  from v$lock where request!=0 and request!=1"));
	}
	public int getFalg() {
		return falg;
	}
	public void setFalg(int falg) {
			this.falg = 1;
	}
	private static int queryCount(String sql){
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			count= rs.getInt(1);
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			close(ps, rs);
		}
		return count;
	}
	
	//拼接sql
			public static Map<String, Integer> query(String sql) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				Map<String, Integer> map = new HashMap<String, Integer>();
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						map.put(rs.getObject(1).toString().trim(),
								Integer.valueOf(rs.getObject(2).toString()));
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(ps, rs);
				}

				return map;
			}
			public static String querySql(String sql) {
				PreparedStatement ps = null;
				ResultSet rs = null;
				String result="";
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						result = rs.getString(1);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(ps, rs);
				}

				return result;
			}
			private static List<OracleCustom> OracleSpace(String sql,int falg){
				PreparedStatement ps = null;
				ResultSet rs = null;
				List<OracleCustom> list = new ArrayList<OracleCustom>();
				try {
					ps = conn.prepareStatement(sql);
					rs = ps.executeQuery();
					while (rs.next()) {
						OracleCustom fs = new OracleCustom();
						fs.setCostom1(rs.getString(1));
						if(falg==1){
							fs.setCostom2(String.format("%.2f", Double.valueOf(rs.getString(2)))+"M");
						}else{
						fs.setCostom2(rs.getString(2));
						}
						list.add(fs);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					close(ps, rs);
				}
				
				return list;
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
	
}
