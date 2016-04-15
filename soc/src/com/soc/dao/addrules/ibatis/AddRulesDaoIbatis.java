package com.soc.dao.addrules.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.addrules.AddRulesDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.model.systemsetting.rules.Device_category;
import com.soc.model.systemsetting.rules.QueryEvents_group;

public class AddRulesDaoIbatis extends BaseDaoIbatis implements AddRulesDao {

	@Override
	public void insertAnalysisRules(AnalysisRules analysisrules) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.analysisForAddRules",analysisrules);
	}

	@Override
	public void insertDevice_category(Device_category device_category) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.deviceForAddRules", device_category);
	}

	@Override
	public void insertQueryEvents_group(QueryEvents_group queryevents_group) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.groupForAddRules", queryevents_group);
	}

	@Override
	public List<String> selectALLAnalysisName(Map<String, String> map) {
		return this.queryForList(GlobalConfig.sqlId+"select.allAnalysisName",map);
	}

}
