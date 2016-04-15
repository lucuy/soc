package com.compliance.dao.rank.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.rank.RecordHistoryDao;
import com.compliance.model.rank.Record;
import com.compliance.model.rank.RecordHistory;
import com.soc.model.conf.GlobalConfig;
/**
 * 备案历史dao实现类
 * @author quyongkun
 *
 */
public class RecordHistoryDaoImpl extends BaseDaoiBatis implements RecordHistoryDao {

	/**
	 * 查询备案历史条数
	 * @param mapper map集合
	 * @return 备案历史条数
	 */
	public int querycount(Map mapper) {
		// TODO Auto-generated method stub
		int recordNum = 0 ;
		Object recordObj = null;
		recordObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"recordHistory.count", mapper);
		if(recordObj !=null)
		{
			recordNum = ((Integer)recordObj).intValue();
			
		}
		return recordNum;
	}

	/**
	 * 查询备案历史信息
	 * @param map map集合
	 * @param startRow 起始行号
	 * @param pageSize 页面大小
	 * @return 备案历史信息集合
	 */
	public List<RecordHistory> query(Map map, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"recordHistory.query", map, startRow, pageSize);
	}
    /**
     * 根据备案历史编号查询
     */
	public RecordHistory queryById(int recordHistoryId) {
		return (RecordHistory)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"recordHistory.queryById", recordHistoryId);
	}

	/**
	 * 根据系统编号查询
	 */
	public List<RecordHistory> queryBySysId(String sysInFoId) {
		return this.getSqlMapClientTemplate().queryForList("", sysInFoId);
	}
	 
	 
	 /**
	  * 修改备案历史
	  * @param record 备案历史实体
	  * @return 修改是否成功
	  */
	public boolean update(RecordHistory recordHistory) {
		return this.getSqlMapClientTemplate().update("", recordHistory)>0;
	}
	
	 /**
	  * 添加备案历史
	  * @param recordHistory 备案历史实体
	  * @return 添加是否成功
	  */
	public boolean insert(RecordHistory recordHistory) {
		 this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"recordHistory.insert", recordHistory);
		 return true;
	}

	public int precisequerycount(Map mapper) {
		// TODO Auto-generated method stub
		int recordNum = 0 ;
		Object recordObj = null;
		recordObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"recordHistory.precisecount", mapper);
		if(recordObj !=null)
		{
			recordNum = ((Integer)recordObj).intValue();
			
		}
		return recordNum;
	}

	public List<RecordHistory> precisequery(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"recordHistory.precisequery", map, startRow, pageSize);
	}
	

}
