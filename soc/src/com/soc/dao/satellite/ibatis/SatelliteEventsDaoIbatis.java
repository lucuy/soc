package com.soc.dao.satellite.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.satellite.SatelliteEventsDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.satellite.Events;

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
public class SatelliteEventsDaoIbatis extends BaseDaoIbatis implements SatelliteEventsDao {

	@Override
	public int count(Map<String, Object> map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"satelliteEvents.count", map);
	}

	@Override
	public List<Events> queryEvents(
			Map<String, Object> map, int startIndex, int pageSize) {
		return this.getSqlMapClientTemplate()
				.queryForList("satelliteEvents.queryEvents", map,
						startIndex, pageSize);
	}


	@Override
	public Events selectById(Map map) {
		// TODO Auto-generated method stub
		return null;
	}




	
	}
