package com.util.reportForm.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.soc.service.systemsetting.SettingService;
import com.util.reportForm.datadeal.BaseDao;
import com.util.reportForm.datadeal.model.ReportTimer;
import com.util.reportForm.thread.ReportTimerThread;

/**
 * 计时报表计时器
 * 
 * @author zsa
 * 
 */
public class ReportTimerTrigger {
	private static BaseDao dao = new BaseDao();
	private SettingService settingManager;

	public SettingService getSettingManager() {
		return settingManager;
	}

	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}

	public void reportTimerTask() {
		//System.out.println("report timer start");
		String hql="from ReportTimer";
		//得到所有的数据——显示选定的报表
		List<ReportTimer> allReportTimer=(List<ReportTimer>)dao.getNamedQuery(hql,null);
		if(allReportTimer.size()>0){
			ReportTimer rt=allReportTimer.get(0);
			boolean flag=isORNotExcute(rt.getWeekSet(),rt.getDaySet());
			if(flag){
				// 开启线程导出报表
				Thread thread = new Thread(ReportTimerThread.createThread(settingManager,allReportTimer));
				thread.start();
			}
		}
	}
	/**
	 * 时间设定
	 * @param weekSet
	 * @param daySet
	 * @return
	 */
	private static boolean isORNotExcute(String weekSet,String daySet){
		boolean flag = false;
		Date now = new Date();// 当前日期
		Calendar c = Calendar.getInstance();
		c.setTime(now);
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;// 获得今天是星期几
		if(dayOfWeek==0){
			dayOfWeek=7;
		}
		int day=c.get(Calendar.DATE);
	
		if (!weekSet.equals("0000000")) {
			if (weekSet.substring(dayOfWeek-1, dayOfWeek).equals("1")) {
				flag = true;
			} 
		}
		if (!daySet.equals("000000000000000000000000000000")) {
			if (daySet.substring(day,day+ 1).equals("1")) {
				flag = true;
			} 
		}
		return flag;
	}

}
