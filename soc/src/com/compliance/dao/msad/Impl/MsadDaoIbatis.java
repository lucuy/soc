package com.compliance.dao.msad.Impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.msad.MsadDao;
import com.compliance.model.msad.Msad;
import com.soc.model.conf.GlobalConfig;

public class MsadDaoIbatis extends BaseDaoiBatis implements MsadDao{

	public List<Msad> queryMsadByFatherSort(String msadFatherSort) {
		return (List<Msad>)super.queryForList(GlobalConfig.sqlId+"msad.queryMsadByFatherSort",msadFatherSort);
	}

	/**
	 * 根据排序查询
	 */
	@SuppressWarnings("unchecked")
	public List<Msad> queryByMsadSort(Map map) {
		return (List<Msad>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"msad.queryByMsadSort",map);
	}

	public int queryByNextSort(Map map) {
		return (Integer)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"msad.queryByNextSort",map);
	}

	public String queryMsadByMsadid(String msadid) {
		// TODO Auto-generated method stub
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"msaShow.queryMsadName",msadid);
	}


}
