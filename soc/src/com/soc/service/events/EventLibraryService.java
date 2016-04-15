package com.soc.service.events;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.model.events.EventLibrary;
import com.soc.model.events.Events;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 事件库业务接口类
 * 将查询信息转换成目录树返回到web端
 * @author 何贝贝
 *
 */
public interface EventLibraryService  extends Serializable
{
	/**
     * <计算符合条件的事件库记录数>
     * <功能详细描述>
     * @param map Map
     * @return int
     */
    public int count(Map map);
    
    /**
     * 查询事件库对象
     * @return
     */
    public SearchResult queryEventLibrary(Map map, Page page);
    /**
     * 根据Id查询事件库对象
     * @param map
     * @return
     */
    public EventLibrary queryEventLibraryById(Map map);
    
    
	/**
     * 插入事件库
     * @param eventLibrary
     * @return
     */
    public int addEventLibrary(EventLibrary eventLibrary);
    
    
    
    /**
     * 更新事件库
     * @param eventLibrary
     * @return
     */
    public int modifyEventLibrary(EventLibrary eventLibrary);
    /**
     * 根节点ids删除 节点
     * @param ids
     * @return
     */
    public int deleteEventLibraryById(String...ids);
}
