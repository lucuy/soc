package com.soc.webapp.quartz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.poi.hssf.util.HSSFColor.GOLD;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.conf.GlobalConfig;
import com.soc.service.alert.AlertMessageSerive;
import com.soc.service.alert.impl.AlertMessageServiceImpl;
import com.soc.service.systemsetting.SettingService;
import com.soc.service.systemsetting.impl.SettingServiceImpl;
import com.util.OSUtil;

public class DiskSpaceMonitor {
	
	private SettingService settingManager = new SettingServiceImpl() ; 
	private AlertMessageSerive alertMessageManager = new AlertMessageServiceImpl() ; 
	public void coreMonitor(){
		
		String OSName = OSUtil.getOSName() ; 
		List<String[]> diskList = new ArrayList<String[]>();
		String[] result = null;
		Process p;
		BufferedReader br = null;
		String occupy = null ; 
		//磁盘告警阈值
		int hddThreshold = 0 ; 
		//磁盘告警等级
		int hddRank = 0 ; 
		AlertMessage alertMessage = null ;
		int num  = 0 ; 
		
		try{
			if(null == OSName || !OSUtil.getOSName().toLowerCase().contains("linux")){
				System.out.println("-----底层操作系统不为LINUX----");

				return ; 
			}
			p = Runtime.getRuntime().exec("df -h");
			br = new BufferedReader(new InputStreamReader(
					p.getInputStream()));
			br.readLine();
			String str = null;
			StringTokenizer token = null;
			str = br.readLine() ; 
			token = new StringTokenizer(str);
			int countToken = token.countTokens() ;
			result = new String[6] ; 
			if(countToken > 0 ){
				for(int i=0 ; i <countToken ; i++){
					result[i] = token.nextToken() ; 
				}
			}
				occupy = result[4] ; 
				if(occupy.endsWith("%")){
					num  = Integer.parseInt(occupy.substring(0, occupy.length()-1)) ;
				}else{
					System.out.println("-----底层操作系统df -h第5位数据不是百分比----");
					return ; 
				}
				System.out.println("每一分执行一次------：" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(System.currentTimeMillis())));
				System.out.println("硬盘的使用率为：   " + num);
				System.out.println("磁盘空间标识--------："+GlobalConfig.diskSpaceFlag);

				hddThreshold = Integer.parseInt(settingManager.queryByKey("hdd_threshold"));
				hddRank = Integer.parseInt(settingManager.queryByKey("hdd_rank"));
				//大于等于阈值告警值时差生告警信息
				if(0 != hddThreshold && num >= hddThreshold){
					System.out.println(" -------- 产生告警信息 -------- ");
					alertMessage=new AlertMessage();
					
					 alertMessage.setAlertRank(hddRank);
					 alertMessage.setSendMode("邮件提示,平台提示,短信提示,snmp,trap/syslog提示");
					 alertMessage.setAlertState(0);
					 alertMessage.setAlertEventName("SOC主机平台磁盘空间不足");
					 alertMessage.setAlertEventType("120023");
					 alertMessage.setAlertDeviceIp("");
					 alertMessage.setAlertDeviceType("");
					 alertMessage.setUserRealName("");
					 alertMessage.setRelEventsIdentification(0L);
					 alertMessage.setAlertCreateDatetime(new Date().getTime());
					 alertMessage.setAlertAssetId(0L);
					 alertMessage.setAlertAssetName("SOC主机平台");
					 SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
					 String date=sdf.format(new java.util.Date()); 
					 alertMessage.setAlertLogTableName(date);
					 alertMessage.setAlarmRuleId(999L);
					 alertMessage.setEventsSourceAdd(0L);
					 alertMessage.setEventsSourcePort(0L);
					 alertMessage.setAlertDate(new Date());
					 alertMessage.setAlertDeviceName("SOC主机平台");
					 alertMessage.setEventsTargetAdd(alertMessage.lIpTransitionStrIpRevert("192.168.1.50"));
					 alertMessage.setMessageOperateDate(new Date());
					 alertMessage.setEventsTargetPort(0L);
					 alertMessage.setEventsSourceAddT("192.168.1.50");
					 alertMessage.setEventsTargetAddT("192.168.1.50");
					
					 alertMessageManager.insertAlertMessage(alertMessage) ; 
					//GlobalConfig.diskSpaceFlag = true ; 
				}
				
				
			}catch(Exception e){
				e.printStackTrace(); 
			}finally{
				if(null != br){
					try {
						br.close() ;
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
				}
			}
	}
	public SettingService getSettingManager() {
		return settingManager;
	}
	public void setSettingManager(SettingService settingManager) {
		this.settingManager = settingManager;
	}
	public AlertMessageSerive getAlertMessageManager() {
		return alertMessageManager;
	}
	public void setAlertMessageManager(AlertMessageSerive alertMessageManager) {
		this.alertMessageManager = alertMessageManager;
	}

	
	
}
