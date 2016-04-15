package com.soc.dao.audit;

import java.util.List;
import java.util.Map;

import com.soc.model.audit.Audit;

/**
 * 
 * Description: 审计Dao <对审计的相关操作：查询用户>
 * 
 * @author shenhaiguang
 * @version
 * @Created at 2012-08-11
 * @since
 */
public interface AuditDao
{
    /**
     * <返回审计记录数>
     * <根据条件返回审计记录数>
     * @param map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <分页查询审计信息>
     * <根据条件查询系统审计信息，分页展示>
     * @param map
     * @param startRow
     * @param pageSize
     * @return List<Audit>
     * @see [类、类#方法、类#成员]
     */
    public List<Audit> queryAudit(Map map, int startRow, int pageSize);
    
    /**
     * <报表导出>
     * <根据条件导出系统审计信息>
     * @param map
     * @return List<Map>
     * @see [类、类#方法、类#成员]
     */
    public List<Map> auditExport(Map map);
    
    /**
     * <添加一条系统审计记录>
     * <添加一条系统审计记录>
     * @param audit
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int insert(Audit audit);
    
    /**
     * <排序升级的结果>
     * <str参数用来前台查询语句的字符串>
     * @param str
     * @param audit
     * @param startRow
     * @param pageSize
     * @return List<Audit>
     * @see [类、类#方法、类#成员]
     */
    public List<Audit> queryAuditBySort(String str,int startRow,int pageSize);
}