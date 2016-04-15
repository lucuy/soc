package com.soc.service.systemsetting.impl;

import java.io.File;

import com.soc.dao.systemsetting.SettingFunctionControlDao;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.systemsetting.SettingFunctionControlSerive;

public class SettingFunctionControlSeriveImpl extends BaseServiceImpl implements SettingFunctionControlSerive  {
private SettingFunctionControlDao settingFunctionControlDao;
	@Override
	public void SettingFunctionControl(String sqName) {
		settingFunctionControlDao.SettingFunctionControl(sqName);
	}
	public SettingFunctionControlDao getSettingFunctionControlDao() {
		return settingFunctionControlDao;
	}
	public void setSettingFunctionControlDao(
			SettingFunctionControlDao settingFunctionControlDao) {
		this.settingFunctionControlDao = settingFunctionControlDao;
	}
	
}
