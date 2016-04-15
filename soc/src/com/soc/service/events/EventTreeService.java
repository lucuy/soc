package com.soc.service.events;

import java.io.Serializable;
import java.util.List;

import com.soc.model.events.EventTree;

/**
 * 事件树业务接口类
 * 将查询信息转换成目录树返回到web端
 * @author 何贝贝
 *
 */
public interface EventTreeService  extends Serializable
{
	/**
     * 插入事件树
     * @param eventTree
     * @return
     */
    public int addEventTree(List<EventTree> eventTree);
    
    /**
     * 查询事件树对象
     * @return
     */
    public List<EventTree> queryEventTree();
    
    /**
     * 更新事件树
     * @param eventTree
     * @return
     */
    public int modifyEventTree(List<EventTree> eventTree);
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
    public EventTree queryEventTreeById(int id);
}
