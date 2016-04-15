package com.soc.service.systemsetting.impl;

import java.util.Map;

import com.soc.dao.systemsetting.SystemSettingDao;
import com.soc.model.systemsetting.Setting;
import com.soc.service.systemsetting.SystemSettingService;

public class SystemSettingServiceImpl implements SystemSettingService {

	private SystemSettingDao ssDao;
	@Override
	public void update4AIp(String str) {
		if (ssDao.query4AIpByKey()!=null && ssDao.query4AIpByKey()!="") {
			ssDao.update4AIp(str);
		}else {
			ssDao.insert4AIp(str);
		}
	}

	@Override
	public String query4AIpByKey() {
		return ssDao.query4AIpByKey();
	}

	public SystemSettingDao getSsDao() {
		return ssDao;
	}

	public void setSsDao(SystemSettingDao ssDao) {
		this.ssDao = ssDao;
	}
	

}
