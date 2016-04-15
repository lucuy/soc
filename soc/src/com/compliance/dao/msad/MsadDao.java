package com.compliance.dao.msad;

import java.util.List;
import java.util.Map;

import com.compliance.model.msad.Msad;

public interface MsadDao {

	public List<Msad> queryMsadByFatherSort(String msadFatherSort);
	/**
	 * 根据排序查询
	 */
	public List<Msad> queryByMsadSort(Map map);
	/**
	 * 根据排序查询是否有下一级
	 */
	public int queryByNextSort(Map map);
	/**
	 * 根据msadid查找控制域名
	 */
	public String queryMsadByMsadid(String msadid);

}
