package com.soc.webapp.quartz;

import global.GlobalThreadPool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.monitor.MonitorDatabaseTask;
import com.soc.service.monitor.MonitorDatabaseTaskService;
import com.soc.webapp.listener.CommunicationContextListener;
import com.util.monitorDatabase.MonitorDatabaseUtils;

/**
 * 监控数据库是否在线
 * @author changhong
 *此类已经废弃
 */
public class MonitorDatabaseChangeOnline {
	private MonitorDatabaseTaskService mdtManager;

	public MonitorDatabaseTaskService getMdtManager() {
		return mdtManager;
	}

	public void setMdtManager(MonitorDatabaseTaskService mdtManager) {
		this.mdtManager = mdtManager;
	}
	public void checkOnlie(){
		List<MonitorDatabaseTask> list = mdtManager.queryAll();
		for (MonitorDatabaseTask mdt : list) {
			if (CommunicationContextListener.threadPoolId <= GlobalConfig.MAX_THREADPOOL_NUMBER) {
				CommunicationContextListener.threadPoolId++;
				GlobalThreadPool.pool.execute(createDBThread(
						mdt, mdtManager));
				CommunicationContextListener.threadPoolId--;
			}
		}
	}
	public Runnable createDBThread(final MonitorDatabaseTask mdt,final MonitorDatabaseTaskService mdtServer){
    	return new Runnable(){
    		public void run(){
    			updateOnline(mdt,mdtServer);
    		}
    	};
    }
	private static void updateOnline(MonitorDatabaseTask mdt,MonitorDatabaseTaskService mdtServer){
		boolean falg = MonitorDatabaseUtils.checkOnline(mdt);
		Map map = new HashMap();
		map.put("taskId", mdt.getTaskId());
		if(falg){
			map.put("dbOnline", 1);
		}else{
			map.put("dbOnline", 0);
		}
		mdtServer.updateDBOnlie(map);
	}
}
