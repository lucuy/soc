package com.compliance.service.rank.impl;

import java.util.List;

import com.compliance.dao.rank.RecordDocListDao;
import com.compliance.model.rank.RecordDocList;
import com.compliance.service.rank.RecordDocListService;
/**
 * 备案集业务实现类
 * @author quyongkun
 *
 */
public class RecordDocListServiceImpl implements RecordDocListService{
	
	/**
	 * 备案集dao接口
	 */
	private RecordDocListDao recordDocListDao;
	

	public RecordDocListDao getRecordDocListDao() {
		return recordDocListDao;
	}

	public void setRecordDocListDao(RecordDocListDao recordDocListDao) {
		this.recordDocListDao = recordDocListDao;
	}
	
	/**
	 * 根据备案集编号查询
	 * @param recordDocListId 备案集编号
	 * @return 备案集信息
	 */
	public RecordDocList queryById(int recordDocListId) {
		return this.recordDocListDao.queryById(recordDocListId);
	}

	/**
	 * 根据备案编号查询备案集
	 * @param recordId 备案编号
	 * @return 备案集合
	 */
	public List<RecordDocList> queryByRecordId(int recordId) {
		return this.recordDocListDao.queryByRecordId(recordId);
	}
	
	/**
	 * 插入备案记录
	 * @param recordDocList 备案记录
	 * @return 插入是否成功
	 */
	public boolean insert(RecordDocList recordDocList) {
		return  this.recordDocListDao.insert(recordDocList);
	}

	/**
	 * 修改备案记录
	 * @param recordDocList 备案信息
	 * @return 修改是否成功
	 */
	public boolean update(RecordDocList recordDocList) {
		return this.recordDocListDao.update(recordDocList);
	}

}
