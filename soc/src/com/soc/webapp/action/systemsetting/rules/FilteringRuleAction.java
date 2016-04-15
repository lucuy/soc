package com.soc.webapp.action.systemsetting.rules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.eventtypetag.EventTypeTag;
import com.soc.model.eventtypetag.eventcategorytag;
import com.soc.model.systemsetting.rules.FilteringRule;
import com.soc.model.user.User;
import com.soc.service.alert.AlertRuleService;
import com.soc.service.asset.AssetGroupService;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.systemsetting.rules.FilteringRuleService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.FileruleCreateFile;
import com.util.FreeMarkerUtil;
import com.util.StringUtil;
import com.util.page.Page;
import com.util.page.SearchResult;

public class FilteringRuleAction extends BaseAction {

	// FilteringRuleServic类
	private FilteringRuleService filteringRuleManager;
	// 前台传过来的id 用于显示详细信息
	private long id;
	private String filteringRuleName;
	private Integer filteringRuleType;
	private int filteringRuleStatus;

	// 传过来的字符串数组 用来批量删除
	private String ids;
	// 用来批量修改启用状态
	private int status;
	// 用来显示详细信息实体封装类
	private FilteringRule filteringRule;

	// 显示列表的liset
	private List<FilteringRule> list;

	// 用于查询的关键字
	private String keyword;

	// 后台验证的结果标签
	private boolean flg;
	// 资产的业务处理类
	private AssetService assetManager;
	// 资产组业务处理类
	private AssetGroupService assetGroupManager;
	//审计业务处理类
	private AuditService auditManager;
	//前台显示的设备列表
	private List<Asset> assetList;
	//前台显示的事件类别列表
	private List<eventcategorytag> eventCategoryList;
	//前台显示的事件类型列表
	private List<EventTypeTag> eventTypeTagList;
	//前台显示的设备select框
	private List<Map<String, Object>> assetListSel;
	//前台显示的事件类别select框
	private List<Map<String, Object>> eventCategoryListSel;
	//前台显示的事件类型select框
	private List<Map<String, Object>> eventTypeTagListSel;
	//放等级的String []
	private int[] priorityArr;
	// 告警规则服务管理类
	private AlertRuleService alertRuleManager;
	//用来显示源地址的list
	private List<String> srouceAddrSel;
	//用来显示目标地址的list
	private List<String> targetAddrSel;
	//用来显示目标端口的list
	private List<String> targetPortSel;
	//用来显示源端口的list
	private List<String> sourcePortSel;

	/**
	 * <跳转到添加界面> <这里需要查询资产>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String toInsertFilterPage() {
		log.info("[FilteringRuleAction] enter toInsertFilterPage()");
		Map<String, Object> map = new HashMap<String, Object>();
		//获得当前登录用户所管辖的组的id
		long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid(); 
      assetList = this.assetManager.queryAllAssetByUserId(groupId);
      Iterator<Entry<String, String>> it = GlobalConfig.eventCategoryTag.entrySet().iterator();
      eventCategoryList = new ArrayList<eventcategorytag>();
      //做一个封装eventcategorytag的list 事件类型
      while (it.hasNext()) {  
         Entry<String, String> e = (Entry<String, String>) it.next();  
         eventcategorytag eventcategory = new eventcategorytag();
         eventcategory.setEventcategorykey(e.getKey());
         eventcategory.setEventcategoryValue(e.getValue());
         eventCategoryList.add(eventcategory);
      } 
      this.eventTypeTagList = new ArrayList<EventTypeTag>();
      Iterator<Entry<Long, String>> itEventType = GlobalConfig.eventTypeTag.entrySet().iterator();
    //做一个封装eventcategorytag的list		事件类别
      while (itEventType.hasNext()) {  
          Entry<Long, String> e = (Entry<Long, String>) itEventType.next();  
          EventTypeTag eventTypeTag = new EventTypeTag();
          eventTypeTag.setEventtypetagId(e.getKey());
          eventTypeTag.setEventtypetagName(e.getValue());
          this.eventTypeTagList.add(eventTypeTag);
       } 
		return SUCCESS;
	}

	/**
	 * <显示列表> <有分页功能>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryFilterRuleList() {
		log.info("[FilteringRuleAction] enter queryFilterRuleList()");
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
		SearchResult<FilteringRule> sr = null;

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
		if (request.getParameter("keyword") != null) {
			try {

				keyword = java.net.URLDecoder.decode(keyword, "UTF-8");

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			map.put("keyword", keyword);
		}
		if (StringUtil.isNotBlank(filteringRuleName)) {
			map.put("filteringRuleName", filteringRuleName);
		}
		if (filteringRuleType != null) {

			map.put("filteringRuleType", filteringRuleType);
		}

		sr = filteringRuleManager.queryFilterRuleList(map, page);
		if (sr != null) {
			list = sr.getList();
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
	public String deleteFilterRule() {
		log.info("[FilteringRuleAction] enter deleteFilterRule()");
		filteringRuleManager.deleteFilterRule(ids);
		
		//写入审计日志
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("删除过滤规则"+"("+"删除过滤规则"+")");
		auditManager.insertByDeleteOperator(((User) this.getSession()
            .getAttribute("SOC_LOGON_USER")).getUserId(), "过滤规则", super
            .getRequest().getRemoteAddr(), fieldList);
        
        //syslog
       /* String logString = "";
        
        logString =
            "登录名:" + ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserLoginName() + " 源IP:"
                + getRequest().getRemoteAddr() + " 操作时间:" + DateUtil.curDateTimeStr19() + " 操作类型 :删除过滤规则状态";
        
        logManager.writeSystemAuditLog(logString);*/
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "删除过滤规则");
		
		createXML();
		return SUCCESS;
	}

	/**
	 * <插入规则> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String insertOrUpdateFilterRule() {
		log.info("[FilteringRuleAction] enter insertOrUpdateFilterRule()");
		//将放放等级的数组解析成1,2,3的形式
		String priorityTemp = "";
		if (priorityArr!=null) {
			for (int i = 0; i < this.priorityArr.length; i++) {
				if (i==0) {
					priorityTemp = priorityTemp+priorityArr[i];
				}else {
					priorityTemp = priorityTemp+","+priorityArr[i];
				}
			}
		}
		
		
		
		filteringRule.setAssetIds(this.getIDs(filteringRule.getAssetIds().split(",")));
		filteringRule.setEventsTypeIds(this.getIDs(filteringRule.getEventsTypeIds().split(",")));
		filteringRule.setPriorityIds(priorityTemp);
		filteringRule.setEventsCategoryIds(this.getIDs(filteringRule.getEventsCategoryIds().split(",")));
	
		if (filteringRule.getFilteringRuleId()==0) {	
			//如果传过来的id是0 说明这是插入
		     String userName = (String) super.getSession().getAttribute("userinfo");
		     filteringRule.setFilteringRuleCreater(userName);//当前登录的用户名
			filteringRule.setFilteringRuleCreateDate(new Date());//放创建时间
			filteringRule.setFilteringRuleMenderDate(new Date());//修改时间
			filteringRuleManager.insertFilterRule(filteringRule);
			
			//添加审计
			List<String> fieldList = new ArrayList<String>();

			fieldList.add(filteringRule.getFilteringRuleName() + "(" + filteringRule.getFilteringRuleName() + ")");
			auditManager.insertByInsertOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "过滤规则", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "新增过滤规则");
		}else {
			String userName = (String) super.getSession().getAttribute("userinfo");
		     filteringRule.setFilteringRuleMender(userName);//当前登录的用户名
			filteringRule.setFilteringRuleMenderDate(new Date());//修改时间
			filteringRuleManager.updateFilterRule(filteringRule);		
			
			//添加审计
			List<String> fieldList = new ArrayList<String>();

			fieldList.add(filteringRule.getFilteringRuleName() + "(" + filteringRule.getFilteringRuleName() + ")");
			auditManager.insertByUpdateOperator(((User) this.getSession()
					.getAttribute("SOC_LOGON_USER")).getUserId(), "过滤规则", super
					.getRequest().getRemoteAddr(), fieldList);
			logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
					.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改过滤规则");
		}
		createXML();
	return SUCCESS;

	}

	/**
	 * <更新规则> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String updateFilteringRule() {
		log.info("[FilteringRuleAction] enter updateFilteringRule()");
		filteringRuleManager.updateFilterRule(filteringRule);
		
		//添加审计
		List<String> fieldList = new ArrayList<String>();

		fieldList.add(filteringRule.getFilteringRuleName() + "(" + filteringRule.getFilteringRuleName() + ")");
		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "过滤规则", super
				.getRequest().getRemoteAddr(), fieldList);
		
		createXML();
		return SUCCESS;

	}

	/**
	 * <查询详细规则> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String selectFilterRuleById() {
		log.info("[FilteringRuleAction] enter selectFilterRuleById()");
		Map<String, Object> map = new HashMap<String, Object>();
		//获得当前登录用户所管辖的组的id
	long groupId =  ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid(); 
	assetList = this.assetManager.queryAllAssetByUserId(groupId);
      Iterator<Entry<String, String>> it = GlobalConfig.eventCategoryTag.entrySet().iterator();
      eventCategoryList = new ArrayList<eventcategorytag>();
      //做一个封装eventcategorytag的list 事件类型
      while (it.hasNext()) {  
         Entry<String, String> e = (Entry<String, String>) it.next();  
         eventcategorytag eventcategory = new eventcategorytag();
         eventcategory.setEventcategorykey(e.getKey());
         eventcategory.setEventcategoryValue(e.getValue());
         eventCategoryList.add(eventcategory);
      } 
      this.eventTypeTagList = new ArrayList<EventTypeTag>();
      Iterator<Entry<Long, String>> itEventType = GlobalConfig.eventTypeTag.entrySet().iterator();
    //做一个封装eventcategorytag的list		事件类别
      while (itEventType.hasNext()) {  
          Entry<Long, String> e = (Entry<Long, String>) itEventType.next();  
          EventTypeTag eventTypeTag = new EventTypeTag();
          eventTypeTag.setEventtypetagId(e.getKey());
          eventTypeTag.setEventtypetagName(e.getValue());
          this.eventTypeTagList.add(eventTypeTag);
       } 
  	filteringRule = filteringRuleManager.selectFilterRuleByid(id);
  	//System.out.println(filteringRule);
		this.priorityArr = new int[5];
	if (!"".equals(filteringRule.getPriorityIds())) {
		String [] priorityTemp = filteringRule.getPriorityIds().split(",");
		for (int i = 0; i < priorityTemp.length; i++) {
		    
			this.priorityArr[i] = Integer.parseInt(priorityTemp[i]);
		}
	}
	this.assetListSel = new ArrayList<Map<String, Object>>();
	this.eventCategoryListSel = new ArrayList<Map<String, Object>>();
	this.eventTypeTagListSel = new ArrayList<Map<String, Object>>();
	//filteringRule = filteringRuleManager.selectFilterRuleByid(id);
		//alertRule = alertRuleManager.queryByRuleId(ruleId);
	//Map<String, Object> map = new HashMap<String, Object>();

	if(!"".equals(filteringRule.getEventsCategoryIds())){

String[] CcategoryIdsArr = filteringRule.getEventsCategoryIds().split(",");

if (CcategoryIdsArr.length!=0) {
	for(int i = 0 ; i < CcategoryIdsArr.length;i++){    
		eventCategoryListSel.addAll(alertRuleManager.queryEventType(CcategoryIdsArr[i]));//事件类别alertRuleManager.queryDeviceTypeById(definitionIdSt[i])
	}			
}


}
if(!"".equals(filteringRule.getAssetIds())){
String[] assetIdsArr=filteringRule.getAssetIds().split(",");
if (assetIdsArr.length!=0) {
	for(int i = 0 ; i<assetIdsArr.length;i++){	    
		this.assetListSel.addAll(alertRuleManager.queryDeviceByNameById(assetIdsArr[i]));//设备名称
	}
}
}

if(!"".equals(filteringRule.getEventsTypeIds())){
String[] eventsTypeIdsArr=filteringRule.getEventsTypeIds().split(",");
if (eventsTypeIdsArr.length!=0) {
	for(int i = 0;i<eventsTypeIdsArr.length;i++){
		//System.out.println(alertRuleManager.queryAssetTypeById(eventsTypeIdsArr[i]));
		this.eventTypeTagListSel.addAll(alertRuleManager.queryAssetTypeById(eventsTypeIdsArr[i]));//事件类型		
	}
}
}
//用来显示源地址的list
srouceAddrSel = new ArrayList<String>();
//用来显示目标地址的list
targetAddrSel= new ArrayList<String>();
//用来显示目标端口的list
targetPortSel = new ArrayList<String>();
//用来显示源端口的list
sourcePortSel = new ArrayList<String>();
//组建前台用来显示源地址的list
if (!"".equals(filteringRule.getSourceAddr())) {
	String [] sourceAddr = filteringRule.getSourceAddr().split(",");
	for (int i = 0; i < sourceAddr.length; i++) {
		this.srouceAddrSel.add(sourceAddr[i]);
	}
}
//组建前台用来显示目标地址的list
if (!"".equals(filteringRule.getTargetAddr())) {
	String [] targetAddr = filteringRule.getTargetAddr().split(",");
	for (int i = 0; i < targetAddr.length; i++) {
		this.targetAddrSel.add(targetAddr[i]);
	}
}
//组建前台用来显示源端口的list
if (!"".equals(filteringRule.getSourcePort())) {
	String [] sourcePort = filteringRule.getSourcePort().split(",");
	for (int i = 0; i < sourcePort.length; i++) {
		this.sourcePortSel.add(sourcePort[i]);
	}
}
//组建前台用来显示目标端口的list
if (!"".equals(filteringRule.getTargetPort())) {
	String [] targetPort = filteringRule.getTargetPort().split(",");
	for (int i = 0; i < targetPort.length; i++) {
		this.targetPortSel.add(targetPort[i]);
	}
}
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
			filteringRuleManager.updateStatus(ids, status);
		}
		
		createXML();
		
		//添加审计
		List<String> fieldList = new ArrayList<String>();

		fieldList.add("修改过滤规则状态" + "(" + "修改过滤规则状态" + ")");
		
		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "过滤规则", super
				.getRequest().getRemoteAddr(), fieldList);
		logManager.writeSystemAuditLog(((User) this.getSession().getAttribute("SOC_LOGON_USER"))
				.getUserLoginName(), getRequest().getRemoteAddr(), DateUtil.curDateTimeStr19(), "修改过滤规则状态");
		return SUCCESS;
	}

	public FilteringRuleService getFilteringRuleManager() {
		return filteringRuleManager;
	}

	public void setFilteringRuleManager(
			FilteringRuleService filteringRuleManager) {
		this.filteringRuleManager = filteringRuleManager;
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

	public FilteringRule getFilteringRule() {
		return filteringRule;
	}

	public void setFilteringRule(FilteringRule filteringRule) {
		this.filteringRule = filteringRule;
	}

	public List<FilteringRule> getList() {
		return list;
	}

	public void setList(List<FilteringRule> list) {
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

	public String getFilteringRuleName() {
		return filteringRuleName;
	}

	public void setFilteringRuleName(String filteringRuleName) {
		this.filteringRuleName = filteringRuleName;
	}

	public Integer getFilteringRuleType() {
		return filteringRuleType;
	}

	public void setFilteringRuleType(Integer filteringRuleType) {
		this.filteringRuleType = filteringRuleType;
	}

	public int getFilteringRuleStatus() {
		return filteringRuleStatus;
	}

	public void setFilteringRuleStatus(int filteringRuleStatus) {
		this.filteringRuleStatus = filteringRuleStatus;
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

	public List<Asset> getAssetList() {
		return assetList;
	}

	public void setAssetList(List<Asset> assetList) {
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

	public void setEventTypeTagListSel(List<Map<String, Object>> eventTypeTagListSel) {
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

	private void createXML(){
		
		Writer out = null;
		String path = getServletContext().getRealPath(File.separator);
		String newPath = "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/";
		FreeMarkerUtil fm = new FreeMarkerUtil();
		Map map = new HashMap();
		List<FilteringRule> list = this.filteringRuleManager.queryFilterByType();
		map.put("list",list);
		
		try {
			
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(newPath
							+ "filterRuleXML.xml")),"utf-8"));
		// 这里要判断	
		   fm.loadTemplate("filterRuleXML.ftl",map, "template", out);// 生成文件的代码
		   FileruleCreateFile.createFile();
		   String path1="/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/";
		   String versionpath = "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/soc.version";
		   
		   FileruleCreateFile.modifinyConfigFile(versionpath, path1);
		   
		   out.close();
		   
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			/*try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	}

	public AssetGroupService getAssetGroupManager() {
		return assetGroupManager;
	}

	public void setAssetGroupManager(AssetGroupService assetGroupManager) {
		this.assetGroupManager = assetGroupManager;
	}
	
	
	private String getIDs(String [] strs){
		StringBuffer sbf = new StringBuffer();
		int falg=0;
		
		for (String str : strs) {
			if(str.equals("on")){
				
			}else{
			if(falg==0){
				sbf.append(str);
			}else{
				sbf.append(","+str);
			}
              falg++;
			}
			
		}
		return sbf.toString();
	}
	
}







