package com.topo.service.device;

import java.util.List;

import com.topo.dao.device.DeviceAlertCountDao;
import com.topo.model.device.DeviceAlertCount;

public interface DeviceAlertCountService {

	/**
	 * 插入
	 */
	public void insertAlertCount(DeviceAlertCount deviceAlertCount);
	
	/**
	 *更新 
	 */
	public void updateAlertCount(DeviceAlertCount deviceAlertCount);
	
	/**
	 * 根据IP更新
	 */
	public void updateAlertCountByIp(DeviceAlertCount deviceAlertCount);
	
	/**
	 * 查询所有的
	 */
	public List<DeviceAlertCount> queryAllAlertCount();
	
	/**
	 * 根据Ip查询
	 */
	public DeviceAlertCount queryAlertCountByIp(String ip);
}
