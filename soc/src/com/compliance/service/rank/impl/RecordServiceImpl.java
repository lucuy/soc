package com.compliance.service.rank.impl;


import java.util.List;
import java.util.Map;

import com.compliance.dao.rank.RecordDao;
import com.compliance.model.rank.Record;
import com.compliance.service.impl.BaseServiceImpl;
import com.compliance.service.rank.RecordService;
import com.util.page.Page;
import com.util.page.SearchResult;
/**
 * 备案业务实现类
 * @author quyongkun
 *
 */
public class RecordServiceImpl extends BaseServiceImpl implements RecordService {
	
	/**
	 * 备案dao接口
	 */
	private RecordDao recordDao;
	
	
	public RecordDao getRecordDao() {
		return recordDao;
	}

	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	/**
	 * 查询备案条数
	 */
	public int querycount(Map mapper) {
		return recordDao.querycount(mapper);
	}

	/**
	 * 查询备案信息
	 */
	public SearchResult query(Map map, Page page) {
		// TODO Auto-generated method stub
		int rowCount = recordDao.querycount(map);
		page.setTotalCount(rowCount);
		List<Record>list =recordDao.query(map, page.getStartIndex(),page.getPageSize());
		//处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return  sr;
	}
	

	/**
	 *根据系统编号查询
	 */
	public List<Record> queryBySysId(String sysInFoId) {
		return recordDao.queryBySysId(sysInFoId);
	}

	/**
	 * 根据备案编号查询
	 */
	public Record queryById(int recordId) {
		return recordDao.queryById(recordId);
	}
	
	 /**
	  * 修改备案
	  * @param record 备案实体
	  * @return 修改是否成功
	  */
	public boolean update(Record record) {
		return recordDao.update(record);
	}

	 /**
	  * 添加备案
	  * @param record 备案实体
	  * @return 添加是否成功
	  */
	public boolean insert(Record record) {
		 return recordDao.insert(record);
	}

	public int precisequerycount(Map mapper) {
		// TODO Auto-generated method stub
		return recordDao.precisequerycount(mapper);
	}

	public SearchResult precisequery(Map map, Page page) {
		// TODO Auto-generated method stub
		int rowCount = recordDao.precisequerycount(map);
		page.setTotalCount(rowCount);
		List<Record>list =recordDao.precisequery(map, page.getStartIndex(),page.getPageSize());
		//处理分页
		SearchResult sr = new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return  sr;
	}
	
	

}
