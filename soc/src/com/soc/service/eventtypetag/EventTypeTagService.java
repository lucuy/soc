package com.soc.service.eventtypetag;

import java.util.List;

import com.soc.model.eventtypetag.EventTypeTag;

public interface EventTypeTagService {
	
	public List<EventTypeTag> query();
	
	/**
	 * <根据传递的事件类型的keys查询对应的事件类型列表>
	 * <功能详细描述>
	 * @param keys
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<EventTypeTag> queryByKeys(String keys);
	
	/**
	 * <根据传递过来的Name查找ID>
	 * @param name
	 * @return String
	 * 
	 */
	public int eventTypeIdByName(String name)  ; 
	
}