package com.soc.dao.ibatis;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ibatis.sqlmap.engine.execution.SqlExecutor;
import com.ibatis.sqlmap.engine.mapping.statement.RowHandlerCallback;
/*import com.ibatis.sqlmap.engine.scope.RequestScope;*/
import com.ibatis.sqlmap.engine.scope.StatementScope;

/**
 * @author 郭煜玺
 * @verson	1.0
 */
public class LimitSqlExecutor extends SqlExecutor {

    private static final Log logger = LogFactory.getLog(LimitSqlExecutor.class);
    
    private MyDialect dialect;

    private boolean enableLimit = true;

    public MyDialect getDialect() {
        return dialect;
    }

    public void setDialect(MyDialect dialect) {
        this.dialect = dialect;
    }

    public boolean isEnableLimit() {
        return enableLimit;
    }

    public void setEnableLimit(boolean enableLimit) {
        this.enableLimit = enableLimit;
    }

 /*   @Override*/
   /* public void executeQuery(RequestScope request, Connection conn, String sql,
            Object[] parameters, int skipResults, int maxResults,
            RowHandlerCallback callback) throws SQLException {
        if ((skipResults != NO_SKIPPED_RESULTS || maxResults != NO_MAXIMUM_RESULTS)
                && supportsLimit()) {
            sql = dialect.getLimitString(sql, skipResults, maxResults);
            if(logger.isDebugEnabled()){
                logger.debug(sql);
            }
            skipResults = NO_SKIPPED_RESULTS;
            maxResults = NO_MAXIMUM_RESULTS;            
        }
        super.executeQuery(request, conn, sql, parameters, skipResults,
                maxResults, callback);
    }*/

 /*   public boolean supportsLimit() {
        if (enableLimit && dialect != null) {
            return dialect.supportsLimit();
        }
        return false;
    }*/
    
    public void executeQuery(StatementScope statementScope, Connection conn,
			String sql, Object[] parameters, int skipResults, int maxResults,
			RowHandlerCallback callback) throws SQLException {
    	//取数据库产品名称
    		super.executeQuery(statementScope, conn, sql, parameters, skipResults, maxResults, callback);
    	
    }
    public boolean supportsLimit() {
        if (enableLimit && dialect != null) {
            return dialect.supportsLimit();
        }
        return false;
    }

}