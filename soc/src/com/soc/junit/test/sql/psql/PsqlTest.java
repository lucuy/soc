package com.soc.junit.test.sql.psql;

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

public class PsqlTest {
	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://198.9.9.7:5432/soc";// 默认的数据库
	private static final String USERNAME = "soc";
	private static final String PWD = "soc";
	static JDBCUtils jdbc = JDBCUtils.getInitialization(DRIVER, URL, USERNAME,
			PWD);
	static Connection conn = null;
	static {
		long time = System.currentTimeMillis();
		JdbcConnectionWrapper warp = jdbc.getConnection();
		conn = warp.getConn();
		long end = System.currentTimeMillis();
		System.out.println("连接时间：" + (end - time) + "ms");
	}

	// 版本
	public static void version() {
		String sql = "select version();";
		System.out.println(querySql(sql));
	}

	// 数据库
	public static List<PGdb> db() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "select d.\"datname\",u.\"usename\",d.\"datcollate\",d.\"datctype\"  from pg_database d ,pg_user u where d.\"datdba\" = u.\"usesysid\";";
		List<PGdb> list = new ArrayList<PGdb>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				PGdb db = new PGdb();
				System.out.println(rs.getString(1) + "\t" + rs.getString(2)
						+ "\t" + rs.getString(3) + "\t" + rs.getString(4));
				db.setDatname(rs.getString(1));
				db.setDatdba(rs.getString(2));
				db.setDatcollate(rs.getString(3));
				db.setDatctype(rs.getString(4));
				list.add(db);
			}
		} catch (Exception e) {
		} finally {
			jdbc.close(rs, ps);

		}

		return list;
	}

	// 数据库大小M
	public static void dbSize() {
		String sql = "select pg_database.datname, pg_database_size(pg_database.datname)/1024/1024 AS \"size(M)\" from pg_database;";
		SET(sql);
	}

	// 查看schema 里所有的索引大小，按从大到小的顺序排列
	public static void indexSize() {
		String sql = "select indexrelname, pg_size_pretty(pg_relation_size(relid)) from pg_stat_user_indexes where schemaname='soc' order by pg_relation_size(relid) desc;";
		SET(sql);
	}
	//数据存放路径
	public static void dataFilePath(){
		String sql = "select * from pg_settings where \"name\" = 'data_directory';";
		SET(sql);
	}
	// 对map进行迭代输出
	public static void SET(String sql) {
		Map<String, String> map = query(sql);
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		while (it.hasNext()) {
			Entry<String, String> entry = it.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
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
				// System.out.println(rs.getObject(1).toString().trim() + "---"
				// + rs.getObject(2));
				map.put(rs.getObject(1).toString().trim(), rs.getObject(2)
						.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbc.close(rs, ps);
		}

		return map;
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

	public static void main(String[] args) {
		version();
		db();
		dbSize();
		//indexSize();
		dataFilePath();
	}
}
