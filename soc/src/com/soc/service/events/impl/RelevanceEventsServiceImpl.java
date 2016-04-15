package com.soc.service.events.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.events.RelevanceEventsDao;
import com.soc.model.events.Events;
import com.soc.service.events.RelevanceEventsService;
import com.soc.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 关联后事件的service实现
 * @author gaosong
 *
 */
public class RelevanceEventsServiceImpl extends BaseServiceImpl implements RelevanceEventsService
{
private RelevanceEventsDao relevanceEventsDao;
	@Override
	public SearchResult query(Map<String, Object> map, Page page) {
		int rowsCount = relevanceEventsDao.count(map);
		page.setTotalCount(rowsCount);
		List<Events> list = relevanceEventsDao.queryRelevanceEvents(map,page.getStartIndex(), page.getPageSize());
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
	
	@Override
	public SearchResult queryAtnalyticEvents(Map map, Page page) {
		//根据id找到关联后事件
	Events	events = relevanceEventsDao.selectById(map);
	String tableName = "tbl_"+events.getCustoms1();
	String identifications = events.getCustoms7();//根据他找产生他的解析后事件
	map.put("tableName", tableName);
	map.put("identification", identifications+"0");
	//查询解析后事件的count
	int rowsCount = relevanceEventsDao.countAtnalyticEvents(map);
	page.setTotalCount(rowsCount);
	List<Events> list = relevanceEventsDao.queryAtnalyticEvents(map,page.getStartIndex(), page.getPageSize());
	// 对list的处理
/*	String eventName = "";
	for (Events events1 : list) {
		String eventNameTemp = events1.getName();
		try {
			eventName = GlobalConfig.eventTypeTag.get(Long
					.parseLong(eventNameTemp));
			if (eventName.equals(null)) {
			} else {
				events1.setName(eventName);
			}
		} catch (Exception e) {
			//log.info("转化失败");
		}
		String eventstype = events1.getType();
		try {
			events1.setCustomd1(Integer.parseInt(events1.getType()));
		} catch (Exception e) {
			//log.info("事件类型转化失败 原因:事件类型原本是汉字无需转化");

		}

		try {
			String typeTemp = GlobalConfig.eventTypeTag.get(Long
					.parseLong(eventstype));

			if ((typeTemp != null) && (!typeTemp.equals(""))) {
				events1.setType(typeTemp);
			}

		} catch (Exception e) {
			//log.info("类型转化失败");
		}

	}*/
	SearchResult sr = new SearchResult();
	sr.setList(list);

	sr.setPage(page);

	return sr;
///		return null;
	}

	public RelevanceEventsDao getRelevanceEventsDao() {
		return relevanceEventsDao;
	}
	public void setRelevanceEventsDao(RelevanceEventsDao relevanceEventsDao) {
		this.relevanceEventsDao = relevanceEventsDao;
	}


}