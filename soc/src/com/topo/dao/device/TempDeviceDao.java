package com.topo.dao.device;

import java.util.List;

import com.topo.model.device.TempDevice;

public interface TempDeviceDao {

	//插入
	public void insertTempDevice(TempDevice tempDevice);
	//更新
	public void updateTempDevice(TempDevice tempDevice);
	//根据设备ID查询
	public TempDevice queryByDeviceId(long deviceId);
	//清空临时表
	public void  deleteTempDevice();
	//查询所有的临时表数据
	public List<TempDevice> queryAll();
}
