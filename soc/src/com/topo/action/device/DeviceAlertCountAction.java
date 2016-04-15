package com.topo.action.device;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.soc.model.alert.alertMessage.AlertMessage;
import com.soc.model.user.User;
import com.soc.service.alert.AlertMessageSerive;
import com.soc.service.asset.AssetService;
import com.topo.action.BaseAction;
import com.topo.model.device.DeviceAlertCount;
import com.topo.service.device.DeviceAlertCountService;

public class DeviceAlertCountAction extends BaseAction {

	private DeviceAlertCountService deviceAlertCountService;
	private AlertMessageSerive alertMessageManager;
	private AssetService assetManager;//资产管理类
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void queryAlertCount() throws IOException{
		HttpServletRequest request=super.getRequest();
		HttpServletResponse response=super.getResponse();
		List<DeviceAlertCount> list=deviceAlertCountService.queryAllAlertCount();
		List<AlertMessage> list1=new ArrayList<AlertMessage>();
		//AlertMessage alertMessage=new AlertMessage();
		/*Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long timstmp =  cal.getTimeInMillis();
        alertMessage.setAlertCreateDatetime(timstmp);*/
        //alertMessage.setAlertMark(1);
		Map map = new HashMap();
    	map.put("alertMark", 0);
    	long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
 		String assetId=assetManager.queryIDSByUser(groupid);
    if(groupid==1){
	}else{
		map.put("alertAssetId", assetId);
	}
    	list1=alertMessageManager.queryAllAlertInfo(map);
		StringBuffer sb=new StringBuffer();
		for(DeviceAlertCount bean:list){
			sb.append("设备名称：");
			sb.append(bean.getDeviceName());
			sb.append(",设备IP：");
			sb.append(bean.getDeviceAlertCountIp());
			sb.append(",告警数量：");
			sb.append(bean.getDeviceAlertCount());
		}
		for(AlertMessage bean:list1){
			sb.append("事件级别：");
			sb.append(String.valueOf(bean.getAlertRank()));
			sb.append(",事件名称：");
			sb.append(bean.getAlertEventName());
			sb.append(",事件类型：");
			sb.append(bean.getAlertEventType());
			sb.append(",设备名称：");
			sb.append(bean.getAlertDeviceName());
			sb.append(",接收时间：");
			sb.append(bean.getDate());
		}
		System.out.print(sb.toString());
		outPutString(request,response,sb);
    }
    public void outPutString(HttpServletRequest request,HttpServletResponse response,StringBuffer str) throws IOException{
		request.setCharacterEncoding("utf-8");
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/xml; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print(str.toString());
		out.flush();
	}
	
	public DeviceAlertCountService getDeviceAlertCountService() {
		return deviceAlertCountService;
	}

	public void setDeviceAlertCountService(
			DeviceAlertCountService deviceAlertCountService) {
		this.deviceAlertCountService = deviceAlertCountService;
	}
	public AlertMessageSerive getAlertMessageManager() {
		return alertMessageManager;
	}
	public void setAlertMessageManager(AlertMessageSerive alertMessageManager) {
		this.alertMessageManager = alertMessageManager;
	}
	public AssetService getAssetManager() {
		return assetManager;
	}
	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	
}
