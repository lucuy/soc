package com.soc.dao.asset.system;

import java.util.List;
import java.util.Map;

import org.ietf.jgss.Oid;

import com.soc.dao.BaseDao;
import com.soc.model.asset.system.AssetSystem;
import com.soc.model.asset.system.AssetSystemBrand;

/**
 * 关联资源的系统类
 * 
 * @author lc
 *
 */
public interface AssetSystemDao extends BaseDao {
	
	/**
	 * 获得所有系统、系统版本、系统版本号
	 * @param map
	 * @return
	 */
	public List<AssetSystem> queryAssetSystem(Map map);
	
	/**
	 * 添加系统、系统版本、系统版本号
	 * @param map
	 * @return 新增的id
	 */
	public long insertAssetSystem(Map map);
	
	/**
	 * 添加系统品牌
	 * @param map
	 */
	public void insertAssetSystemBrand(Map map);
	
	/**
	 * 获得系统所对应的所有品牌的集合
	 * @param id
	 * @return
	 */
	
	public String querySystemNameById(long id);
	
	public long querySystemIdByName(Map map);
	
	/**
	 * 查询所有资产系统的集合，并进行分页展示
	 * @param map
	 * @param startRow
	 * @param pageSize
	 * @return
	 */
	public List<AssetSystem> queryassetSystem(Map map, int startRow, int pageSize);
	
	
	/**
	 * 查询所有资产系统的记录数
	 * @param map
	 * @return
	 */
	public int countAssetSystem(Map map);
	
	
	/**
	 * 根据id删除资产系统的单条记录
	 * @param id
	 */
	public void delAssetSystemById(long id);
	
	/**
	 * 根据级别id删除资产系统的单挑记录
	 * @param id
	 */
	public void delAssetSystemByLevel(long id);
	
	/**
	 * 根据Id查询资产系统的单条记录
	 * @param id
	 * @return
	 */
	public AssetSystem queryAssetSystemById(long id);
	
	/**
	 * 根据字段模糊查询系统对象
	 * @return AssetSystem
	 */
	public List<AssetSystem> queryAssetSystemByString(Map map);
	/**
	 * 根据名称查询资产系统的单条记录
	 * @param id
	 * @return
	 */
	public AssetSystem queryAssetSystemByName(String assetSystemName);
	
}
