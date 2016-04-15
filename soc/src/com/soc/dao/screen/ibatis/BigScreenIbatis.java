package com.soc.dao.screen.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.screen.BigScreenDao;
import com.soc.model.asset.Asset;
import com.soc.model.conf.GlobalConfig;

/**
 * 
 * <大屏展示：首页：Dao的实现类> <>
 * 
 * @author zhaokui
 * @version [版本号, 2012-03-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BigScreenIbatis extends BaseDaoIbatis implements BigScreenDao{

	
	@Override
	public List<Map> queryEventNum(Map map) {
		// 事件统计Top 5
		return super.queryForList(GlobalConfig.sqlId+"screen.indexevent", map);
	}

	
	@Override
	public List<Map> queryAssetsNum() {
		// 资产分布
		return super.queryForList(GlobalConfig.sqlId+"screen.queryAssets");
	}

	@Override
	public int countByAssetGroupId(Map map) {
		 Object ob = null;
	        //根据map中存储的条件查询符合条件的记录数
	        try
	        {
	            ob = super.queryForObject(GlobalConfig.sqlId+"screen.queryAssets", map);
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
	public String AssetGroupNameById(int id) {
		
		return (String) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"screen.queryAssetGroup",id);
	}

	@Override
	public List<Map> countEvents(Map map) {
		return super.queryForList(GlobalConfig.sqlId+"screen.queryCountEvents",map);
	}


	@Override
	public List<Asset> queryAllAsset() {
		Map map = new HashMap();
		return super.queryForList(GlobalConfig.sqlId+"asset.query",map);
	}

}
