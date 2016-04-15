package com.topo.dao.device.ibatis;

import java.util.List;

import com.topo.dao.device.TempDeviceDao;
import com.topo.dao.ibatis.BaseDaoiBatis;
import com.topo.model.device.TempDevice;

public class TempDeviceDaoIbatis extends BaseDaoiBatis implements TempDeviceDao {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insertTempDevice(TempDevice tempDevice) {
		this.getSqlMapClientTemplate().insert("addTempDevice", tempDevice);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateTempDevice(TempDevice tempDevice) {
		this.getSqlMapClientTemplate().insert("updateTempDevice", tempDevice);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public TempDevice queryByDeviceId(long deviceId) {
		return (TempDevice)this.getSqlMapClientTemplate().queryForObject("queryByDeviceId",deviceId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteTempDevice() {
		this.getSqlMapClientTemplate().insert("deleteTempDevice");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<TempDevice> queryAll() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("tempdeviceAll");
	}

}
