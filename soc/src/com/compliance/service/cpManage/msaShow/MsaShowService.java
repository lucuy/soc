package com.compliance.service.cpManage.msaShow;

import java.util.List;

import com.compliance.model.cpManage.msaShow.MsaShow;

/**
 * 通用管理安全测评数据业务接口
 * @author quyongkun
 *
 */
public interface MsaShowService {
	
	
	/**
	 * 查询所有项数据
	 * @return 通用管理安全测评数据集合
	 */
	List<MsaShow> queryAllData();

}
