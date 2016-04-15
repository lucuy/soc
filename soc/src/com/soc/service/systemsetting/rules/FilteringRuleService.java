package com.soc.service.systemsetting.rules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.rules.FilteringRule;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <自定义过滤规则服务类>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-2-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface FilteringRuleService {
	/**
	 * <查询自定义过滤规则带分页功能>
	 * <功能详细描述>
	 * @param map
	 * @param page
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public SearchResult queryFilterRuleList(Map map,Page page);
	/**
	 * <删除自定义过滤规则>
	 * <功能详细描述>
	 * @param ids
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteFilterRule(String ids);

	/**
	 * <插入自定义过滤规则>
	 * <功能详细描述>
	 * @param filteringRule
	 * @see [类、类#方法、类#成员]
	 */
	public void insertFilterRule(FilteringRule  filteringRule );

	/**
	 * <更新自定义过滤规则>
	 * <功能详细描述>
	 * @param filteringRule
	 * @see [类、类#方法、类#成员]
	 */
	public void updateFilterRule(FilteringRule  filteringRule );

	/**
	 * <查询详细的自定义过滤规则>
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public FilteringRule selectFilterRuleByid(long id);
	/**
	 * <更新启用状态>
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public void updateStatus(String ids,int status);
	/**
	 * <查询出所有启用的规则>
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public List<FilteringRule> queryFilterByType();
}
