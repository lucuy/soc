package com.soc.dao.systemsetting.ibatis.rules;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.rules.FilteringRuleDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.rules.FilteringRule;
import com.soc.model.systemsetting.rules.RelevanceRule;

public class FilteringRuleDaoIbatis extends BaseDaoIbatis implements
		FilteringRuleDao {

	@Override
	public List<FilteringRule> queryFilteringRule(Map map, int startRow,
			int pageSize) {
		return this.getSqlMapClientTemplate()
				.queryForList(GlobalConfig.sqlId+"FilteringRule.queryFilteringRuleList", map,
						startRow, pageSize);
	}

	@Override
	public FilteringRule queryFilteringRuleById(long filteringRuleId) {
		return (FilteringRule) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"FilteringRule.queryFilteringRuleById", filteringRuleId);
	}

	@Override
	public void addFilteringRule(FilteringRule filteringRule) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"FilteringRule.addFilteringRule",
				filteringRule);
	}

	@Override
	public void updateFilteringRule(FilteringRule filteringRule) {
		this.getSqlMapClientTemplate().update(
				GlobalConfig.sqlId+"FilteringRule.updateFilteringRule", filteringRule);

	}

	@Override
	public void deleteFilteringRule(long filteringRuleId) {
		this.getSqlMapClientTemplate().delete(
				GlobalConfig.sqlId+"FilteringRule.deleteFilteringRule", filteringRuleId);

	}

	@Override
	public int count(Map map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"FilteringRule.count", map);
	}

	@Override
	public void updateStatus(FilteringRule filteringRule) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"FilteringRule.updateStatus",
				filteringRule);

	}
	@Override
	public List<FilteringRule> queryFilterByType() {
	return 	(List<FilteringRule>) getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"FilteringRule.queryFilteringRuleByType");
	}


}
