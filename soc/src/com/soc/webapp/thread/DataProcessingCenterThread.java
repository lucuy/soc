package com.soc.webapp.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.config.SearchAttribute;
import net.sf.ehcache.config.Searchable;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soc.model.alert.AlertRule;
import com.soc.model.asset.Asset;
import com.soc.model.conf.AgentModel;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.events.FilterByGroup;
import com.soc.model.events.NotAnalyticEvents;
import com.soc.model.events.SummaryEvents;
import com.soc.model.monitor.MonitorAlarm;
import com.soc.model.progressmsg.LinuxProgressMsg;
import com.soc.model.progressmsg.WinProgressMsg;
import com.soc.model.progressmsg.WinSoftMsg;
import com.soc.model.servicemsg.LinuxServiceMsg;
import com.soc.model.servicemsg.WinServiceMsg;
import com.soc.model.systemsetting.Collector;
import com.soc.service.alert.AlertRuleService;
import com.soc.service.asset.AssetService;
import com.soc.service.events.EventsService;
import com.soc.service.events.NotAnalyticEventsService;
import com.soc.service.events.OriginalLogService;
import com.soc.service.events.SummaryEventsService;
import com.soc.service.monitor.MonitorAlarmService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.service.systemsetting.SettingService;
import com.util.Base64;
import com.util.StringUtil;
import com.util.analytic.LogAnalytic;
import com.util.analytic.ProgressAnalytic;
import com.util.analytic.ServiceAnalytic;
import com.util.analytic.WinSoftAnalytic;
import com.util.logic.MatchesFilterLogic;

/**
 * 
 * <数据处理类> <将Syslog接收到的数据执行解析、入库、告警>
 * 
 * @author 王亚男
 * @version [V100R001C001, 2012-11-7]
 * @see [相关类/方法]
 * @since [soc/V100R001C001]
 */
public class DataProcessingCenterThread  {
	// 时间戳,用来判断进程是否是一组
	private static String timestampProgress;
	// 时间戳,用来判断服务是否是一组
	private static String timestampService;
	// 时间戳,用来判断软件是否是一组
		private static String timestampWinSoft;
	@SuppressWarnings("unused")
	private List<String> dataList;

	@SuppressWarnings("unused")
	private CacheManager cacheManager;

	@SuppressWarnings("unused")
	private ServletContextEvent servletContextEvent;

	/**
     * 
     */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	public DataProcessingCenterThread(List<String> dataList,
			CacheManager cacheManager, ServletContextEvent servletContextEvent) {
		this.dataList = dataList;
		this.cacheManager = cacheManager;
		this.servletContextEvent = servletContextEvent;
	}

	/*@Override
	protected void compute() {
		
		 * if (dataList != null) { long start5 = new Date().getTime(); for
		 * (String data : dataList) { long startTask = new Date().getTime(); if
		 * (data.indexOf("|") != -1) { long start = new Date().getTime();
		 * 
		 * String message = data.substring((data.indexOf("|") - 17));
		 * 
		 * 
		 * 
		 * // 得到注入service类 WebApplicationContext wac =
		 * WebApplicationContextUtils
		 * .getRequiredWebApplicationContext(servletContextEvent
		 * .getServletContext());
		 * 
		 * SettingCollectorService collectorManager =
		 * (SettingCollectorService)wac.getBean("settingLoggerManager");
		 * 
		 * if (collectorIsExists(collectorManager, message)) {
		 * 
		 * message = message.substring(message.indexOf("|") + 1); int dataType =
		 * DataProcessingCenterThread.dataReader(message);
		 * //System.out.println("解析事件类型时间：" + (new Date().getTime() - start) +
		 * "毫秒");
		 * 
		 * // 如果接收的数据的格式是正确的 if (dataType != GlobalConfig.FORMAT_THE_WRONG_DATA)
		 * {
		 * 
		 * EventsService eventsManager =
		 * (EventsService)wac.getBean("eventsManager"); SummaryEventsService
		 * summaryEventsManager =
		 * (SummaryEventsService)wac.getBean("summaryEventsManager");
		 * NotAnalyticEventsService notAnalyticEventsManager =
		 * (NotAnalyticEventsService)wac.getBean("notAnalyticEventsManager");
		 * OriginalLogService originalLogManager =
		 * (OriginalLogService)wac.getBean("originalLogManager"); AssetService
		 * assetManager = (AssetService)wac.getBean("assetManager");
		 * AlertRuleService alertRuleManager =
		 * (AlertRuleService)wac.getBean("alertRuleManager");
		 * 
		 * // 解析后的日志的处理 if (dataType == GlobalConfig.ANALYTIC_FINISHED) { long
		 * start1 = new Date().getTime(); Map<String, Object> map =
		 * LogAnalytic.logAnalytic(message, assetManager);
		 * //System.out.println("解析日志为Event、summaryEvent对象的时间：" + (new
		 * Date().getTime() - start1)); Events events =
		 * (Events)map.get("events");
		 * 
		 * // 存储日志 if (map != null) { // 获得事件对象 Events sourceEvents =
		 * (Events)map.get("events"); long assetId = sourceEvents.getAssetId();
		 * Asset asset = assetManager.searchById(assetId);
		 * 
		 * // 资产不存在 事件不入库 if (asset != null) { long start2 = new
		 * Date().getTime(); logStorageHandling(eventsManager,
		 * summaryEventsManager, map);
		 * 
		 * //System.out.println("存储日志消耗时间：" + (new Date().getTime() - start2) +
		 * "毫秒");
		 * 
		 * long start3 = new Date().getTime();
		 * logAlarmHandling(alertRuleManager,
		 * events.getIdentification(),events.getName(),
		 * events.getType(),events.getDevAdds(),events.getDevType(),
		 * String.valueOf(events.getPriority()), events.getTableName(),
		 * events.getAssetId(), asset.getAssetName(),
		 * events.getAggregatedCount()); //System.out.println("日志告警消耗的时间：" +
		 * (new Date().getTime() - start3) + "毫秒"); } else {
		 * //System.out.println("系统信息:  资产未注册!!!"); } }
		 * 
		 * // 如果插入成功，处理事件日志的告警 if (events != null) { logAlarmHandling(wac,
		 * events.getIdentification(), events.getType(),
		 * String.valueOf(events.getPriority())); } }
		 * 
		 * // 采集器解析失败的数据 else if (dataType == GlobalConfig.ANALYTIC_FAILED) {
		 * notAnalyticEventsStorageHandling(originalLogManager,
		 * notAnalyticEventsManager, assetManager, message); }
		 * 
		 * // 原始日志的处理 else if (dataType == GlobalConfig.RAW_DATA) { Map<String,
		 * Object> map = null;
		 * 
		 * // 获得原始日志格式化后的map对象 if (StringUtil.isNotEmpty(message)) { map =
		 * getRawLogMap(assetManager, message,""); }
		 * 
		 * // 存入数据库 if (map != null) { long assetId = (Long)map.get("assetId");
		 * AssetService assetManager =
		 * (AssetService)wac.getBean("assetManager"); Asset asset =
		 * assetManager.searchById(assetId);
		 * 
		 * // 资产不存在 源始日志不入库 if (asset != null) {
		 * rawLogStorageHandling(eventsManager, notAnalyticEventsManager, map);
		 * } else { //System.out.println("系统信息:  资产未注册!!!"); } }
		 * 
		 * } else if (dataType == GlobalConfig.COLLECTOR_VERSION) {
		 * 
		 * }
		 * 
		 * // 监控数据的处理 else { AgentModel agentModel = null;
		 * 
		 * // 解析数据，获得监控数据对象 if (StringUtil.isNotEmpty(message)) { agentModel =
		 * DataProcessingCenterThread.getAgentModel(message); }
		 * 
		 * if (agentModel != null) { String assetMac = agentModel.getMac();
		 * 
		 * //判断采集器的mac内是否含有该mac if
		 * (GlobalConfig.collectorIsOnline.containsKey(assetMac)) {
		 * GlobalConfig.collectorIsOnline.remove(assetMac);
		 * 
		 * GlobalConfig.collectorIsOnline.put(assetMac, 1); }
		 * 
		 * //以ip为标识 String Ip = agentModel.getIp();
		 * 
		 * // 获得资产 Map<String, Object> map = new HashMap<String, Object>();
		 * 
		 * map.put("selAssetMac", Ip);
		 * 
		 * List<Asset> assetList = assetManager.query(map); if (assetList.size()
		 * > 0) { // 将agentModel存储到内存中 agentStorageHandling(agentModel,
		 * cacheManager, message);
		 * 
		 * // 监控阀值及告警处理 agentAlarmHandling(wac, agentModel, cacheManager);
		 * 
		 * //System.out.println("进入次数：  " + GlobalConfig.count++); } else {
		 * //System.out.println("系统信息:   MAC【 " + assetMac + "】的资产为注册!!!"); } }
		 * 
		 * } }
		 * 
		 * //System.out.println("时间信息：         【开始时间-" + start + "毫秒】 " + (new
		 * Date().getTime() - start) + "毫秒"); } else {
		 * //System.out.println("采集器未注册。。。。"); }
		 * 
		 * } else { //System.out.println("系统信息:   数据格式错误！！！！"); }
		 * 
		 * //System.out.println("一个任务执行的时间：" + (new Date().getTime() -
		 * startTask) + "毫秒"); }
		 * 
		 * //System.out.println("循环" + dataList.size() + "次需要【" + (new
		 * Date().getTime() - start5) + "】毫秒"); }
		
	} */

	/**
	 * <数据处理的主线程> <功能详细描述>
	 * 
	 * @param threadId
	 *            int
	 * @param event
	 *            SyslogServerEventIF
	 * @param cacheManager
	 *            CacheManager
	 * @param servletContextEvent
	 *            ServletContextEvent
	 * @return threadId
	 * @see [类、类#方法、类#成员]
	 */
	public static Runnable createThread(final int threadId,
			final List<String> dataList, final CacheManager cacheManager,
			final ServletContextEvent servletContextEvent) {
		return new Runnable() {
			@SuppressWarnings("unchecked")
			public void run() {
				if (dataList != null) {
					// long start5 = new Date().getTime();
					for (String data : dataList) {
						// long startTask = new Date().getTime();
						if (data.indexOf("|") != -1) {
							// long start = new Date().getTime();

							// String message =
							// data.substring((data.indexOf("|") - 17));

							// System.out.println("messageData:"+data);

							// ]] System.out.println("2341234134"+message);

							// 得到注入service类
							WebApplicationContext wac = WebApplicationContextUtils
									.getRequiredWebApplicationContext(servletContextEvent
											.getServletContext());

							SettingCollectorService collectorManager = (SettingCollectorService) wac
									.getBean("settingLoggerManager");
							// 日志转发service
							/*LogWriteServiceImpl logManager = (LogWriteServiceImpl) wac
									.getBean("logManager");*/

							if (collectorIsExists(collectorManager, data)) {

								String ipUserForProgressAndService = data
										.split(" ")[1];

								String collectorMac = data.substring(0,
										data.indexOf("|"));

								// System.out.println(collectorMac);

								try {

									String MDmessage = data.split(" ")[2];

									// System.out.println("messageliu:"+MDmessage);
									// 得到资产的Ip
									// String collectorMac=data.split(" ")[1];
									String message="";
									try{
									 message = Base64
											.decodeString(MDmessage);
									}catch (Exception e) {
										//System.out.println("解密出现问题了--"+MDmessage);
									}
									/** 屏蔽日志打印功能 -- 苑恒润  20140424 */
									//System.out.println(message);

									// 把接收到日志转发出去
									// logManager.writeSystemAuditLog(message);

									int dataType = DataProcessingCenterThread
											.dataReader(message);

									// System.out.println("解析事件类型时间："+ (new
									// Date().getTime() - start)+ "毫秒");

									// 如果接收的数据的格式是正确的
									if (dataType != GlobalConfig.FORMAT_THE_WRONG_DATA
											&& dataType != 0) {

										EventsService eventsManager = (EventsService) wac
												.getBean("eventsManager");
										SummaryEventsService summaryEventsManager = (SummaryEventsService) wac
												.getBean("summaryEventsManager");
										NotAnalyticEventsService notAnalyticEventsManager = (NotAnalyticEventsService) wac
												.getBean("notAnalyticEventsManager");
										OriginalLogService originalLogManager = (OriginalLogService) wac
												.getBean("originalLogManager");
										AssetService assetManager = (AssetService) wac
												.getBean("assetManager");
										AlertRuleService alertRuleManager = (AlertRuleService) wac
												.getBean("alertRuleManager");
										//MonitorHistoryService monitorHistoryManager = (MonitorHistoryService) wac.getBean("monitorHistoryManager");
										// 解析后的日志的处理
										if (dataType == GlobalConfig.ANALYTIC_FINISHED) {
											// long start1 = new
											// Date().getTime();
											Map<String, Object> map = LogAnalytic
													.logAnalytic(message,
															assetManager,
															collectorMac);
											// System.out.println("解析日志为Event、summaryEvent对象的时间："
											// + (new Date()
											// .getTime() - start1));

											Events events = (Events) map
													.get("events");

											// 存储日志
											if (map != null && (!map.isEmpty())) {
												// 获得事件对象
												Events sourceEvents = (Events) map
														.get("events");

												long assetId = sourceEvents
														.getAssetId();

												Asset asset = GlobalConfig.assetGlobleMap.get(assetId);

												// 资产不存在 事件不入库
												if (asset != null) {
													/*
													 * long start2 = new Date()
													 * .getTime(); long start3 =
													 * new Date() .getTime();
													 */

													/*
													 * if(!sourceEvents.getType()
													 * .trim().equals("120000"))
													 * {
													 */

													// System.out.println("tongguo");
													logStorageHandling(
															eventsManager,
															summaryEventsManager,
															map);
													// System.out.println("存储日志消耗时间："
													// + (new Date()
													// .getTime() - start2)
													// + "毫秒");
													

													int priority = (int)events.getPriority() ; 
													List<AlertRule> ruleList = alertRuleManager.queryAlertRule(assetId,priority);
													//判断是告警信息：1、告警级别是否符合 2、资产ID是否存在
													if(ruleList != null && !ruleList.isEmpty()){
														logAlarmHandling(
																alertRuleManager,
																ruleList,
																events.getIdentification(),
																events.getName(),
																events.getType(),
																events.getCateGory(),
																events.getsAddr(),
																events.getdAddr(),
																events.getDevAdds(),
																events.getDevproduct(),
																String.valueOf(events
																		.getPriority()),
																events.getTableName(),
																events.getAssetId(),
																asset.getAssetName(),
																events.getAggregatedCount(),
																events.getsAddr(),
																events.getsPort(),
																events.getdAddr(),
																events.getdPort(),
																events.getCustoms5(),
																events.getSendTime());
													}
												}
												// System.out.println("日志告警消耗的时间："
												// + (new Date()
												// .getTime() - start3)
												// + "毫秒");

											} /*else {
												// System.out.println("系统信息:  资产未注册!!!");
											}*/
											/* } */

											// 如果插入成功，处理事件日志的告警
											/*
											 * if (events != null) {
											 * logAlarmHandling(wac,
											 * events.getIdentification(),
											 * events.getType(),
											 * String.valueOf(events
											 * .getPriority())); }
											 */
										}

										// 采集器解析失败的数据(未解析原始事件入库操作)
										else if (dataType == GlobalConfig.ANALYTIC_FAILED) {
											notAnalyticEventsStorageHandling(
													originalLogManager,
													notAnalyticEventsManager,
													assetManager, message);
										}

										// 原始日志的处理
										else if (dataType == GlobalConfig.RAW_DATA) {

											Map<String, Object> map = null;

											// 获得原始日志格式化后的map对象
											if (StringUtil.isNotEmpty(message)) {
												map = getRawLogMap(
														/*assetManager,*/ message,
														collectorMac);
											}

											// 存入数据库
											if (map != null) {
												/*
												 * long assetId =
												 * (Long)map.get("assetId");
												 * AssetService assetManager =
												 * (AssetService
												 * )wac.getBean("assetManager");
												 * Asset asset =
												 * assetManager.searchById
												 * (assetId);
												 * 
												 * // 资产不存在 源始日志不入库 if (asset !=
												 * null) {
												 */
												rawLogStorageHandling(
														eventsManager,
														notAnalyticEventsManager,
														map);
												/*
												 * } else {
												 * //System.out.println(
												 * "系统信息:  资产未注册!!!"); }
												 */
											}

										}

										// 监控数据的处理
										else if (GlobalConfig.MONITOR_DATA == dataType) {

											AgentModel agentModel = null;

											// 解析数据，获得监控数据对象
											if (StringUtil.isNotEmpty(message)) {
												// //System.out.println("gsgsgs"+message);
												System.out
												.println("message为截断信息：" + "\n" + message + "");
												
												message = message
														.substring(message
																.indexOf("|") + 1);
												System.out
														.println("APM信息：" + "\n" + message + "");
												agentModel = DataProcessingCenterThread
														.getAgentModel(message);
												System.out
												.println("agentModel：" + "\n" + agentModel + "");
											}

											if (agentModel != null) {
												// String assetMac =
												// agentModel.getMac();

												// 接收采集器的APM证明接收事件。
												String assetMac = agentModel
														.getMac();

												System.out
												.println("assetMac：" + "\n" + assetMac + "");
												
												// 判断采集器的mac内是否含有该mac
												if (GlobalConfig.collectorIsOnline
														.containsKey(assetMac)) {
													/*
													 * GlobalConfig.
													 * collectorIsOnline
													 * .remove(assetMac);
													 */

													GlobalConfig.collectorIsOnline
															.put(assetMac, 1);
												}

												// 根据ip查询
												String assetIp = agentModel
														.getIp();
												System.out
												.println("assetIp：" + "\n" + assetIp + "");
												// 获得资产
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("selAssetMac", assetIp);

												List<Asset> assetList = assetManager
														.query(map);

												if (assetList.size() > 0) {
												//	MonitorAlarmHistoryUtils.addMonitorAlarmHistory(agentModel, monitorHistoryManager);
													//apm信息存在的时间点，判断资产是否一直在线
													GlobalConfig.AssetTimeNote.put(assetIp,new Date()); 
													// 将agentModel存储到内存中
													agentStorageHandling(
															agentModel,
															cacheManager,
															message);
													
													// 监控阀值及告警处理
													agentAlarmHandling(wac,
															agentModel,
															cacheManager);
													
													// System.out.println("进入次数：  "
													// + GlobalConfig.count++);
												} /*else {
													// System.out.println("系统信息:   MAC【 "
													// + assetMac
													// + "】的资产未注册!!!");
												}*/
											}
											// 解析win进程
										} else if (dataType == GlobalConfig.WIN_PROGRESS) {

											WinProgressMsg progressMsg = null;

											// 解析数据，获得监控数据win进程对象
											if (StringUtil.isNotEmpty(message)) {
												progressMsg = ProgressAnalytic
														.winProgressAnalytic(
																message,
																ipUserForProgressAndService,
																collectorMac);

											}

											if (progressMsg != null) {
												/*String progressMac = progressMsg
														.getMac();*/

												// 判断采集器的mac内是否含有该mac
												/*
												 * if
												 * (GlobalConfig.collectorIsOnline
												 * .containsKey(progressMac)) {
												 * GlobalConfig
												 * .collectorIsOnline
												 * .remove(progressMac);
												 * 
												 * GlobalConfig.collectorIsOnline
												 * .put(progressMac, 1); }
												 */
												// 根据ip查询

												/*String progressIp = progressMsg
														.getIp();*/

												// 获得资产
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("selAssetMac",
														/*progressIp*/progressMsg
														.getIp());

												List<Asset> assetList = assetManager
														.query(map);

												if (assetList.size() > 0) {
//													progressMsg.setFromDate(new Date());
//													//将win进程添加至数据库
//													monitorHistoryManager.insterWinProgressMsg(progressMsg);
													// 将win进程存储到内存中
													timestampProgress = progressMsg
															.getTimestamp();
													progressStorageHandling(
															progressMsg,
															cacheManager);

													// System.out.println("进入次数：  "
													// + GlobalConfig.count++);
												} /*else {
													// System.out.println("系统信息:   MAC【 "
													// + progressMac
													// + "】的资产未注册!!!");
												}*/
											}

											// 解析linux进程
										} else if (dataType == GlobalConfig.LINUX_PROGRESS) {

											LinuxProgressMsg progressMsg = null;

											// 解析数据，获得监控数据linux进程对象
											if (StringUtil.isNotEmpty(message)) {
												progressMsg = ProgressAnalytic
														.LinuxProgressAnalytic(
																message,
																ipUserForProgressAndService,
																collectorMac);

											}

											if (progressMsg != null) {
												/*String progressMac = progressMsg
														.getMac();*/

												// 判断采集器的mac内是否含有该mac
												/*
												 * if
												 * (GlobalConfig.collectorIsOnline
												 * .containsKey(progressMac)) {
												 * GlobalConfig
												 * .collectorIsOnline
												 * .remove(progressMac);
												 * 
												 * GlobalConfig.collectorIsOnline
												 * .put(progressMac, 1); }
												 */
												// 根据ip查询

												/*String progressIp = progressMsg
														.getIp();*/

												// 获得资产
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("selAssetMac",
														/*progressIp*/progressMsg
														.getIp());

												List<Asset> assetList = assetManager
														.query(map);

												if (assetList.size() > 0) {
//													progressMsg.setFromDate(new Date());
//													//将linux进程添加至数据库
//													monitorHistoryManager.insterLinuxProgressMsg(progressMsg);
													// 将linux进程存储到内存中
													timestampProgress = progressMsg
															.getTimestamp();
													progressStorageHandling(
															progressMsg,
															cacheManager);

													// System.out.println("进入次数：  "
													// + GlobalConfig.count++);
												} /*else {
													// System.out.println("系统信息:   MAC【 "
													// + progressMac
													// + "】的资产未注册!!!");
												}*/
											}

											// win服务信息
										} else if (dataType == GlobalConfig.WIN_SERVICE) {

											WinServiceMsg serviceMsg = null;

											// 解析数据，获得监控数据win服务对象
											if (StringUtil.isNotEmpty(message)) {
												serviceMsg = ServiceAnalytic
														.WinServiceAnalytic(
																message,
																ipUserForProgressAndService,
																collectorMac);

											}

											if (serviceMsg != null) {
												/*String serviceMac = serviceMsg
														.getMac();*/

												// 判断采集器的mac内是否含有该mac
												/*
												 * if
												 * (GlobalConfig.collectorIsOnline
												 * .containsKey(serviceMac)) {
												 * GlobalConfig
												 * .collectorIsOnline
												 * .remove(serviceMac);
												 * 
												 * GlobalConfig.collectorIsOnline
												 * .put(serviceMac, 1); }
												 */

												// 根据ip查询

												/*String serviceIp = serviceMsg
														.getIp();*/

												// 获得资产
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("selAssetMac",
														/*serviceIp*/serviceMsg
														.getIp());

												List<Asset> assetList = assetManager
														.query(map);

												if (assetList.size() > 0) {
													//将win服务添加至数据库
//													serviceMsg.setFromDate(new Date());
//													monitorHistoryManager.insterWinServiceMsg(serviceMsg);
													// 将win服务存储到内存中
													timestampService = serviceMsg
															.getTimestamp();
													serviceStorageHandling(
															serviceMsg,
															cacheManager);

//													System.out.println("进入次数：  "
//																	+ GlobalConfig.count++);
												}/* else {
//													System.out.println("系统信息:   MAC【 "
//																	+ serviceMac
//																	+ "】的资产未注册!!!");
												}*/
											}

											// win软件信息
										} 
										
										else if (dataType == GlobalConfig.WIN_SOFT) {
											System.out.println("已经进入了win软件信息程序+++++++++++++++++++++++++++++++++++++++" + dataType);
											WinSoftMsg softMsg = null;

											// 解析数据，获得监控数据win服务对象
											if (StringUtil.isNotEmpty(message)) {
												softMsg = WinSoftAnalytic
														.SoftAnalytic(
																message,
																ipUserForProgressAndService,
																collectorMac);

											}

											if (softMsg != null) {
												/*String softMac = softMsg.getMac();*/

												// 判断采集器的mac内是否含有该mac
												/*if (GlobalConfig.collectorIsOnline
														.containsKey(serviceMac)) {
													GlobalConfig.collectorIsOnline
															.remove(serviceMac);

													GlobalConfig.collectorIsOnline
															.put(serviceMac, 1);
												}*/

												// 根据ip查询

												/*String softIp = softMsg
														.getIp();*/

												// 获得资产
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("selAssetMac",
														/*softIp*/softMsg
														.getIp());

												List<Asset> assetList = assetManager
														.query(map);

												if (assetList.size() > 0) {
													//将win服务添加至数据
//													softMsg.setFromDate(new Date());
//													monitorHistoryManager.insterWinSoftMsg(softMsg);
													// 将win服务存储到内存中
													timestampWinSoft = softMsg
															.getTimestamp();
													softStorageHandling(
															softMsg,
															cacheManager);

//													System.out.println("进入次数：  "
//																	+ GlobalConfig.count++);
												} /*else {
//													System.out.println("系统信息:   MAC【 "
//																	+ serviceMac
//																	+ "】的资产未注册!!!");
												}*/
											}

											// linuix 服务信息
										} 
										else if (dataType == GlobalConfig.LINUX_SERVICE) {

											LinuxServiceMsg serviceMsg = null;

											// 解析数据，获得监控数据linux服务对象
											if (StringUtil.isNotEmpty(message)) {
												serviceMsg = ServiceAnalytic
														.LinuxServiceAnalytic(
																message,
																ipUserForProgressAndService,
																collectorMac);

											}

											if (serviceMsg != null) {
												/*String serviceMac = serviceMsg
														.getMac();*/

												// 判断采集器的mac内是否含有该mac
												/*
												 * if
												 * (GlobalConfig.collectorIsOnline
												 * .containsKey(serviceMac)) {
												 * GlobalConfig
												 * .collectorIsOnline
												 * .remove(serviceMac);
												 * 
												 * GlobalConfig.collectorIsOnline
												 * .put(serviceMac, 1); }
												 */
												// 根据ip查询

												/*String serviceIp = serviceMsg
														.getIp();*/

												// 获得资产
												Map<String, Object> map = new HashMap<String, Object>();
												map.put("selAssetMac",
														/*serviceIp*/serviceMsg
														.getIp());

												List<Asset> assetList = assetManager
														.query(map);

												if (assetList.size() > 0) {
//													serviceMsg.setFromDate(new Date());
//													monitorHistoryManager.insterLinuxServiceMsg(serviceMsg);
													// 将linux服务存储到内存中
													timestampService = serviceMsg
															.getTimestamp();
													serviceStorageHandling(
															serviceMsg,
															cacheManager);

													/*
													 * System.out
													 * .println("进入次数：  " +
													 * GlobalConfig.count++);
													 */
												} /*else {
													System.out
															.println("系统信息:   MAC【 "
																	+ serviceMac
																	+ "】的资产未注册!!!");
												}*/
											}

										}
										// ///

										// /
									}

									// System.out.println("时间信息：         【开始时间-"
									// + start + "毫秒】 "
									// + (new Date().getTime() - start)
									// + "毫秒");
								} catch (Exception e) {
									e.printStackTrace();
									// System.out.println("日志格式不正确。。。。。");
								}
							}/* else {
								// System.out.println("采集器未注册。。。。");
							}*/
						}/* else {
							// System.out.println("系统信息:   数据格式错误！！！！");
						}*/

						// System.out.println("一个任务执行的时间："
						// + (new Date().getTime() - startTask) + "毫秒");
					}

					// System.out.println("循环" + dataList.size() + "次需要【"
					// + (new Date().getTime() - start5) + "】毫秒");
				}
			}
		};
	}

	/**
	 * <采集器是否注册> <功能详细描述>
	 * 
	 * @param collectorManager
	 *            SettingCollectorService
	 * @param message
	 *            String
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static boolean collectorIsExists(
			SettingCollectorService collectorManager, String message) {
		boolean flag = false;

		String mac = message.substring(0, message.indexOf("|"));

		if (GlobalConfig.collectorList != null && StringUtil.isNotEmpty(mac)) {
			for (Collector collector : GlobalConfig.collectorList) {
				if (StringUtil.equals(mac, collector.getCollectorMac())) {
					flag = true;
					// collector.setCollectorReceiveEvents(collector.getCollectorReceiveEvents()
					// + 1);
					// collectorManager.updateId(collector);
					break;
				}
			}
		}

		return flag;

	}

	/**
	 * <获得数据的类型> <1:解析后的日志 | 2:原始日志 | 3:agent状态 | -1:数据格式错误>
	 * 
	 * @param message
	 *            String
	 * @return dataType
	 * @see [类、类#方法、类#成员]
	 */
	public static int dataReader(String message) {
		String subMessage = null;

		int dataType = 0;

		if (StringUtil.isNotBlank(message)) {
			subMessage = message.substring(message.indexOf(":") + 1,
					message.indexOf("|"));
			// System.out.println(subMessage);
		}

		if (StringUtil.isNotBlank(subMessage)) {
			try {
				dataType = Integer.valueOf(subMessage);
			} catch (NumberFormatException e) {
				dataType = GlobalConfig.FORMAT_THE_WRONG_DATA;
			}

		}

		return dataType;
	}

	/**
	 * <将接收到的UDP数据格式化为AgentModel> <将接收到的agent状态信息格式化为对象，此对象存储在缓存中>
	 * 
	 * @param datas
	 *            String
	 * @return AgentModel
	 * @see [类、类#方法、类#成员]
	 */
	public static AgentModel getAgentModel(String datas) {
		AgentModel agentModel = new AgentModel();
		// 格式化String为Date的模版
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd-HH-mm-ss");

		String agentDatas = null;
		String[] agentData = null;

		if (StringUtil.isNotBlank(datas)) {
			agentDatas = datas;
		}

		// 将data拆分成data[]
		if (agentDatas.indexOf("|") != -1) {
			agentData = StringUtil.split(agentDatas, "|");
		}

		if (agentData != null) {

			// 操作系统
			if (StringUtil.isNotEmpty(agentData[0])) {
				agentModel.setOperatingSystem(agentData[0]);
			}

			// agent目录
			if (StringUtil.isNotEmpty(agentData[1])) {
				agentModel.setAgentInstallPath(agentData[1]);
			}

			// CPU型号
			if (StringUtil.isNotEmpty(agentData[2])) {
				agentModel.setCpuType(agentData[2]);
			}

			// 主机名
			if (StringUtil.isNotEmpty(agentData[3])) {
				agentModel.setHostName(agentData[3]);
			}

			// IP地址
			if (StringUtil.isNotEmpty(agentData[4])) {
				agentModel.setIp(agentData[4]);
			}

			// MAC地址
			if (StringUtil.isNotEmpty(agentData[5])) {
				agentModel.setMac(agentData[5].toUpperCase());
			}

			// CPU占用
			if (StringUtil.isNotEmpty(agentData[6])) {
				agentModel.setCpuOccupy(Integer.valueOf((agentData[6].trim())));
			}

			// 内存占用率|内存空闲量
			if (StringUtil.isNotEmpty(agentData[7])) {

				// 交换机过来的是百分比
				// if(Double.parseDouble(agentData[7].trim())<100.1){

				// agentModel.setCacheOccupy(Double.valueOf((agentData[7].trim())));

				// }else {

				// agentModel.setCacheOccupy((Double.valueOf(agentData[7]) *
				// 100)
				// / Double.valueOf(agentData[8]));
				// }
				// 修改过
				if (StringUtil.isNotEmpty(agentData[8])) {
					if(Double.parseDouble(agentData[7].trim())<90.1)
					{
						agentModel.setCacheOccupy(Double.valueOf((agentData[7].trim())));
					}
					else
					{
					agentModel.setCacheOccupy(((Double.valueOf(agentData[8])-Double.valueOf(agentData[7])) *100)
						/ Double.valueOf(agentData[8]));
					}
				}else{
					agentModel.setCacheOccupy(Double.valueOf((agentData[7].trim())));
				}
			}

			// 内存总数
			if (StringUtil.isNotEmpty(agentData[8])) {
				agentModel
						.setMemoryTotal(Double.valueOf((agentData[8].trim())));
			}

			// 网络发送流量
			if (StringUtil.isNotEmpty(agentData[9])) {
				agentModel.setTransmitFlow(Double.parseDouble(agentData[9]));
			}

			// 网络接收流量
			if (StringUtil.isNotEmpty(agentData[10])) {
				agentModel.setReceiveFlow(Double.parseDouble(agentData[10]));
			}

			// 系统时间
			if (StringUtil.isNotEmpty(agentData[11])) {
				try {
					agentModel.setSystemTime(dateFormat.parse(agentData[11])
							.getTime());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 开机时间
			if (StringUtil.isNotEmpty(agentData[12])) {
				try {
					agentModel.setUptime(dateFormat.parse(agentData[12]));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			// 运行时间
			if (StringUtil.isNotEmpty(agentData[13])) {

				if (agentData[13].contains("-")) {
					String[] time = agentData[13].split("-");

					agentModel.setRuntime(time[0] + " 小时 " + time[1] + " 分 "
							+ time[2] + " 秒");
				} else {
					agentModel.setRuntime(agentData[13]);
				}
			}

			// 版本
			if (StringUtil.isNotEmpty(agentData[14])) {

				agentModel.setVersionnumber(agentData[14]);
			}

			// 盘符（挂载点）_当前使用大小_总大小
			// 修改以前遗留的bug
			if (StringUtil.isNotEmpty(agentData[15])) {

				StringBuffer discSize = new StringBuffer();

				for (int i = 15; i < agentData.length; i++) {
					discSize.append(agentData[i] + "|");
				}

				agentModel.setDiscSize(discSize.toString());
			}

		}

		return agentModel;
	}

	/**
	 * <解析原始日志> <功能详细描述>
	 * 
	 * @param message
	 *            String
	 * @return map
	 * @see [类、类#方法、类#成员]
	 */
	public static Map<String, Object> getRawLogMap(/*AssetService assetManager,*/
			String message, String collectorMac) {
		Map<String, Object> map = null;

		String[] messages = message.split("\\|");

		if (messages != null) {
			// 统计采集器采集日志总数如果KEY存在于collector Map中条数增1
			if (GlobalConfig.collector.containsKey(collectorMac)) {

				Map<String, Long> collector = GlobalConfig.collector;

				Long collectorCount = collector.get(collectorMac) + 1;

				// GlobalConfig.collector.remove(collectorMac);

				GlobalConfig.collector.put(collectorMac, collectorCount);
			}

			map = new HashMap<String, Object>();

			// 此原始日志的唯一标识
			map.put("identification", Long.parseLong(messages[1]));

			// 原始日志
			map.put("msg", messages[2].toString());

			// 资产id
			map.put("assetId", Long.parseLong(messages[3]));

			//Asset asset = assetManager.searchById(Long.parseLong(messages[3]));
			Asset asset = GlobalConfig.assetGlobleMap.get(Long.parseLong(messages[3]));

			if (asset != null) {
				// 资产名称
				map.put("assetName", asset.getAssetName());
			} else {
				// 资产名称
				map.put("assetName", "资产不存在");
			}

		}
		return map;
	}

	/**
	 * <日志入库> <功能详细描述>
	 * 
	 * @param wac
	 *            WebApplicationContext
	 * @param map
	 *            Map<String, Object>
	 * @return events
	 * @see [类、类#方法、类#成员]
	 */
	public static Events logStorageHandling(EventsService eventsManager,
			SummaryEventsService summaryEventsManager, Map<String, Object> map) {
		Events sourceEvents = null;

		if (map.size() > 1) {
			// 获得事件对象
			sourceEvents = (Events) map.get("events");

			// 获得概要事件对象
			SummaryEvents summaryEvents = (SummaryEvents) map
					.get("summaryEvents");

			// 插入事件
			if (sourceEvents != null) {
				// 获得当前时间
				//long currentTime = new Date().getTime();

				GlobalConfig.eventsQueue.add(sourceEvents);

				// 判断是否到达执行插入事件的事务的时间
				// if((currentTime - GlobalConfig.storeEventsQueueTime) >=
				// GlobalConfig.storeDataInterval)
				/*
				 * if (GlobalConfig.eventsQueue.size() >=
				 * GlobalConfig.QUEUE_MAX_NUMBER) { // 将事件队列中的事件放入临时队列中
				 * synchronized (GlobalConfig.eventsQueue) { if
				 * (!GlobalConfig.eventsQueue.isEmpty()) { while
				 * (!GlobalConfig.eventsQueue.isEmpty()) {
				 * GlobalConfig.temporaryEventsQueue
				 * .add(GlobalConfig.eventsQueue.poll()); } } else {
				 * //System.out.println("系统信息:   队列中没有事件！！！！"); }
				 * 
				 * // 重新记录开始时间 GlobalConfig.storeEventsQueueTime = new
				 * Date().getTime(); }
				 * 
				 * // 将临时队列中的事件插入数据库 synchronized (GlobalConfig.eventsQueue) {
				 * if (!GlobalConfig.temporaryEventsQueue.isEmpty()) {
				 * eventsManager
				 * .insertEvents(GlobalConfig.temporaryEventsQueue); } else {
				 * //System.out.println("系统信息:   临时事件队列中没有事件！！！！"); } }
				 * 
				 * } else if ((currentTime - GlobalConfig.storeEventsQueueTime)
				 * >= GlobalConfig.storeDataInterval) { // 将事件队列中的事件放入临时队列中
				 * synchronized (GlobalConfig.eventsQueue) { if
				 * (!GlobalConfig.eventsQueue.isEmpty()) { while
				 * (!GlobalConfig.eventsQueue.isEmpty()) {
				 * GlobalConfig.temporaryEventsQueue
				 * .add(GlobalConfig.eventsQueue.poll()); } } else {
				 * //System.out.println("系统信息:   队列中没有事件！！！！"); }
				 * 
				 * // 重新记录开始时间 GlobalConfig.storeEventsQueueTime = new
				 * Date().getTime(); }
				 * 
				 * // 将临时队列中的事件插入数据库 synchronized (GlobalConfig.eventsQueue) {
				 * if (!GlobalConfig.temporaryEventsQueue.isEmpty()) {
				 * eventsManager
				 * .insertEvents(GlobalConfig.temporaryEventsQueue); } else {
				 * //System.out.println("系统信息:   临时事件队列中没有事件！！！！"); } } }
				 */

				// System.out.println(summaryEvents.getEventsType());
				eventsManager.insertEvents(GlobalConfig.eventsQueue);
				// 将事件按规则分类，存入小表

				eventCategory(summaryEventsManager, sourceEvents, summaryEvents);

			}
		}
		return sourceEvents;
	}

	/**
	 * <事件分类处理> <将事件按照规则归类，并存入小表>
	 * 
	 * @param summaryEventsManager
	 *            SummaryEventsService
	 * @param events
	 *            Events
	 * @param summaryEvents
	 *            SummaryEvents
	 * @see [类、类#方法、类#成员]
	 */
	public static void eventCategory(SummaryEventsService summaryEventsManager,
			Events events, SummaryEvents summaryEvents) {

		List<String> operatorList = GlobalConfig.operatorList;

		Map<String, String> eventAttributes = GlobalConfig.eventAttributes;

		List<FilterByGroup> filterByGroupList = GlobalConfig.filterByGroupList;

		//long start = new Date().getTime();

		// 遍历过滤规则
		for (FilterByGroup filterByGroup : filterByGroupList) {
			boolean flag = MatchesFilterLogic.MatchesFilterHandling(
					filterByGroup, events, operatorList, eventAttributes);

			if (flag) {
				// 获得当前时间
				//long currentTime = new Date().getTime();

				// 获取过滤规则对应的概要表的插入方法的sqlkey
				String sqlKey = filterByGroup.getFilterByGroupMapping();

				if (StringUtil.isNotEmpty(sqlKey)) {
					summaryEventsManager.insertSummaryEvents(sqlKey,
							summaryEvents);
				}

				/*
				 * if (StringUtil.isNotEmpty(sqlKey)) {
				 * GlobalConfig.summaryEventsQueue.add(summaryEvents); } else {
				 * //System.out.println("系统信息:   未找到 " + filter.getFilterName()
				 * + "【" + filter.getFilterId() + "】 对应的概要表！！！"); }
				 * 
				 * //if((currentTime - GlobalConfig.storeSummaryEventsQueueTime)
				 * >= GlobalConfig.storeDataInterval) if
				 * (GlobalConfig.summaryEventsQueue.size() >=
				 * GlobalConfig.QUEUE_MAX_NUMBER) { // 将概要事件队列中的概要事件放入临时队列中
				 * synchronized (GlobalConfig.summaryEventsQueue) { if
				 * (!GlobalConfig.summaryEventsQueue.isEmpty()) { while
				 * (!GlobalConfig.summaryEventsQueue.isEmpty()) {
				 * GlobalConfig.temporarySummaryEventsQueue
				 * .add(GlobalConfig.summaryEventsQueue.poll()); } } else {
				 * //System.out.println("系统信息:   队列中没有概要事件！！！！"); }
				 * 
				 * // 重新记录开始时间 GlobalConfig.storeSummaryEventsQueueTime = new
				 * Date().getTime(); }
				 * 
				 * // 将临时队列中的概要事件插入数据库 synchronized
				 * (GlobalConfig.temporarySummaryEventsQueue) { if
				 * (!GlobalConfig.temporarySummaryEventsQueue.isEmpty()) {
				 * summaryEventsManager
				 * .insertSummaryEvents(filterManager.queryTableNameByFilterId
				 * (filter.getFilterId()),
				 * GlobalConfig.temporarySummaryEventsQueue); } else {
				 * //System.out.println("系统信息:   临时概要事件队列中没有概要事件！！！！"); } } }
				 * else if ((currentTime -
				 * GlobalConfig.storeSummaryEventsQueueTime) >=
				 * GlobalConfig.storeDataInterval) { // 将概要事件队列中的概要事件放入临时队列中
				 * synchronized (GlobalConfig.summaryEventsQueue) { if
				 * (!GlobalConfig.summaryEventsQueue.isEmpty()) { while
				 * (!GlobalConfig.summaryEventsQueue.isEmpty()) {
				 * GlobalConfig.temporarySummaryEventsQueue
				 * .add(GlobalConfig.summaryEventsQueue.poll()); } } else {
				 * //System.out.println("系统信息:   队列中没有概要事件！！！！"); }
				 * 
				 * // 重新记录开始时间 GlobalConfig.storeSummaryEventsQueueTime = new
				 * Date().getTime(); }
				 * 
				 * // 将临时队列中的概要事件插入数据库 synchronized
				 * (GlobalConfig.temporarySummaryEventsQueue) { if
				 * (!GlobalConfig.temporarySummaryEventsQueue.isEmpty()) {
				 * summaryEventsManager
				 * .insertSummaryEvents(filterManager.queryTableNameByFilterId
				 * (filter.getFilterId()),
				 * GlobalConfig.temporarySummaryEventsQueue); } else {
				 * //System.out.println("系统信息:   临时概要事件队列中没有概要事件！！！！"); } } }
				 */

			}
		}

		// //System.out.println("系统信息:   概要事件【" + summaryEvents.getEventsName()
		// +
		// "】入库执行了" + (new Date().getTime() - start) + "ms");
		// System.out.println("系统信息:   事件【" + summaryEvents.getEventsName()
		// + "】过滤规则【" + filterByGroupList.size() + "】执行了"
		// + (new Date().getTime() - start) + "ms");
	}

	/**
	 * <解析失败日志入库> <功能详细描述>
	 * 
	 * @param notAnalyticEventsManager
	 *            NotAnalyticEventsService
	 * @param message
	 *            String
	 * @see [类、类#方法、类#成员]
	 */
	public static void notAnalyticEventsStorageHandling(
			OriginalLogService originalLogManager,
			NotAnalyticEventsService notAnalyticEventsManager,
			AssetService assetManager, String message) {
		String[] messages = null;
		NotAnalyticEvents notAnalyticEvents = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		// //System.out.println("qwdqwd"+message.indexOf("|"));
		if (message.indexOf("|") != -1) {
			messages = message.split("\\|");
		}

		if (messages != null) {
			notAnalyticEvents = new NotAnalyticEvents();

			// 将事件内容存入未解析事件的库内
			notAnalyticEvents.setNotAnalyticEventsContent(messages[2]);

			notAnalyticEvents.setIdentification(Long.parseLong(messages[1]));

			// notAnalyticEvents.setN
		}
		Asset asset = GlobalConfig.assetGlobleMap.get(Long.parseLong(messages[3]));
		if (asset != null) {
			notAnalyticEvents.setNotAnalyticEventsAssetId(asset.getAssetId());
			notAnalyticEvents.setNotAnalyticEventsAssetName(asset
					.getAssetName());
			notAnalyticEvents.setNotAnalyticEventsCollectorId(asset
					.getAssetCollectorId());
			for(Collector collector : GlobalConfig.collectorList){
				if(asset.getAssetCollectorId()==collector.getCollectorId()){
					notAnalyticEvents.setNotAnalyticEventsCollectorName(collector.getCollectorBasic());
				}
			}
			// notAnalyticEvents.setNotAnalyticEventsId(Long.parseLong(messages[2]));
		}

		if (notAnalyticEvents != null) {
			/* 获得昨天的日期 */
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal
					.getTime());

			/* 查询昨天的日志表中是否存在与当前的原始日志对应的解析日志 */
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("tableName", "tbl_" + yesterday + "_original_log");
			map.put("identification", Long.parseLong(messages[1]));
			String originalLogContent = "";

			if (originalLogManager.existsTable("tbl_" + yesterday
					+ "_original_log") != 0) {
				originalLogContent = originalLogManager.queryOriginalLog(map);
			}
			if (StringUtil.isEmpty(originalLogContent)) {
				notAnalyticEvents.setTableName("tbl_" + sdf.format(new Date())
						+ "_not_analytic_events");
				notAnalyticEventsManager
						.insertNotAnalyticEvents(notAnalyticEvents);
			} else {
				notAnalyticEvents.setTableName("tbl_" + yesterday
						+ "_not_analytic_events");
				notAnalyticEventsManager
						.insertNotAnalyticEvents(notAnalyticEvents);
			}

		}

	}

	/**
	 * <监控数据存储> <将监控信息存储到内存中>
	 * 
	 * @param agentModel
	 *            AgentModel
	 * @param cacheManager
	 *            CacheManager
	 * @param message
	 *            String
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("deprecation")
	public static void agentStorageHandling(AgentModel agentModel,
			CacheManager cacheManager, String message) {

		// 换为ip
		String mac = agentModel.getIp();

		Cache cache = null;

		if (mac != null) {
			// 初始化cache
			if (cacheManager.cacheExists(mac)) {
				// 如果cache存在，从容器中获得
				cache = cacheManager.getCache(mac);
			} else {
				// 如果cache不存在，配置新cache
				// 创建cache配置对象
				CacheConfiguration cacheConfig = new CacheConfiguration(mac, 0);

				// 设置cache在内存中的element个数
				cacheConfig
						.setMaxElementsInMemory(GlobalConfig.CACHE_AGENT_NUM);

				// 设置cache在磁盘中的element个数
				cacheConfig.setMaxElementsOnDisk(GlobalConfig.CACHE_AGENT_NUM);

				// 设置是否持久化到磁盘
				cacheConfig.overflowToOffHeap(false);

				//
				cacheConfig.memoryStoreEvictionPolicy("FIFO");

				// 设置缓存存活时间
				cacheConfig.setTimeToIdleSeconds(0);

				// 设置缓存的间隔时间
				cacheConfig.setTimeToLiveSeconds(0);

				// 配置检索项
				Searchable searchable = new Searchable();
				cacheConfig.addSearchable(searchable);
				searchable.addSearchAttribute(new SearchAttribute().name("mac")
						.expression("value.getMac()"));
				searchable.addSearchAttribute(new SearchAttribute().name(
						"system_time").expression("value.getSystemTime()"));
				searchable.addSearchAttribute(new SearchAttribute().name(
						"operating_system").expression(
						"value.getOperatingSystem()"));
				searchable.addSearchAttribute(new SearchAttribute().name(
						"host_name").expression("value.getHostName()"));
				// 将cache放入容器中
				cacheManager.addCache(new Cache(cacheConfig));

				// 获得cache
				cache = cacheManager.getCache(agentModel.getMac());

			}

		} /*else {
			// System.out.println("系统信息:   MAC为空!!!");
		}*/

		if (cache != null) {
			// 将agent状态存到缓存中
			cache.put(new Element(agentModel.getSystemTime(), agentModel));
			// System.out.println("缓存大小：" + cache.getSize());
		}
	}
	/**
	 * <Win软件服务信息> <把这些信息放内存中>
	 * 
	 * @param msg
	 * @param cacheManager
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("deprecation")
	public static void softStorageHandling(WinSoftMsg msg,
			CacheManager cacheManager) {
		Cache cache = null;
		// 判断时间戳是不是跟以前的一样 不一样清空以前的信息再放
		if (!timestampWinSoft.equals(msg.getTimestamp())) {

			// cache = cacheManager.getCache(msg.getIp() + "service");
			cacheManager.removeCache(msg.getIp() + "soft");
			/*
			 * if (cache != null) { cache.removeAll(); }
			 */

		}
		// 换为ip
		String mac = msg.getIp() + "soft";

		if (mac != null) {
			// 初始化cache
			if (cacheManager.cacheExists(mac)) {
				// 如果cache存在，从容器中获得
				cache = cacheManager.getCache(mac);
			} else {
				// 如果cache不存在，配置新cache
				// 创建cache配置对象
				CacheConfiguration cacheConfig = new CacheConfiguration(mac, 0);

				// 设置cache在内存中的element个数
				cacheConfig
						.setMaxElementsInMemory(GlobalConfig.CACHE_AGENT_NUM);

				// 设置cache在磁盘中的element个数
				cacheConfig.setMaxElementsOnDisk(GlobalConfig.CACHE_AGENT_NUM);

				// 设置是否持久化到磁盘
				cacheConfig.overflowToOffHeap(false);

				//
				cacheConfig.memoryStoreEvictionPolicy("FIFO");

				// 设置缓存存活时间
				cacheConfig.setTimeToIdleSeconds(0);

				// 设置缓存的间隔时间
				cacheConfig.setTimeToLiveSeconds(0);

				// 配置检索项
				Searchable searchable = new Searchable();
				cacheConfig.addSearchable(searchable);
				searchable.addSearchAttribute(new SearchAttribute().name("mac")
						.expression("value.getMac()"));

				// 将cache放入容器
				cacheManager.addCache(new Cache(cacheConfig));

				// 获得cache
				cache = cacheManager.getCache(msg.getIp() + "soft");

			}

		}

		if (cache != null) {
			// 将agent状态存到缓存中
			cache.put(new Element(msg.getSoftId(), msg));
			//System.out.println("缓存大小：" + cache.getSize());
		}

	}
	/**
	 * <监控win进程信息> <把这些信息放内存中>
	 * 
	 * @param progressMsg
	 * @param cacheManager
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("deprecation")
	public static void progressStorageHandling(WinProgressMsg progressMsg,
			CacheManager cacheManager) {

		Cache cache = null;
		// //System.out.println("progress      "+msg.getTimestamp());
		// 判断时间戳是不是跟以前的一样 不一样清空以前的信息再放
		if (!timestampProgress.equals(progressMsg.getTimestamp())) {

			cacheManager.removeCache(progressMsg.getIp() + "progress");
			// cache = cacheManager.getCache(msg.getIp() + "progress");
			/*
			 * if (cache != null) { cache.removeAll(); }
			 */

		}
		// 换为ip
		String mac = progressMsg.getIp() + "progress";

		if (mac != null) {
			// 初始化cache
			if (cacheManager.cacheExists(mac)) {
				// 如果cache存在，从容器中获得
				cache = cacheManager.getCache(mac);
			} else {
				// 如果cache不存在，配置新cache
				// 创建cache配置对象
				CacheConfiguration cacheConfig = new CacheConfiguration(mac, 0);

				// 设置cache在内存中的element个数
				cacheConfig
						.setMaxElementsInMemory(GlobalConfig.CACHE_AGENT_NUM);

				// 设置cache在磁盘中的element个数
				cacheConfig.setMaxElementsOnDisk(GlobalConfig.CACHE_AGENT_NUM);

				// 设置是否持久化到磁盘
				cacheConfig.overflowToOffHeap(false);

				//
				cacheConfig.memoryStoreEvictionPolicy("FIFO");

				// 设置缓存存活时间
				cacheConfig.setTimeToIdleSeconds(0);

				// 设置缓存的间隔时间
				cacheConfig.setTimeToLiveSeconds(0);

				// 配置检索项
				Searchable searchable = new Searchable();
				cacheConfig.addSearchable(searchable);
				searchable.addSearchAttribute(new SearchAttribute().name("mac")
						.expression("value.getMac()"));

				// 将cache放入容器
				cacheManager.addCache(new Cache(cacheConfig));

				// 获得cache
				cache = cacheManager.getCache(progressMsg.getIp() + "progress");

			}

		} /*else {
			// System.out.println("系统信息:   MAC为空!!!");
		}*/

		if (cache != null) {
			// 将agent状态存到缓存中
			cache.put(new Element(progressMsg.getProgressNO(), progressMsg));// 这里放到进程名,不知道为什么代码写的跟服务的一样那个行
			// 这个就不行
			// System.out.println("缓存大小：" + cache.getSize());
		}

	}

	/**
	 * <监控linux进程信息> <把这些信息放内存中>
	 * 
	 * @param progressMsg
	 * @param cacheManager
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("deprecation")
	public static void progressStorageHandling(LinuxProgressMsg progressMsg,
			CacheManager cacheManager) {

		Cache cache = null;
		// //System.out.println("progress      "+msg.getTimestamp());
		// 判断时间戳是不是跟以前的一样 不一样清空以前的信息再放
		if (!timestampProgress.equals(progressMsg.getTimestamp())) {

			cacheManager.removeCache(progressMsg.getIp() + "progress");
			// cache = cacheManager.getCache(msg.getIp() + "progress");
			/*
			 * if (cache != null) { cache.removeAll(); }
			 */

		}
		// 换为ip
		String mac = progressMsg.getIp() + "progress";

		if (mac != null) {
			// 初始化cache
			if (cacheManager.cacheExists(mac)) {
				// 如果cache存在，从容器中获得
				cache = cacheManager.getCache(mac);
			} else {
				// 如果cache不存在，配置新cache
				// 创建cache配置对象
				CacheConfiguration cacheConfig = new CacheConfiguration(mac, 0);

				// 设置cache在内存中的element个数
				cacheConfig
						.setMaxElementsInMemory(GlobalConfig.CACHE_AGENT_NUM);

				// 设置cache在磁盘中的element个数
				cacheConfig.setMaxElementsOnDisk(GlobalConfig.CACHE_AGENT_NUM);

				// 设置是否持久化到磁盘
				cacheConfig.overflowToOffHeap(false);

				//
				cacheConfig.memoryStoreEvictionPolicy("FIFO");

				// 设置缓存存活时间
				cacheConfig.setTimeToIdleSeconds(0);

				// 设置缓存的间隔时间
				cacheConfig.setTimeToLiveSeconds(0);

				// 配置检索项
				Searchable searchable = new Searchable();
				cacheConfig.addSearchable(searchable);
				searchable.addSearchAttribute(new SearchAttribute().name("mac")
						.expression("value.getMac()"));

				// 将cache放入容器
				cacheManager.addCache(new Cache(cacheConfig));

				// 获得cache
				cache = cacheManager.getCache(progressMsg.getIp() + "progress");

			}

		} /*else {
			// System.out.println("系统信息:   MAC为空!!!");
		}*/

		if (cache != null) {
			// 将agent状态存到缓存中
			cache.put(new Element(progressMsg.getProgressNO(), progressMsg));// 这里放到进程名,不知道为什么代码写的跟服务的一样那个行
			// 这个就不行
			// System.out.println("缓存大小：" + cache.getSize());
		}

	}

	/**
	 * <Win监控服务信息> <把这些信息放内存中>
	 * 
	 * @param msg
	 * @param cacheManager
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("deprecation")
	public static void serviceStorageHandling(WinServiceMsg msg,
			CacheManager cacheManager) {
		Cache cache = null;
		// 判断时间戳是不是跟以前的一样 不一样清空以前的信息再放
		if (!timestampService.equals(msg.getTimestamp())) {

			// cache = cacheManager.getCache(msg.getIp() + "service");
			cacheManager.removeCache(msg.getIp() + "service");
			/*
			 * if (cache != null) { cache.removeAll(); }
			 */

		}
		// 换为ip
		String mac = msg.getIp() + "service";

		if (mac != null) {
			// 初始化cache
			if (cacheManager.cacheExists(mac)) {
				// 如果cache存在，从容器中获得
				cache = cacheManager.getCache(mac);
			} else {
				// 如果cache不存在，配置新cache
				// 创建cache配置对象
				CacheConfiguration cacheConfig = new CacheConfiguration(mac, 0);

				// 设置cache在内存中的element个数
				cacheConfig
						.setMaxElementsInMemory(GlobalConfig.CACHE_AGENT_NUM);

				// 设置cache在磁盘中的element个数
				cacheConfig.setMaxElementsOnDisk(GlobalConfig.CACHE_AGENT_NUM);

				// 设置是否持久化到磁盘
				cacheConfig.overflowToOffHeap(false);

				//
				cacheConfig.memoryStoreEvictionPolicy("FIFO");

				// 设置缓存存活时间
				cacheConfig.setTimeToIdleSeconds(0);

				// 设置缓存的间隔时间
				cacheConfig.setTimeToLiveSeconds(0);

				// 配置检索项
				Searchable searchable = new Searchable();
				cacheConfig.addSearchable(searchable);
				searchable.addSearchAttribute(new SearchAttribute().name("mac")
						.expression("value.getMac()"));

				// 将cache放入容器
				cacheManager.addCache(new Cache(cacheConfig));

				// 获得cache
				cache = cacheManager.getCache(msg.getIp() + "service");

			}

		} /*else {
			// System.out.println("系统信息:   MAC为空!!!");
		}*/

		if (cache != null) {
			// 将agent状态存到缓存中
			cache.put(new Element(msg.getServiceNO(), msg));
			// System.out.println("缓存大小：" + cache.getSize());
		}

	}

	/**
	 * <linux监控服务信息> <把这些信息放内存中>
	 * 
	 * @param msg
	 * @param cacheManager
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings("deprecation")
	public static void serviceStorageHandling(LinuxServiceMsg msg,
			CacheManager cacheManager) {
		Cache cache = null;
		// 判断时间戳是不是跟以前的一样 不一样清空以前的信息再放
		if (!timestampService.equals(msg.getTimestamp())) {

			// cache = cacheManager.getCache(msg.getIp() + "service");
			cacheManager.removeCache(msg.getIp() + "service");
			/*
			 * if (cache != null) { cache.removeAll(); }
			 */

		}
		// 换为ip
		String mac = msg.getIp() + "service";

		if (mac != null) {
			// 初始化cache
			if (cacheManager.cacheExists(mac)) {
				// 如果cache存在，从容器中获得
				cache = cacheManager.getCache(mac);
			} else {
				// 如果cache不存在，配置新cache
				// 创建cache配置对象
				CacheConfiguration cacheConfig = new CacheConfiguration(mac, 0);

				// 设置cache在内存中的element个数
				cacheConfig
						.setMaxElementsInMemory(GlobalConfig.CACHE_AGENT_NUM);

				// 设置cache在磁盘中的element个数
				cacheConfig.setMaxElementsOnDisk(GlobalConfig.CACHE_AGENT_NUM);

				// 设置是否持久化到磁盘
				cacheConfig.overflowToOffHeap(false);

				//
				cacheConfig.memoryStoreEvictionPolicy("FIFO");

				// 设置缓存存活时间
				cacheConfig.setTimeToIdleSeconds(0);

				// 设置缓存的间隔时间
				cacheConfig.setTimeToLiveSeconds(0);

				// 配置检索项
				Searchable searchable = new Searchable();
				cacheConfig.addSearchable(searchable);
				searchable.addSearchAttribute(new SearchAttribute().name("mac")
						.expression("value.getMac()"));

				// 将cache放入容器
				cacheManager.addCache(new Cache(cacheConfig));

				// 获得cache
				cache = cacheManager.getCache(msg.getIp() + "service");

			}

		} /*else {
			// System.out.println("系统信息:   MAC为空!!!");
		}*/

		if (cache != null) {
			// 将agent状态存到缓存中
			cache.put(new Element(msg.getServiceNO(), msg));
			// System.out.println("缓存大小：" + cache.getSize());
		}

	}

	/**
	 * <原始日志入库> <功能详细描述>
	 * 
	 * @param wac
	 *            WebApplicationContext
	 * @param map
	 *            Map<String, Object>
	 * @see [类、类#方法、类#成员]
	 */
	public static void rawLogStorageHandling(EventsService eventsManager,
			NotAnalyticEventsService notAnalyticEventsManager,
			Map<String, Object> map) {

		// 获得当前时间
		//long currentTime = new Date().getTime();

		/* 获得昨天的日期 */
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd").format(cal
				.getTime());

		/* 查询昨天的日志表中是否存在与当前的原始日志对应的解析日志 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableName", "tbl_" + yesterday);
		paramMap.put("identification", map.get("identification"));
		Events events = null;
		//String tableName = "tbl_" + yesterday;

		/**
		 * 屏蔽当事件入库时查询数据表唯一标识功能 -- 苑恒润 20140408
		 */
		// if (eventsManager.existsTable(tableName)!=0) {
		// events = eventsManager.queryEvents(paramMap);
		// String temp = ori
		// }

		// paramMap.remove("tableName");
		paramMap.put("tableName", "tbl_" + yesterday + "_not_analytic_events");
		paramMap.put("identification", map.get("identification"));
		// paramMap
		NotAnalyticEvents notAnalytic = null;
		if (notAnalyticEventsManager.existsTable("tbl_" + yesterday
				+ "_not_analytic_events") != 0) {
			notAnalytic = notAnalyticEventsManager
					.queryNotAnalyticEvents(paramMap);
		}
		if (events != null || notAnalytic != null) {
			map.put("tableName", "tbl_" + yesterday + "_original_log");
			// map.put("tableName", "tbl_"+yesterday);
		} else {
			map.put("tableName",
					"tbl_"
							+ new SimpleDateFormat("yyyyMMdd")
									.format(new Date()) + "_original_log");
			// map.put("tableName", "tbl_" + new
			// SimpleDateFormat("yyyyMMdd").format(new Date()));
		}

		// 将原始日志放入原始日志队列
		GlobalConfig.rawLogQueue.add(map);

		GlobalConfig.temporaryRawLogQueue.add(GlobalConfig.rawLogQueue.poll());

		// 判断是否到达执行事务时间
		// if((currentTime - GlobalConfig.storeRawLogQueueTime) >=
		// GlobalConfig.storeDataInterval)
		/*
		 * if (GlobalConfig.rawLogQueue.size() >= GlobalConfig.QUEUE_MAX_NUMBER)
		 * { // 将原始日志队列中的原始日志放入临时队列中 synchronized (GlobalConfig.rawLogQueue) {
		 * if (!GlobalConfig.rawLogQueue.isEmpty()) { while
		 * (!GlobalConfig.rawLogQueue.isEmpty()) {
		 * GlobalConfig.temporaryRawLogQueue
		 * .add(GlobalConfig.rawLogQueue.poll()); } } else {
		 * //System.out.println("系统信息:   原始日志队列中没有原始日志！！！！"); }
		 * 
		 * // 重新记录开始时间 GlobalConfig.storeRawLogQueueTime = new Date().getTime();
		 * }
		 * 
		 * // 将临时队列中的原始日志插入数据库 synchronized (GlobalConfig.temporaryRawLogQueue)
		 * { if (!GlobalConfig.temporaryRawLogQueue.isEmpty()) {
		 * eventsManager.insertOriginLog(GlobalConfig.temporaryRawLogQueue); }
		 * else { //System.out.println("系统信息:   临时原始日志队列中没有概要事件！！！！"); } } }
		 * else if ((currentTime - GlobalConfig.storeRawLogQueueTime) >=
		 * GlobalConfig.storeDataInterval) { // 将原始日志队列中的原始日志放入临时队列中
		 * synchronized (GlobalConfig.rawLogQueue) { if
		 * (!GlobalConfig.rawLogQueue.isEmpty()) { while
		 * (!GlobalConfig.rawLogQueue.isEmpty()) {
		 * GlobalConfig.temporaryRawLogQueue
		 * .add(GlobalConfig.rawLogQueue.poll()); } } else {
		 * //System.out.println("系统信息:   原始日志队列中没有原始日志！！！！"); }
		 * 
		 * // 重新记录开始时间 GlobalConfig.storeRawLogQueueTime = new Date().getTime();
		 * }
		 * 
		 * // 将临时队列中的原始日志插入数据库 synchronized (GlobalConfig.temporaryRawLogQueue)
		 * { if (!GlobalConfig.temporaryRawLogQueue.isEmpty()) {
		 * eventsManager.insertOriginLog(GlobalConfig.temporaryRawLogQueue); }
		 * else { //System.out.println("系统信息:   临时原始日志队列中没有概要事件！！！！"); } } }
		 */
		eventsManager.insertOriginLog(GlobalConfig.temporaryRawLogQueue);
	}

	/**
	 * <事件日志告警> <事件日志根据告警规则告警>
	 * 
	 * @param wac
	 *            WebApplicationContext
	 * @param eventsId
	 *            long
	 * @param eventsType
	 *            String
	 * @param oriPriority
	 *            String
	 * @see [类、类#方法、类#成员]
	 */
	public static void logAlarmHandling(AlertRuleService alertRuleManager,
			List<AlertRule> ruleList ,
			long identification, String eventsName, String eventsType,
			String category, long sDDr, long Daddr, String DeviceIp,
			String DeviceType, String oriPriority, String logTableName,
			long assetId, String assetName, long num, long sAddr, long sPort,
			long dAddr, long dPort, String DevName,long sendtime) {
		Map<String, Object> dataMap = new HashMap<String, Object>();

		if (StringUtil.isNotEmpty(String.valueOf(identification))) {
			dataMap.put("identification", String.valueOf(identification));
		}

		if (StringUtil.isNotEmpty(eventsName)) {
			dataMap.put("eventsName", eventsName);
		}
		if (StringUtil.isNotEmpty(eventsType)) {
			dataMap.put("eventsType", eventsType);
		}

		dataMap.put("SAddr", sDDr);

		dataMap.put("DAddr", Daddr);

		if (StringUtil.isNotEmpty(category)) {
			dataMap.put("category", category);
		}
		if (StringUtil.isNotEmpty(DeviceIp)) {
			dataMap.put("deviceIp", DeviceIp);
		}
		if (StringUtil.isNotEmpty(DeviceType)) {

			dataMap.put("deviceType", DeviceType);
		}
		if (StringUtil.isNotEmpty(oriPriority)) {
			dataMap.put("oriPriority", oriPriority);
		}
		if (StringUtil.isNotEmpty(logTableName)) {
			dataMap.put("logTableName",
					logTableName.substring(logTableName.indexOf("_") + 1));
		}
		if (assetId != 0) {
			dataMap.put("assetId", assetId);
		}
		if (StringUtil.isNotEmpty(assetName)) {
			dataMap.put("assetName", assetName);
		}
		if (sAddr != 0) {
			dataMap.put("sAddr", sAddr);
		}

		dataMap.put("sPort", sPort);

		if (dAddr != 0) {
			dataMap.put("dAddr", dAddr);
		}

		dataMap.put("dPort", dPort);

		if (StringUtil.isNotEmpty(DevName)) {
			dataMap.put("DevName", DevName);
		}
		// 临时加的数量
		dataMap.put("num", num);
		
		dataMap.put("sendTime", sendtime);

		alertRuleManager.logAlarmHandling(dataMap,ruleList);
	}

	/**
	 * <监控告警> <根据阀值设置进行告警>
	 * 
	 * @param wac
	 *            WebApplicationContext
	 * @param agentModel
	 *            AgentModel
	 * @see [类、类#方法、类#成员]
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void agentAlarmHandling(WebApplicationContext wac,
			AgentModel agentModel, CacheManager cacheManager) {

		String mac = agentModel.getIp();

		Cache cache = null;

		if (mac != null) {
			// 初始化cache
			if (cacheManager.cacheExists(mac)) {
				// 如果cache存在，从容器中获得
				cache = cacheManager.getCache(mac);
			}

			if (cache != null) {
				//Element hddElement = null;

				// 获得告警阀值Service
				SettingService settingManager = (SettingService) wac
						.getBean("settingManager");

				// 获得监控告警Service
				MonitorAlarmService monitorAlarmManager = (MonitorAlarmService) wac
						.getBean("monitorAlarmManager");

				// 获得资产Service
				AssetService assetManager = (AssetService) wac
						.getBean("assetManager");

				// 获得资产
				Map map = new HashMap<String, Object>();
				map.put("selAssetMac", mac);
				List<Asset> assetList = assetManager.query(map);
				/*
				 * if(assetList.size() > 0) {
				 */
				Asset asset = assetList.get(0);

				// 告警-cpu使用率超过cpu阀值
				String cpuThreshold = settingManager
						.queryByKey("cpu_threshold"); // cpu阀值
				String cpuTime = settingManager.queryByKey("cpu_time"); // cpu再次告警间隔
				String cpuRank = settingManager.queryByKey("cpu_rank"); // cpu级别
				/* //System.out.println("刘延旭CPU级别---"+cpuRank); */
				if (StringUtil.isNotEmpty(cpuThreshold)
						&& StringUtil.isNotEmpty(cpuTime)
						&& StringUtil.isNotEmpty(cpuRank)) {

					// 对比agent的cpu使用率与cpu阀值
					if (agentModel.getCpuOccupy() >= Integer
							.valueOf(cpuThreshold)) {

						// 获得cpu上一次超过告警阀值的时间
						if (cache.isElementInMemory("cpu_last_time")) {
							Element cpuElement = cache.get("cpu_last_time");

							Long lastTime = (Long) cpuElement.getValue();
							long interval = (agentModel.getSystemTime() - lastTime) / 1000;

							// 判断上一次的时间与当前时间的间隔是否超过间隔时间
							if (interval >= Long.valueOf(cpuTime)) {
								// 初始化告警对象
								MonitorAlarm monitorAlarm = new MonitorAlarm();
								monitorAlarm.setMonitorAlarmContent("阀值告警");
								monitorAlarm.setMonitorAlarmIndex("CPU使用率");
								monitorAlarm.setMonitorAlarmRank(Integer
										.valueOf(cpuRank));
								monitorAlarm.setMonitorAlarmThreshold(Integer
										.valueOf(cpuThreshold));
								monitorAlarm
										.setMonitorAlarmCurrentValue(agentModel
												.getCpuOccupy());
								monitorAlarm.setMonitorAlarmTime(new Date());
								monitorAlarm.setMonitorAlarmAssetId(asset
										.getAssetId());

								// 执行插入告警信息操作
								monitorAlarmManager.insert(monitorAlarm);

								// 触发告警后，将cache中的时间清空
								cache.remove("cpu_last_time");
								cache.put(new Element("cpu_last_time", agentModel
										.getSystemTime()));
							}

						} else {
							
							MonitorAlarm monitorAlarm = new MonitorAlarm();
							monitorAlarm.setMonitorAlarmContent("阀值告警");
							monitorAlarm.setMonitorAlarmIndex("CPU使用率");
							monitorAlarm.setMonitorAlarmRank(Integer
									.valueOf(cpuRank));
							monitorAlarm.setMonitorAlarmThreshold(Integer
									.valueOf(cpuThreshold));
							monitorAlarm
									.setMonitorAlarmCurrentValue(agentModel
											.getCpuOccupy());
							monitorAlarm.setMonitorAlarmTime(new Date());
							monitorAlarm.setMonitorAlarmAssetId(asset
									.getAssetId());

							// 执行插入告警信息操作
							monitorAlarmManager.insert(monitorAlarm);
							// 如果不存在上一次超过告警阀值的时间，将当前的这条agent的系统时间放入cache中
							cache.put(new Element("cpu_last_time", agentModel
									.getSystemTime()));
						}

					} /*else {

						// 如果最新的这条agent信息没有达到告警阀值，将cache中存储的时间清空
						//if (cache.isElementInMemory("cpu_last_time")) {
						//	Element cpuElement = cache.get("cpu_last_time");
						//	cache.remove("cpu_last_time");
					   //}
					}*/
				} /*else {
					// System.out.println("系统信息:   未设置cpu告警阀值信息");
				}*/

				// 告警-内存使用率超过内存阀值
				String ramThreshold = settingManager
						.queryByKey("ram_threshold"); // 内存阀值
				String ramTime = settingManager.queryByKey("ram_time"); // 内存再次告警间隔
				String ramRank = settingManager.queryByKey("ram_rank"); // 内存级别
				// System.out.println("刘延旭内存---"+ramRank);
				if (StringUtil.isNotEmpty(ramThreshold)
						&& StringUtil.isNotEmpty(ramTime)
						&& StringUtil.isNotEmpty(ramRank)) {
					if (agentModel.getCacheOccupy() >= Integer
							.valueOf(ramThreshold)) {
						if (cache.isElementInMemory("ram_last_time")) {
							Element ramElement = cache.get("ram_last_time");

							Long lastTime = (Long) ramElement.getValue();
							long interval = (agentModel.getSystemTime() - lastTime) / 1000 / 60;

							// 判断上一次的时间与当前时间的间隔是否超过间隔时间
							if (interval >= Long.valueOf(ramTime)) {
								MonitorAlarm monitorAlarm = new MonitorAlarm();
								monitorAlarm.setMonitorAlarmContent("阀值告警");
								monitorAlarm.setMonitorAlarmIndex("内存使用率");
								// 修改，原来存的是cpu的告警级别。----李长红20140402
								monitorAlarm.setMonitorAlarmRank(Integer
										.valueOf(ramRank));
								// 修改，原来存的是cpu的阀值。----李长红20140402
								monitorAlarm.setMonitorAlarmThreshold(Integer
										.valueOf(ramThreshold));
								monitorAlarm
										.setMonitorAlarmCurrentValue(agentModel
												.getCacheOccupy());
								monitorAlarm.setMonitorAlarmTime(new Date());
								monitorAlarm.setMonitorAlarmAssetId(asset
										.getAssetId());

								// 执行插入告警信息操作
								monitorAlarmManager.insert(monitorAlarm);

								// 触发告警后，将cache中的时间清空
								cache.remove("ram_last_time");
								cache.put(new Element("ram_last_time", agentModel
										.getSystemTime()));
							}
						} else {
							MonitorAlarm monitorAlarm = new MonitorAlarm();
							monitorAlarm.setMonitorAlarmContent("阀值告警");
							monitorAlarm.setMonitorAlarmIndex("内存使用率");
							// 修改，原来存的是cpu的告警级别。----李长红20140402
							monitorAlarm.setMonitorAlarmRank(Integer
									.valueOf(ramRank));
							// 修改，原来存的是cpu的阀值。----李长红20140402
							monitorAlarm.setMonitorAlarmThreshold(Integer
									.valueOf(ramThreshold));
							monitorAlarm
									.setMonitorAlarmCurrentValue(agentModel
											.getCacheOccupy());
							monitorAlarm.setMonitorAlarmTime(new Date());
							monitorAlarm.setMonitorAlarmAssetId(asset
									.getAssetId());

							// 执行插入告警信息操作
							monitorAlarmManager.insert(monitorAlarm);
							// 如果不存在上一次超过告警阀值的时间，将当前的这条agent的系统时间放入cache中
							cache.put(new Element("ram_last_time", agentModel
									.getSystemTime()));
						}
					} /*else {
						
						// 如果最新的这条agent信息没有达到告警阀值，将cache中存储的时间清空
						if (cache.isElementInMemory("ram_last_time")) {
							Element cpuElement = cache.get("ram_last_time");
							cache.remove("ram_last_time");
						}
					}*/
				}

				// 告警-硬盘使用超过硬盘阀值
				if (StringUtil.isNotEmpty(agentModel.getDiscSize())) {
					String discInfo = agentModel.getDiscSize();

					if (discInfo.indexOf("|") != -1) {
						String[] str = discInfo.split("\\|");
						for (int i = 0; i < str.length; i++) {
							String[] info = str[i].split("_");
							if (info != null) {
								hddHandling(cache, settingManager,
										monitorAlarmManager, info, agentModel,
										asset.getAssetId());
							}
						}
					}
				}

				// 告警-上行流量使用超过流量阀值
				String flowUp = settingManager.queryByKey("flow_up");// 上行流量阀值
				String flowUpRank = settingManager.queryByKey("flow_up_rank"); // 上行流量级别
				if (StringUtil.isNotEmpty(flowUp)
						&& StringUtil.isNotEmpty(flowUpRank)) {
					// 判断上行流量是否大于设置的告警信息
					if (agentModel.getTransmitFlow() >= Integer.valueOf(flowUp)) {
						MonitorAlarm monitorAlarm = new MonitorAlarm();
						monitorAlarm.setMonitorAlarmContent("阀值告警");
						monitorAlarm.setMonitorAlarmIndex("上行流量");
						monitorAlarm.setMonitorAlarmRank(Integer
								.valueOf(flowUpRank));
						monitorAlarm.setMonitorAlarmThreshold(Integer
								.valueOf(flowUp));
						monitorAlarm.setMonitorAlarmCurrentValue(agentModel
								.getTransmitFlow());
						monitorAlarm.setMonitorAlarmTime(new Date());
						monitorAlarm.setMonitorAlarmAssetId(asset.getAssetId());
						// 执行插入告警信息操作
						monitorAlarmManager.insert(monitorAlarm);
					}

				}
				// 告警-下行流量使用超过流量阀值
				String flowDown = settingManager.queryByKey("flow_down");// 上行流量阀值
				String flowDownRank = settingManager
						.queryByKey("flow_down_rank"); // 上行流量级别
				if (StringUtil.isNotEmpty(flowDown)
						&& StringUtil.isNotEmpty(flowDownRank)) {
					// 判断上行流量是否大于设置的告警信息
					if (agentModel.getReceiveFlow() >= Integer
							.valueOf(flowDown)) {
						MonitorAlarm monitorAlarm = new MonitorAlarm();
						monitorAlarm.setMonitorAlarmContent("阀值告警");
						monitorAlarm.setMonitorAlarmIndex("下行流量");
						monitorAlarm.setMonitorAlarmRank(Integer
								.valueOf(flowDownRank));
						monitorAlarm.setMonitorAlarmThreshold(Integer
								.valueOf(flowDown));
						monitorAlarm.setMonitorAlarmCurrentValue(agentModel
								.getReceiveFlow());
						monitorAlarm.setMonitorAlarmTime(new Date());
						monitorAlarm.setMonitorAlarmAssetId(asset.getAssetId());
						// 执行插入告警信息操作
						monitorAlarmManager.insert(monitorAlarm);
					}

				}

				/*
				 * String flowUp = settingManager.queryByKey("flow_up"); //
				 * 上行流量阀值 String flowUpRank =
				 * settingManager.queryByKey("flow_up_rank"); // 上行流量级别
				 * if(StringUtil.isNotEmpty(flowUp) &&
				 * StringUtil.isNotEmpty(flowUpRank)) { if
				 * (agentModel.getTransmitFlow() >= Integer.valueOf(flowUp)) {
				 * if(cache.isElementInMemory("flow_up_last_time")) { Element
				 * flowUpElement = cache.get("flow_up_last_time");
				 * 
				 * Long lastTime = (Long)flowUpElement.getValue(); long interval
				 * = (agentModel.getSystemTime().getTime() - lastTime)/1000;
				 * 
				 * // 判断上一次的时间与当前时间的间隔是否超过间隔时间 if(interval >=
				 * Long.valueOf(ramTime)) { MonitorAlarm monitorAlarm = new
				 * MonitorAlarm(); monitorAlarm.setMonitorAlarmContent("阀值告警");
				 * monitorAlarm.setMonitorAlarmIndex("内存使用率");
				 * monitorAlarm.setMonitorAlarmRank(Integer.valueOf(cpuRank));
				 * monitorAlarm
				 * .setMonitorAlarmThreshold(Integer.valueOf(cpuThreshold));
				 * monitorAlarm
				 * .setMonitorAlarmCurrentValue(agentModel.getCpuOccupy());
				 * monitorAlarm.setMonitorAlarmTime(new Date());
				 * 
				 * // 执行插入告警信息操作 monitorAlarmManager.insert(monitorAlarm);
				 * 
				 * // 触发告警后，将cache中的时间清空 cache.remove("ram_last_time"); } else {
				 * // 如果不存在上一次超过告警阀值的时间，将当前的这条agent的系统时间放入cache中 cache.put(new
				 * Element("ram_last_time",
				 * agentModel.getSystemTime().getTime())); } } else { //
				 * 如果最新的这条agent信息没有达到告警阀值，将cache中存储的时间清空
				 * if(cache.isElementInMemory("ram_last_time")) { Element
				 * cpuElement = cache.get("ram_last_time");
				 * cache.remove("ram_last_time"); } } } else {
				 * //System.out.println("未设置上行流量阀值信息"); } }
				 * 
				 * // 告警-下行流量使用超过流量阀值 String flowDown =
				 * settingManager.queryByKey("flow_down"); // 下行流量阀值 String
				 * flowDownRank = settingManager.queryByKey("flow_down_rank");
				 */// 下行流量级别
			}
		} /*else {
			// System.out.println("系统信息:   MAC【 " + mac + "】的资产为注册!!!");
		}*/
		/*
		 * } else { //System.out.println("系统信息:   MAC为空!!!"); }
		 */
	}

	/**
	 * <硬盘告警的处理方法> <根据需要处理的磁盘 不同，获得cache中存储的此磁盘的上一次超过阀值的时间，比对时间间隔，根据间隔做是否告警的处理>
	 * 
	 * @param drive
	 *            String
	 * @param used
	 *            int
	 * @param total
	 *            int
	 * @see [类、类#方法、类#成员]
	 */
	public static void hddHandling(Cache cache, SettingService settingManager,
			MonitorAlarmService monitorAlarmManager, String[] info,
			AgentModel agentModel, long assetId) {
		// 硬盘阀值信息
		String hddThreshold = settingManager.queryByKey("hdd_threshold"); // 硬盘阀值
		String hddTime = settingManager.queryByKey("hdd_time"); // 硬盘再次告警间隔
		String hddRank = settingManager.queryByKey("hdd_rank"); // 硬盘级别

		/* //System.out.println("刘延旭硬盘级别---"+hddRank); */
		// 硬盘状况
		int drivelength = info.length;
		String drive = "";
		for (int i = 0; i < drivelength - 2; i++) {
			drive += info[i];
		}

		double used = Double.valueOf(info[drivelength - 2]); // 已使用
		double total = Double.valueOf(info[drivelength - 1]); // 总容量
		double occupy = (used * 100) / total; // 硬盘占用率

		// 判断硬盘占用率是否达到阀值
		if (occupy >= Integer.valueOf(hddThreshold)) {

			// 判断cache中是否存在硬盘超过阀值的元素
			if (cache.isElementInMemory("hdd_last_time")) {
				// 获得元素
				Element ramElement = cache.get("hdd_last_time");
				@SuppressWarnings("unchecked")
				Map<String, Long> map = (Map<String, Long>) ramElement
						.getValue();

				// 判断此资产的当前盘符是否有超过阀值的历史
				if (map.containsKey(drive)) {
					// 获得上一次此磁盘超过阀值的时间
					Long lastTime = map.get(drive);

					// 获得上一次超过阀值距现在的时间间隔
					long interval = (agentModel.getSystemTime() - lastTime)
							/ 1000 / 60 / 60 / 24;

					// 判断上一次的时间与当前时间的间隔是否超过间隔时间，触发告警
					if (interval >= Long.valueOf(hddTime)) {
						MonitorAlarm monitorAlarm = new MonitorAlarm();
						monitorAlarm.setMonitorAlarmContent(drive + "目录 阀值告警");
						monitorAlarm.setMonitorAlarmIndex("硬盘使用率");
						monitorAlarm.setMonitorAlarmRank(Integer
								.valueOf(hddRank));
						monitorAlarm.setMonitorAlarmThreshold(Integer
								.valueOf(hddThreshold));
						monitorAlarm.setMonitorAlarmCurrentValue(occupy);
						monitorAlarm.setMonitorAlarmTime(new Date());
						monitorAlarm.setMonitorAlarmAssetId(assetId);

						// 执行插入告警信息操作
						monitorAlarmManager.insert(monitorAlarm);

						// 触发告警后，将cache中的时间清空
						map.remove(drive);
						cache.remove("hdd_last_time");
						cache.put(new Element("hdd_last_time", map));
					}
				} else {
					// 将此磁盘超过阀值的系统时间存入cache
					MonitorAlarm monitorAlarm = new MonitorAlarm();
					monitorAlarm.setMonitorAlarmContent(drive + "目录  阀值告警");
					monitorAlarm.setMonitorAlarmIndex("硬盘使用率");
					monitorAlarm.setMonitorAlarmRank(Integer
							.valueOf(hddRank));
					monitorAlarm.setMonitorAlarmThreshold(Integer
							.valueOf(hddThreshold));
					monitorAlarm.setMonitorAlarmCurrentValue(occupy);
					monitorAlarm.setMonitorAlarmTime(new Date());
					monitorAlarm.setMonitorAlarmAssetId(assetId);

					// 执行插入告警信息操作
					monitorAlarmManager.insert(monitorAlarm);
					map.put(drive, agentModel.getSystemTime());
					cache.remove("hdd_last_time");
					cache.put(new Element("hdd_last_time", map));
				}

			} else {
				
				MonitorAlarm monitorAlarm = new MonitorAlarm();
				monitorAlarm.setMonitorAlarmContent(drive + "目录  阀值告警");
				monitorAlarm.setMonitorAlarmIndex("硬盘使用率");
				monitorAlarm.setMonitorAlarmRank(Integer
						.valueOf(hddRank));
				monitorAlarm.setMonitorAlarmThreshold(Integer
						.valueOf(hddThreshold));
				monitorAlarm.setMonitorAlarmCurrentValue(occupy);
				monitorAlarm.setMonitorAlarmTime(new Date());
				monitorAlarm.setMonitorAlarmAssetId(assetId);

				// 执行插入告警信息操作
				monitorAlarmManager.insert(monitorAlarm);
				Map<String, Long> map = new HashMap<String, Long>();
				map.put(drive, agentModel.getSystemTime());

				// 如果不存在上一次超过告警阀值的时间，将当前的这条agent的系统时间放入cache中
				cache.put(new Element("hdd_last_time", map));
			}
		} /*else {
			// 如果最新的这条agent信息没有达到告警阀值，将cache中存储的时间清空
			if (cache.isElementInMemory("hdd_last_time")) {
				Element cpuElement = cache.get("hdd_last_time");

				Map<String, Long> map = (Map<String, Long>) cpuElement
						.getValue();

				// 将此磁盘的上一次超过阀值的时间记录清空
				if (map.containsKey(drive)) {
					map.remove(drive);

					// 此处执行删除的目的是：cache是采用先进先出的存储方式的，当cache内的元素达到一定数量时，就会将最先存入的元素移除

					cache.remove("hdd_last_time");
					cache.put(new Element("hdd_last_time", map));
				}
			}
		}*/
	}

}
