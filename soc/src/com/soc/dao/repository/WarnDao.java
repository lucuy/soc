package com.soc.dao.repository;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.user.User;
import com.soc.model.warn.Warn;

/**
 * 
 * <预警发布DAO 对预警进行 查询，增加，删除，修改>
 * <功能详细描述>
 * 
 * @author  liruifeng
 * @version  [版本号, 2013-1-22]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public interface WarnDao extends BaseDao
{
    
    /**
     * <查询预警总条数>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Warn> query(Map map, int startRow, int pageSize);
    
    /**
     * <查询所有的预警>
     * <功能详细描述>
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public List<Warn> findAllWarn();
    
    /**
     * <新增预警>
     * <功能详细描述>
     * @param warn
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public int insertWarn(Warn warn);
    
    /**
     * <根据warnId详细查询预警>
     * <功能详细描述>
     * @param warn
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public Warn queryById(Warn warn);
    
    /**
     * <批量删除预警>
     * <功能详细描述>
     * @param result
     * @return
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public int deleteWarn(String result);
    
    /**
     * <修改预警信息>
     * <功能详细描述>
     * @param warn
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public void updateWarn(Warn warn);
    
    /**
     * <排序查询出来的信息>
     * @param str
     * @param startRow
     * @param pageSize
     * @throws SQLException
     * @see [类、类#方法、类#成员]
     */
    public List<Warn> sort(String str,int startRow,int pageSize) ; 

}
