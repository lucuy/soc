package com.soc.dao.systemsetting.rules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.rules.RelevanceRule;

/**
 * 
 * <自定义关联规则dao> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-3-2]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RelevanceRuleDao {
	/**
	 * <查询自定义规则>
	 * <功能详细描述>
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<RelevanceRule> queryRelevanceRule(Map map, int startRow, int pageSize);
	/**
	 * <根据Id查询自定义关联规则的详情>
	 * <功能详细描述>
	 * @param relevanceRuleId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public RelevanceRule queryRelevanceRuleById(long relevanceRuleId);
  /**
    * <增加新的自定义关联规则>
    * <功能详细描述>
    * @param relevanceRule
    * @see [类、类#方法、类#成员]
    */
	public void addRelevanceRule(RelevanceRule relevanceRule);
	/**
	 * <根据id更新自定义关联规则>
	 * <功能详细描述>
	 * @param relevanceRule
	 * @see [类、类#方法、类#成员]
	 */
	public void updateRelevanceRule(RelevanceRule relevanceRule);
	/**
	 * <删除自定义关联规则>
	 * <功能详细描述>
	 * @param relevanceRuleId
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteRelevanceRule(long relevanceRuleId);
	/**
	 * <返回条数>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int count(Map map);
	/**
	 * <根据id更新启用状态>
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public void updateStatus(RelevanceRule relevanceRule);
	List<RelevanceRule> queryRelevanceRule(Map map);
	public int countRelevanceRuleByGroupId(int groupId);
	public List<RelevanceRule> queryRuleBuGroupId(int id);
}