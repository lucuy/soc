package com.soc.service.systemsetting.rules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <自定义关联规则服务类> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-2-27]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface RelevanceRuleGroupService {
	/**
	 * 展现左侧数的方法
	 * 
	 * @return
	 */
	public String displayLeftTree(String path);

	/**
	 * 查询出所有规则组的方法
	 * @param page
	 * @return
	 */

	SearchResult queryRuleGroup(Map map, Page page);

	public void addGroup(RelevanceRuleGroup relevanceRuleGroup);

	public void deleteRuleGroup(Map map);

	public void updateStatus(Map map);

	List<RelevanceRuleGroup> queryEnableRule();

	

	public List<RelevanceRule> queryRuleBuGroupId(int id);

	/**
	 * 更新状态的方法
	 * 
	 * @return
	 */
	// public String updateStatus();
	/**
	 * 删除规则组
	 * 
	 * @return
	 */

	// public String deleteRuleGroup();
}
