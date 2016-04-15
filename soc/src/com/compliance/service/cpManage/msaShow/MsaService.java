package com.compliance.service.cpManage.msaShow;



import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.msaShow.Msa;

/**
 * 用管理安全测评业务接口
 * @author quyongkun
 *
 */
public interface MsaService {

	
	/**
	 * 根据map集合查询通用管理安全测评信息
	 * @param map 存放时间和评估结果
	 * @return 通用管理安全测评集合
	 */
	List<Msa>  needConnect(Map map);
	
	/**
	 * 根据map集合查询通用管理测评评估项个数
	 * @param map 存放时间和评估结果
	 * @return 通用物理测评集合
	 */
	 int rectCount(Map map);
	
	
	/**
     * 查询唯一日期
	 * @return 通用管理安全测评集合
	 */
	 List<Msa> querySoleDate();

}
