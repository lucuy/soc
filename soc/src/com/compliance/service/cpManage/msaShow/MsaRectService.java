package com.compliance.service.cpManage.msaShow;
import java.util.List;
import java.util.Map;
import com.compliance.model.cpManage.msaShow.MsaRect;

/**
 * 通用管理整改建议业务接口
 * @author quyongkun
 *
 */
public interface MsaRectService {

	/**
	 * 查询所有的通用管理源数据
	 * @return 所有的通用管理源数据
	 */
	List<MsaRect> queryAllData();
	
	/**
	 * 根据日期 查询通用管理整改建议
	 * @param msaDate 通用管理测评时间
	 * @return 通用管理整改建议
	 */	
	List<MsaRect> query(String msaDate);
	
	/**
	 * 根据map查询通用管理整改建议
	 * @param map
	 * @return
	 */
	public List<MsaRect> queryByMap(Map map);
	
	/**
	 * 查询整改建议历史树
	 * @return 通用管理整改建议历史集合
	 */
	List<MsaRect>  queryTree();
	
	

	/**
	 * 根据主键查询通用管理整改建议
	 * @param msaRectId 主键
	 * @return
	 */
   MsaRect queryById(int msaRectId);
	
	
	/**
	 * 插入通用管理整改建议
	 * @param msaRect 通用管理整改建议
	 * @return 插入是否成功
	 */
	boolean insert(MsaRect msaRect);
	
	/**
	 * 根据主键删除通用管理整改建议
	 * @param msaRectId 通用管理整改建议主键
	 * @return 删除是否成功
	 */	
	boolean delete(int msaRectId);
	
	/**
	 * 修改通用管理整改建议
	 * @param msaRect 通用管理整改建议
	 * @return 修改是否成功
	 */
	boolean update(MsaRect msaRect);
	
}
