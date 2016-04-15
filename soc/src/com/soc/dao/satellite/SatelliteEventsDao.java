package com.soc.dao.satellite;

import java.util.List;
import java.util.Map;

import com.soc.dao.BaseDao;
import com.soc.model.satellite.Events;


/**
 * 直播星事件的dao
 *  * @author gaosong
 *
 */
public interface SatelliteEventsDao extends BaseDao
{

	int count(Map<String, Object> map);

	List<Events> queryEvents(Map<String, Object> map,
			int startIndex, int pageSize);

	Events selectById(Map map);

}
