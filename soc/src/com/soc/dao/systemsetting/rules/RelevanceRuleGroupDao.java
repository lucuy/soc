package com.soc.dao.systemsetting.rules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.rules.RelevanceRuleGroup;



/**
 * 
 * <自定义关联规则dao> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-3-2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RelevanceRuleGroupDao {
	public List<RelevanceRuleGroup> queryByParentId(Map map);

	public int count(Map map);

	public List<RelevanceRuleGroup> queryByParentId(Map map,int startIndex, int pageSize);

	public void addGroup(RelevanceRuleGroup relevanceRuleGroup);

	public void deleteRuleGroup(Map map);

	public void updateStatus(Map map);
}
