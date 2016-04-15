package com.soc.dao.systemsetting.ibatis.rules;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.rules.RelevanceRuleDao;
import com.soc.dao.systemsetting.rules.RelevanceRuleGroupDao;
import com.soc.model.audit.AuditReport;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;

public class RelevanceRuleGroupDaoIbatis extends BaseDaoIbatis implements
		RelevanceRuleGroupDao {

	@Override
	public List<RelevanceRuleGroup> queryByParentId(Map map) {
		
		return getSqlMapClientTemplate().queryForList(
				GlobalConfig.sqlId+"relevanceRuleGroupSQL.queryByParentId", map);
	}

	@Override
	public int count(Map map) {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				GlobalConfig.sqlId+"relevanceRuleGroupSQL.count",map);
	}

	@Override
	public List<RelevanceRuleGroup> queryByParentId(Map map,int startIndex, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"relevanceRuleGroupSQL.queryByParentId",map,startIndex,pageSize);
	}

	@Override
	public void addGroup(RelevanceRuleGroup relevanceRuleGroup) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"relevanceRuleGroupSQL.addGroup", relevanceRuleGroup);
		
	}

	@Override
	public void deleteRuleGroup(Map map) {
	getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"relevanceRuleGroupSQL.deleteGroup", map);
		
	}

	@Override
	public void updateStatus(Map map) {
		getSqlMapClientTemplate().update(GlobalConfig.sqlId+"relevanceRuleGroupSQL.updateStatus", map);
		
	}



}
