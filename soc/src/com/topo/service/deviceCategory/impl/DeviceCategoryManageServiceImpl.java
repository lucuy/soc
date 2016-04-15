package com.topo.service.deviceCategory.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.topo.dao.deviceCategory.DeviceCategoryDao;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.model.deviceCategory.DeviceCategoryPhoto;
import com.topo.service.deviceCategory.DeviceCategoryManageService;
import com.topo.service.impl.BaseServiceImpl;

public class DeviceCategoryManageServiceImpl extends BaseServiceImpl implements DeviceCategoryManageService {
	
	

	private DeviceCategoryDao deviceCategoryDao;

	public void setDeviceCategoryDao(DeviceCategoryDao deviceCategoryDao) {
		this.deviceCategoryDao = deviceCategoryDao;
	}
	

	public void addDeviceCategory(DeviceCategory deviceCategory) throws SQLException {
		deviceCategoryDao.addDeviceCategory(deviceCategory);
	}

	public void delDeviceCategory(long... deviceCategoryIds) throws SQLException {
		deviceCategoryDao.delDeviceCategory(deviceCategoryIds);
	}

	public void editDeviceCategory(DeviceCategory deviceCategory) throws SQLException {
		deviceCategoryDao.editDeviceCategory(deviceCategory);
	}

	/*public Device getDeviceById(String did) throws SQLException {
		return (Device) commonDao.queryObjectById("Device.getDeviceById", did);
	}*/


	public List<DeviceCategory> queryAllDeviceCategory()throws SQLException {
		// TODO Auto-generated method stub
		return deviceCategoryDao.queryAllDeviceCategory();
	}

	public DeviceCategory queryDeviceCategoryById(long id)throws SQLException{
		return deviceCategoryDao.queryDeviceCategoryById(id);
	}
	
	public DeviceCategory queryDeviceCategoryByName(String name)throws SQLException{
		return deviceCategoryDao.queryDeviceCategoryByName(name);
	}
	
	//或取所有设备类型图片的路径
	public List<DeviceCategoryPhoto> queryAllDeviceCategoryPhoto()throws SQLException{
		return deviceCategoryDao.queryAllDeviceCategoryPhoto();
	}
	//@SuppressWarnings("unchecked")
	/*public List<Device> getDevice(Device device, Pagination pagination)
			throws SQLException {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("device", device);
		pagination.setRecordCount((Integer) commonDao.queryObject("Device.getRecordCount", paraMap));
		paraMap.put("pagination", pagination);
		return (List<Device>) commonDao.queryList("Device.getDevice", paraMap);
	}*/

}
