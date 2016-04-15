package com.soc.dao.monitor;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;

import com.soc.model.monitor.MonitorGroup;

/**
 * 
 * <监控组Dao>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-10-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MonitorGroupDao extends BaseDao
{
    
    /**
     * <根据父节点ID查询list>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<MonitorGroup> queryByParentId(Map map);
    
    /**
     * <根据id查询组>
     * <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public MonitorGroup queryById(long id);
    
    /**
     * <更新监控组关联的监控表>
     * <功能详细描述>
     * @param map
     * @see [类、类#方法、类#成员]
     */
    public void updateRelMonitors(Map map);
    
    /**
     * <查询所有的监控组>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<MonitorGroup> queryall(Map map, int startRow, int pageSize);
    
    /**
     * <查询所有监控组的数量>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int countGroups(Map map);
    
    /**
     * <插入监控组>
     * <功能详细描述>
     * @param monitorGroup
     * @see [类、类#方法、类#成员]
     */
    public void insertGroup(MonitorGroup monitorGroup);
    
    /**
     * <删除监控组>
     * <功能详细描述>
     * @param id
     * @see [类、类#方法、类#成员]
     */
    public void deleteGroup(Long id);
}
