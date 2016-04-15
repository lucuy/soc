package com.compliance.dao.cpManage.contrastRectification.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.contrastRectification.ContrastDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.rectificationProposal.RectificationProposal;
import com.soc.model.conf.GlobalConfig;

public class ContrastDaoIbatis extends BaseDaoiBatis implements ContrastDao{

	/**
	 * 得到历史整改建议系统列表
	 */
	@SuppressWarnings("unchecked")
	public List<RectificationProposal> querySysList() {
		return  this.queryForList(GlobalConfig.sqlId+"contrast.querySysList");
	}
/**
 * 统计列表个数
 */
	public int count(Map map) {
		 
		Object ob = null;
		try {
			ob = super.queryForObject(GlobalConfig.sqlId+"contrast.count", map);
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
 * 控制单元列表
 */

	@SuppressWarnings("unchecked")
	public List<RectificationProposal> queryUnitList(Map<String, Object> map,
			int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"contrast.queryUnitList", map,startRow,pageSize);
	}
	/**
	 * 控制项列表
	 */

	 
	@SuppressWarnings("unchecked")
	public List <RectificationProposal>  queryItemList(Map map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"contrast.queryItemListBySysIdAndUnitNum", map);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<RectificationProposal> queryUnitList(Map<String, Object> map) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"contrast.queryUnitList", map);
	}
}
