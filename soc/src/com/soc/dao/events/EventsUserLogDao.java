package com.soc.dao.events;

import java.util.List;

import com.soc.dao.BaseDao;
import com.soc.model.events.EventsUserLog;

public interface EventsUserLogDao extends BaseDao {

	
	/**
	 * 查询所有远程Oracle中的LOG_INFO表中的所有的日志
	 */
	public List<EventsUserLog> queryAllUserLog();
	
	/**
	 * 根据NumID查询之后的log信息
	 */
	public List<EventsUserLog> queryAllUserLogByNumId(long numId);
	
}
