package com.soc.service.monitor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.monitor.MonitorGroup;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <监控组的管理>
 * <监控组的添加，删除>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-10-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface MonitorGroupService extends Serializable
{
    
    /**
     * <根据父节点id查询>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<MonitorGroup> queryByParentId(Map map);
    
    /**
     * <根据ID查询某个组>
     * <功能详细描述>
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    public MonitorGroup queryById(long id);
    
    /**
     * <更新关联的图表>
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
    public SearchResult queryAll(Map map, Page page);
    
    /**
     * <查询监控组的组的数量>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
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
    
    /**
     * <创建监控组的树>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String createTree(String objectpath);
   
}
