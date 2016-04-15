package com.compliance.dao.rank.impl;

import java.util.List;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.rank.RecordDocListDao;
import com.compliance.model.rank.RecordDocList;
import com.soc.model.conf.GlobalConfig;
/**
 * 备案集dao实现类
 * @author quyongkun
 *
 */
public class RecordDocListDaoImpl extends BaseDaoiBatis  implements RecordDocListDao {
	
	/**
	 * 根据备案集编号查询
	 * @param recordDocListId 备案集编号
	 * @return 备案集信息
	 */
	public RecordDocList queryById(int recordDocListId) {
		return (RecordDocList)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"recordDocList.queryById", recordDocListId);
	}

	/**
	 * 根据备案编号查询备案集
	 * @param recordId 备案编号
	 * @return 备案集合
	 */
	public List<RecordDocList> queryByRecordId(int recordId) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"recordDocList.queryByRecordId", recordId);
	}
	
	/**
	 * 插入备案记录
	 * @param recordDocList 备案记录
	 * @return 插入是否成功
	 */
	public boolean insert(RecordDocList recordDocList) {
		Object obj=this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"recordDocList.insert", recordDocList);
		if(obj==null){
			return  true;	
		}else{
			return  false;
		}
		
	}

	/**
	 * 修改备案记录
	 * @param recordDocList 备案信息
	 * @return 修改是否成功
	 */
	public boolean update(RecordDocList recordDocList) {
		return this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"recordDocList.update", recordDocList)>0;
	}

}
