package com.compliance.service.rank.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.rank.RecordHistoryDao;
import com.compliance.model.rank.Record;
import com.compliance.model.rank.RecordHistory;
import com.compliance.service.rank.RecordHistoryService;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 备案历史业务实现类
 * @author quyongkun
 *
 */
public class RecordHistoryServiceImpl implements RecordHistoryService {
	
	/**
	 * 备案历史dao接口
	 */
	private RecordHistoryDao recordHistoryDao;
	
	public RecordHistoryDao getRecordHistoryDao() {
		return recordHistoryDao;
	}

	public void setRecordHistoryDao(RecordHistoryDao recordHistoryDao) {
		this.recordHistoryDao = recordHistoryDao;
	}

	/**
	 * 查询备案历史条数
	 */
	public int querycount(Map mapper) {
		return recordHistoryDao.querycount(mapper);
	}

	/**
	 * 查询备案历史
	 */
	public SearchResult query(Map map, Page page) {
		// TODO Auto-generated method stub
		int rowCount = recordHistoryDao.querycount(map);
		page.setTotalCount(rowCount);
		List<RecordHistory>list =recordHistoryDao.query(map, page.getStartIndex(),page.getPageSize());
		//处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return  sr;
	}
	
	/**
	 * 
	 * 根据备案历史编号查询
	 * @param recordId 备案历史编号
	 * @return 备案历史信息
	 */
	public RecordHistory queryById(int recordHistoryId) {
		return recordHistoryDao.queryById(recordHistoryId);
	}

	 /**
	  * 添加备案
	  * @param record 备案实体
	  * @return 添加是否成功
	  */
	public boolean insert(RecordHistory recordHistory) {
		return recordHistoryDao.insert(recordHistory);
	}

	public int precisequerycount(Map mapper) {
		// TODO Auto-generated method stub
		return recordHistoryDao.precisequerycount(mapper);
	}

	public SearchResult precisequery(Map map, Page page) {
		// TODO Auto-generated method stub
		int rowCount = recordHistoryDao.precisequerycount(map);
		page.setTotalCount(rowCount);
		List<RecordHistory>list =recordHistoryDao.precisequery(map, page.getStartIndex(),page.getPageSize());
		//处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return  sr;
	}
	

}
