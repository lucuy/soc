package com.soc.service.systemsetting.impl.rules;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.rules.RelevanceRuleDao;
import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.service.systemsetting.rules.RelevanceRuleService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class RelevanceRuleServiceImpl implements RelevanceRuleService {
	// dao类
	private RelevanceRuleDao relevanceRuleDao;

	@Override
	public SearchResult queryRelevanceRuleList(Map map, Page page) {
		int rowsCount = relevanceRuleDao.count(map);
		page.setTotalCount(rowsCount);
		List<RelevanceRule> list =relevanceRuleDao.queryRelevanceRule(map,
				page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);

		sr.setPage(page);

		return sr;
	}

	@Override
	public void deleteRelevanceRule(String ids) {
		// 拆分字符串
		if (!ids.equals("")) {
			String[] idTemp = ids.split(",");
			for (String id : idTemp) {
				//System.out.println(id);
				relevanceRuleDao.deleteRelevanceRule(Long.parseLong(id));
			}

		}

	}

	@Override
	public void insertRelevanceRule(RelevanceRule relevanceRule) {
	
		relevanceRuleDao.addRelevanceRule(relevanceRule);

	}

	@Override
	public void updateRelevanceRule(RelevanceRule relevanceRule) {
	
		relevanceRuleDao.updateRelevanceRule(relevanceRule);

	}

	@Override
	public RelevanceRule selectRelevanceRuleByid(long id) {
		return relevanceRuleDao.queryRelevanceRuleById(id);
	}

	public RelevanceRuleDao getRelevanceRuleDao() {
		return relevanceRuleDao;
	}

	public void setRelevanceRuleDao(RelevanceRuleDao relevanceRuleDao) {
		this.relevanceRuleDao = relevanceRuleDao;
	}

	@Override
	public void updateStatus(String ids, int status) {

		// 拆分字符串
		RelevanceRule relevanceRule = new RelevanceRule();
		String[] idTemp = ids.split(",");
		for (String id : idTemp) {
			relevanceRule.setRelevanceRuleId(Long.parseLong(id));
			relevanceRule.setRelevanceRuleType(status);
			relevanceRuleDao.updateStatus(relevanceRule);
		}

	}

	@Override
	public int countRelevanceRuleByGroupId(int groupId) {
		return relevanceRuleDao.countRelevanceRuleByGroupId(groupId);
	}

}
