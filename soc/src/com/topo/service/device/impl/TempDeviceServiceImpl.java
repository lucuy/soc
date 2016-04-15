package com.topo.service.device.impl;

import java.util.List;

import com.topo.dao.device.TempDeviceDao;
import com.topo.model.device.TempDevice;
import com.topo.service.device.TempDeviceService;
import com.topo.service.impl.BaseServiceImpl;

public class TempDeviceServiceImpl extends BaseServiceImpl implements
		TempDeviceService {

	private TempDeviceDao tempDeviceDao;
	@Override
	public void insertTempDevice(TempDevice tempDevice) {
		this.tempDeviceDao.insertTempDevice(tempDevice);

	}

	@Override
	public void updateTempDevice(TempDevice tempDevice) {
		this.tempDeviceDao.updateTempDevice(tempDevice);
	}

	@Override
	public TempDevice queryByDeviceId(long deviceId) {
		return this.tempDeviceDao.queryByDeviceId(deviceId);
	}

	@Override
	public void deleteTempDevice() {
		this.tempDeviceDao.deleteTempDevice();
	}
	
	@Override
	public List<TempDevice> queryAll() {
		// TODO Auto-generated method stub
		return this.tempDeviceDao.queryAll();
	}
	
	public TempDeviceDao getTempDeviceDao() {
		return tempDeviceDao;
	}

	public void setTempDeviceDao(TempDeviceDao tempDeviceDao) {
		this.tempDeviceDao = tempDeviceDao;
	}

	

	
}
