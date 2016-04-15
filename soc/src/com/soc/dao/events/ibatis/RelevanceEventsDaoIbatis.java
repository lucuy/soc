package com.soc.dao.events.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.events.EventLibraryDao;
import com.soc.dao.events.RelevanceEventsDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.EventLibrary;
import com.soc.model.events.Events;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-11-14]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class RelevanceEventsDaoIbatis extends BaseDaoIbatis implements RelevanceEventsDao {

	@Override
	public int count(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+
				"relevanceEvents.count", map);
	}

	@Override
	public List<Events> queryRelevanceEvents(
			Map<String, Object> map, int startIndex, int pageSize) {
		return this.getSqlMapClientTemplate()
				.queryForList(GlobalConfig.sqlId+"relevanceEvents.queryRelevanceEvents", map,
						startIndex, pageSize);
	}

	@Override
	public Events selectById(Map map) {
		return (Events) this.getSqlMapClientTemplate()
				.queryForObject(GlobalConfig.sqlId+"relevanceEvents.queryRelevanceEvents", map);
	}

	@Override
	public List<Events> queryAtnalyticEvents(Map map, int startIndex,
			int pageSize) {
		return this.getSqlMapClientTemplate()
				.queryForList(GlobalConfig.sqlId+"relevanceEvents.queryAtnalyticEvents", map,startIndex, pageSize);
	}

	@Override
	public int countAtnalyticEvents(Map map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"relevanceEvents.countAtnalyticEvents", map);
	}

	
	}
