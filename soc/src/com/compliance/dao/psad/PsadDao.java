package com.compliance.dao.psad;

import java.util.List;
import java.util.Map;

import com.compliance.model.psad.Psa;
import com.compliance.model.psad.Psad;

/**
 * 通用物理管理
 * 
 * 胡亚丹
 */
public interface PsadDao {

	public List<Psad> queryPsadByFatherSort(String psadFatherSort);
	/**
	 * 根据排序查询
	 */
   public List<Psad> queryByPsadSortInfo(Map map);
   /**
    * 根据排序查询是否还有下一级
    */
   public int queryNextPsadSortInfo(Map map);
   /**
    * 根据排序查找psadname
    */
   public String queryPsadNameByid(String psadid);

}
