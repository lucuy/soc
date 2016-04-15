package com.compliance.service.cpManage.msaShow.impl;



import java.util.List;
import java.util.Map;

import com.compliance.dao.cpManage.msaShow.MsaRectDao;
import com.compliance.model.cpManage.msaShow.MsaRect;
import com.compliance.service.cpManage.msaShow.MsaRectService;
/**
 * 通用管理整改建议业务实现类
 * @author quyongkun
 *
 */
public class MsaRectServiceImpl implements MsaRectService {
	
	/**
	 * 通用管理整改建议dao接口
	 */
	private MsaRectDao msaRectDao;
	

	public MsaRectDao getMsaRectDao() {
		return msaRectDao;
	}

	public void setMsaRectDao(MsaRectDao msaRectDao) {
		this.msaRectDao = msaRectDao;
	}

	/**
	 * 查询所有的通用管理源数据
	 * @return 所有的通用管理源数据
	 */
	public List<MsaRect> queryAllData() {
		return msaRectDao.queryAllData();
	}

	/**
	 * 根据日期 查询通用管理整改建议
	 * @param msaDate 通用管理测评时间
	 * @return 通用管理整改建议
	 */
	public List<MsaRect> query(String msaDate) {
		return msaRectDao.query(msaDate);
	}
	

	/**
	 * 根据主键查询通用管理整改建议
	 * @param msaRectId 主键
	 * @return
	 */
	public MsaRect queryById(int msaRectId){
		return msaRectDao.queryById(msaRectId);
	}
	
	/**
	 * 根据map查询通用管理整改建议
	 * @param map
	 * @return
	 */
	public List<MsaRect> queryByMap(Map map){
		return msaRectDao.queryByMap(map);
	}
	
	/**
	 * 查询整改建议历史树
	 * @return 通用管理整改建议历史集合
	 */
	public List<MsaRect>  queryTree(){
		return msaRectDao.queryTree();
	}

	/**
	 * 插入通用管理整改建议
	 * @param msaRect 通用管理整改建议
	 * @return 插入是否成功
	 */
	public boolean insert(MsaRect msaRect) {
		return msaRectDao.insert(msaRect);
	}

	/**
	 * 根据主键删除通用管理整改建议
	 * @param msaRectId 通用管理整改建议主键
	 * @return 删除是否成功
	 */
	public boolean delete(int msaRectId) {
		return msaRectDao.delete(msaRectId);
	}

	/**
	 * 修改通用管理整改建议
	 * @param msaRect 通用管理整改建议
	 * @return 修改是否成功
	 */
	public boolean update(MsaRect msaRect) {
		return msaRectDao.update(msaRect);
	}

}
