package com.soc.dao.asset.ibatis.device;

import java.util.List;
import java.util.Map;

import com.soc.dao.asset.device.CategoryDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.device.Category;
import com.soc.model.conf.GlobalConfig;

public class CategoryDaoIbatis extends BaseDaoIbatis implements CategoryDao {

	@Override
	public Object insertCategory(Map map) {
		 
		return this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.category",map);
	}

	@Override
	public List<Category> queryCategories(Map map, int startRow, int pageSize) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"select.devicelist",map,startRow,pageSize);
	}

	@Override
	public int count(Map map) {
		   Object ob = null;
	        
	        // 根据map中存储的条件查询符合条件的用户的记录数
	        try
	        {
	            ob = super.queryForObject(GlobalConfig.sqlId+"select.categoryCount", map);
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        
	        //总条数
	        int totalRows = 0;
	        
	        if (ob != null)
	        {
	            totalRows = ((Integer)ob).intValue();
	        }
	        
	        return totalRows;
	}

	@Override
	public void delCategoryById(long id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"delete.categoryById",id);
	}

	@Override
	public void delCategoryByUpsId(long upsid) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"delete.categoryByUpsId",upsid);
	}

	@Override
	public Category queryCategoryById(long id) {
		return (Category) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"select.devicelistById",id);
	}

	@Override
	public Category queryCategoryByCategoryName(String categoryName) {
		return (Category) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"select.devicelistByName",categoryName);
	
	}

}
