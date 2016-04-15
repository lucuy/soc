package com.soc.dao.eventtypetag;

import java.util.List;

import com.soc.model.eventtypetag.EventTypeTag;

public interface EventTypeTagDao {
	
	public List<EventTypeTag> query();
	
	/**
	 * <根据传递的keys查询对应的事件类型类表>
	 * <keys为数组>
	 * @param keys
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<EventTypeTag> queryByKeys(String keys);
	
	
	public int eventTypeIdByName(String name) ; 
}