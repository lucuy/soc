package com.soc.dao.asset;

import java.util.List;

import com.soc.dao.BaseDao;
import com.soc.model.asset.AssetProbe;
/**
 * 资产信息接口
 * @author 张浩磊
 *
 */
public interface AssetProbeDao extends BaseDao {
	/**
	 * 根据MAC查询资产信息
	 */
	public List<AssetProbe> queryByMac(String Mac);
	
	/**
	 * 根据任务id查询探测出来的资产
	 */
	public List<AssetProbe> queryByTaskId(int id) ; 
	
	/**
	 * 	删除资产探测对应taskId的资产
	 */
	public void deleteAssetProbe(int id) ; 
	
	
}

