package com.compliance.service.basicinfo.unitinfo.impl;

import com.compliance.dao.basicinfo.unitinfo.UnitInfoDao;
import com.compliance.model.basicinfo.unitinfo.UnitInfo;
import com.compliance.service.basicinfo.unitinfo.UnitInfoService;
import com.compliance.service.impl.BaseServiceImpl;

public class UnitInfoServiceImpl extends BaseServiceImpl implements
		UnitInfoService {

	private UnitInfoDao unitInfoDao;

	public UnitInfo query() {
		// TODO Auto-generated method stub
		return unitInfoDao.query();
	}

	public void insert(UnitInfo unitInfo) {
		// TODO Auto-generated method stub
		unitInfoDao.insert(unitInfo);
	}

	public void update(UnitInfo unitInfo) {
		// TODO Auto-generated method stub
		unitInfoDao.update(unitInfo);
	}

	public UnitInfoDao getUnitInfoDao() {
		return unitInfoDao;
	}

	public void setUnitInfoDao(UnitInfoDao unitInfoDao) {
		this.unitInfoDao = unitInfoDao;
	}

}
