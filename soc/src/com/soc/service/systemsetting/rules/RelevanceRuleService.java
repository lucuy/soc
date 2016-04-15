package com.soc.service.systemsetting.rules;

import java.util.Map;

import com.soc.model.systemsetting.rules.RelevanceRule;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <自定义关联规则服务类>
 * <功能详细描述>
 * 
 * @author  gaosong
 * @version  [版本号, 2013-2-27]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface RelevanceRuleService {
	/**
	 * <查询自定义关联规则带分页功能>
	 * <功能详细描述>
	 * @param map
	 * @param page
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public SearchResult queryRelevanceRuleList(Map map,Page page);
	/**
	 * <删除自定义关联规则>
	 * <功能详细描述>
	 * @param ids
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteRelevanceRule(String ids);

	/**
	 * <插入自定义关联规则>
	 * <功能详细描述>
	 * @param relevanceRule
	 * @see [类、类#方法、类#成员]
	 */
	public void insertRelevanceRule(RelevanceRule  relevanceRule );

	/**
	 * <更新自定义关联规则>
	 * <功能详细描述>
	 * @param relevanceRule
	 * @see [类、类#方法、类#成员]
	 */
	public void updateRelevanceRule(RelevanceRule  relevanceRule );

	/**
	 * <查询详细的自定义关联规则>
	 * <功能详细描述>
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public RelevanceRule selectRelevanceRuleByid(long id);
	/**
	 * <更新启用状态>
	 * <功能详细描述>
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public void updateStatus(String ids,int status);
	public int countRelevanceRuleByGroupId(int groupId);
}
