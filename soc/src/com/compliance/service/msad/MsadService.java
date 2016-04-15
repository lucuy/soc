package com.compliance.service.msad;

import java.util.List;
import java.util.Map;

import com.compliance.model.msad.Msad;

public interface MsadService {

	public List<Msad> queryMsadByFatherSort(String fatherSort);
	/**
	 * 根据排序查询
	 */
	public List<Msad> queryByMsadSort(Map map);
	/**
	 * 查询是否有下一级
	 */
	public int queryByNextSort(Map map);
	/**
	 * 根据psadid查找控制域名
	 */
	public String queryMsadName(String msadid);
}
