package com.topo.dao.device.ibatis;

import java.util.List;

import com.topo.dao.device.DeviceAlertCountDao;
import com.topo.dao.ibatis.BaseDaoiBatis;
import com.topo.model.device.DeviceAlertCount;

public class DeviceAlertCountIbatis extends BaseDaoiBatis implements
		DeviceAlertCountDao {

	@Override
	public void insertAlertCount(DeviceAlertCount deviceAlertCount) {
		this.getSqlMapClientTemplate().insert("addDeviceAlertCount", deviceAlertCount);

	}

	@Override
	public void updateAlertCount(DeviceAlertCount deviceAlertCount) {
		this.getSqlMapClientTemplate().insert("editDeviceAlertCount", deviceAlertCount);
	}

	@Override
	public void updateAlertCountByIp(DeviceAlertCount deviceAlertCount) {
		this.getSqlMapClientTemplate().insert("editDeviceAlertCountByIp", deviceAlertCount);

	}

	@Override
	public List<DeviceAlertCount> queryAllAlertCount() {
		return this.getSqlMapClientTemplate().queryForList("deviceAlertCountAllInfo");
	}

	@Override
	public DeviceAlertCount queryAlertCountByIp(String ip) {
		// TODO Auto-generated method stub
		return (DeviceAlertCount)this.getSqlMapClientTemplate().queryForObject("queryDeviceAlertCountByIp",ip);
	}

}
