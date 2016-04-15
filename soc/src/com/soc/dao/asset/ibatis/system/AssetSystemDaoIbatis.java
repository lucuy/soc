package com.soc.dao.asset.ibatis.system;

import java.util.List;
import java.util.Map;

import com.soc.dao.asset.AssetDao;
import com.soc.dao.asset.system.AssetSystemDao;
import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.model.asset.Asset;
import com.soc.model.asset.system.AssetSystem;
import com.soc.model.asset.system.AssetSystemBrand;
import com.soc.model.conf.GlobalConfig;

public class AssetSystemDaoIbatis extends BaseDaoIbatis implements AssetSystemDao {

	@Override
	public List<AssetSystem> queryAssetSystem(Map map) {
		List<AssetSystem> list = this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"asset.queryAssetSystem",map);
		return list;
	}

	@Override
	public long insertAssetSystem(Map map) {
		Object obj = this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.system",map);
		return  Long.parseLong(obj.toString());
	}


	@Override
	public void insertAssetSystemBrand(Map map) {
		this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"insert.systemBrand",map);
	}

	@Override
	public String querySystemNameById(long id) {
		Object obj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"asset.queryNameById",id);
		if (obj!=null) {
			return obj.toString();
		}else {
			return "";
		}
		
	}

	@Override
	public long querySystemIdByName(Map map) {
		Object obj = this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"asset.queryIdByName",map);
		if (obj!=null) {
			return Long.parseLong(obj.toString());
		}else {
			return 0;
		}
		
		
	}

	@Override
	public List<AssetSystem> queryassetSystem(Map map, int startRow,
			int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.AssetSystem",map,startRow,pageSize);
	}

	@Override
	public int countAssetSystem(Map map) {
		Object ob = null;
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"select.assetSystemCount", map);
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
	public void delAssetSystemById(long id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"delete.assetSystemById",id);
	}

	@Override
	public void delAssetSystemByLevel(long id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"delete.assetSystemByLevel",id);
	}

	@Override
	public AssetSystem queryAssetSystemById(long id) {
		return (AssetSystem) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"asset.queryAssetSystemById",id);
	}

	@Override
	public  List<AssetSystem> queryAssetSystemByString(Map map) {
		// TODO Auto-generated method stub
		return  (List<AssetSystem>)this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.assetSystemByString",map);
	}

	@Override
	public AssetSystem queryAssetSystemByName(String assetSystemName) {
		AssetSystem assetSystem=null;
		try {
			assetSystem = (AssetSystem) this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"asset.queryAssetSystemByAssetSystemName",assetSystemName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return assetSystem;
	}

	
	
	
}
