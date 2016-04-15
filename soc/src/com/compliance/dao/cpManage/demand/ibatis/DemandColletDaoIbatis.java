package com.compliance.dao.cpManage.demand.ibatis;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.demand.DemandColletDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.demand.DemandCollet;
import com.soc.model.conf.GlobalConfig;

public class DemandColletDaoIbatis extends BaseDaoiBatis implements DemandColletDao{

	public List<DemandCollet> query(Map<String, Object> map, int startRow, int pageSize) {
		 
		return null;
	}

	public String queryUnitDomainNameByNum(String unitDomainName) {
		return  (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"demandColletSql.queryDemainName", unitDomainName);
	}

	public String queryUnitNameByNum(String unitName) {
		
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"demandColletSql.queryDemainName", unitName);
	}

	public String queryConByUnitName(String unitNameNum) {
		
		return (String) super.queryForObject(GlobalConfig.sqlId+"demandColletSql.queryCriteria", unitNameNum);
	}
	
	/**
	 * 根据评估项排序得到评估项是树形结构
	 * @param map
	 * @return List<DemandCollet>
	 */
	@SuppressWarnings("unchecked")
	public List<DemandCollet> queryBySortForTree(Map map) {
		return (List<DemandCollet>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"demandColletSql.queryForTree",map);
	}
	
	/**
	 * 根据排序查找该对象
	 * @param String
	 * @return DemandCollet
	 */
	@SuppressWarnings("unchecked")
	public DemandCollet querydemandColletBySort(String sort){
		return (DemandCollet)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"demandColletSql.querydemandColletBySort",sort);
	}
	
	/**
	 * 根据排序查询评估项是否还有下一级
	 * @param map
	 * @return int
	 */
	@SuppressWarnings("unchecked")
	public int queryNextSortInfo(Map map) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"demandColletSql.queryNextSortInfo",map);
	}
	
	/**
	 * 根据排序查询评估项是否还有下一级
	 * @param map
	 * @return List<DemandCollet>
	 */
	@SuppressWarnings("unchecked")
	public List<DemandCollet> queryNextSort(Map map) {
		return (List<DemandCollet>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"demandColletSql.queryNextSort",map);
	}
	
	/**
	 * 根据排序查询评估项
	 * @param map
	 * @return List<DemandCollet>
	 */
	@SuppressWarnings("unchecked")
	public List<DemandCollet> querySortInfo(Map map) {
		return (List<DemandCollet>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"demandColletSql.querySortInfo",map);
	}

	/**
	 * 查询还有哪些项未评估
	 * @param map
	 * @return List<DemandCollet>
	 */
	@SuppressWarnings({"rawtypes", "unchecked"})
	public List<DemandCollet> queryAssessInfo(Map map){
		return (List<DemandCollet>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"demandColletSql.queryAssessInfo",map);
		
	}
	
	/**
	 * 差距分布图查询
	 * @param map
	 * @return List<DemandCollet>
	 */
	public List<DemandCollet> queryAssessInfoImage(Map map){
		return (List<DemandCollet>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"demandColletSql.queryAssessInfoImage",map);

	}
}
