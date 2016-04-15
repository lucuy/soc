package com.soc.service.satellite.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.satellite.SatelliteEventsDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.satellite.Events;
import com.soc.service.satellite.SatelliteEventsService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class SatelliteEventsServiceImpl implements SatelliteEventsService {
	private SatelliteEventsDao satelliteEventsDao;
	public SearchResult query(Map<String, Object> map, Page page) {
		int rowsCount = satelliteEventsDao.count(map);
		page.setTotalCount(rowsCount);
		List<Events> list = satelliteEventsDao.queryEvents(map,page.getStartIndex(), page.getPageSize());
		//对list的处理
		
		/*for (Events events : list) {
			events.setType(GlobalConfig.eventTypeTag.get(Long.parseLong(events.getType())));
			events.setCateGory(GlobalConfig.eventCategoryTag.get(events.getCateGory()));
		}*/
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);

		sr.setPage(page);

		return sr;
	}
	public SatelliteEventsDao getSatelliteEventsDao() {
		return satelliteEventsDao;
	}
	public void setSatelliteEventsDao(SatelliteEventsDao satelliteEventsDao) {
		this.satelliteEventsDao = satelliteEventsDao;
	}
	
}
