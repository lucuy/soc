/*
 * 文 件 名:  HomeAction.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-12-6
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.webapp.action.home;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.home.HomeDiv;
import com.soc.model.home.HomePageDiv;
import com.soc.model.systemsetting.Collector;
import com.soc.model.user.User;
import com.soc.service.alert.AlertMessageSerive;
import com.soc.service.asset.AssetService;
import com.soc.service.audit.AuditService;
import com.soc.service.events.EventsService;
import com.soc.service.events.QueryEventsService;
import com.soc.service.homepagediv.HomePageDivService;
import com.soc.service.monitor.CustomTrendService;
import com.soc.service.monitor.MonitorAlarmService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.service.user.UserService;
import com.soc.webapp.action.BaseAction;
import com.util.DateUtil;
import com.util.StringUtil;
import com.util.page.Page;

/**
 * <一句话功能简述> <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-12-6]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HomeAction extends BaseAction {

	// 告警信息列表
	private List<AlertMessage> alertMessageList;
	private AssetService assetManager;
	// 告警业务处理类
	private AlertMessageSerive alertMessageManager;
	private CustomTrendService customTrendManager;
	private List<Map> alertMessageAsset;
	private String chartsBuffx;//上周事件趋势X轴数据
	private String chartsBuff;//数据
	
	private JSONArray jsonArray;

	private JSONArray jsonAsset;

	private MonitorAlarmService monitorAlarmManager ;  //APM告警信息监控类
	
	// 事件按照级别分组统计
	private JSONArray jsonEvents;

	public JSONArray getJsonEvents() {
		return jsonEvents;
	}

	public void setJsonEvents(JSONArray jsonEvents) {
		this.jsonEvents = jsonEvents;
	}

	// 前台传递的时间
	private long timeHour;

	// 采集器列表
	private List<Collector> collectorList;

	// 采集器业务处理类
	private SettingCollectorService settingCollectorManager;

	// 人员类型
	private String personType;

	// 标识
	private String flag;

	private QueryEventsService queryEeventsManager; // 事件管理业务对象
	// 用来存储div是否的字符串
	private String homePageDiv;
	// 用来存储div是否的字符串的服务类
	private HomePageDivService homePageDivManager;
	// 用来存储div是的坐标
	private String homePageDivXY;
	//事件业务处理类
	private EventsService eventsManager;
	//前台拼接js代码的initpage
	private String initPage;
	//前台选择多少列
	private int layout;
	// 用户服务管理类
	private UserService userManager;
	 // 审计业务管理类
	 private AuditService auditManager;
	 private List<HomePageDiv> divs;

	 private String jsonAssetResult;//最近7天告警最多的10个资产
	 private String eventResult1;//最近7天的告警类型分布
	 private String hourEventResut;//最近一小时告警类型分布
	 private String dayEventResut;//最近24小时告警类型分布
	 private String hourAssetResult;//最近一小时告警数量最多的10个资产
	 private String dayAssetResult;//最近24小时告警数量最多的10个资产
	 private String aepetitionMessage;//24小时内重复最多的告警
	 private String eventToday;//当天事件统计
	 private String trendAnalysis;//近期事件趋势
	 private String eventsDate;//当天的事件类型分布
	 private String ipData;//当天事件IP分布
	 private Map mapCon;//放条件的map
	 private List<Map<String,Object>> monitorAlarmList ;  
	 private String queryFlag ;  //前台传递过来的是否是10条告警信息查找
	/**
	 * <显示首页信息表> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String showHomeInfo() {
		log.info("[HomeAction] enter method showHomeInfo....");
		mapCon = new HashMap();
		//根据用户id查出这个用户的div显示情况
		long userId = ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId();
		this.divs = homePageDivManager.queryHomePageDiv(userId);
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetIds =  assetIds= assetManager.queryIDSByUser(groupid);
 		
 		mapCon.put("alertAssetId", assetIds);
		String homePageDivIds ="";//定义一个字符串,拼接出显示的div的id串然后判断id串是不是包含显示的div的id 然后调用对应的java方法
		//根据id调用java方法
		for (HomePageDiv div : divs) {
			homePageDivIds = homePageDivIds + div.getId();
		}
		if (homePageDivIds.contains("d1")) {
			eventToday = this.dayEventStats(assetIds);
		}
		if (homePageDivIds.contains("d2")) {
			//collectorList = theCurrentCollectorStatistics();
		}
		if (homePageDivIds.contains("d3")) {
			//修改--原来初始化的时候personType是null，数据不对，现在根据类型给定参数----李长红-20140403
			eventResult1 = lastTimeDaysAlarmTypeDistribution("3");
		}
		if (homePageDivIds.contains("d4")) {
			//修改--原来初始化的时候personType是null，数据不对，现在根据类型给定参数----李长红-20140403
			jsonAssetResult  = lastTimeDaysUpTo10AlarmsAssets("3");
		}
		/*if (homePageDivIds.contains("d5")) {
			recentlyAcertainTimeOfTheLatestNAlarms(personType);
		}*/
		if (homePageDivIds.contains("d6")) {
			//修改--原来初始化的时候personType是null，数据不对，现在根据类型给定参数----李长红-20140403
			hourEventResut = lastTimeDaysAlarmTypeDistribution("1");
		}
		if (homePageDivIds.contains("d7")) {
			//修改--原来初始化的时候personType是null，数据不对，现在根据类型给定参数----李长红-20140403
			dayEventResut = lastTimeDaysAlarmTypeDistribution("2");
		}
		if (homePageDivIds.contains("d8")) {
			//修改--原来初始化的时候personType是null，数据不对，现在根据类型给定参数----李长红-20140403
			hourAssetResult = lastTimeDaysUpTo10AlarmsAssets("1");
		}
		if (homePageDivIds.contains("d9")) {
			//修改--原来初始化的时候personType是null，数据不对，现在根据类型给定参数----李长红-20140403
			dayAssetResult = lastTimeDaysUpTo10AlarmsAssets("2");
		}
		if (homePageDivIds.contains("d10")) {
			aepetitionMessage = repeatUpTo10AlarmsWithin24hours();
		}
	/*	if (homePageDivIds.contains("d11")) {
			
		}
		if (homePageDivIds.contains("d12")) {
			
		}*/
		if (homePageDivIds.contains("d13")) {
			trendAnalysis = trendOfRecentEvents();
		}
		if (homePageDivIds.contains("d14")) {
			eventsDate = theDayOfTheEventTypeDistribution();
		}
		if (homePageDivIds.contains("d15")) {
			ipData = theDayOfIPDistribution();
		}
		if(homePageDivIds.contains("d16")){
		 	//queryTemAlarmMessage() ; 
		} 
		if(homePageDivIds.contains("d17")){
		 	String result = customTrendManager.queryLastWeekcountEvents(assetIds);
		 	chartsBuff=result.split("_")[1];
		 	chartsBuffx=result.split("_")[0];
		} 

		StringBuffer sb = new StringBuffer();
		sb.append("function initPage(){var userWidth = userAgent();if(userWidth = 'MSIE'){userWidth = document.body.clientWidth;}else{userWidth = document.body.clientWidth;}$('#portletdemo').portlet({sortable: true,create: function() {},removeItem: function() {},events: {drag: {stop: function(event, ui) {savePosition();");
		for (HomePageDiv div : divs) {
			sb.append(HomeDiv.mapJs.get(div.getId()));		
		}
			sb.append("}}},columns: [");
		layout  = this.userManager.queryUserLayoutByUserId(userId); 
		//根据layout的个数 拼接行数
		for (int i = 0; i < layout; i++) {
			sb.append("{ width: userWidth/"+layout);
			switch (layout) {
			case 1:
				sb.append("-17");
				break;
			case 2:
				sb.append("-9");
				break;
			case 3:
				sb.append("-6");
				break;
			case 4:
				sb.append("-5");
				break;
			}
			sb.append(", portlets: [  "); 
			//拼接横坐标
			for (HomePageDiv div : this.divs) {
			if (div.getX()==i) {
				sb.append(div.getDivContext()).append(",");
			}
		}
			//这里多了一个,
			sb.delete(sb.length()-1, sb.length());
			 //initPage =  initPage.substring(0, initPage.length()-1);
			 //然后拼接这个columns的结束  再加上一个   ,    后期修改为Stringbuffer吧
			sb.append("]},"); 
		}
		sb.delete(sb.length()-1, sb.length());
		 //initPage =  initPage.substring(0, initPage.length()-1);
		sb.append("]});}");
		initPage = sb.toString();
		return SUCCESS;
	}
	/**
	 * <查询首页div显示情况的字符串> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryHomePageDiv() {
		log.info("[HomeAction] enter method queryHomePageDiv....");
		long userId = ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId();
		this.divs = homePageDivManager.queryHomePageDiv(userId);
		
		return SUCCESS;
	}
	/**
	 * <查询最新的xx条告警信息> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public String queryAlertMessageList() {
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetIds = null;
 		if(groupid==1){
 			}else{
 			 assetIds= assetManager.queryIDSByUser(groupid);
 			if(assetIds.equals("")){
 				assetIds="0";
 				}
 			}
			alertMessageList = this.recentlyAcertainTimeOfTheLatestNAlarms(personType,assetIds);
			if(StringUtil.isNotBlank(queryFlag)){
				HttpServletRequest request = super.getRequest();
				request.setAttribute("alertList", alertMessageList);
				request.setAttribute("Page", new Page(null,10,Page.DEFAULT_PAGE_SIZE,0));
				return "QueryTenAlarm" ; 
			}
		return SUCCESS;
	}
	/**
	 * <查询所有已经组测的采集器> <功能详细描述> 
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */

	public String queryCollectorList() {
		log.info("[HomeAction] enter method queryCollectorList....");
		// 查询所有已经组测的采集器
				Map map = new HashMap();
				long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
				String collectorId = null;
		 		if(groupid==1){
		 			}else{
		 				collectorId= assetManager.queryCollectorByUser(groupid);
		 			
		 			}
		 		map.put("collectorId", collectorId);
				collectorList = settingCollectorManager.queryCollectors(map);
		return SUCCESS;
	}
/**
 * <保存位置的方法>
 * <功能详细描述>
 * @see [类、类#方法、类#成员]
 */
	public void savePosition(){
		log.info("[HomeAction] enter method savePosition....");
		String [] homePageDivArr = this.homePageDiv.split("\\|");
		String [] homePageDivXYArr = this.homePageDivXY.split("\\|");
		Map map = new HashMap();
		map.put("status", 1);
		for (int i = 0; i < homePageDivXYArr.length; i++) {
			map.put("id", homePageDivArr[i]);
			map.put("x", Integer.parseInt(homePageDivXYArr[i].split(",")[0]));
			map.put("y", Integer.parseInt(homePageDivXYArr[i].split(",")[1]));
			map.put("userId", ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId());
			homePageDivManager.updateHomePageDiv(map);
		}
	}
/**
 * <一句话功能简述>
 * <功能详细描述>
 * @return
 * @see [类、类#方法、类#成员]
 */
	public String saveHomePageDiv() {
		log.info("[HomeAction] enter method saveHomePageDiv....");
		long userId = ((User)this.getSession().getAttribute("SOC_LOGON_USER")).getUserId();
		homePageDivManager.saveHomePageDiv(homePageDiv,userId);
		//根据用户的id保存列数
		Map map =new HashMap();
		map.put("userId", userId);
		map.put("layout", layout);
		//目前有个问题 如果列数不变的话 div的位置会变化 这里判断一下如果layout的没有变 div.x用以前的数
		int layoutDatabase = this.userManager.queryUserLayoutByUserId(userId);
		this.userManager.updateLayoutByUserId(map);
		//改不了布局后要改变一下x的值,
		/**
		 * 1 先按照userId吧所有的div取出来
		 * 2 修改x的值
		 * 3 放进去
		 */
		this.divs = homePageDivManager.queryHomePageDiv(userId);
		for (int i = 0; i < divs.size(); i++) {
			map.clear();
			map.put("id",divs.get(i).getId());
			if (layout == layoutDatabase) {
				map.put("x", divs.get(i).getX());
			}else {
				map.put("x", i%layout);
			}
			map.put("y", divs.get(i).getY()%layout);
			map.put("userId", userId);
			map.put("status", divs.get(i).getStatus());
			homePageDivManager.updateHomePageDiv(map);
		}
		/*for (HomePageDiv div : divs) {
			
		}*/
		
		List<String> fieldList = new ArrayList<String>();
		fieldList.add("首页布局" + "( 首页布局 )");
        // 审计日志
		auditManager.insertByUpdateOperator(((User) this.getSession()
				.getAttribute("SOC_LOGON_USER")).getUserId(), "首页布局", super
				.getRequest().getRemoteAddr(), fieldList);
		
		return SUCCESS;
	}
	
	//关闭浏览器窗口时候调用的方法
	public void test()
	{
		HttpSession session = super.getSession();
		
		session.invalidate();
	}

	public List<AlertMessage> getAlertMessageList() {
		return alertMessageList;
	}

	public void setAlertMessageList(List<AlertMessage> alertMessageList) {
		this.alertMessageList = alertMessageList;
	}

	public AlertMessageSerive getAlertMessageManager() {
		return alertMessageManager;
	}

	public void setAlertMessageManager(AlertMessageSerive alertMessageManager) {
		this.alertMessageManager = alertMessageManager;
	}

	public long getTimeHour() {
		return timeHour;
	}

	public void setTimeHour(long timeHour) {
		this.timeHour = timeHour;
	}

	public JSONArray getJsonArray() {
		return jsonArray;
	}

	public void setJsonArray(JSONArray jsonArray) {
		this.jsonArray = jsonArray;
	}

	public List<Collector> getCollectorList() {
		return collectorList;
	}

	public void setCollectorList(List<Collector> collectorList) {
		this.collectorList = collectorList;
	}

	public SettingCollectorService getSettingCollectorManager() {
		return settingCollectorManager;
	}

	public void setSettingCollectorManager(
			SettingCollectorService settingCollectorManager) {
		this.settingCollectorManager = settingCollectorManager;
	}

	public List<Map> getAlertMessageAsset() {
		return alertMessageAsset;
	}

	public void setAlertMessageAsset(List<Map> alertMessageAsset) {
		this.alertMessageAsset = alertMessageAsset;
	}

	public JSONArray getJsonAsset() {
		return jsonAsset;
	}

	public void setJsonAsset(JSONArray jsonAsset) {
		this.jsonAsset = jsonAsset;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public QueryEventsService getQueryEeventsManager() {
		return queryEeventsManager;
	}

	public void setQueryEeventsManager(QueryEventsService queryEeventsManager) {
		this.queryEeventsManager = queryEeventsManager;
	}

	public String getHomePageDiv() {
		return homePageDiv;
	}

	public void setHomePageDiv(String homePageDiv) {
		this.homePageDiv = homePageDiv;
	}

	public HomePageDivService getHomePageDivManager() {
		return homePageDivManager;
	}

	public void setHomePageDivManager(HomePageDivService homePageDivManager) {
		this.homePageDivManager = homePageDivManager;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}

	public String getEventResult1() {
		return eventResult1;
	}

	public void setEventResult1(String eventResult1) {
		this.eventResult1 = eventResult1;
	}

	public String getEventToday() {
		return eventToday;
	}

	public void setEventToday(String eventToday) {
		this.eventToday = eventToday;
	}

	public AuditService getAuditManager() {
		return auditManager;
	}

	public void setAuditManager(AuditService auditManager) {
		this.auditManager = auditManager;
	}

	public String getHourEventResut() {
		return hourEventResut;
	}

	public void setHourEventResut(String hourEventResut) {
		this.hourEventResut = hourEventResut;
	}

	public String getDayEventResut() {
		return dayEventResut;
	}

	public void setDayEventResut(String dayEventResut) {
		this.dayEventResut = dayEventResut;
	}

	public String getHourAssetResult() {
		return hourAssetResult;
	}

	public void setHourAssetResult(String hourAssetResult) {
		this.hourAssetResult = hourAssetResult;
	}

	public String getDayAssetResult() {
		return dayAssetResult;
	}

	public void setDayAssetResult(String dayAssetResult) {
		this.dayAssetResult = dayAssetResult;
	}

	public String getAepetitionMessage() {
		return aepetitionMessage;
	}

	public void setAepetitionMessage(String aepetitionMessage) {
		this.aepetitionMessage = aepetitionMessage;
	}

	public List<HomePageDiv> getDivs() {
		return divs;
	}

	public void setDivs(List<HomePageDiv> divs) {
		this.divs = divs;
	}

	public String getHomePageDivXY() {
		return homePageDivXY;
	}

	public void setHomePageDivXY(String homePageDivXY) {
		this.homePageDivXY = homePageDivXY;
	}

	public String getInitPage() {
		return initPage;
	}

	public void setInitPage(String initPage) {
		this.initPage = initPage;
	}

	public String getTrendAnalysis() {
		return trendAnalysis;
	}

	public void setTrendAnalysis(String trendAnalysis) {
		this.trendAnalysis = trendAnalysis;
	}

	public String getJsonAssetResult() {
		return jsonAssetResult;
	}

	public void setJsonAssetResult(String jsonAssetResult) {
		this.jsonAssetResult = jsonAssetResult;
	}

	public String getEventsDate() {
		return eventsDate;
	}

	public void setEventsDate(String eventsDate) {
		this.eventsDate = eventsDate;
	}

	

	public int getLayout() {
		return layout;
	}

	public void setLayout(int layout) {
		this.layout = layout;
	}

	public UserService getUserManager() {
		return userManager;
	}

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}
	public EventsService getEventsManager() {
		return eventsManager;
	}

	public void setEventsManager(EventsService eventsManager) {
		this.eventsManager = eventsManager;
	}
/**
 * <当天事件统计>
 * <功能详细描述>
 * @param assetIds 资产id串1,2,3,4,4,5
 * @return 一个字符串,放在hight中显示图表
 * @see [类、类#方法、类#成员]
 */
	private String dayEventStats(String assetIds){

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Map map = new HashMap();
 		map.put("assetId", assetIds);
		map.put("tableName", "tbl_" + sdf.format(new Date()));
		Object temp2 = eventsManager.getEventsByToday(map);
		return temp2.toString();
	}
	/**
	 * <最近7天 24小时 一小时 的告警类型分布>
	 * <功能详细描述>
	 * @return  一个字符串,放在hight中显示图表
	 * @see [类、类#方法、类#成员]
	 */
	private String lastTimeDaysAlarmTypeDistribution(String personType){
		
 		
		if (personType != null) {
			mapCon.put("endTime", System.currentTimeMillis());
			if (personType.equals("1")) {
				long time = System.currentTimeMillis() - 60 * 60 * 1000;
				mapCon.put("timeHour", time);
			}
			//最近24小时告警类型分布
			if (personType.equals("2")) {
				long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
				mapCon.put("timeHour", time);
			}
			//最近7天的告警类型分布
			if (personType.equals("3")) {
				long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000* 7;
				mapCon.put("timeHour", time);
			}
		}
		String str = this.alertMessageManager.lastTimeDaysAlarmTypeDistribution(mapCon); 
		return str ;
	}
/**
 * <最近7天 一小时 24小时 告警最多的10个资产>
 * <功能详细描述>
 * @param map
 * @return
 * @see [类、类#方法、类#成员]
 */
	private String lastTimeDaysUpTo10AlarmsAssets(String personType){
		
		if (personType != null) {
			if (personType.equals("1")) {
				long time = System.currentTimeMillis() - 60 * 60 * 1000;
				mapCon.put("timeHour", time);
			}
			if (personType.equals("2")) {
				long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
				mapCon.put("timeHour", time);
			}
			if (personType.equals("3")) {
				long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000* 7;
				mapCon.put("timeHour", time);
			}
		}
	return 	alertMessageManager.lastTimeDaysUpTo10AlarmsAssets(mapCon);
	}
	/**
	 * <近期事件趋势>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String trendOfRecentEvents(){
		//近期事件趋势
		return eventsManager.queryEventsTrendAnalysis(mapCon);
	}
	/**
	 * <当天的事件类型分布>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String theDayOfTheEventTypeDistribution(){
		return 	eventsManager.queryEventByCategory(mapCon);
	}
	/**
	 * <当天事件IP分布>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String theDayOfIPDistribution(){
		return eventsManager.queryEventByIP(mapCon);
	}
	/**
	 * <24小时内重复最多的10个告警>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private String repeatUpTo10AlarmsWithin24hours(){
//		return alertMessageManager.queryAepetitionMessage(	System.currentTimeMillis() - 24 * 60 * 60 * 1000* 7);
		//修改，原来的参数不对。--李长红-20140403
		mapCon.put("time", System.currentTimeMillis() - 24 * 60 * 60 * 1000);
		return alertMessageManager.queryAepetitionMessage(mapCon);
	}

	/**
	 * <最近某时间最新的N条告警>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	private List<AlertMessage> recentlyAcertainTimeOfTheLatestNAlarms(String personType,String alertAssetId ){
		// 定义map
		Map map = new HashMap();
		map.put("alertAssetId", alertAssetId);
		if (personType == null || personType.equals("")) {
			long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000 * 7;
			map.put("timeHour", time);
			map.put("topN", 10);
		}
		if (personType != null) {
			if (personType.equals("1")) {
				long time = System.currentTimeMillis() - 60 * 60 * 1000;
				map.put("timeHour", time);
				map.put("topN", 5);
			}
			if (personType.equals("2")) {
				long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000;
				map.put("timeHour", time);
				map.put("topN", 10);
			}
			if (personType.equals("3")) {
				long time = System.currentTimeMillis() - 24 * 60 * 60 * 1000* 7;
				map.put("topN", 10);
				map.put("timeHour", time);
			}
		}
		alertMessageList = alertMessageManager.queryCurrentMessage(map);
		/*for (int i = 0; i < alertMessageList.size(); i++) {
			String name = alertMessageList.get(i).getAlertEventName();
			String type = alertMessageList.get(i).getAlertEventType();
			try{
				long temp = Long.valueOf(name);
				alertMessageList.get(i).setAlertEventName(
						GlobalConfig.eventTypeTag.get(temp));
			}catch(Exception e)
			{
				log.warn("类型转换错误");
			}
			alertMessageList.get(i).setAlertEventType(
					GlobalConfig.eventTypeTag.get(Long.valueOf(type)));
		}*/
		return alertMessageList;
	}
	
	/**
	 * 最近24小时最新的10条阀值告警
	 * @return String
	 * @author HR
	 */
	public String queryTenAlarmMessage(){
		long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
		String assetIds = null;
 		if(groupid==1){
 			}else{
 			 assetIds= assetManager.queryIDSByUser(groupid);
 			if(assetIds.equals("")){
 				assetIds="0";
 				}
 			}
		Map map =new HashMap() ; 
		map.put("alertAssetId", assetIds);
		long startTime = System.currentTimeMillis() - (1000 * 60 * 60 * 24);
		long endTime = System.currentTimeMillis() ; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if (GlobalConfig.sqlId.equals("sqlServer")) {
				map.put("startTime", sdf.format(new Date(startTime)));
				map.put("endTime", sdf.format(new Date(endTime)));
			}else {
				map.put("startTime", sdf.parse(DateUtil.curDateTimeStr19()));
				map.put("endTime", sdf.parse(DateUtil.get24HourAgoTime()));
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	//	monitorAlarmList = monitorAlarmManager.queryTenAlarmMessage(map);
		
		monitorAlarmList = monitorAlarmManager.queryTenAlarmMessage(map);
		
		return SUCCESS ; 
	}

	public MonitorAlarmService getMonitorAlarmManager() {
		return monitorAlarmManager;
	}

	public void setMonitorAlarmManager(MonitorAlarmService monitorAlarmManager) {
		this.monitorAlarmManager = monitorAlarmManager;
	}

	public List<Map<String, Object>> getMonitorAlarmList() {
		return monitorAlarmList;
	}

	public void setMonitorAlarmList(List<Map<String, Object>> monitorAlarmList) {
		this.monitorAlarmList = monitorAlarmList;
	}

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}

	public String getIpData() {
		return ipData;
	}

	public void setIpData(String ipData) {
		this.ipData = ipData;
	}

	public CustomTrendService getCustomTrendManager() {
		return customTrendManager;
	}

	public void setCustomTrendManager(CustomTrendService customTrendManager) {
		this.customTrendManager = customTrendManager;
	}

	public String getChartsBuffx() {
		return chartsBuffx;
	}

	public void setChartsBuffx(String chartsBuffx) {
		this.chartsBuffx = chartsBuffx;
	}

	public String getChartsBuff() {
		return chartsBuff;
	}

	public void setChartsBuff(String chartsBuff) {
		this.chartsBuff = chartsBuff;
	}
	
	


	
	
}
