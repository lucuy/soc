package com.compliance.dao.rank.impl;

import java.util.List;
import java.util.Map;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.rank.RecordDao;
import com.compliance.model.rank.Record;
import com.soc.model.conf.GlobalConfig;

/**
 * 备案dao实现类
 * @author quyongkun
 *
 */
public class RecordDaoImpl extends BaseDaoiBatis implements RecordDao {

	/**
	 * 查询备案条数
	 * @param mapper map集合
	 * @return 备案条数
	 */
	public int querycount(Map mapper) {
		// TODO Auto-generated method stub
		int recordNum = 0 ;
		Object recordObj = null;
		recordObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"record.count", mapper);
		if(recordObj !=null)
		{
			recordNum = ((Integer)recordObj).intValue();
			
		}
		return recordNum;
	}

	/**
	 * 查询备案信息
	 * @param map map集合
	 * @param startRow 起始行号
	 * @param pageSize 页面大小
	 * @return 备案信息集合
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public List<Record> query(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"record.query", map, startRow, pageSize);
	}

	/**
	 * 根据id查询
	 */
	public Record queryById(int recordId) {
		return (Record) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"record.queryById", recordId);
	}

	
	/**
	 * 根据系统编号查询
	 */
	public List<Record> queryBySysId(String sysInFoId) {
		return (List<Record>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"record.queryBySysId",sysInFoId);
	}

	 /**
	  * 修改备案
	  * @param record 备案实体
	  * @return 修改是否成功
	  */
	public boolean update(Record record) {
		return this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"record.update", record)>0;
	}

	 /**
	  * 添加备案
	  * @param record 备案实体
	  * @return 添加是否成功
	  */
	public boolean insert(Record record) {
		 this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"record.insert", record);
		 return true;
	}

	public int precisequerycount(Map mapper) {
		// TODO Auto-generated method stub
		int recordNum = 0 ;
		Object recordObj = null;
		recordObj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"record.precisecount", mapper);
		if(recordObj !=null)
		{
			recordNum = ((Integer)recordObj).intValue();
			
		}
		return recordNum;
	}

	public List<Record> precisequery(Map map, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"record.precisequery", map, startRow, pageSize);
	}
	
	
	
	
	
}
