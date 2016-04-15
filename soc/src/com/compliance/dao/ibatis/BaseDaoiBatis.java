package com.compliance.dao.ibatis;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.compliance.dao.BaseDao;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;

public class BaseDaoiBatis extends SqlMapClientDaoSupport implements BaseDao{
	

	protected transient Log log = LogFactory.getLog(getClass());
	private SqlExecutor sqlExecutor;

    public SqlExecutor getSqlExecutor() {
        return sqlExecutor;
    }

    public void setSqlExecutor(SqlExecutor sqlExecutor) {
        this.sqlExecutor = sqlExecutor;
    }

    public void setEnableLimit(boolean enableLimit) {
        if (sqlExecutor instanceof LimitSqlExecutor) {
            ((LimitSqlExecutor) sqlExecutor).setEnableLimit(enableLimit);
        }
    }

    public void initialize() throws Exception {
        if (sqlExecutor != null) {
            SqlMapClient sqlMapClient = getSqlMapClientTemplate().getSqlMapClient();
            if (sqlMapClient instanceof ExtendedSqlMapClient) {
                ReflectUtil.setFieldValue(((ExtendedSqlMapClient) sqlMapClient)
                        .getDelegate(), "sqlExecutor", SqlExecutor.class,
                        sqlExecutor);
            }
        }
    }
    
    public List queryForList(String sqlKey){
    	return getSqlMapClientTemplate().queryForList(sqlKey);
    }

    public List queryForList(String sqlKey, Object obj){
    	return getSqlMapClientTemplate().queryForList(sqlKey, obj);
    }
    
    /**
     * @deprecated
     * @param sqlKey
     * @param param
     * @param startRow
     * @param endRow
     * @return
     */
    public List queryForList(String sqlKey, Object param,int startRow,int length){
    	return getSqlMapClientTemplate().queryForList(sqlKey, param, startRow, length);
    }
    
    
    
	public Object create(String sqlKey, Object obj) {
		return (Object)getSqlMapClientTemplate().insert(sqlKey, obj);
	}
    
    
	public Object createWithoutPopulate(String sqlKey, Object obj) {
		return (Object)getSqlMapClientTemplate().insert(sqlKey, obj);
	}


	public int delete(String sqlKey, Integer pk) {
		return getSqlMapClientTemplate().delete(sqlKey,pk);
	}
		
	public int delete(String sqlKey, Object ids){
		return getSqlMapClientTemplate().delete(sqlKey, ids);
	}
	
	public Object queryForObject(String sqlKey, Object param) {
		return getSqlMapClientTemplate().queryForObject(sqlKey,param);
	}
	
	public Object queryForObject(String sqlKey) {
		return getSqlMapClientTemplate().queryForObject(sqlKey);
	}

	public List search(String sqlKey, Object param) {
		return getSqlMapClientTemplate().queryForList(sqlKey,param);
	}

	public List search(String sqlKey, Object param, int skipResults, int maxResults) {
		return getSqlMapClientTemplate().queryForList(sqlKey,param,skipResults,maxResults);
	}

	public int updateWithoutPopulate(String sqlKey, Object obj) 
	{
		return getSqlMapClientTemplate().update(sqlKey, obj);
	}
	/**
	 * @deprecated
	 * @param sqlKey
	 * @param value
	 */
	public int update(String sqlKey, String value){
		return getSqlMapClientTemplate().update(sqlKey,value);
	}

	public int update(String sqlKey, Object obj) {
		return getSqlMapClientTemplate().update(sqlKey, obj);
	}

}
