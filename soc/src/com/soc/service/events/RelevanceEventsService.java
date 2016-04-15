package com.soc.service.events;

import java.util.List;
import java.util.Map;

import com.soc.model.events.Events;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 关联后事件的service
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RelevanceEventsService {
/**
 * <前台的分页查询>
 * <功能详细描述>
 * @param map
 * @param page
 * @return
 * @see [类、类#方法、类#成员]
 */
	SearchResult query(Map<String, Object> map, Page page);
/**
 * 获得解析后事件的方法
 * <功能详细描述>
 * @param map 
 * @param page 
 * @return
 * @see [类、类#方法、类#成员]
 */
SearchResult queryAtnalyticEvents(Map map, Page page);

}
