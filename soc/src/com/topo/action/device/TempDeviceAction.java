package com.topo.action.device;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.ParserConfigurationException;

import com.soc.model.user.User;
import com.soc.service.asset.AssetService;
import com.topo.action.BaseAction;
import com.topo.model.device.Device;
import com.topo.model.device.TempDevice;
import com.topo.service.device.DeviceManageService;
import com.topo.service.device.TempDeviceService;

public class TempDeviceAction extends BaseAction {

	private TempDeviceService tempDeviceService;
	private DeviceManageService deviceManagerService;
	private AssetService assetManager;//资产管理类
	/**
	 * 添加或更新临时设备表
	 */
	public void editTempDevice(){
		HttpServletRequest request=super.getRequest();
		String strdeviceId=request.getParameter("id");
		String strLocationX=request.getParameter("positionX");
		String strLocationY=request.getParameter("positionY");
		TempDevice tempDevice=new TempDevice();
		tempDevice.setTemp_device_id(Long.parseLong(strdeviceId));
		tempDevice.setTemp_locationx(strLocationX);
		tempDevice.setTemp_locaitony(strLocationY);
		TempDevice td=tempDeviceService.queryByDeviceId(Long.parseLong(strdeviceId));
		if(td!=null){
			tempDeviceService.updateTempDevice(tempDevice);
		}else{
			tempDeviceService.insertTempDevice(tempDevice);
		}
	}
	
	/**
	 * 点击“保存”按钮时，先把临时表中的数据更新到设备表中，然后清空临时表
	 */
	public void save(){
		List<TempDevice> tdList=tempDeviceService.queryAll();
		for(TempDevice td:tdList){
			Device device=new Device();
			device.setDevice_id(td.getTemp_device_id());
			device.setDevice_locationX(td.getTemp_locationx());
			device.setDevice_locationY(td.getTemp_locaitony());
			deviceManagerService.updateDeviceByTempDevice(device);
		}
		tempDeviceService.deleteTempDevice();
		try {
			Map map = new HashMap();
			long groupid=((User) this.getSession().getAttribute("SOC_LOGON_USER")).getAssetGroupid();
	 		String assetId=assetManager.queryIDSByUser(groupid);
	    if(groupid==1){
		}else{
			map.put("alertAssetId", assetId);
		}
			List<Device> deviceList = deviceManagerService.queryAllDevice(map);
			// writeXml = new WriteXml();
			//writeXml.writeTopoXml(deviceList);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	
	public TempDeviceService getTempDeviceService() {
		return tempDeviceService;
	}

	public void setTempDeviceService(TempDeviceService tempDeviceService) {
		this.tempDeviceService = tempDeviceService;
	}

	public DeviceManageService getDeviceManagerService() {
		return deviceManagerService;
	}

	public void setDeviceManagerService(DeviceManageService deviceManagerService) {
		this.deviceManagerService = deviceManagerService;
	}

	public AssetService getAssetManager() {
		return assetManager;
	}

	public void setAssetManager(AssetService assetManager) {
		this.assetManager = assetManager;
	}
	
	
}
