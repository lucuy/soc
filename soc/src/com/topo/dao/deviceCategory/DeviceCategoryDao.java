package com.topo.dao.deviceCategory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.model.deviceCategory.DeviceCategoryPhoto;

public interface DeviceCategoryDao {
	//添加设备类型
	Integer addDeviceCategory(DeviceCategory deviceCategory) throws SQLException;
	//删除设备类型
	void delDeviceCategory(long... deviceCategoryIds) throws SQLException;
	//修改设备类型
	Integer editDeviceCategory(DeviceCategory deviceCategory) throws SQLException;
	//查询所有的设备类型列表
	List<DeviceCategory> queryAllDeviceCategory()throws SQLException;
	//根据设备类型ID到设备类型
	DeviceCategory queryDeviceCategoryById(long deviceCategoryId)throws SQLException;
	//根据设备类型名称到设备类型
	DeviceCategory queryDeviceCategoryByName(String deviceCategoryName)throws SQLException;
	//获取所有设备类型的图片路径
	List<DeviceCategoryPhoto> queryAllDeviceCategoryPhoto()throws SQLException;
	//Integer batchInsert(String sqlId, Object[] paraObject) throws SQLException;
	
	//Integer batchDelete(String sqlId, Object[] paraObject) throws SQLException;
	
	//Integer batchUpdate(String sqlId, Object[] paraObject) throws SQLException;
	
	/*Integer getRecordCount(String sqlId, Map<String, Object> paraMap) throws SQLException;
	
	Object queryObjectById(String sqlId, Object id) throws SQLException;
	
	Object queryObject(String sqlId, Map<String, Object> paraMap) throws SQLException;
	
	List<?> queryList(String sqlId, Map<String, Object> paraMap) throws SQLException;*/
	
	//Long getRecordCount(String sqlId,) throws SQLException;
	
}
