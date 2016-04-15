package com.compliance.dao.cpManage.assessResult.ibatis;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.assessResult.AssessResultDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.assessResult.AssessResult;
import com.soc.model.conf.GlobalConfig;

public class AssessResultDaoIbatis extends BaseDaoiBatis implements AssessResultDao{
	/**
	 * 计算有多少条记录
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("rawtypes")
	public int count(Map map) {
		Object ob = null;
		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"assessResult.count", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int totalRows = 0;
		if (ob != null) {
			totalRows = ((Integer) ob).intValue();
		}
		return totalRows;
	}
	/**
	 * 查询数据
	 * @param Map, int, int
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> assessResults(Map<String, Object> map,
			int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assessResult.query", map,startRow,pageSize);
	}
	
	/**
	 * 查询评估项存在数量
	 * @param Map
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> queryAssessCount(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assessResult.queryAssessCount", map);
	}
	
	/**
	 * 模糊查询差距分布表
	 * @param Map
	 * @return List<AssessResult>
	 */
	@SuppressWarnings("unchecked")
	public List<AssessResult> queryAssessCountTable(Map map){
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"assessResult.queryAssessCountTable", map);

	}
	
	/**
	 * 查询评估项已评估数量
	 * @param String
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int queryAssessOverCount(String acId){
		return (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"assessResult.queryAssessOverCount", Integer.parseInt(acId));
	}
	
	/**
	 * 添加数据
	 * @param AssessResult
	 * @return void
	 */
	public void insert(AssessResult a) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"assessResult.insert", a); 
	}
	

	/**
	 * 修改数据
	 * @param AssessResult
	 * @return void
	 */
	public void update(AssessResult a) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"assessResult.update", a); 
	}
	
}
