package com.topo.dao.deviceCategory.ibatis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import com.topo.dao.deviceCategory.DeviceCategoryDao;
import com.topo.dao.ibatis.BaseDaoiBatis;
import com.topo.model.deviceCategory.DeviceCategory;
import com.topo.model.deviceCategory.DeviceCategoryPhoto;

public class DeviceCategoryDaoImpl extends BaseDaoiBatis implements DeviceCategoryDao {
	
	//增加设备类型
	public Integer addDeviceCategory(DeviceCategory deviceCategory) throws SQLException {
		return (Integer) this.getSqlMapClientTemplate().insert("addDeviceCategory",deviceCategory);
	}

	//删除设备类型
	public void delDeviceCategory(long... deviceCategoryIds) throws SQLException {
		 this.getSqlMapClientTemplate().delete("deleteDeviceCategoryByIds",deviceCategoryIds);
	}

	//修改设备类型
	public Integer editDeviceCategory(DeviceCategory deviceCategory) throws SQLException {
		return this.getSqlMapClientTemplate().update("editDeviceCategory",deviceCategory);
	}

	//获取全部设备类型
	@Override
	public List<DeviceCategory> queryAllDeviceCategory() throws SQLException {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList("deviceCategoryAll");
	}

	//根据设备类型ID查询设备类型
	@Override
	public DeviceCategory queryDeviceCategoryById(long deviceCategoryId)throws SQLException {
		// TODO Auto-generated method stub
		return (DeviceCategory) this.getSqlMapClientTemplate().queryForObject("queryDeviceCategoryById",deviceCategoryId);
	}
	//根据设备类型名称查询设备类型
		@Override
		public DeviceCategory queryDeviceCategoryByName(String deviceCategoryName)throws SQLException {
			// TODO Auto-generated method stub
			return (DeviceCategory) this.getSqlMapClientTemplate().queryForObject("queryDeviceCategoryByName",deviceCategoryName);
		}
	//查询所有设备类型的图片路径
	public List<DeviceCategoryPhoto> queryAllDeviceCategoryPhoto()throws SQLException{
		return this.getSqlMapClientTemplate().queryForList("queryAllDeviceCategoryPhoto");
	}

/*	public Integer batchInsert(String sqlId, Object[] paraObject)
			throws SQLException {
		if(paraObject == null || paraObject.length == 0) {
			return 0;
		}
		
		this.getSqlMapClientTemplate().startBatch();
		for(Object obj : paraObject) {
			this.getSqlMapClientTemplate().insert(sqlId, obj);
		}
		
		return this.getSqlMapClientTemplate().executeBatch();
	}

	public Integer batchDelete(String sqlId, Object[] paraObject)
			throws SQLException {
		if(paraObject == null || paraObject.length == 0) {
			return 0;
		}
		
		this.getSqlMapClientTemplate().startBatch();
		for(Object obj : paraObject) {
			sqlMapClient.delete(sqlId, obj);
		}
		
		return sqlMapClient.executeBatch();
	}

	public Integer batchUpdate(String sqlId, Object[] paraObject)
			throws SQLException {
		if(paraObject == null || paraObject.length == 0) {
			return 0;
		}
		
		sqlMapClient.startBatch();
		for(Object obj : paraObject) {
			sqlMapClient.update(sqlId, obj);
		}
		
		return sqlMapClient.executeBatch();
	}*/
	
	/*public Integer getRecordCount(String sqlId, Map<String, Object> paraMap)
			throws SQLException {
		return (Integer) this.getSqlMapClientTemplate().queryForObject(sqlId, paraMap);
	}
	
	public Object queryObjectById(String sqlId, Object id)
			throws SQLException {
		return this.getSqlMapClientTemplate().queryForObject(sqlId, id);
	}

	public Object queryObject(String sqlId, Map<String, Object> paraMap)
			throws SQLException {
		return this.getSqlMapClientTemplate().queryForObject(sqlId, paraMap);
	}
	
	public List<?> queryList(String sqlId, Map<String, Object> paraMap)
			throws SQLException {
		return (List<?>) this.getSqlMapClientTemplate().queryForList(sqlId, paraMap);
	}*/

}
