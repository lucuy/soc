package com.topo.service.deviceCategory;

import java.sql.SQLException;
import java.util.List;

import com.topo.model.device.Device;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.model.deviceCategory.DeviceCategoryPhoto;

public interface DeviceCategoryManageService {
	void addDeviceCategory(DeviceCategory deviceCategory) throws SQLException;
	
	void delDeviceCategory(long... deviceCategoryIds) throws SQLException;
	
	void editDeviceCategory(DeviceCategory deviceCategory) throws SQLException;
	
	//Device getDeviceById(String did) throws SQLException;
	List<DeviceCategory> queryAllDeviceCategory()throws SQLException;
	
	DeviceCategory queryDeviceCategoryById(long id)throws SQLException;
	
	DeviceCategory queryDeviceCategoryByName(String name)throws SQLException;
	
	//获取所有设备类型图片的路径
	List<DeviceCategoryPhoto> queryAllDeviceCategoryPhoto()throws SQLException;
}
