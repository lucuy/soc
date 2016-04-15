package com.soc.dao.systemsetting.rules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.rules.FilteringRule;

/**
 * 
 * <自定义过滤规则dao> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-2-26]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface FilteringRuleDao {
	/**
	 * <查询自定义过滤规则>
	 * <功能详细描述>
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<FilteringRule> queryFilteringRule(Map map, int startRow, int pageSize);
	/**
	 * <根据Id查询自定义过滤规则的详情>
	 * <功能详细描述>
	 * @param filteringRuleId
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public FilteringRule queryFilteringRuleById(long filteringRuleId);
  /**
    * <增加新的自定义过滤规则>
    * <功能详细描述>
    * @param filteringRule
    * @see [类、类#方法、类#成员]
    */
	public void addFilteringRule(FilteringRule filteringRule);
	/**
	 * <根据id更新自定义过滤规则>
	 * <功能详细描述>
	 * @param filteringRule
	 * @see [类、类#方法、类#成员]
	 */
	public void updateFilteringRule(FilteringRule filteringRule);
	/**
	 * <删除自定义过滤规则>
	 * <功能详细描述>
	 * @param filteringRuleId
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteFilteringRule(long filteringRuleId);
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
	public void updateStatus(FilteringRule filteringRule);
	/**
	 * <查询出启用的规则>
	 * <功能详细描述>
	 * @param id
	 * @return 
	 * @see [类、类#方法、类#成员]
	 */
	public List<FilteringRule> queryFilterByType();
}
