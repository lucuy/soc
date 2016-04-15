package com.topo.dao.device.ibatis;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.topo.dao.device.CommonDao;
import com.topo.dao.ibatis.BaseDaoiBatis;
import com.topo.model.device.Device;
import com.topo.model.device.NetBackGround;
import com.topo.model.deviceCategory.DeviceCategory;

public class CommonDaoImpl extends BaseDaoiBatis implements CommonDao {
	
	/**
	 * {@inheritDoc}
	 */
	public Integer addObject(Device device)
			throws SQLException {
		return (Integer) this.getSqlMapClientTemplate().insert("addDevice",device);
	}

	/**
	 * {@inheritDoc}
	 */
	public Integer editObject(Device device)
			throws SQLException {
		return this.getSqlMapClientTemplate().update("editDevice",device);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Device> queryAllDevice(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("deviceAll",map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Device queryDeviceById(long id) throws SQLException {
		// TODO Auto-generated method stub
		return (Device) this.getSqlMapClientTemplate().queryForObject("queryDeviceById",id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteDeviceByIds(int... ids) throws SQLException {
		this.getSqlMapClientTemplate().delete("deleteDeviceByIds", ids);
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Device> queryAssociationDeviceByID(long id) throws SQLException {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("queryAssociationDeviceByID", String.valueOf(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Device> query(Map map, int startRow, int pageSize)
			throws SQLException {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("queryAllDeviceUseFenYe",map,startRow,pageSize);
	}

	public int queryDeviceByDeviceCategoryID(long id)throws SQLException{
		return (Integer) this.getSqlMapClientTemplate().queryForObject("queryDeviceByDeviceCategoryID", id);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deviceCount(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return (Integer)this.getSqlMapClientTemplate().queryForObject("deviceCount",map);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeviceCategory queryDeviceCategoryByDeviceName(String name)
			throws SQLException {
		// TODO Auto-generated method stub
		return (DeviceCategory)this.getSqlMapClientTemplate().queryForObject("queryDeciceCategoryById",name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateDeviceByTempDevice(Device device) {
		this.getSqlMapClientTemplate().update("updateDeviceByTempDevice", device);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateDeviceStatusByPing(Device device) {
		this.getSqlMapClientTemplate().update("updateDeviceStatusByPing", device);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Device> queryAllOutDevice() {
		return this.getSqlMapClientTemplate().queryForList("deviceAllDeviceOut");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Device queryDeviceByAssetId(long assetId) {
		return (Device)this.getSqlMapClientTemplate().queryForObject("queryDeviceByAssetId",assetId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteDeviceByAssetIds(int... ids) {
		this.getSqlMapClientTemplate().delete("deleteDeviceByAssetIds", ids);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateDeviceStatusByAssetStatus(Device device) {
		this.getSqlMapClientTemplate().update("updateDeviceStatusByAssetStatus", device);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateDeviceByAssetId(Device device) {
		this.getSqlMapClientTemplate().update("editDeviceByAssetId", device);
	}
	@Override
	public void saveBackGround(NetBackGround netBackGround) {
		 this.getSqlMapClientTemplate().insert("addBackGround",netBackGround);
		
	}

	@Override
	public List<NetBackGround> loadRoundrectInfo() {
		return this.getSqlMapClientTemplate().queryForList("loadAllRoundrectInfo");
		
	}

	@Override
	public List<NetBackGround> loadRoundrectInfoBM() {
		return this.getSqlMapClientTemplate().queryForList("loadAllRoundrectInfoBM");
		
	}
	
	@Override
	public void updateRoundrectInfoById(Map map) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().update("updateRoundrectInfoById", map);
	}

	@Override
	public void delBackGroundById(int id) {
		// TODO Auto-generated method stub
		this.getSqlMapClientTemplate().delete("delBackGroundById", id);
	}

	@Override
	public List<Device> queryAllDeviceInfo() {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("deviceAllInfo");
	}

	@Override
	public void updateDeviceByIp(Device device) {
		this.getSqlMapClientTemplate().update("updateDeviceStatusByIp",device);
		
	}

	@Override
	public Device queryDeviceByIp(Map map) {
		return (Device)this.getSqlMapClientTemplate().queryForObject("queryDeviceByIp",map);
	}




}
