package com.topo.service.device.impl;

import java.util.List;

import com.topo.dao.device.DeviceAlertCountDao;
import com.topo.model.device.DeviceAlertCount;
import com.topo.service.device.DeviceAlertCountService;
import com.topo.service.impl.BaseServiceImpl;

public class DeviceAlertCountServiceImpl extends BaseServiceImpl implements
		DeviceAlertCountService {

	private DeviceAlertCountDao deviceAlertCountDao;
	@Override
	public void insertAlertCount(DeviceAlertCount deviceAlertCount) {
		// TODO Auto-generated method stub
		deviceAlertCountDao.insertAlertCount(deviceAlertCount);
	}

	@Override
	public void updateAlertCount(DeviceAlertCount deviceAlertCount) {
		// TODO Auto-generated method stub
		deviceAlertCountDao.updateAlertCount(deviceAlertCount);
	}

	@Override
	public void updateAlertCountByIp(DeviceAlertCount deviceAlertCount) {
		// TODO Auto-generated method stub
		deviceAlertCountDao.updateAlertCountByIp(deviceAlertCount);
	}

	@Override
	public List<DeviceAlertCount> queryAllAlertCount() {
		// TODO Auto-generated method stub
		return deviceAlertCountDao.queryAllAlertCount();
	}

	public DeviceAlertCountDao getDeviceAlertCountDao() {
		return deviceAlertCountDao;
	}

	public void setDeviceAlertCountDao(DeviceAlertCountDao deviceAlertCountDao) {
		this.deviceAlertCountDao = deviceAlertCountDao;
	}

	@Override
	public DeviceAlertCount queryAlertCountByIp(String ip) {
		// TODO Auto-generated method stub
		return deviceAlertCountDao.queryAlertCountByIp(ip);
	}

	

	
}
