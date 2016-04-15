package com.soc.service.addrules.impl;

import java.util.List;
import java.util.Map;

import com.soc.dao.addrules.AddRulesDao;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.model.systemsetting.rules.Device_category;
import com.soc.model.systemsetting.rules.QueryEvents_group;
import com.soc.service.addrules.AddRulesSerive;
import com.soc.service.impl.BaseServiceImpl;

public class AddRulesServiceImpl extends BaseServiceImpl implements
		AddRulesSerive {

	private AddRulesDao addrulesdao;
	
	@Override
	public void insertAnalysisRules(AnalysisRules analysisrules) {
		addrulesdao.insertAnalysisRules(analysisrules);
	}

	@Override
	public void insertDevice_category(Device_category device_category) {
		addrulesdao.insertDevice_category(device_category);
	}

	@Override
	public void insertQueryEvents_group(QueryEvents_group queryevents_group) {
		addrulesdao.insertQueryEvents_group(queryevents_group);
	}

	@Override
	public List<String> selectALLAnalysisName(Map<String, String> map) {
		return addrulesdao.selectALLAnalysisName(map);
	}
	
	public AddRulesDao getAddrulesdao() {
		return addrulesdao;
	}

	public void setAddrulesdao(AddRulesDao addrulesdao) {
		this.addrulesdao = addrulesdao;
	}

	
	
	

}
