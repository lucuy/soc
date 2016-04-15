package com.soc.service.eventtypetag;

import java.util.List;


import com.soc.model.eventtypetag.eventcategorytag;

/**
 * 
 * <事件类别的对应类>
 * <功能详细描述>
 * 
 * @author  admin
 * @version  [版本号, 2013-6-26]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface EventCategoryTagService {
	
    /**
     * <查询所有的事件类别的list>
     * <功能详细描述>
     * @return
     * @see [类、类#方法、类#成员]
     */
	public List<eventcategorytag> query();
	
	/**
	 * <根据传递的key数组查询对应的事件类别list>
	 * <功能详细描述>
	 * @param keys
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<eventcategorytag> queryByKeys(String keys);
	
	/**
	 * <根据传递过来的字符串，查询到事件类别的KEY>
	 * @prama String
	 * @return int
	 */
	public String queryKeyBYCategoryName(String name) ; 
}
