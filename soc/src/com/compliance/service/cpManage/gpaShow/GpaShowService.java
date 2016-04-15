package com.compliance.service.cpManage.gpaShow;



import java.util.List;
import com.compliance.model.cpManage.gpaShow.GpaShow;

/**
 * 通用物理测评整改需求汇总业务接口
 * @author quyongkun
 *
 */
public interface GpaShowService {
	
	/**
	 * 查询所有项数据
	 * @return 
	 */
	List<GpaShow> queryAllData();

}
