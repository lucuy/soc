package com.compliance.service.cpManage.gpaShow.impl;

import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.gpaShow.GpaRectDao;
import com.compliance.model.cpManage.gpaShow.GpaRect;
import com.compliance.service.cpManage.gpaShow.GpaRectService;
/**
 * 通用物理整改建议业务实现类
 * @author quyongkun
 *
 */
public class GpaRectServiceImpl implements GpaRectService {
	
	/**
	 * 通用物理整改建议dao接口
	 */
	private GpaRectDao gpaRectDao;
	

	public GpaRectDao getGpaRectDao() {
		return gpaRectDao;
	}

	public void setGpaRectDao(GpaRectDao gpaRectDao) {
		this.gpaRectDao = gpaRectDao;
	}

	/**
	 * 查询所有的通用物理源数据
	 * @return 所有的通用物理源数据
	 */
	public List<GpaRect> queryAllData() {
		return gpaRectDao.queryAllData();
	}

	/**
	 * 根据日期 查询通用物理整改建议
	 * @param gpaDate 通用物理测评时间
	 * @return 通用物理整改建议
	 */
	public List<GpaRect> query(String gpaDate) {
		return gpaRectDao.query(gpaDate);
	}
	

	/**
	 * 根据主键查询通用物理整改建议
	 * @param gpaRectId 主键
	 * @return
	 */
	public GpaRect queryById(int gpaRectId){
		return gpaRectDao.queryById(gpaRectId);
	}
	
	/**
	 * 根据map查询通用物理整改建议
	 * @param map
	 * @return
	 */
	public List<GpaRect> queryByMap(Map map){
		return gpaRectDao.queryByMap(map);
	}
	
	/**
	 * 查询整改建议历史树
	 * @return 通用物理整改建议历史集合
	 */
	public List<GpaRect>  queryTree(){
		return gpaRectDao.queryTree();
	}

	/**
	 * 插入通用物理整改建议
	 * @param gpaRect 通用物理整改建议
	 * @return 插入是否成功
	 */
	public boolean insert(GpaRect gpaRect) {
		return gpaRectDao.insert(gpaRect);
	}

	/**
	 * 根据主键删除通用物理整改建议
	 * @param gpaRectId 通用物理整改建议主键
	 * @return 删除是否成功
	 */
	public boolean delete(int gpaRectId) {
		return gpaRectDao.delete(gpaRectId);
	}

	/**
	 * 修改通用物理整改建议
	 * @param gpaRect 通用物理整改建议
	 * @return 修改是否成功
	 */
	public boolean update(GpaRect gpaRect) {
		return gpaRectDao.update(gpaRect);
	}

}
