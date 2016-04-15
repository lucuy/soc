package com.soc.dao.ibatis;


public class MyDialect{

	private static final String SQL_END_DELIMITER = ";";
	
	
	public boolean supportsLimit() {
		// TODO Auto-generated method stub
		return true;
	}

	
	public static String getLimitString(String dbName, String sql, int offset,
			int limit) {
		// TODO Auto-generated method stub
		String limitString = sql;
		//System.out.println("sql语句为:"+limitString);
		if (dbName.toLowerCase().indexOf("mysql") != -1) {
			limitString = getMysqlLimitString(sql, offset, limit);
		}
		if (dbName.toLowerCase().indexOf("microsoft sql server") != -1) {
			limitString = getMssqlLimitString(sql, limit, offset);
		}
		if (dbName.toLowerCase().indexOf("oracle") != -1) {
			limitString = getOracleLimitString(sql, limit, offset);
		}
		if (dbName.toLowerCase().indexOf("db2") != -1) {
			limitString = getDB2LimitString(sql, limit, offset);
		}
		if(dbName.toLowerCase().indexOf("postgresql") != -1)
		{
			limitString = getMysqlLimitString(sql, offset, limit);
		}
		return limitString;
	}
	private static String getMysqlLimitString(String sql, int offset, int limit) {
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 20);
		sb.append(sql);
		sb.append(" limit ");
		sb.append(limit);
		sb.append(" offset ");
		sb.append(offset);
		return sb.toString();
	}

	private static String getOracleLimitString(String sql, int offset, int limit) {
		sql = trim(sql);
		StringBuffer sb = new StringBuffer(sql.length() + 100);
		if (offset > 0) {
			sb.append("select * from ( select row_.*, rownum rownum_ from ( ")
					.append(sql).append(" ) row_ where rownum <= ").append(
							offset + limit).append(") where rownum_ > ")
					.append(offset);
		} else {
			sb.append("select * from ( ").append(sql).append(
					" ) where rownum <= ").append(limit);
		}
		return sb.toString();
	}

	private static String getMssqlLimitString(String sql, int offset, int limit) {
		return sql;
	}
	
	private static String getDB2LimitString(String sql, int offset, int limit) {		
		return null;
	}
	
	private static String trim(String sql) {
		sql = sql.trim();
		if (sql.endsWith(SQL_END_DELIMITER)) {
			sql = sql.substring(0, sql.length() - 1
					- SQL_END_DELIMITER.length());
		}
		return sql;
	}

}
