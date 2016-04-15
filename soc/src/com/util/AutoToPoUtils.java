package com.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.asset.Asset;
import com.soc.model.asset.AutoToPo;
import com.soc.service.asset.AssetService;
import com.topo.model.device.Device;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.service.device.DeviceManageService;

public class AutoToPoUtils {
	
	public static long assetId=0;
	
	private AssetService assetManager;
	private DeviceManageService   deviceManageService;
	public AutoToPoUtils(AssetService assetManager,DeviceManageService deviceManageService){
		this.assetManager = assetManager;
		this.deviceManageService = deviceManageService;
	}
	
	public  void autoInsert(List<AutoToPo> list){
		
		for (AutoToPo autoToPo : list) {
			if (autoToPo.getType()==1) {
				this.addJiaohuanji(autoToPo);
			}else{
				this.addPC(autoToPo);
			}
			
		}
	}
	private  void addJiaohuanji(AutoToPo autoToPo){
		Map map = new HashMap();
		map.put("mac", autoToPo.getIp());
		List<Asset> assetList = new ArrayList<Asset>();
		assetList = assetManager.checkMac(map);
		Device device = new Device();
		DeviceCategory deviceCategory = new DeviceCategory();
		if (assetList.size()>0) {
			Asset asset1 = assetList.get(0);
			assetId=asset1.getAssetId();
			device = deviceManageService.queryDeviceByAssetId(assetId);
			deviceCategory.setDeviceCategory_id(2);
			device.setDeviceCategory(deviceCategory);
			asset1.setAssetIps(autoToPo.getIp());
			asset1.setAssetGateWays("255.255.255.0");
			assetManager.updateAsset(asset1, "1,", device);
		}else{
			deviceCategory.setDeviceCategory_id(2);
			device.setDevice_locationX("147.15");
			device.setDevice_locationY("52.9");
			device.setDevice_status(1);
			 device.setDevice_asset_id(assetId);
			device.setDeviceCategory(deviceCategory);
			device.setDevice_ip(autoToPo.getIp());//资产IP
			device.setDevice_name(autoToPo.getIp());//资产名称
			device.setDevice_mark(0);
			device.setDevice_community_name("public");
			Asset asset = new Asset();
			asset.setAssetName(autoToPo.getIp());
			asset.setAssetMac(autoToPo.getIp());
			asset.setAssetUserLoginName("admin");
			asset.setAssetIps(autoToPo.getIp());
			asset.setAssetUnName(1);
			asset.setAssetStatus(1);
			// 设置监控文件为空
			asset.setDirectorise("");
			// 设置监控目录为空
			asset.setFile("");
			// 设置特权命令为空
			asset.setAssetPrivilegeCommand("");
			// 设置版本为空
			asset.setVersion("");
			// 设置团体名为空
			asset.setOrganizationName("");
			asset.setAssetIssued(0);
			asset.setAssetImportance(-1);
			asset.setAssetDeviceTypeId(39);
			asset.setAssetSupportDeviceId(41);
			asset.setAssetCollectType("代理");
			asset.setAssetSupportDevice("Windows");
			asset.setAssetDeviceType("Server服务器");
			asset.setAsset_pessponsibility_user_id(38);
			asset.setAsset_answer_userName("admin");
			asset.setAsset_pessonsibility_userName("admin");
			asset.setAsset_answer_user_id(38);
			asset.setAssetGateWays("255.255.255.0");
			asset.setAssetCollectorId(1);
			asset.setAssetCollectorName("本机采集器");
			asset.setAssetCollectType("代理");
			asset.setSecurityPort(23);
			asset.setSecurityLinkeThod("Telnet");
			asset.setSecurityPWD("admin");
			asset.setSecurityUserName("admin");
			assetManager.updateAsset(asset, "1", device);
			
		}

		
	}
	private  void addPC(AutoToPo autoToPo){
		Map map = new HashMap();
		map.put("mac", autoToPo.getSuperIp());
		List<Asset> assetList = new ArrayList<Asset>();
		List<Asset> assetList1 = new ArrayList<Asset>();
		assetList = assetManager.checkMac(map);
		
	
		Device device = new Device();
		Asset asset = new Asset();
		DeviceCategory deviceCategory = new DeviceCategory();
		if(assetList.size()>0){
			Asset suAsset  = assetList.get(0);
	//	Device	device2 = deviceManageService.queryDeviceByAssetId(suAsset.getAssetId());
			map.put("mac", autoToPo.getIp());
			assetList1= assetManager.checkMac(map);
			if(assetList1.size()>0){
				
				asset = assetList1.get(0);
				device = deviceManageService.queryDeviceByAssetId(asset.getAssetId());
				deviceCategory.setDeviceCategory_id(10);
				device.setDevice_asset_id(assetList.get(0).getAssetId());
				device.setDeviceCategory(deviceCategory);
				asset.setAssetIps(autoToPo.getIp());
			
				asset.setAssetGateWays("255.255.255.0");
				assetManager.updateAsset(asset, "1,", device);
				
			}else{
				Device device1 = new Device();
				device1 = deviceManageService.queryDeviceByAssetId(suAsset.getAssetId());
			deviceCategory.setDeviceCategory_id(10);
			device.setDevice_locationX("147.15");
			device.setDevice_locationY("52.9");
			device.setDevice_status(1);
			device.setDevice_asset_id(assetList.get(0).getAssetId());
		   	device.setDevice_association_id(device1.getDevice_id()+"");
			device.setDeviceCategory(deviceCategory);
			device.setDevice_ip(autoToPo.getIp());//资产IP
			device.setDevice_name(autoToPo.getIp());//资产名称
			device.setDevice_mark(0);
			device.setDevice_community_name("public");
			
			asset.setAssetName(autoToPo.getIp());
			asset.setAssetMac(autoToPo.getIp());
			asset.setAssetIps(autoToPo.getIp());
			asset.setAssetUserLoginName("admin");
			asset.setAssetUnName(1);
			asset.setAssetStatus(1);
			asset.setAssetIssued(0);
			// 设置监控文件为空
			asset.setDirectorise("");
			// 设置监控目录为空
			asset.setFile("");
			// 设置特权命令为空
			asset.setAssetPrivilegeCommand("");
			// 设置版本为空
			asset.setVersion("");
			// 设置团体名为空
			asset.setOrganizationName("");
			asset.setAsset_answer_userName("admin");
			asset.setAsset_pessonsibility_userName("admin");
			asset.setAssetImportance(-1);
			asset.setAssetDeviceTypeId(39);
			asset.setAssetSupportDeviceId(41);
			asset.setAssetCollectType("代理");
			asset.setAssetSupportDevice("Windows");
			asset.setAssetDeviceType("Server服务器");
			asset.setAsset_pessponsibility_user_id(38);
			asset.setAsset_answer_user_id(38);
			asset.setAssetGateWays("255.255.255.0");
			asset.setAssetCollectorId(1);
			asset.setAssetCollectorName("本机采集器");
			asset.setAssetCollectType("代理");
			asset.setSecurityPort(23);
			asset.setSecurityLinkeThod("Telnet");
			asset.setSecurityPWD("admin");
			asset.setSecurityUserName("admin");
			assetManager.updateAsset(asset, "1", device);
			}
		}
	}
	
	
	
}
