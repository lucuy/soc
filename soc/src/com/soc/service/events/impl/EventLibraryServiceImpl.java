package com.soc.service.events.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.events.EventLibraryDao;
import com.soc.model.events.EventLibrary;
import com.soc.service.events.EventLibraryService;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 事件库业务层实现类
 * @author 何贝贝
 */
public class EventLibraryServiceImpl implements EventLibraryService {

	private static final long serialVersionUID = 1L;
	private EventLibraryDao eventLibraryDao;
	
	@Override
	public int count(Map map){
		return eventLibraryDao.count(map);
	}
	@Override
	public int addEventLibrary(EventLibrary eventLibrary) {
		return eventLibraryDao.insertEventLibrary(eventLibrary);
	}

	@Override
	public SearchResult queryEventLibrary(Map map, Page page) {
		int rowsCount = eventLibraryDao.count(map);
        page.setTotalCount(rowsCount);
        List<EventLibrary> list = eventLibraryDao.query(map, page.getStartIndex(), page.getPageSize());
        
        // 对查找的事件库做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        
		return sr;
	}
	
	@Override
	public int modifyEventLibrary(EventLibrary eventLibrary) {
		return eventLibraryDao.updateEventLibrary(eventLibrary);
	}

	@Override
	public int deleteEventLibraryById(String... ids) {
		//System.out.println("service=====================================");
		//System.out.println(ids);
		return eventLibraryDao.deleteEventLibraryById(ids);
	}
	@Override
	public EventLibrary queryEventLibraryById(Map map){
		List<EventLibrary> list=eventLibraryDao.query(map, 0,1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public EventLibraryDao getEventLibraryDao() {
		return eventLibraryDao;
	}
	public void setEventLibraryDao(EventLibraryDao eventLibraryDao) {
		this.eventLibraryDao = eventLibraryDao;
	}
}
