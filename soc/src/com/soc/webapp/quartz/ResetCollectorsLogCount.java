package com.soc.webapp.quartz;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Collector;
import com.soc.service.systemsetting.SettingCollectorService;
import com.util.DateUtil;


/**
 * 触发采集器更新日志的统计
 * @author Ren Zhongyuan
 *
 */

public class ResetCollectorsLogCount {
	
	private SettingCollectorService settingLoggerManager;
	
	public SettingCollectorService getSettingLoggerManager() {
		return settingLoggerManager;
	}

	public void setSettingLoggerManager(SettingCollectorService settingLoggerManager) {
		this.settingLoggerManager = settingLoggerManager;
	}

	private List<Collector> getCollectors = null;
	
	private ConcurrentLinkedQueue<Collector> updateCollectors = new ConcurrentLinkedQueue<Collector>();
	
	public void resetCount () {
		
	    String currentTime = DateUtil.curDateHour();
		
		//System.out.println("Start reset collectors' log count (" + currentTime  +  ").......  ");
		
		getCollectors = settingLoggerManager.queryCollector();
		
		if (getCollectors != null && !getCollectors.isEmpty()) {
		
			for(Collector oneCollector:getCollectors) {
				
				oneCollector.setCollectorReceiveEvents(0);
				
				updateCollectors.add(oneCollector);
				
			}
		}
		
		if (updateCollectors != null && !updateCollectors.isEmpty()) {
			
			settingLoggerManager.updateById(updateCollectors);
			
		}
		
		// 清空采集器Map
		GlobalConfig.collector.clear();
		
		List<Collector> collectorList = settingLoggerManager.queryCollector();
		
		for(Collector collector : collectorList)
		{
		    GlobalConfig.collector.put(collector.getCollectorMac(), collector.getCollectorReceiveEvents());
		}
	}
	
	public void updateCount()
	{	   
	    
	    //System.out.println("刘艳旭亲测采集器的状态更改");
	    
	    //System.out.println("liuyanxu123");
	    
	    settingLoggerManager.updateColloectorCount(GlobalConfig.collector);
	    
	    //更改采集器的在线状态
	    settingLoggerManager.updateCollectorIsOnline(GlobalConfig.collectorIsOnline);
	    
	    
	}

}
