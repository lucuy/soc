package com.topo.service.device;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.topo.model.device.Device;
import com.topo.model.device.TempDevice;
import com.topo.model.deviceCategory.DeviceCategory;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface TempDeviceService {
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
