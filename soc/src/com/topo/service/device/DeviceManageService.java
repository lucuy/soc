package com.topo.service.device;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.topo.model.device.Device;
import com.topo.model.device.NetBackGround;
import com.topo.model.deviceCategory.DeviceCategory;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface DeviceManageService {
	//添加设备信息
	void addDevice(Device device) throws SQLException;
	//删除设备信息
	void delDevice(int... ids) throws SQLException;
	//编辑设备信息
	void editDevice(Device device) throws SQLException;
	//查询所有的设备信息
	List<Device> queryAllDevice(Map map)throws SQLException;
	//根据设备ID到设备
	Device queryDeviceById(long id)throws SQLException;
	//根据设备ID查询与该设备像关联的设备
	List<Device> queryAssociationDeviceByID(long id)throws SQLException;
	//按条件分页查询设备信息
	public SearchResult query(Map map,Page page)throws SQLException;
	/**
     * 查询设备总记录数
     */
    public int deviceCount(Map map) throws SQLException;
    
    public int queryDeviceByDeviceCategoryID(long id)throws SQLException;
    /**
     * 根据设备名称查询设备类型信息
     */
    public DeviceCategory queryDeviceCategoryByDeviceName(String name)throws SQLException;
    /**
     * 更新（临时设备表中的数据来更新设备表中的数据时调用）
     */
    public void updateDeviceByTempDevice(Device device);
    
    /**
     * 更新（根据ping的结果改变设备的状态） 
     */
	public void updateDeviceStatusByPing(Device device);
	/**
	 * 查询soc间的设备列表
	 */
	public List<Device> queryAllOutDevice();
	/**
	 * 根据资产ID查询设备信息
	 */
	public Device queryDeviceByAssetId(long assetId);
	/**
	 * 根据资产ID删除设备信息，可以传1个或多个参数
	 */
	void deleteDeviceByAssetIds(int... ids) ;
	/**
	 * 根据资产的启用状态改变设备的状态
	 */
	public void updateDeviceStatusByAssetStatus(Device device);
	/**
	 * 根据资产ID更新所有的设备信息
	 */
	public void updateDeviceByAssetId(Device device);
	
	///添加背景Panle
	public void saveBackGround(NetBackGround netBackGround);
		
		///查询所有背景Panle
	public List<NetBackGround> loadRoundrectInfo();
		
	///查询所有背景Panle内网
	public List<NetBackGround> loadRoundrectInfoBM();
	
	public void updateRoundrectInfoById(Map map);
		
	public void delBackGroundById(int id);	
	
	/**
	 * 查询所有的设备信息
	 */
	public List<Device> queryAllDeviceInfo();
	
	/**
	 * 根据设备Ip更新设备的告警状态
	 */
	public void updateDeviceByIp(Device device);
	
	/**
	 * 根据设备Ip查询 
	 */
	public Device queryDeviceByIp(Map map);
	
}
