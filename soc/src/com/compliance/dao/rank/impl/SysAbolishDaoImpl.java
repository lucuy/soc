package com.compliance.dao.rank.impl;


import java.util.List;

import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.dao.rank.SysAbolishDao;
import com.compliance.model.rank.SysAbolish;
import com.soc.model.conf.GlobalConfig;



public class SysAbolishDaoImpl extends BaseDaoiBatis implements SysAbolishDao {

	/**
	 * 
	 * 插入系统废止信息
	 * @param sysAbolish 系统废止信息
	 */
	public void insert(SysAbolish sysAbolish) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"sysAbolish.insert", sysAbolish);
	}

	/**
	 * 
	 * 更新系统废止信息
	 * @param sysAbolish
	 */
	public void update(SysAbolish sysAbolish) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"sysAbolish.update",sysAbolish);
	}

	/**
	 * 
	 * 根据信息系统主键查询系统废止信息
	 * @param pkSysInfo 信息系统主键
	 * @return 系统废止信息
	 */
	public SysAbolish query(int pkSysInfo) {
		List<SysAbolish> sysAbolishs= this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"sysAbolish.queryBySysInfo",pkSysInfo);
		if(sysAbolishs.size()==0){
			return null;
		}else{
			return sysAbolishs.get(0);
		}
		 
	}

}
