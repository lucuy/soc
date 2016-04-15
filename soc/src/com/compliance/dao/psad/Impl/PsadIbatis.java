package com.compliance.dao.psad.Impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.psad.PsadDao;
import com.compliance.model.psad.Psad;
import com.soc.model.conf.GlobalConfig;

/**
 * 通用物理管理
 * 
 * @author 胡亚丹
 *
 */
public class PsadIbatis extends BaseDaoiBatis implements PsadDao{

	public List<Psad> queryPsadByFatherSort(String psadFatherSort) {
		return (List<Psad>)super.queryForList(GlobalConfig.sqlId+"psad.queryPsadByFatherSort",psadFatherSort);
	}
/**
 * 根据排序查询
 */
	public List<Psad> queryByPsadSortInfo(Map map) {
		return (List<Psad>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"psad.queryByPsadSortInfo",map);
	}
/***
 * 根据排序查询是否还有下一级
 */
	public int queryNextPsadSortInfo(Map map) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"psad.queryNextPsadSortInfo",map);
	}
	public String queryPsadNameByid(String psadid) {
		// TODO Auto-generated method stub
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"gpaShow.querypasdName",psadid);
	}

}
