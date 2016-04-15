package com.soc.service.systemsetting.impl.rules;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.dao.systemsetting.rules.RelevanceRuleDao;
import com.soc.dao.systemsetting.rules.RelevanceRuleGroupDao;
import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
import com.soc.service.systemsetting.rules.RelevanceRuleGroupService;
import com.util.page.Page;
import com.util.page.SearchResult;

public class RelevanceRuleGroupServiceImpl implements RelevanceRuleGroupService {
	// dao类
	private RelevanceRuleGroupDao relevanceRuleGroupDao;
	private StringBuffer treeBuff;
	private RelevanceRuleDao relevanceRuleDao;
	private static final String PICTURE_NAME_SEED = "arrow_03.gif"; // 表示页子节点的图片
	private static final String PICTURE_NAME_CLOSE = "u321_normal.gif"; // 表示关闭的图片

	@Override
	public String displayLeftTree(String path) {

		treeBuff = new StringBuffer();
		Map<String, Integer> map = new HashMap<String, Integer>();

		map.put("parentId", 0);

		List<RelevanceRuleGroup> RelevanceRuleGroupList = relevanceRuleGroupDao
				.queryByParentId(map);

		for (RelevanceRuleGroup relevanceRuleGroup : RelevanceRuleGroupList) {
			Map<String, Integer> map1 = new HashMap<String, Integer>();

			map1.put("parentId", relevanceRuleGroup.getId());

			List<RelevanceRuleGroup> reportList1 = relevanceRuleGroupDao
					.queryByParentId(map1);
			if (reportList1.size() <= 0) {
				treeBuff.append("<ul>");
				treeBuff.append("<li><a href=\"javascript:showRule('"
						+ relevanceRuleGroup.getId() + "');\"><img src=\""
						+ path + "/images/" + PICTURE_NAME_SEED
						+ "\"></a><a href=\"javascript:showRule('"
						+ relevanceRuleGroup.getId()
						+ "');\"><span class=\"eventext\" style=\"margin-left:0px\">&nbsp;"
						+ relevanceRuleGroup.getName() + "</span></a>");
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			} else {
				// 画出下级组
				treeBuff.append("<ul>");
				treeBuff.append("<li id=\"query_" + relevanceRuleGroup.getId()
						+ "\"><a href=\"javascript:action('"
						+ relevanceRuleGroup.getId()
						+ "','img');\"><img src=\"" + path + "/images/"
						+ PICTURE_NAME_CLOSE + "\" id=\"img_query_"
						+ relevanceRuleGroup.getId()
						+ "\"></a><a href=\"javascript:showRule('"
						+ relevanceRuleGroup.getId()
						+ "');\" ondblclick=\"action('"
						+ relevanceRuleGroup.getId()
						+ "','img')\"><span class=\"eventext\" style=\"margin-left:0px\">&nbsp;"
						+ relevanceRuleGroup.getName() + "</span></a>");
				drawSon(reportList1, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			}

		}

		return treeBuff.toString();
	}

	public void drawSon(List<RelevanceRuleGroup> relevanceRuleGroupList,
			String path) {
		for (RelevanceRuleGroup relevanceRuleGroup : relevanceRuleGroupList) {
			Map<String, Integer> map = new HashMap<String, Integer>();

			map.put("parentId", relevanceRuleGroup.getId());

			List<RelevanceRuleGroup> groupList = relevanceRuleGroupDao
					.queryByParentId(map);
			if (groupList.size() <= 0) {
				// 写出页子节点
				treeBuff.append("<ul  class=\"disply\">");
				treeBuff.append("<li class=\"eventleft\"><a href=\"javascript:showRule('"
						+ relevanceRuleGroup.getId()
						+ "');\"><img src=\""
						+ path
						+ "/images/"
						+ PICTURE_NAME_SEED
						+ "\"></a><a href=\"javascript:showRule('"
						+ relevanceRuleGroup.getId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ relevanceRuleGroup.getName() + "</span></a>");
				treeBuff.append("</li>");
				treeBuff.append("</ul>");
			} else {
				// 画出下级组
				treeBuff.append("<ul  class=\"disply\">");
				treeBuff.append("<li id=\"query_"
						+ relevanceRuleGroup.getId()
						+ "\" class=\"eventleft\"><a href=\"javascript:action('"
						+ relevanceRuleGroup.getId()
						+ "','img');\"><img src=\"" + path + "/images/"
						+ PICTURE_NAME_CLOSE + "\" id=\"img_query_"
						+ relevanceRuleGroup.getId()
						+ "\"></a><a href=\"javascript:showRule('"
						+ relevanceRuleGroup.getId()
						+ "')\" ondblclick=\"action('"
						+ relevanceRuleGroup.getId() + "','"
						+ relevanceRuleGroup.getId()
						+ "');\"><span class=\"eventext\">&nbsp;"
						+ relevanceRuleGroup.getName() + "</span></a>");
				drawSon(groupList, path);
				treeBuff.append("</li>");
				treeBuff.append("</ul>");

			}
		}
	}

	@Override
	public SearchResult queryRuleGroup(Map map,Page page) {

		int rowsCount = relevanceRuleGroupDao.count(map);
		page.setTotalCount(rowsCount);
		List<RelevanceRuleGroup> list = relevanceRuleGroupDao.queryByParentId(map,page.getStartIndex(), page.getPageSize());
		// 对查找的用户列表做分页处理
		SearchResult sr = new SearchResult();
		sr.setList(list);

		sr.setPage(page);

		return sr;
	}

	@Override
	public void deleteRuleGroup(Map map) {
		relevanceRuleGroupDao.deleteRuleGroup(map);
		
	}

	@Override
	public void updateStatus(Map map) {
		relevanceRuleGroupDao.updateStatus(map);
		
	}

	public RelevanceRuleGroupDao getRelevanceRuleGroupDao() {
		return relevanceRuleGroupDao;
	}

	public void setRelevanceRuleGroupDao(
			RelevanceRuleGroupDao relevanceRuleGroupDao) {
		this.relevanceRuleGroupDao = relevanceRuleGroupDao;
	}

	public StringBuffer getTreeBuff() {
		return treeBuff;
	}

	public void setTreeBuff(StringBuffer treeBuff) {
		this.treeBuff = treeBuff;
	}

	@Override
	public void addGroup(RelevanceRuleGroup relevanceRuleGroup) {
		relevanceRuleGroupDao.addGroup(relevanceRuleGroup);
		
	}
	@Override
	public List<RelevanceRuleGroup> queryEnableRule(){
		//根据组的id去掉用规则dao找到规则类 放到组内封装好的规则类
		Map map = new HashMap<String,Object>();
		map.put("parentId", 1);
		map.put("status", 1);
		List<RelevanceRuleGroup> ruleGroups = this.relevanceRuleGroupDao.queryByParentId(map);
		for (RelevanceRuleGroup relevanceRuleGroup : ruleGroups) {
			Map mapTemp = new HashMap<String,Object>();
			map.put("groupId", relevanceRuleGroup.getId());
			map.put("init", 1);
			List<RelevanceRule> rules = 	this.relevanceRuleDao.queryRelevanceRule(map);
			//把list变成队列
		/*	ConcurrentLinkedQueue<RelevanceRule> ruleQueue = new  ConcurrentLinkedQueue<RelevanceRule>();
			for (RelevanceRule relevanceRule : rules) {
				ruleQueue.add(relevanceRule);
			}	*/	
			relevanceRuleGroup.setRelevanceRules(rules);
		}
		//取出没有规则的规则组
		List<RelevanceRuleGroup> ruleGroupsTemp = new ArrayList<RelevanceRuleGroup>();
		for (RelevanceRuleGroup relevanceRuleGroup : ruleGroups) {
			List<RelevanceRule> rules = relevanceRuleGroup.getRelevanceRules();
			if (rules.isEmpty()) {
				ruleGroupsTemp.add(relevanceRuleGroup);
			}
		}
		ruleGroups.removeAll(ruleGroupsTemp);
		return ruleGroups;
	}

	public RelevanceRuleDao getRelevanceRuleDao() {
		return relevanceRuleDao;	
	}

	public void setRelevanceRuleDao(RelevanceRuleDao relevanceRuleDao) {
		this.relevanceRuleDao = relevanceRuleDao;
	}



	@Override
	public List<RelevanceRule> queryRuleBuGroupId(int id) {
		return relevanceRuleDao.queryRuleBuGroupId(id);
	}
	
}
