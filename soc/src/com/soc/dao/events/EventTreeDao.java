package com.soc.dao.events;

import java.util.List;

import com.soc.dao.BaseDao;
import com.soc.model.events.EventTree;

/**
 * 事件树dao接口类
 * @author 何贝贝
 *
 */
public interface EventTreeDao extends BaseDao
{
    /**
     * 插入事件树
     * @param eventTree
     * @return
     */
    public int insertEventTree(List<EventTree> eventTree);
    
    /**
     * 查询事件树对象
     * @return
     */
    public List<EventTree> selectEventTree();
    
    /**
     * 更新事件树
     * @param eventTree
     * @return
     */
    public int updateEventTree(List<EventTree> eventTree);
    /**
     * 根节点id删除 节点
     * @param id
     * @return
     */
    public int deleteEventTreeById(int...ids);
    /**
     * 根据Id查询节点
     * @param id
     * @return
     */
    public EventTree selectEventTreeById(int id);
}
