package com.compliance.dao.basicinfo.unitinfo.ibatis;

import com.compliance.dao.basicinfo.unitinfo.UnitInfoDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.basicinfo.unitinfo.UnitInfo;
import com.soc.model.conf.GlobalConfig;

public class UnitInfoDaoIbatis extends BaseDaoiBatis implements UnitInfoDao {

	public UnitInfo query() {
		// TODO Auto-generated method stub

		return (UnitInfo) this.getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"unit.query");
	}

	public void insert(UnitInfo unitInfo) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"unit.insert", unitInfo);

	}

	public void update(UnitInfo unitInfo) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"unit.update", unitInfo);

	}

}
