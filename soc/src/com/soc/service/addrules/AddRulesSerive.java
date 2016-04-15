package com.soc.service.addrules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.model.systemsetting.rules.Device_category;
import com.soc.model.systemsetting.rules.QueryEvents_group;

public interface AddRulesSerive{
	
	/**
     * <添加AnalysisRules>
     * <添加AnalysisRules>
     * @see [类、类#方法、类#成员]
     * 参数：AnalysisRules类
     */
	public void insertAnalysisRules(AnalysisRules analysisrules);
	
	/**
     * <添加Device_category>
     * <添加Device_category>
     * @see [类、类#方法、类#成员]
     * 参数：Device_category类
     */
	public void insertDevice_category(Device_category device_category);
	
	/**
     * <添加QueryEvents_group>
     * <添加QueryEvents_group>
     * @see [类、类#方法、类#成员]
     * 参数：QueryEvents_group类
     */
	public void insertQueryEvents_group(QueryEvents_group queryevents_group);
	
	/**
     * <查询所有AnalysisName>
     * <查询所有AnalysisName>
     * @see [类、类#方法、类#成员]
     * 参数：null
     */
	public List<String> selectALLAnalysisName(Map<String, String> map);
}
