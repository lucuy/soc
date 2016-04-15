package com.soc.webapp.action.systemsetting.rules;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
import com.soc.service.systemsetting.rules.RelevanceRuleGroupService;
import com.soc.webapp.action.BaseAction;
import com.util.StringUtil;
import com.util.drools.Drools;
import com.util.page.Page;
import com.util.page.SearchResult;

public class RelevanceRuleGroupAction extends BaseAction {
	// 规则管理的服务类
	private RelevanceRuleGroupService relevanceRuleGroupManager;
	private String htmlBuffer;
	// 规则组
	private List<RelevanceRuleGroup> ruleGroupList;
	//关联规则类
	private RelevanceRuleGroup relevanceRuleGroup;
	//ids
	private String ids;
	// 启用状态
	private int status;
	//关键系
	private String keyword;
	/**
	 * 展现左侧数的方法
	 * 
	 * @return
	 */
	public String displayLeftTree() {
		log.info("[AuditReportAction] enter displayLeftManagetree");
		String objectPath = super.getRequest().getContextPath();

		htmlBuffer = relevanceRuleGroupManager.displayLeftTree(objectPath);

		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String showRuleGroup() {
		log.info("[AuditReportAction] enter showRuleGroup");
		// 对分页的处理
		Page page = null;
		HttpServletRequest request = super.getRequest();
		SearchResult sr = null;
		Map map = new HashMap();
		String startIndex = request.getParameter("startIndex");
		try {
			if (StringUtil.isNotBlank(startIndex)) {
				if (keyword==null || keyword.equals("")) {
					page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
				}else {
					if (Page.getKeyword().equals(keyword)) {
						page = new Page(Page.DEFAULT_PAGE_SIZE, Integer.valueOf(startIndex));
						Page.setKeyword(keyword);
					}else{
						page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
						Page.setKeyword(keyword);
					}
				}
			} else {
				page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
				Page.setKeyword(keyword);
			}			
		} catch (Exception e) {
			page = new Page(Page.DEFAULT_PAGE_SIZE, 0);
			Page.setKeyword(keyword);
		}
		// 对前台获得的等级和关键字中文编码进行处理
		if (keyword != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		map.put("parentId", 1);
		map.put("keyword", keyword);
		sr = relevanceRuleGroupManager.queryRuleGroup(map, page);
		if (sr != null) {
			ruleGroupList = (List<RelevanceRuleGroup>) sr.getList();
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		}

		return SUCCESS;
	}

	/**
	 * 更新状态的方法
	 * 
	 * @return
	 */
	public String updateStatus() {
		Map map = new HashMap();
		map.put("ids", ids);
		map.put("status",status);
		this.relevanceRuleGroupManager.updateStatus(map);
		GlobalConfig.drools.closeSesssion();
		 List<RelevanceRuleGroup> relevanceRuleGroups = this.relevanceRuleGroupManager.queryEnableRule();
	        GlobalConfig.drools.relevanceRuleGroups = relevanceRuleGroups;
	        GlobalConfig.ruleNameList = initRuleNamesList(relevanceRuleGroups);//初始化规则引擎需要用的名字
	        GlobalConfig.drools.initEngine( GlobalConfig.ruleNameList,relevanceRuleGroupManager);
        return SUCCESS;
	}

	/**
	 * 删除规则组
	 * 
	 * @return
	 */

	public String deleteRuleGroup() {
		Map map = new HashMap();
		map.put("ids", ids);
		this.relevanceRuleGroupManager.deleteRuleGroup(map);
		// 刷新父页面
		try {
			String script = "<script language=javascript>parent.parent.leftFrame.location.href ="
					+ " '/soc/relevanceGroup/displayLeftTree.action';"
					+ "parent.parent.mainFrame.location.href ="
					+ "'/soc/relevanceGroup/showGroupList.action;'" + "</script>";
			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GlobalConfig.drools.closeSesssion();
		 List<RelevanceRuleGroup> relevanceRuleGroups = this.relevanceRuleGroupManager.queryEnableRule();
	        GlobalConfig.drools.relevanceRuleGroups = relevanceRuleGroups;
	        GlobalConfig.ruleNameList = initRuleNamesList(relevanceRuleGroups);//初始化规则引擎需要用的名字
	        GlobalConfig.drools.initEngine( GlobalConfig.ruleNameList,relevanceRuleGroupManager);
		return null;
	}
	/**
	 * 增加规则组
	 * @return
	 */
	public String addGroup() {
		String userName = (String) super.getSession().getAttribute(
				"userinfo");
		relevanceRuleGroup.setCreater(userName);// 当前登录的用户名
		relevanceRuleGroup.setTime(new Date());// 放创建时间
		relevanceRuleGroupManager.addGroup(relevanceRuleGroup);
		// 刷新父页面
		try {
			String script = "<script language=javascript>parent.parent.leftFrame.location.href ="
					+ " '/soc/relevanceGroup/displayLeftTree.action';"
					+ "parent.parent.mainFrame.location.href ="
					+ "'/soc/relevanceGroup/showGroupList.action;'" + "</script>";
			super.getResponse().getWriter().print(script);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	}

	public RelevanceRuleGroupService getRelevanceRuleGroupManager() {
		return relevanceRuleGroupManager;
	}

	public void setRelevanceRuleGroupManager(
			RelevanceRuleGroupService relevanceRuleGroupManager) {
		this.relevanceRuleGroupManager = relevanceRuleGroupManager;
	}

	public String getHtmlBuffer() {
		return htmlBuffer;
	}

	public void setHtmlBuffer(String htmlBuffer) {
		this.htmlBuffer = htmlBuffer;
	}

	public List<RelevanceRuleGroup> getRuleGroupList() {
		return ruleGroupList;
	}

	public void setRuleGroupList(List<RelevanceRuleGroup> ruleGroupList) {
		this.ruleGroupList = ruleGroupList;
	}

	public RelevanceRuleGroup getRelevanceRuleGroup() {
		return relevanceRuleGroup;
	}

	public void setRelevanceRuleGroup(RelevanceRuleGroup relevanceRuleGroup) {
		this.relevanceRuleGroup = relevanceRuleGroup;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

    /**
     * <加载规则的名字group1Rule1>
     * <功能详细描述>
     * @param relevanceRuleGroups
     * @return
     * @see [类、类#方法、类#成员]
     */
    private List<String> initRuleNamesList(
    		List<RelevanceRuleGroup> relevanceRuleGroups) {
    	//遍历规则组 规则组下还有还多规则 双重循环
		List<String> ruleName = new Vector<String>();
    	for (RelevanceRuleGroup relevanceRuleGroup : relevanceRuleGroups) {
    		List<RelevanceRule> relevanceRules = relevanceRuleGroup.getRelevanceRules();
    		RelevanceRule relevanceRule  = relevanceRules.get(0);
    			ruleName.add("group"+relevanceRuleGroup.getId()+"Rule"+relevanceRule.getRelevanceRuleId()+"");
    	}
    	return ruleName;
    }


}
