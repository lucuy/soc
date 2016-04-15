package com.topo.dao.ibatis;

/**
 * Dialect 
 * @author 郭煜玺
 * @version 1.0
 */
public interface Dialect {
    
    public boolean supportsLimit();
    
    public String getLimitString(String dbName,String sql, int offset, int limit);
}
