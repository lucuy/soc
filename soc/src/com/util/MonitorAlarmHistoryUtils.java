package com.util;

import java.math.BigDecimal;
import java.util.Date;

import com.soc.model.conf.AgentModel;
import com.soc.model.progressmsg.MonitorAlarmHistory;
import com.soc.model.progressmsg.WinSoftMsg;
import com.soc.service.monitor.MonitorHistoryService;

public class MonitorAlarmHistoryUtils {
	//增加AMP监控
	public static void addMonitorAlarmHistory(AgentModel agentModel,MonitorHistoryService monitorHistoryManager){
		Date date = new Date();
		MonitorAlarmHistory m= new MonitorAlarmHistory();
		m.setIp(agentModel.getIp());
		m.setMonitorAlarmIndex("Cpu使用率");
		m.setMonitorAlarmCurrentValue(agentModel.getCpuOccupy()+"%");
		m.setMonitorAlarmTime(date);
		MonitorAlarmHistory m1= new MonitorAlarmHistory();
		m1.setIp(agentModel.getIp());
		m1.setMonitorAlarmIndex("内存使用率");
		m1.setMonitorAlarmCurrentValue(agentModel.getCacheOccupy()+"%");
		m1.setMonitorAlarmTime(date);
		MonitorAlarmHistory m2= new MonitorAlarmHistory();
		m2.setIp(agentModel.getIp());
		m2.setMonitorAlarmIndex("发送流量");
		m2.setMonitorAlarmCurrentValue(forMatNumber(agentModel.getTransmitFlow())+"M");
		m2.setMonitorAlarmTime(date);
		MonitorAlarmHistory m4= new MonitorAlarmHistory();
		m4.setIp(agentModel.getIp());
		m4.setMonitorAlarmIndex("接收流量");
		double d = agentModel.getReceiveFlow();
		m4.setMonitorAlarmCurrentValue(forMatNumber(agentModel.getReceiveFlow())+"M");
		m4.setMonitorAlarmTime(date);
		String discInfo = agentModel.getDiscSize();
		if (discInfo.indexOf("|") != -1) {
			String[] str = discInfo.split("\\|");
			for (int i = 0; i < str.length; i++) {
				String[] info = str[i].split("_");
				if (info != null) {
					int drivelength = info.length;
					String drive = "";
					for (int j = 0; j < drivelength - 2; j++) {
						drive += info[j];
						double used = Double.valueOf(info[drivelength - 2]); // 已使用
						double total = Double.valueOf(info[drivelength - 1]); // 总容量
						double occupy = (used * 100) / total; // 硬盘占用率
						MonitorAlarmHistory m3= new MonitorAlarmHistory();
						m3.setIp(agentModel.getIp());
						m3.setMonitorAlarmCurrentValue(occupy+"%");
						m3.setMonitorAlarmIndex("目录 "+drive);
						m3.setMonitorAlarmTime(date);
						monitorHistoryManager.insterMonitorAlarmHistory(m3);
					}
				}
			}
		}
		monitorHistoryManager.insterMonitorAlarmHistory(m);
		monitorHistoryManager.insterMonitorAlarmHistory(m1);
		monitorHistoryManager.insterMonitorAlarmHistory(m2);
	
		monitorHistoryManager.insterMonitorAlarmHistory(m4);
		
	}
	public static String forMatNumber(double d){
		BigDecimal bd = new BigDecimal(d+"");
		return bd.toString();
	}
}
