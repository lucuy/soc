package com.soc.dao.events;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;

/**
 * 事件组Dao
 * @author admin
 *
 */
public interface QueryEventsGroupDao extends BaseDao
{
    /**
     * 根据父ID查询事件组对象集合
     * @return
     */
    public List<QueryEventsGroup> queryByParentId(Map<String, Long> map);
    
    /**
     * 根据父ID查询叶子事件
     * @param map
     * @return
     */
    public List<QueryEventsGroup> queryNodeByParentId(Map<String, Long> map);
    
    /**
     * 查询认证
     * @param map
     * @return
     */
    public List<QueryEvents> queryeEventsAttestationList(Map<String, Long> map);
    
    /**
     * 查询配置变更
     * @param map
     * @return
     */
    public List<QueryEvents> queryeEventsConfigList(Map<String, Long> map); 
    
    /**
     * 查询操作
     * @param map
     * @return
     */
    public List<QueryEvents> queryeEventsResourcesList(Map<String, Long> map); 
    
    /**
     * 查询资源
     * @param map
     * @return
     */
    public List<QueryEvents> queryeEventsOperationList(Map<String, Long> map); 
    
    /**
     * 查询服务器事件
     * @param map
     * @return
     */
    public List<QueryEvents> queryEventsServerList(Map<String, Long> map);
    
    /**
     * 查询组节点
     * @param map
     * @return
     */
    public List<QueryEventsGroup>  queryGroupNodeList(Map<String, Long> map);
   
    /**
     * 查询自定义节点
     * @param map
     * @return
     */
    public List<QueryEvents> queryCustomEventsNodeList(Map<String,Long> map);
}
