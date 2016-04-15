package com.compliance.dao.cpManage.gpaShow;

import java.util.List;
import java.util.Map;

import com.compliance.model.cpManage.gpaShow.GpaRect;

/**
 * 通用物理整改建议dao接口
 * @author quyongkun
 *
 */
public interface GpaRectDao {
	
	/**
	 * 查询所有的通用物理源数据
	 * @return 所有的通用物理源数据
	 */
	List<GpaRect> queryAllData();
	
	/**
	 * 根据日期 查询通用物理整改建议
	 * @param gpaDate 通用物理测评时间
	 * @return 通用物理整改建议
	 */	
	List<GpaRect> query(String gpaDate);
	
	/**
	 * 根据主键查询通用物理整改建议
	 * @param gpaRectId 主键
	 * @return
	 */
	GpaRect queryById(int gpaRectId);
	
	/**
	 * 根据map查询通用物理整改建议
	 * @param map
	 * @return
	 */
	List<GpaRect> queryByMap(Map map);
	
	/**
	 * 查询整改建议历史树
	 * @return 通用物理整改建议历史集合
	 */
	List<GpaRect>  queryTree();
	
	/**
	 * 插入通用物理整改建议
	 * @param gpaRect 通用物理整改建议
	 * @return 插入是否成功
	 */
	boolean insert(GpaRect gpaRect);
	
	/**
	 * 根据主键删除通用物理整改建议
	 * @param gpaRectId 通用物理整改建议主键
	 * @return 删除是否成功
	 */	
	boolean delete(int gpaRectId);
	
	/**
	 * 修改通用物理整改建议
	 * @param gpaRect 通用物理整改建议
	 * @return 修改是否成功
	 */
	boolean update(GpaRect gpaRect);
	

}
