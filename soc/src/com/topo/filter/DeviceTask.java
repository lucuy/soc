package com.topo.filter;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.service.alert.AlertMessageSerive;
import com.topo.model.device.Device;
import com.topo.service.device.DeviceManageService;
import com.topo.util.Ping;
import com.util.StringUtil;

public class DeviceTask extends TimerTask {
	private static int pno=1;//当前页数
	private static final int DEFAULT_PAGE_SIZE = 10;
	private List<Device> list;
	private String returnMsg="";
	private String memoryUsed="";
	private String memoryTotal="";
	private String hardDisk="";
	private String courseSum="";
	
	ServletContextEvent servletContextEvent=null;
	
	public DeviceTask(ServletContextEvent servletContextEvent) {
		this.servletContextEvent = servletContextEvent;
	}

	@Override
	public void run() {
		//得到注入的Service类
 		WebApplicationContext wac=WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
		DeviceManageService dmc=(DeviceManageService)wac.getBean("deviceService");
		AlertMessageSerive alertMessageManager=(AlertMessageSerive)wac.getBean("alertMessageManager");
		//AssetService assetService=(AssetService)wac.getBean("assetManager");
		Map map1 = new HashMap();
			if(pno==1){
				list=new ArrayList<Device>();
				try {
					list=dmc.queryAllDevice(map1);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			List<Device> selectList=new ArrayList<Device>();
			int totalRecord=list.size();
			int totalPage=0;
			totalPage=totalRecord/DEFAULT_PAGE_SIZE;
			if(totalRecord%DEFAULT_PAGE_SIZE!=0){
				totalPage+=1;
			}
			int startNo=0;
			int endNo=0;
			startNo=(pno-1)*DEFAULT_PAGE_SIZE;
			endNo=pno*DEFAULT_PAGE_SIZE-1;
			if(totalRecord<=endNo){
				endNo=totalRecord-1;
			}
			for(int i=startNo;i<=endNo;i++){
				selectList.add(list.get(i));
			}
			
			for(Device device:selectList){
				String ip=device.getDevice_ip();
				String result="";
				Ping ping=new Ping(ip);
				result=ping.pingIP();
				if(StringUtil.isNotBlank(ip)){
					//Asset asset=assetService.queryAssetByIp(IpConvert.iPTransition(ip));
					if(device.getDevice_status()<2){//如果设备有告警就不用ping了
						if(StringUtil.isNotBlank(result)){
							//如果ping通，则把设备状态改为在线
							device.setDevice_status(Device.STATUS_ONLINE);
						}else{
							//如果ping不通，则把设备状态改为不在线
							device.setDevice_status(Device.STATUS_OFFLINE);
						}
					}
				
				////////----------------------
				
		if(StringUtil.isNotBlank(result)){
				/*	String str=alertMessageManager.getMac(ip,device.getDevice_mac(),String.valueOf(device.getDevice_asset_id()),device.getDevice_name(),device.getDevice_community_name());
					if("1".equals(str)){//str=1,mac没有被换，str=2mac被换
*/						//如果设备mac没有被更换，那么看该设备还有其他告警没有，有的话，则显示其他告警，没有的话，则设备处于正常在线状态
						Map map=new HashMap();
						map.put("ids", device.getDevice_asset_id());
						map.put("workorder", "01");
						List<AlertMessage> amList=new ArrayList<AlertMessage>();
						amList=alertMessageManager.queryByAssetId(map);
						if(amList.size()!=0){
							device.setDevice_status(amList.get(amList.size()-1).getAlertRank());
						}else{
							device.setDevice_status(Device.STATUS_ONLINE);
						}
						dmc.updateDeviceStatusByPing(device);
				//	}
				}else{
					device.setDevice_status(Device.STATUS_OFFLINE);
					dmc.updateDeviceStatusByPing(device);
				}
					 
				}
			}
			pno++;
			if(pno>totalPage){
				pno=1;
			}
		
		//外网设备的状态
		List<Device> outDeviceList=new ArrayList<Device>();
		outDeviceList=dmc.queryAllOutDevice();
		for(Device dev:outDeviceList){
			String ip=dev.getDevice_ip();
			String result="";
			Ping ping=new Ping(ip);
			result=ping.pingIP();
			if(dev.getDevice_status()<2){//如果设备有告警就不用ping了
				if(StringUtil.isNotBlank(result)){
					//如果ping通，则把设备状态改为在线
					dev.setDevice_status(Device.STATUS_ONLINE);
				}else{
					//如果ping不通，则把设备状态改为不在线
					dev.setDevice_status(Device.STATUS_OFFLINE);
				}
			}
			dmc.updateDeviceStatusByPing(dev);
		}
		
	}

}
