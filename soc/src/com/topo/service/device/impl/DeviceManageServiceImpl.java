package com.topo.service.device.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.topo.dao.device.CommonDao;
import com.topo.model.device.Device;
import com.topo.model.device.NetBackGround;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.service.device.DeviceManageService;
import com.topo.service.impl.BaseServiceImpl;
import com.util.page.Page;
import com.util.page.SearchResult;

public class DeviceManageServiceImpl extends BaseServiceImpl implements DeviceManageService {
	
	private CommonDao commonDao;

	
	public void setCommonDao(CommonDao commonDao) {
		this.commonDao = commonDao;
	}
	/**
	 * {@inheritDoc}
	 */
	public void addDevice(Device device) throws SQLException {
		commonDao.addObject(device);
	}

	/**
	 * {@inheritDoc}
	 */
	public void editDevice(Device device) throws SQLException {
		commonDao.editObject(device);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Device> queryAllDevice(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return commonDao.queryAllDevice(map);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void delDevice(int... ids) throws SQLException {
		// TODO Auto-generated method stub
		commonDao.deleteDeviceByIds(ids);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Device queryDeviceById(long id) throws SQLException {
		// TODO Auto-generated method stub
		return commonDao.queryDeviceById(id);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Device> queryAssociationDeviceByID(long id) throws SQLException {
		// TODO Auto-generated method stub
		return commonDao.queryAssociationDeviceByID(id);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public SearchResult query(Map map, Page page)throws SQLException {
		int rowCount=commonDao.deviceCount(map);
		page.setTotalCount(rowCount);
		List<Device> list=commonDao.query(map, page.getStartIndex(), page.getPageSize());
		SearchResult sr=new SearchResult();
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int deviceCount(Map map) throws SQLException {
		// TODO Auto-generated method stub
		return commonDao.deviceCount(map);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public DeviceCategory queryDeviceCategoryByDeviceName(String name)
			throws SQLException {
		// TODO Auto-generated method stub
		return commonDao.queryDeviceCategoryByDeviceName(name);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateDeviceByTempDevice(Device device) {
		this.commonDao.updateDeviceByTempDevice(device);
	}
	public int queryDeviceByDeviceCategoryID(long id)throws SQLException{
		return commonDao.queryDeviceByDeviceCategoryID(id);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateDeviceStatusByPing(Device device) {
		this.commonDao.updateDeviceStatusByPing(device);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Device> queryAllOutDevice() {
		return this.commonDao.queryAllOutDevice();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Device queryDeviceByAssetId(long assetId) {
		// TODO Auto-generated method stub
		return this.commonDao.queryDeviceByAssetId(assetId);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void deleteDeviceByAssetIds(int... ids) {
		this.commonDao.deleteDeviceByAssetIds(ids);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateDeviceStatusByAssetStatus(Device device) {
		this.commonDao.updateDeviceStatusByAssetStatus(device);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateDeviceByAssetId(Device device) {
		this.commonDao.updateDeviceByAssetId(device);
	}
	
	@Override
	public void saveBackGround(NetBackGround netBackGround) {
		// TODO Auto-generated method stub
		commonDao.saveBackGround(netBackGround);
	}
	@Override
	public List<NetBackGround> loadRoundrectInfo() {
		// TODO Auto-generated method stub
	return 	commonDao.loadRoundrectInfo();
	}
	@Override
	public void updateRoundrectInfoById(Map map) {
		// TODO Auto-generated method stub
		commonDao.updateRoundrectInfoById(map);
	}
	@Override
	public void delBackGroundById(int id) {
		commonDao.delBackGroundById(id);
	}
	@Override
	public List<NetBackGround> loadRoundrectInfoBM() {
		// TODO Auto-generated method stub
		return commonDao.loadRoundrectInfoBM();
	}
	@Override
	public List<Device> queryAllDeviceInfo() {
		// TODO Auto-generated method stub
		return commonDao.queryAllDeviceInfo();
	}
	@Override
	public void updateDeviceByIp(Device device) {
		this.commonDao.updateDeviceByIp(device);
		
	}
	@Override
	public Device queryDeviceByIp(Map map) {
		// TODO Auto-generated method stub
		return this.commonDao.queryDeviceByIp(map);
	}
	
		

}
