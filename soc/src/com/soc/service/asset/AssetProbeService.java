package com.soc.service.asset;

import java.io.Serializable;
import java.util.List;

import com.soc.model.asset.AssetProbe;
/**
 * 资产信息service接口
 * @author 张浩磊
 *
 */
public interface AssetProbeService extends Serializable {
	/**
	 * 根据MAC查询资产信息
	 */
	public List<AssetProbe> queryByMac(String Mac);
	
	public List<AssetProbe> queryByTaskId(int id) ; 
	
	/**
	 * 删除资产探测出来的资产
	 */
	public void deleteByTaskId(int taskId) ; 
}
