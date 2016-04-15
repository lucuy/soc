package com.soc.service.systemsetting.impl.rules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.rules.FilteringRuleDao;
import com.soc.model.knowledge.Security;
import com.soc.model.systemsetting.rules.FilteringRule;
import com.soc.service.systemsetting.rules.FilteringRuleService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class FilteringRuleServiceImpl implements FilteringRuleService {
	// dao类
	private FilteringRuleDao filteringRuleDao;

	@Override
	public SearchResult queryFilterRuleList(Map map, Page page) {
		int rowsCount = filteringRuleDao.count(map);
		page.setTotalCount(rowsCount);
		List<FilteringRule> list = filteringRuleDao.queryFilteringRule(map,
				page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);

		sr.setPage(page);

		return sr;
	}

	@Override
	public void deleteFilterRule(String ids) {
		// 拆分字符串
		if (!ids.equals("")) {
			String[] idTemp = ids.split(",");
			for (String id : idTemp) {
				//System.out.println(id);
				filteringRuleDao.deleteFilteringRule(Long.parseLong(id));
			}

		}

	}

	@Override
	public void insertFilterRule(FilteringRule filteringRule) {

		filteringRuleDao.addFilteringRule(filteringRule);

	}

	@Override
	public void updateFilterRule(FilteringRule filteringRule) {
		filteringRuleDao.updateFilteringRule(filteringRule);

	}

	@Override
	public FilteringRule selectFilterRuleByid(long id) {
		return filteringRuleDao.queryFilteringRuleById(id);
	}

	public FilteringRuleDao getFilteringRuleDao() {
		return filteringRuleDao;
	}

	public void setFilteringRuleDao(FilteringRuleDao filteringRuleDao) {
		this.filteringRuleDao = filteringRuleDao;
	}

	@Override
	public void updateStatus(String ids, int status) {

		// 拆分字符串
		FilteringRule filteringRule = new FilteringRule();
		String[] idTemp = ids.split(",");
		for (String id : idTemp) {
			filteringRule.setFilteringRuleId(Long.parseLong(id));
			filteringRule.setFilteringRuleType(status);
			filteringRuleDao.updateStatus(filteringRule);
		}

	}
	@Override
	public List<FilteringRule> queryFilterByType() {
		return filteringRuleDao.queryFilterByType();
	}
}
