package com.soc.junit.test.sql.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.soc.junit.test.sql.base.JDBCUtils;
import com.soc.junit.test.sql.base.JdbcConnectionWrapper;

public class TestOracle {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@198.9.9.31:1521:orcl";// 默认的数据库
	private static final String USERNAME = "soc";
	private static final String PWD = "soc";
	static JDBCUtils jdbc = JDBCUtils.getInitialization(DRIVER, URL, USERNAME,
			PWD);
	static Connection conn=null;
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
				jdbc.close(rs, ps);
			}

			return result;
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
							dbList.add(rs.getString(1)+"$$"+rs.getString(2)+"$$"+rs.getString(3));
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						jdbc.close(rs, ps);
					}

					return dbList;
				}
	//主机名
		public static void OracleHostName(){
			String sql = "select host_name from v$instance";
			System.err.println("主机名--");
			System.out.println(querySql(sql));
		}
		//创建时间
		public static void OracleCreateTime(){
			String sql = "select created from v$database";
			System.err.println("创建时间--");
			System.out.println(querySql(sql));
		}
		//启动时间
		public static void OracleStartUpTime(){
			String sql = "select startup_time from v$instance";
			System.err.println("启动时间--");
			System.out.println(querySql(sql));
		}
		//版本
		public static void OracleVersion(){
			String sql = "select version from v$instance";
			System.err.println("版本--");
			System.out.println(querySql(sql));
		}
		//Latch争用总等待次数
		public static void OracleLatch_Total_Waits(){
			String sql = "select total_waits from v$system_event where event = 'latch free'";
			System.err.println("Latch争用总等待次数--");
			System.out.println(querySql(sql));
		}
		//Latch争用总等待时间
		public static void OracleLatch_Time_Waited(){
			String sql = "select time_waited from v$system_event where event = 'latch free'";
			System.err.println("Latch争用总等待时间--");
			System.out.println(querySql(sql));
		}
		//当前登录用户
		public static List<OracleCurrentUser> CurrentUser(){
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
				jdbc.close(rs, ps);
			}
			for (OracleCurrentUser user : list) {
				System.out.println(user.getSid()+"\t"+user.getUsername()+"\t"+user.getOsuser()+"\t"+user.getMschine());
			}
			return list;
		}
		//最消耗系统资源的用户和进程
				public static List<OracleHighCpuUsedUser> HighCpuUsedUser(){
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
						jdbc.close(rs, ps);
					}
					for (OracleHighCpuUsedUser user : list) {
						System.err.println(user.getSid()+"\t"+user.getUsername()+"\t"
								+user.getSpid()+"\t"+user.getStatus()+"\t"+user.getProg()
								+"\t"+user.getTermonal()+"\t"+user.getOsuser()
								);
					}
					return list;
				}
				//最近执行的20条sql语句
				public static List<OracleRecentSql> RecentSql(){
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
						jdbc.close(rs, ps);
					}
					for (OracleRecentSql sl : list) {
						System.out.println(sl.getLastTime()+"\t"+sl.getSqlTest());
					}
					return list;
				}
				//RedoLog使用情况
				public static List<RedoLog> OracleRedoLog(){
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
						jdbc.close(rs, ps);
					}
					for (RedoLog rl : list) {
						System.out.println(rl.getGroup()+"\t"+rl.getType()+"\t"+rl.getMember()+"\t"
					+rl.getBytes()+"\t"+rl.getStatus()+"\t"+rl.getFirst_change()+"\t"+rl.getFirst_time());
					}
					return list;
				}
				//最消耗系统资源的sql信息Top10
				public static List<String> OracleTop10Sql(){
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
						jdbc.close(rs, ps);
					}
					for (String str : list) {
						System.err.println(str);
					}
					return list;
				}
				//当前游标数
				public static int OracleOpenCursors(){
					String sql="select count(*) open_cursor from v$open_cursor";
					return queryCount(sql);
				}
				public static int queryCount(String sql){
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
						jdbc.close(rs, ps);
					}
					return count;
				}
				//表空间状态
				public static List<OracleTableSpace> OracleTableSpace(){
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
						jdbc.close(rs, ps);
					}
					for (OracleTableSpace ts : list) {
						System.out.println(ts.getFile()+"\t"+ts.getStatus()+"\t"+ts.getChange()+"\t"+ts.getTime());
					}
					return list;
				}
				//剩余空间
				public static List<OracleCustom> OracleFreeSpace(){
					String sql="SELECT tablespace_name 表空间,sum(blocks*8192/1000000) " +
							"剩余空间M FROM dba_free_space GROUP BY tablespace_name";
					return OracleSpace(sql,1);
				}
				//总容量
				public static List<OracleCustom> OracleTotalSpace(){
					String sql="select b.name,sum(a.bytes/1000000)总空间 from " +
							"v$datafile a,v$tablespace b where a.ts#=b.ts# group by b.name";
					return OracleSpace(sql,1);
				}
				public static List<OracleCustom> OracleSpace(String sql,int falg){
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
						jdbc.close(rs, ps);
					}
					for (OracleCustom fs : list) {
						System.out.println(fs.getCostom1()+"\t"+fs.getCostom2());
					}
					return list;
				}
				//当前游标数
				public static int OracleConnect(){
					String sql="select count(1) from v$session";
					return queryCount(sql);
				}
				//配置参数
				public static List<OracleCustom> OracleConfigInfo(){
					String sql="select name,value from v$parameter";
					return OracleSpace(sql,0);
				}
				//数据库内存使用
				public static String OracleDBMemUsed(){
					String sql="select ((select sum(bytes) from v$sgastat )+" +
							"(select value from v$PGASTAT where name='total PGA allocated' ))/1024/1024 as mem_use from dual";
					System.out.println(String.format("%.2f", Double.valueOf(querySql(sql))));
					return String.format("%.2f", Double.valueOf(querySql(sql)))+"M";
				}
				//缓存命中率
				public static String Library_Cache_Hit(){
					System.out.println(String.format("%.2f", Double.valueOf(querySql("select round(sum(pinhits)/sum(pins) * 100,2) as librarycachehit from v$librarycache")))+"%");
					return String.format("%.2f", Double.valueOf(querySql("select round(sum(pinhits)/sum(pins) * 100,2) as librarycachehit from v$librarycache")))+"%";
				}
				public static String Buffer_Cache_Hit(){
					System.out.println(String.format("%.2f", Double.valueOf(querySql("select trunc((1-(sum(decode(name,'physical reads',value,0))/(sum(decode(name,'db clock gets',value,0)) + (sum(decode(name,'consistent gets',value,0)))))) * 100) as buffercachehit from v$sysstat")))+"%");
					return String.format("%.2f", Double.valueOf(querySql("select trunc((1-(sum(decode(name,'physical reads',value,0))/(sum(decode(name,'db clock gets',value,0)) + (sum(decode(name,'consistent gets',value,0)))))) * 100) as buffercachehit from v$sysstat")))+"%";
				}
				//等待事件
				public static String Total_Timeouts(){
					System.out.println(String.format("%.1f",Double.valueOf(querySql("select total_timeouts from v$system_event where event = 'buffer busy waits'")))+"ms");
					return  String.format("%.1f",Double.valueOf(querySql("select total_timeouts from v$system_event where event = 'buffer busy waits'")))+"ms";
				}
				public static String Average_Wait(){
					System.out.println(String.format("%.1f",Double.valueOf(querySql("select average_wait/10 as average_wait from v$system_event where event = 'buffer busy waits'")))+"ms");
					return String.format("%.1f",Double.valueOf(querySql("select average_wait/10 as average_wait from v$system_event where event = 'buffer busy waits'")))+"ms";
				}
				//内存排序占总排序的比例
				public static String OracleInMemorySort(){
					System.out.println(String.format("%.2f", Double.valueOf(querySql("select round((100*b.value)/decode((a.value+b.value), 0, 1, (a.value+b.value)), 2) as InMemorySort from v$sysstat a, v$sysstat b where a.name='sorts (disk)' and b.name='sorts (memory)'")))+"%");
					return String.format("%.2f", Double.valueOf(querySql("select round((100*b.value)/decode((a.value+b.value), 0, 1, (a.value+b.value)), 2) as InMemorySort from v$sysstat a, v$sysstat b where a.name='sorts (disk)' and b.name='sorts (memory)'")))+"%";
				}
				//获得锁的进程数
				public static String OracleObtainLockCourse(){
					System.out.println(querySql("select count(*) from v$lock where lmode!=0 and lmode!=1"));
					return (querySql("select count(*) from v$lock where lmode!=0 and lmode!=1"));
				}
				//等待锁的进程数
				public static String OracleWaitLockCourse(){
					System.out.println(querySql("select count(*)  from v$lock where request!=0 and request!=1"));
					return (querySql("select count(*)  from v$lock where request!=0 and request!=1"));
				}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
//		OracleHostName();
//		OracleCreateTime();
//		OracleStartUpTime();
//		OracleVersion();
//		OracleLatch_Total_Waits();
//		OracleLatch_Time_Waited();
//		CurrentUser();
//		HighCpuUsedUser();
//		RecentSql();
//		OracleRedoLog();
//		OracleTop10Sql();
//		OracleOpenCursors();
		//OracleTableSpace();
		OracleFreeSpace();
		OracleTotalSpace();
//		OracleConnect();
//		OracleConfigInfo();
		OracleDBMemUsed();
		Library_Cache_Hit();
		Buffer_Cache_Hit();
		Total_Timeouts();
		Average_Wait();
		OracleInMemorySort();
		OracleObtainLockCourse();
		OracleWaitLockCourse();
	}

}
