package com.soc.junit.test.sql.sysbase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.soc.junit.test.sql.base.JDBCUtils;
import com.soc.junit.test.sql.base.JdbcConnectionWrapper;

public class TestSyBase {
	private static final String DRIVER = "com.sybase.jdbc3.jdbc.SybDriver";
	private static final String URL = "jdbc:sybase:Tds:198.9.9.31:5000";// 默认的数据库
	private static final String USERNAME = "sa";
	private static final String PWD = "";
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
//		System.out.println(warp.isOk());
//		System.out.println(warp.getErrorMsg());
		System.out.println("连接时间："+(end -time)+"ms");
	}
	public static String hostname(){
		String sql ="Select  host_name()";
		query(sql);
		return null;
	}
	//每分钟读入和写入错误数
	public static int RWError(){
		String sql="select @@total_errors";
		query(sql);
		return 0;
	}
	//平均每分钟IO时间
	public static void IoBusy(){
		String sql ="select @@io_busy";
		query(sql);
	}
	//数据库表空间
	public static List<SyBaseTableSpace> tableSpace(){
		String sql="select b.name,(sum(a.size)*2+0.1)/1024 as total," +
				"((sum(a.size)-sum(a.unreservedpgs))*2+0.1)/1024 " +
				"as inuse,100*((sum(a.size)-sum(a.unreservedpgs))*2+0.1)/(sum(a.size)*2+0.1) as " +
				"usage from sysusages a,sysdatabases b where a.dbid=b.dbid group by b.name";
			List<SyBaseTableSpace> list = new ArrayList<SyBaseTableSpace>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				SyBaseTableSpace st = new SyBaseTableSpace();
				st.setName(rs.getString(1));
				st.setTotal(rs.getInt(2));
				st.setInuse(rs.getDouble(3));
				st.setUsage(rs.getDouble(4));
				System.out.println(rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getDouble(3)+"\t"+rs.getDouble(4));
				list.add(st);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs, ps);
		}
		return list;
	}
	//平均每分钟读取数据包数
	public static void  PackReceived(){
		String sql = "select @@pack_received";
		query(sql);
	}
	//平均每分钟发出数据包数
	public static void  PackSent(){
		String sql = "select @@pack_sent";
		query(sql);
	}
	//平均每分钟读写数据包错误数
	public static void  PacketErrors(){
		String sql = "select @@packet_errors";
		query(sql);
	}
	//平均每分钟读次数
	public static void  TotalRead(){
		String sql = "select @@total_read";
		query(sql);
	}
	//平均每分钟使用cpu时间
	public static void  CpuBusy(){
		String sql = "select @@cpu_busy";
		query(sql);
	}
	//平均每分钟写次数
	public static void  TotalWrite(){
		String sql = "select @@total_write";
		query(sql);
	}
	//平均每分钟登录或尝试登录次数
	public static void  Connections(){
		String sql = "select @@connections";
		query(sql);
	}
	//查看当前进程
	public static void process(){
		String sql ="sp_who";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"
			+rs.getString(5)+"\t"+rs.getString(6)+"\t"+rs.getString(8)+"\t"+rs.getString(9));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs, ps);
		}
	}
	//死锁数
		public static List<SyLocks> locks(){
			String sql="select p.spid,suser_name(p.suid),p.blocked,suser_name(p1.suid)," +
					"db_name(p.dbid),p.status,p.program_name,getdate()," +
					"p.time_blocked from master..sysprocesses p,master..sysprocesses " +
					"p1 where p.blocked>0 and p.blocked *=p1.spid";
				List<SyLocks> list = new ArrayList<SyLocks>();
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					SyLocks sl = new SyLocks();
					sl.setSpid(rs.getString(1));
					sl.setBlocked(rs.getString(2));
					sl.setStatus(rs.getString(3));
					sl.setProgram_name(rs.getString(4));
					sl.setTime_blocked(rs.getString(5));
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
					list.add(sl);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				jdbc.close(rs, ps);
			}
			return list;
		}
	//查询sql
	public static String query(String sql){
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
			System.out.println(rs.getObject(1).toString().trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs, ps);
		}
		return null;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		hostname();
		RWError();
		tableSpace();
		locks();
		IoBusy();
		PacketErrors();
		PackReceived();
		PackSent();
		TotalRead();
		TotalWrite();
		CpuBusy();
		Connections();
		process();
	}

}
