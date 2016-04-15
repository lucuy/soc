package com.soc.dao.audit.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.audit.AuditDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.audit.Audit;
import com.soc.model.conf.GlobalConfig;

/**
 * 
 * Description: 审计Dao <对审计的相关操作：查询用户>
 * 
 * @author shenhaiguang
 * @version
 * @Created at 2012-08-11
 * @since
 */
public class AuditDaoIbatis extends BaseDaoIbatis implements AuditDao
{
    
    /**
     * {@inheritDoc}
     */
    public List<Audit> queryAudit(Map map, int startRow, int pageSize)
    {
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"audit.query", map, startRow, pageSize);
    }
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的系统审计的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"audit.count", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        // 总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<Map> auditExport(Map map)
    {
        
        return super.queryForList(GlobalConfig.sqlId+"audit.export", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public  int insert(Audit audit)
    {
        Integer pk = 0;
        Object obj = super.create(GlobalConfig.sqlId+"audit.insert", audit);
        if (obj != null)
        {
            pk = Integer.parseInt(obj.toString());
        }
        return pk;
    }
    
    /**
     * {@inheritDoc}
     */ 
    public List<Audit> queryAuditBySort(String str,int startRow,int pageSize){
    	return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"audit.asc",str, startRow, pageSize);
    }
   
}
