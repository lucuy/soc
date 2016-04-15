package com.soc.dao.systemsetting.ibatis.rules;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.rules.RelevanceRuleDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.rules.RelevanceRule;

public class RelevanceRuleDaoIbatis extends BaseDaoIbatis implements
		RelevanceRuleDao {

	@Override
	public List<RelevanceRule> queryRelevanceRule(Map map, int startRow,
			int pageSize) {
		return this.getSqlMapClientTemplate()
				.queryForList(GlobalConfig.sqlId+"RelevanceRule.queryRelevanceRuleList", map,
						startRow, pageSize);
	}

	@Override
	public RelevanceRule queryRelevanceRuleById(long relevanceRuleId) {
		return (RelevanceRule) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"RelevanceRule.queryRelevanceRuleById", relevanceRuleId);
	}

	@Override
	public void addRelevanceRule(RelevanceRule relevanceRule) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"RelevanceRule.addRelevanceRule",
				relevanceRule);
	}

	@Override
	public void updateRelevanceRule(RelevanceRule relevanceRule) {
		this.getSqlMapClientTemplate().update(
				GlobalConfig.sqlId+"RelevanceRule.updateRelevanceRule", relevanceRule);

	}

	@Override
	public void deleteRelevanceRule(long relevanceRuleId) {
		this.getSqlMapClientTemplate().delete(
				GlobalConfig.sqlId+"RelevanceRule.deleteRelevanceRule", relevanceRuleId);

	}

	@Override
	public int count(Map map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"RelevanceRule.count", map);
	}

	@Override
	public void updateStatus(RelevanceRule relevanceRule) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"RelevanceRule.updateStatus",
				relevanceRule);

	}

	@Override
	public List<RelevanceRule> queryRelevanceRule(Map map) {
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"RelevanceRule.queryRelevanceRuleList", map);
	}


	@Override
	public int countRelevanceRuleByGroupId(int groupId) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"RelevanceRule.countRelevanceRuleByGroupId", groupId);
		
	}

	@Override
	public List<RelevanceRule> queryRuleBuGroupId(int groupId) {
		return (List<RelevanceRule>) getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"RelevanceRule.queryRuleBuGroupId", groupId);
		
	}

}
