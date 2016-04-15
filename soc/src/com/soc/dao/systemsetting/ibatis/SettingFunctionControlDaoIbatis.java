package com.soc.dao.systemsetting.ibatis;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.SettingFunctionControlDao;

public class SettingFunctionControlDaoIbatis extends BaseDaoIbatis implements SettingFunctionControlDao {
/**
 * 功能控制模块daoibatis
 */
	@Override
	public void SettingFunctionControl(String sqName) {
		getSqlMapClientTemplate().delete("settingFunctionControlSQL."+sqName);
		
	}

}
