package com.soc.webapp.action.systemsetting.rules;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.eventtypetag.EventTypeTag;
import com.soc.model.eventtypetag.eventcategorytag;
import com.soc.model.systemsetting.rules.RelevanceRule;
import com.soc.model.systemsetting.rules.RelevanceRuleGroup;
import com.soc.model.user.User;
import com.soc.service.alert.AlertRuleService;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.rules.RelevanceRuleGroupService;
import com.soc.service.systemsetting.rules.RelevanceRuleService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.drools.Drools;
import com.util.page.Page;
import com.util.page.SearchResult;

public class RelevanceRuleAction extends BaseAction {
	// 规则管理的服务类
	private RelevanceRuleGroupService relevanceRuleGroupManager;
	// relevanceRuleServic类
	private RelevanceRuleService relevanceRuleManager;
	
	//审计业务处理类
	private AuditService auditManager;
	// 前台传过来的id 用于显示详细信息
	private long id;
	private String relevanceRuleName;
	private Integer relevanceRuleType;
	private int relevanceRuleStatus;

	// 传过来的字符串数组 用来批量删除
	private String ids;
	// 用来批量修改启用状态
	private int status;
	// 用来显示详细信息实体封装类
	private RelevanceRule relevanceRule;

	// 显示列表的liset
	private List<RelevanceRule> list;

	// 用于查询的关键字
	private String keyword;

	// 后台验证的结果标签
	private boolean flg;
	// 资产的业务处理类
	private AssetService assetManager;
	// 资产组业务处理类
	private AssetGroupService assetGroupManager;
	// 前台显示的设备列表
	private List<Map> assetList;
	// 前台显示的事件类别列表
	private List<eventcategorytag> eventCategoryList;
	// 前台显示的事件类型列表
	private List<EventTypeTag> eventTypeTagList;
	// 前台显示的关联后事件类别列表
	private List<eventcategorytag> eventCategoryListRe;
	// 前台显示的关联后事件类型列表
	private List<EventTypeTag> eventTypeTagListRe;
	// 前台显示的设备select框
	private List<Map<String, Object>> assetListSel;
	// 前台显示的事件类别select框
	private List<Map<String, Object>> eventCategoryListSel;
	// 前台显示的事件类型select框
	private List<Map<String, Object>> eventTypeTagListSel;
	// 前台显示的关联后事件类别select框
	private List<Map<String, Object>> eventCategoryListSelRe;
	// 前台显示的关联后事件类型select框
	private List<Map<String, Object>> eventTypeTagListSelRe;
	// 放等级的String []
	private int[] priorityArr;
	// 告警规则服务管理类
	private AlertRuleService alertRuleManager;
	// 用来显示源地址的list
	private List<String> srouceAddrSel;
	// 用来显示目标地址的list
	private List<String> targetAddrSel;
	// 用来显示目标端口的list
	private List<String> targetPortSel;
	// 用来显示源端口的list
	private List<String> sourcePortSel;
	//前台传过来组的id
	private int groupId;
	
	/**
	 * <跳转到添加界面> <这里需要查询资产>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String toInsertRelevancePage() {
		log.info("[relevanceRuleAction] enter toInsertRelevancePage()");
		Map<String, Object> map = new HashMap<String, Object>();
		assetList = this.assetManager.queryCategory();
		Iterator<Entry<String, String>> it = GlobalConfig.eventCategoryTag
				.entrySet().iterator();
		eventCategoryList = new ArrayList<eventcategorytag>();
		// 做一个封装eventcategorytag的list 事件类型
		while (it.hasNext()) {
			Entry<String, String> e = (Entry<String, String>) it.next();
			eventcategorytag eventcategory = new eventcategorytag();
			eventcategory.setEventcategorykey(e.getKey());
			eventcategory.setEventcategoryValue(e.getValue());
			eventCategoryList.add(eventcategory);
		}
		eventCategoryListRe = eventCategoryList;
		
		this.eventTypeTagList = new ArrayList<EventTypeTag>();
		Iterator<Entry<Long, String>> itEventType = GlobalConfig.eventTypeTag
				.entrySet().iterator();
		// 做一个封装eventcategorytag的list 事件类别
		while (itEventType.hasNext()) {
			Entry<Long, String> e = (Entry<Long, String>) itEventType.next();
			EventTypeTag eventTypeTag = new EventTypeTag();
			eventTypeTag.setEventtypetagId(e.getKey());
			eventTypeTag.setEventtypetagName(e.getValue());
			this.eventTypeTagList.add(eventTypeTag);
		}
		eventTypeTagListRe = eventTypeTagList;
		//根据传过来的组的id去找这个组下的规则 
		//有规则就跳到添加非初始状态 的界面
		//没有规则就跳到初始状态的界面
		return SUCCESS; 
	}

	/**
	 * <显示列表> <有分页功能>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryRelevanceRuleList() {
		log.info("[relevanceRuleAction] enter queryRelevanceRuleList()");
		// 对前台获得的等级和关键字中文编码进行处理
		if (keyword != null) {
			try {
				keyword = java.net.URLDecoder.decode(keyword, "UTF-8").trim();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		// 对分页的处理
		Page page = null;
		HttpServletRequest request = super.getRequest();
		SearchResult<RelevanceRule> sr = null;

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
		// 用来存放查询条件
		Map<String, Object> map = new HashMap<String, Object>();
		//所属组的id放进去
		map.put("groupId", groupId);
		if (request.getParameter("keyword") != null) {
			try {

				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			map.put("keyword", keyword);
		}
		if (StringUtil.isNotBlank(relevanceRuleName)) {
			map.put("relevanceRuleName", relevanceRuleName);
		}
		if (relevanceRuleType != null) {

			map.put("relevanceRuleType", relevanceRuleType);
		}

		sr = relevanceRuleManager.queryRelevanceRuleList(map, page);
		if (sr != null) {
			list = sr.getList();
			for (RelevanceRule rule : list) {
				rule.setEventsTypeIds(GlobalConfig.eventTypeTag.get(Long.parseLong(rule.getEventsTypeIdsRe()+"")));
				rule.setEventsCategoryIds(GlobalConfig.eventCategoryTag.get(rule.getEventsCategoryIdsRe()+""));
			}
			request.setAttribute("Page", sr.getPage());
			sr.setPage(page);
		}

		return SUCCESS;
	}

	/**
	 * <用于批量删除> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String deleteRelevanceRule() {
		//this.groupId = relevanceRule.getGroupId();
		log.info("[relevanceRuleAction] enter deleteRelevanceRule()");
		relevanceRuleManager.deleteRelevanceRule(ids);
		//this.groupId =relevanceRule.getGroupId();
		// createXML();
		GlobalConfig.drools.closeSesssion();
		 GlobalConfig.drools = new Drools();
		 //查询出启用的规则组
		 GlobalConfig.drools.closeSesssion();
		 List<RelevanceRuleGroup> relevanceRuleGroups = this.relevanceRuleGroupManager.queryEnableRule();
	        GlobalConfig.drools.relevanceRuleGroups = relevanceRuleGroups;
	        GlobalConfig.ruleNameList = initRuleNamesList(relevanceRuleGroups);//初始化规则引擎需要用的名字
	        GlobalConfig.drools.initEngine( GlobalConfig.ruleNameList,relevanceRuleGroupManager);
        return SUCCESS;
	}

	/**
	 * <插入规则> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String insertOrUpdateRelevanceRule() {
		log.info("[relevanceRuleAction] enter insertOrUpdateFilterRule()");
		// 将放放等级的数组解析成1,2,3的形式
		this.groupId = relevanceRule.getGroupId();
		String priorityTemp = "";
		if (priorityArr != null) {
			for (int i = 0; i < this.priorityArr.length; i++) {
				if (i == 0) {
					priorityTemp = priorityTemp + priorityArr[i];
				} else {
					priorityTemp = priorityTemp + "," + priorityArr[i];
				}
			}
		}
		relevanceRule.setAssetIds(this.getIDs(relevanceRule.getAssetIds()
				.split(",")));
		relevanceRule.setEventsCategoryIds(this.getIDs(relevanceRule
				.getEventsCategoryIds().split(",")));
		relevanceRule.setEventsTypeIds(this.getIDs(relevanceRule
				.getEventsTypeIds().split(",")));
		relevanceRule.setPriorityIds(priorityTemp);
	
		if (relevanceRule.getRelevanceRuleId() == 0) {
			List<RelevanceRule> relevanceRuleList = this.relevanceRuleGroupManager.queryRuleBuGroupId(relevanceRule.getGroupId());
			if (0 == relevanceRuleList.size()) {//如果是0说明下面没有规则 设置初始状态的字段为1
				relevanceRule.setInitState(1);
			}
			// 如果传过来的id是0 说明这是插入
			String userName = (String) super.getSession().getAttribute(
					"userinfo");
			relevanceRule.setRelevanceRuleCreater(userName);// 当前登录的用户名
			relevanceRule.setRelevanceRuleCreateDate(new Date());// 放创建时间
			relevanceRule.setRelevanceRuleMenderDate(new Date());// 修改时间
			relevanceRule.setRelevanceRuleCondition(""); //条件字符串的东西没用了！
			
			relevanceRuleManager.insertRelevanceRule(relevanceRule);

			//添加审计
			List<String> fieldList = new ArrayList<String>();
			
			fieldList.add(relevanceRule.getRelevanceRuleName() + "(" + relevanceRule.getRelevanceRuleName() + ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "自定义关联规则", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增自定义关联规则");
		} else {
			String userName = (String) super.getSession().getAttribute(
					"userinfo");
			relevanceRule.setRelevanceRuleMender(userName);// 当前登录的用户名
			relevanceRule.setRelevanceRuleMenderDate(new Date());// 修改时间
			relevanceRule.setRelevanceRuleCondition(""); ////条件字符串的东西没用了！
			relevanceRuleManager.updateRelevanceRule(relevanceRule);
			
			this.groupId = relevanceRule.getGroupId();
			//添加审计
			List<String> fieldList = new ArrayList<String>();

			fieldList.add(relevanceRule.getRelevanceRuleName() + "(" + relevanceRule.getRelevanceRuleName() + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "自定义关联规则", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改自定义关联规则");
		}
		 //查询出启用的规则组
		 GlobalConfig.drools.closeSesssion();
		 List<RelevanceRuleGroup> relevanceRuleGroups = this.relevanceRuleGroupManager.queryEnableRule();
	        GlobalConfig.drools.relevanceRuleGroups = relevanceRuleGroups;
	        GlobalConfig.ruleNameList = initRuleNamesList(relevanceRuleGroups);//初始化规则引擎需要用的名字
	        GlobalConfig.drools.initEngine( GlobalConfig.ruleNameList,relevanceRuleGroupManager);
	     
				return SUCCESS; 
			
	}

	/**
	 * <更新规则> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String updaterelevanceRule() {
		log.info("[relevanceRuleAction] enter updaterelevanceRule()");
		relevanceRule.setRelevanceRuleCondition(""); //规则条件字符串，没有用了！
		relevanceRuleManager.updateRelevanceRule(relevanceRule);
		// createXML();
		return SUCCESS;

	}

	/**
	 * <查询详细规则> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectRelevanceRuleById() {
		log.info("[relevanceRuleAction] enter selectFilterRuleById()");
		Map<String, Object> map = new HashMap<String, Object>();
		// 获得当前登录用户所管辖的组的id
		long groupId = ((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getAssetGroupid();
		assetList = this.assetManager.queryCategory();
		Iterator<Entry<String, String>> it = GlobalConfig.eventCategoryTag
				.entrySet().iterator();
		eventCategoryList = new ArrayList<eventcategorytag>();
		// 做一个封装eventcategorytag的list 事件类型
		while (it.hasNext()) {
			Entry<String, String> e = (Entry<String, String>) it.next();
			eventcategorytag eventcategory = new eventcategorytag();
			eventcategory.setEventcategorykey(e.getKey());
			eventcategory.setEventcategoryValue(e.getValue());
			eventCategoryList.add(eventcategory);
		}
		this.eventTypeTagList = new ArrayList<EventTypeTag>();
		Iterator<Entry<Long, String>> itEventType = GlobalConfig.eventTypeTag
				.entrySet().iterator();
		// 做一个封装eventcategorytag的list 事件类别
		while (itEventType.hasNext()) {
			Entry<Long, String> e = (Entry<Long, String>) itEventType.next();
			EventTypeTag eventTypeTag = new EventTypeTag();
			eventTypeTag.setEventtypetagId(e.getKey());
			eventTypeTag.setEventtypetagName(e.getValue());
			this.eventTypeTagList.add(eventTypeTag);
		}
		relevanceRule = relevanceRuleManager.selectRelevanceRuleByid(id);
		//System.out.println(relevanceRule);
		this.priorityArr = new int[5];
		if (!"".equals(relevanceRule.getPriorityIds())) {
			String[] priorityTemp = relevanceRule.getPriorityIds().split(",");
			for (int i = 0; i < priorityTemp.length; i++) {

				this.priorityArr[i] = Integer.parseInt(priorityTemp[i]);
			}
		}
		this.assetListSel = new ArrayList<Map<String, Object>>();
		this.eventCategoryListSel = new ArrayList<Map<String, Object>>();
		this.eventTypeTagListSel = new ArrayList<Map<String, Object>>();
		// relevanceRule = relevanceRuleManager.selectFilterRuleByid(id);
		// alertRule = alertRuleManager.queryByRuleId(ruleId);
		// Map<String, Object> map = new HashMap<String, Object>();

		if (!"".equals(relevanceRule.getEventsCategoryIds())) {

			String[] CcategoryIdsArr = relevanceRule.getEventsCategoryIds()
					.split(",");

			if (CcategoryIdsArr.length != 0) {
				for (int i = 0; i < CcategoryIdsArr.length; i++) {
					eventCategoryListSel.addAll(alertRuleManager
							.queryEventType(CcategoryIdsArr[i]));// 事件类别alertRuleManager.queryDeviceTypeById(definitionIdSt[i])
				}
			}

		}
		if (!"".equals(relevanceRule.getAssetIds())) {
			String[] assetIdsArr = relevanceRule.getAssetIds().split(",");
			if (assetIdsArr.length != 0) {
				for (int i = 0; i < assetIdsArr.length; i++) {
					this.assetListSel.addAll(alertRuleManager
							.queryDeviceTypeById(assetIdsArr[i]));// 设备名称
				}
			}
		}

		if (!"".equals(relevanceRule.getEventsTypeIds())) {
			String[] eventsTypeIdsArr = relevanceRule.getEventsTypeIds().split(
					",");
			if (eventsTypeIdsArr.length != 0) {
				for (int i = 0; i < eventsTypeIdsArr.length; i++) {
					//System.out.println(alertRuleManager.queryAssetTypeById(eventsTypeIdsArr[i]));
					this.eventTypeTagListSel.addAll(alertRuleManager
							.queryAssetTypeById(eventsTypeIdsArr[i]));// 事件类型
				}
			}
		}
		// 用来显示源地址的list
		srouceAddrSel = new ArrayList<String>();
		// 用来显示目标地址的list
		targetAddrSel = new ArrayList<String>();
		// 用来显示目标端口的list
		targetPortSel = new ArrayList<String>();
		// 用来显示源端口的list
		sourcePortSel = new ArrayList<String>();
		// 组建前台用来显示源地址的list
		if (!"".equals(relevanceRule.getSourceAddr())) {
			String[] sourceAddr = relevanceRule.getSourceAddr().split(",");
			for (int i = 0; i < sourceAddr.length; i++) {
				this.srouceAddrSel.add(sourceAddr[i]);
			}
		}
		// 组建前台用来显示目标地址的list
		if (!"".equals(relevanceRule.getTargetAddr())) {
			String[] targetAddr = relevanceRule.getTargetAddr().split(",");
			for (int i = 0; i < targetAddr.length; i++) {
				this.targetAddrSel.add(targetAddr[i]);
			}
		}
		// 组建前台用来显示源端口的list
		if (!"".equals(relevanceRule.getSourcePort())) {
			String[] sourcePort = relevanceRule.getSourcePort().split(",");
			for (int i = 0; i < sourcePort.length; i++) {
				this.sourcePortSel.add(sourcePort[i]);
			}
		}
		// 组建前台用来显示目标端口的list
		if (!"".equals(relevanceRule.getTargetAddr())) {
			String[] targetPort = relevanceRule.getTargetAddr().split(",");
			for (int i = 0; i < targetPort.length; i++) {
				this.targetPortSel.add(targetPort[i]);
			}
		}
		this.groupId = relevanceRule.getGroupId();
		//用来显示源地址的list
		srouceAddrSel = new ArrayList<String>();
		//用来显示目标地址的list
		targetAddrSel= new ArrayList<String>();
		//用来显示目标端口的list
		targetPortSel = new ArrayList<String>();
		//用来显示源端口的list
		sourcePortSel = new ArrayList<String>();
		//组建前台用来显示源地址的list
		if (!"".equals(relevanceRule.getSourceAddr())) {
			String [] sourceAddr = relevanceRule.getSourceAddr().split(",");
			for (int i = 0; i < sourceAddr.length; i++) {
				this.srouceAddrSel.add(sourceAddr[i]);
			}
		}
		//组建前台用来显示目标地址的list
		if (!"".equals(relevanceRule.getTargetAddr())) {
			String [] targetAddr = relevanceRule.getTargetAddr().split(",");
			for (int i = 0; i < targetAddr.length; i++) {
				this.targetAddrSel.add(targetAddr[i]);
			}
		}
		//组建前台用来显示源端口的list
		if (!"".equals(relevanceRule.getSourcePort())) {
			String [] sourcePort = relevanceRule.getSourcePort().split(",");
			for (int i = 0; i < sourcePort.length; i++) {
				this.sourcePortSel.add(sourcePort[i]);
			}
		}
		//组建前台用来显示目标端口的list
		if (!"".equals(relevanceRule.getTargetPort())) {
			String [] targetPort = relevanceRule.getTargetPort().split(",");
			for (int i = 0; i < targetPort.length; i++) {
				this.targetPortSel.add(targetPort[i]);
			}
		}
		
		//关联后事件类别
		this.eventCategoryListSelRe = new ArrayList<Map<String,Object>>();
		eventCategoryListSelRe.addAll( alertRuleManager.queryEventType(relevanceRule.getEventsCategoryIdsRe()+""));
		//关联后事件类型
		this.eventTypeTagListSelRe = new ArrayList<Map<String,Object>>();
		eventTypeTagListSelRe.addAll(alertRuleManager.queryAssetTypeById(relevanceRule.getEventsTypeIdsRe()+""));
		eventCategoryListRe = eventCategoryList;
		eventTypeTagListRe = eventTypeTagList;
		return SUCCESS; 
	}

	/**
	 * <用于更新启用状态> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String updateStatus() {
		if (StringUtil.isNotBlank(ids)) {
			relevanceRuleManager.updateStatus(ids, status);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改自定义关联规则状态");
		}
		// createXML();
		return SUCCESS;
	}

	public RelevanceRuleService getRelevanceRuleManager() {
		return relevanceRuleManager;
	}

	public void setRelevanceRuleManager(
			RelevanceRuleService relevanceRuleManager) {
		this.relevanceRuleManager = relevanceRuleManager;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public RelevanceRule getRelevanceRule() {
		return relevanceRule;
	}

	public void setrelevanceRule(RelevanceRule relevanceRule) {
		this.relevanceRule = relevanceRule;
	}

	public List<RelevanceRule> getList() {
		return list;
	}

	public void setList(List<RelevanceRule> list) {
		this.list = list;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public boolean isFlg() {
		return flg;
	}

	public void setFlg(boolean flg) {
		this.flg = flg;
	}

	public String getrelevanceRuleName() {
		return relevanceRuleName;
	}

	public void setrelevanceRuleName(String relevanceRuleName) {
		this.relevanceRuleName = relevanceRuleName;
	}

	public Integer getrelevanceRuleType() {
		return relevanceRuleType;
	}

	public void setrelevanceRuleType(Integer relevanceRuleType) {
		this.relevanceRuleType = relevanceRuleType;
	}

	public int getrelevanceRuleStatus() {
		return relevanceRuleStatus;
	}

	public void setrelevanceRuleStatus(int relevanceRuleStatus) {
		this.relevanceRuleStatus = relevanceRuleStatus;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public List<Map> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Map> assetList) {
		this.assetList = assetList;
	}

	public List<eventcategorytag> getEventCategoryList() {
		return eventCategoryList;
	}

	public void setEventCategoryList(List<eventcategorytag> eventCategoryList) {
		this.eventCategoryList = eventCategoryList;
	}

	public List<EventTypeTag> getEventTypeTagList() {
		return eventTypeTagList;
	}

	public void setEventTypeTagList(List<EventTypeTag> eventTypeTagList) {
		this.eventTypeTagList = eventTypeTagList;
	}

	public List<Map<String, Object>> getAssetListSel() {
		return assetListSel;
	}

	public void setAssetListSel(List<Map<String, Object>> assetListSel) {
		this.assetListSel = assetListSel;
	}

	public List<Map<String, Object>> getEventCategoryListSel() {
		return eventCategoryListSel;
	}

	public void setEventCategoryListSel(
			List<Map<String, Object>> eventCategoryListSel) {
		this.eventCategoryListSel = eventCategoryListSel;
	}

	public List<Map<String, Object>> getEventTypeTagListSel() {
		return eventTypeTagListSel;
	}

	public void setEventTypeTagListSel(
			List<Map<String, Object>> eventTypeTagListSel) {
		this.eventTypeTagListSel = eventTypeTagListSel;
	}

	public int[] getPriorityArr() {
		return priorityArr;
	}

	public void setPriorityArr(int[] priorityArr) {
		this.priorityArr = priorityArr;
	}

	public AlertRuleService getAlertRuleManager() {
		return alertRuleManager;
	}

	public void setAlertRuleManager(AlertRuleService alertRuleManager) {
		this.alertRuleManager = alertRuleManager;
	}

	public List<String> getSrouceAddrSel() {
		return srouceAddrSel;
	}

	public void setSrouceAddrSel(List<String> srouceAddrSel) {
		this.srouceAddrSel = srouceAddrSel;
	}

	public List<String> getTargetAddrSel() {
		return targetAddrSel;
	}

	public void setTargetAddrSel(List<String> targetAddrSel) {
		this.targetAddrSel = targetAddrSel;
	}

	public List<String> getTargetPortSel() {
		return targetPortSel;
	}

	public void setTargetPortSel(List<String> targetPortSel) {
		this.targetPortSel = targetPortSel;
	}

	public List<String> getSourcePortSel() {
		return sourcePortSel;
	}

	public void setSourcePortSel(List<String> sourcePortSel) {
		this.sourcePortSel = sourcePortSel;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}

	private String getIDs(String[] strs) {
		StringBuffer sbf = new StringBuffer();
		int falg = 0;
		for (String str : strs) {
			if (str.equals("on")) {

			} else {
				if (falg == 0) {
					sbf.append(str);
				} else {
					sbf.append("," + str);
				}
				falg++;
			}

		}
		return sbf.toString();
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

    public RelevanceRuleGroupService getRelevanceRuleGroupManager() {
		return relevanceRuleGroupManager;
	}

	public void setRelevanceRuleGroupManager(
			RelevanceRuleGroupService relevanceRuleGroupManager) {
		this.relevanceRuleGroupManager = relevanceRuleGroupManager;
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

	public void setRelevanceRule(RelevanceRule relevanceRule) {
		this.relevanceRule = relevanceRule;
	}

	public List<eventcategorytag> getEventCategoryListRe() {
		return eventCategoryListRe;
	}

	public void setEventCategoryListRe(List<eventcategorytag> eventCategoryListRe) {
		this.eventCategoryListRe = eventCategoryListRe;
	}

	public List<EventTypeTag> getEventTypeTagListRe() {
		return eventTypeTagListRe;
	}

	public void setEventTypeTagListRe(List<EventTypeTag> eventTypeTagListRe) {
		this.eventTypeTagListRe = eventTypeTagListRe;
	}

	public List<Map<String, Object>> getEventCategoryListSelRe() {
		return eventCategoryListSelRe;
	}

	public void setEventCategoryListSelRe(
			List<Map<String, Object>> eventCategoryListSelRe) {
		this.eventCategoryListSelRe = eventCategoryListSelRe;
	}

	public List<Map<String, Object>> getEventTypeTagListSelRe() {
		return eventTypeTagListSelRe;
	}

	public void setEventTypeTagListSelRe(
			List<Map<String, Object>> eventTypeTagListSelRe) {
		this.eventTypeTagListSelRe = eventTypeTagListSelRe;
	}


}
