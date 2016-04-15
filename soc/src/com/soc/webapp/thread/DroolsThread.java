package com.soc.webapp.thread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContextEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.events.Events;
import com.soc.model.systemsetting.Collector;
import com.soc.service.asset.AssetService;
import com.soc.service.events.EventsService;
import com.soc.service.systemsetting.SettingCollectorService;
import com.soc.service.systemsetting.impl.LogWriteServiceImpl;
import com.util.Base64;
import com.util.StringUtil;
import com.util.analytic.LogAnalytic;

public class DroolsThread {
	/**
	 * 注释内容
	 */

	private transient Log Log = LogFactory.getLog(getClass());

	public static int count = 0;

	/**
	 * <默认构造函数>
	 */
	public DroolsThread() {

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
	 * {@inheritDoc}
	 */
	public void run() {
		Log.info("Monitor Listener Thread start...");

		//List<Events> events = new ArrayList<Events>();// 声明一个解析后的events集合
		List<String> dataList = GlobalConfig.logDroolsQueue.poll();// 获得装事件字符串的list

		if (dataList != null) {
		}

		/*
		 * //启动关联规则 让他自己做就完了 for (Queue<String> string : GlobalConfig.ruleNames)
		 * { GlobalConfig.socRuleEngine.executeRuleEngine(events,string); }
		 */

		// java线程结束会清据嘛
	}

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
			final List<String> dataList,
			final ServletContextEvent servletContextEvent) {
		return new Runnable() {
			public void run() {

			}
		};
	}

	/**
	 * <处理接收到的信息的队列的方法> <功能详细描述>
	 * 
	 * @param cacheManager
	 * @param servletContextEvent
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public static Runnable handleThread(
			final ServletContextEvent servletContextEvent) {
		return new Runnable() {
			public void run() {
				while (true) {

					List<String> dataList = GlobalConfig.logDroolsQueue.poll();

					if (dataList != null&&!GlobalConfig.ruleNameList.isEmpty()) {
					
						//long start5 = new Date().getTime();
						for (String data : dataList) {
							//long startTask = new Date().getTime();
							if (data.indexOf("|") != -1) {
								//long start = new Date().getTime();

								// 得到注入service类
								WebApplicationContext wac = WebApplicationContextUtils
										.getRequiredWebApplicationContext(servletContextEvent
												.getServletContext());

								SettingCollectorService collectorManager = (SettingCollectorService) wac
										.getBean("settingLoggerManager");
								EventsService eventsManager = (EventsService) wac
										.getBean("eventsManager");
								// 日志转发service
								LogWriteServiceImpl logManager = (LogWriteServiceImpl) wac
										.getBean("logManager");
								if (collectorIsExists(collectorManager, data)) {

//									String ipUserForProgressAndService = data
//											.split(" ")[1];

									String collectorMac = data.substring(0,
											data.indexOf("|"));

									// System.out.println(collectorMac);

									try {

										String MDmessage = data.split(" ")[2];

										// //System.out.println(MDmessage);
										// 得到资产的Ip
										// String
										// collectorMac=data.split(" ")[1];

										String message = Base64
												.decodeString(MDmessage);

										// System.out.println(message);
										// 把接收到日志转发出去
										// logManager.writeSystemAuditLog(message);

										int dataType = DataProcessingCenterThread
												.dataReader(message);

										// System.out.println("解析事件类型时间："+ (new
										// Date().getTime() - start)+ "毫秒");

										// 如果接收的数据的格式是正确的
										if (dataType != GlobalConfig.FORMAT_THE_WRONG_DATA
												&& dataType != 0) {
											AssetService assetManager = (AssetService) wac
													.getBean("assetManager");
											// 解析后的日志的处理
											if (dataType == GlobalConfig.ANALYTIC_FINISHED) {
//												long start1 = new Date()
//														.getTime();
												Map<String, Object> map = LogAnalytic
														.logAnalytic(message,
																assetManager,collectorMac);
												// System.out.println("解析日志为Event、summaryEvent对象的时间："
												// + (new Date()
												// .getTime() - start1));

												// 这里为啥这么写?
												Events events = (Events) map
														.get("events");

												// 存储日志
												if (map != null
														&& (!map.isEmpty())) {
													// 获得事件对象
													Events sourceEvents = (Events) map
															.get("events");

													long assetId = sourceEvents
															.getAssetId();

													Asset asset = GlobalConfig.assetGlobleMap.get(assetId);
													if (asset != null) {
														// 资产不存在 事件不入库
														if (!sourceEvents
																.getType()
																.trim()
																.equals("120000")) {
															// 启动关联规则 让他自己做就完了
															
					/*	GlobalConfig.drools.executeRuleEngineList(events,string,0,eventsManager);*/
																
																
					GlobalConfig.drools.executeRuleEngineCondition(events, eventsManager,asset);
					GlobalConfig.drools.executeRuleEngineResult(events, eventsManager,asset);
					
											
														}
													} 
//														else {
//														System.out
//																.println("系统信息:  资产未注册!!!");
//													}

												}

											}
										}
										// System.out.println("时间信息：         【开始时间-"
										// + start + "毫秒】 "
										// + (new Date().getTime() - start)
										// + "毫秒");
									} catch (Exception e) {
										e.printStackTrace();
										// System.out.println("日志格式不正确。。。。。");
									}
								} 
								else {
									System.out.println("采集器未注册。。。。");
								}
							} else {
								// System.out.println("系统信息:   数据格式错误！！！！");
							}

							// System.out.println("一个任务执行的时间："
							// + (new Date().getTime() - startTask) + "毫秒");
						}

						// System.out.println("循环" + dataList.size() + "次需要【"
						// + (new Date().getTime() - start5) + "】毫秒");
					}
					
					
                   try {
					   Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		};
	}

}
