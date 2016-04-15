package com.soc.service.events;

import java.util.List;

import com.soc.model.events.EventsUserLog;

public interface EventsUserLogService {

	/**
	 * 根据NumID查询NumID之后的log信息
	 */
	public List<EventsUserLog> queryAllUserLogByNumId(long numId);
	
}
