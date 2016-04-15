package com.compliance.dao.cpManage.msaShow;


import java.util.List;
import com.compliance.model.cpManage.msaShow.MsaShow;


/**
 * 通用管理安全测评整改需求汇总dao接口
 * @author quyongkun
 *
 */
public interface MsaShowDao {
	
	/**
	 * 查询所有项数据
	 * @return 
	 */
	List<MsaShow> queryAllData();

}
