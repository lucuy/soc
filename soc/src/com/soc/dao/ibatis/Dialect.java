package com.soc.dao.ibatis;

/**
 * Dialect 
 * @author 
 * @version 1.0
 */
public interface Dialect {
    
	 public boolean supportsLimit();
	    
	 public String getLimitString(String dbName,String sql, int offset, int limit);
}
