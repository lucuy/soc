package com.soc.dao.events;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.events.Events;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;

/**
 * 关联后事件的dao
 *  * @author gaosong
 *
 */
public interface RelevanceEventsDao extends BaseDao
{

	int count(Map<String, Object> map);

	List<Events> queryRelevanceEvents(Map<String, Object> map,
			int startIndex, int pageSize);

	Events selectById(Map map);

	List<Events> queryAtnalyticEvents(Map map, int startIndex, int pageSize);

	int countAtnalyticEvents(Map map);
   
}
