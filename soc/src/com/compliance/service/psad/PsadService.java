package com.compliance.service.psad;

import java.util.List;
import java.util.Map;

import com.compliance.model.psad.Psad;

public interface PsadService {
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
    * 根据id查找psadname
    */
   public String queryPsadnameById(String psadid);
}
