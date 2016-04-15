package com.soc.dao.events;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.events.EventLibrary;

/**
 * 事件Librarydao接口类
 * @author 何贝贝
 *
 */
public interface EventLibraryDao extends BaseDao
{
	/**
     * 计算符合条件的事件库记录数
     * 
     * @param Map
     * @return 返回符合查询条件的记录数
     */
	public int count(Map map);
	/**
	 * 插入 事件库对象
	 * @param EventLibrary
	 * @return
	 */
    public int insertEventLibrary(EventLibrary eventLibrary);
    /**
     * 按条件分页查询用户
     * 
     * @param Map,int,int
     * @return 返回符合查询条件的事件库List<EventLibrary>
     */
    public List<EventLibrary> query(Map map, int startRow, int pageSize);
    /**
     * 更新事件库对象
     * @param EventLibrary
     * @return
     */
    public int updateEventLibrary(EventLibrary eventLibrary);
    /**
     * 根据Id列表删除多个Id
     * @param eventIds
     * @return
     */
    public int deleteEventLibraryById(String... eventIds);
}
