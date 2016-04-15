package com.soc.model.systemsetting.rules;

import java.io.Serializable;

public class RulesVO implements Serializable {
	private Device_category device_category;
	private QueryEvents_group queryevents_group;
	private AnalysisRules analysisRules;
	public Device_category getDevice_category() {
		return device_category;
	}
	public void setDevice_category(Device_category device_category) {
		this.device_category = device_category;
	}
	public QueryEvents_group getQueryevents_group() {
		return queryevents_group;
	}
	public void setQueryevents_group(QueryEvents_group queryevents_group) {
		this.queryevents_group = queryevents_group;
	}
	public AnalysisRules getAnalysisRules() {
		return analysisRules;
	}
	public void setAnalysisRules(AnalysisRules analysisRules) {
		this.analysisRules = analysisRules;
	}
	
}
