package com.soc.dao.systemsetting.ibatis;

import java.util.Map;

import org.jfree.chart.plot.RingPlot;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.SystemSettingDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Setting;

public class SystemSettingDaoIbatis extends BaseDaoIbatis implements SystemSettingDao
{

	@Override
	public long insert4AIp(String str) {
		Object obj = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"setting.insertMap",str);
		if (obj!=null) {
			return Long.parseLong(obj.toString());
		}
		return 0;
	}

	@Override
	public void update4AIp(String str) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"setting.updateMap",str);
	}

	@Override
	public String query4AIpByKey() {
		Object obj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"setting.queryByKeyMap");
		if (obj!=null) {
			return obj.toString();
		}
		return "";
	}
    
}
