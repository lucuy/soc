package com.compliance.dao.cpManage.msaShow.impl;

import java.util.List;
import java.util.Map;
import com.compliance.dao.cpManage.msaShow.MsaRectDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.cpManage.msaShow.MsaRect;
import com.soc.model.conf.GlobalConfig;

/**
 * 通用管理整改建议dao实现类
 * @author quyongkun
 *
 */
public class MsaRectDaoImpl extends  BaseDaoiBatis implements MsaRectDao {
	
	/**
	 * 查询所有的通用管理源数据
	 * @return 所有的通用管理源数据
	 */
	public List<MsaRect> queryAllData() {
		return super.queryForList(GlobalConfig.sqlId+"msaRect.queryAllData");
	}
	
	/**
	 * 根据日期 查询通用管理整改建议
	 * @return 通用管理整改建议
	 */	
	public List<MsaRect> query(String msaDate) {
		return super.queryForList(GlobalConfig.sqlId+"msaRect.query",msaDate);
	}
	
	/**
	 * 根据map查询通用管理整改建议历史
	 * @param map
	 * @return
	 */
	public List<MsaRect> queryByMap(Map map){
		return super.queryForList(GlobalConfig.sqlId+"msaRect.queryByMap",map);
	}

	
	/**
	 * 查询整改建议树
	 * @return 通用管理整改建议集合
	 */
	public List<MsaRect>  queryTree(){
		return super.queryForList(GlobalConfig.sqlId+"msaRect.queryTree");
	}
	
	/**
	 * 插入通用管理整改建议
	 * @param msaRect 通用管理整改建议
	 * @return 插入是否成功
	 */
	public boolean insert(MsaRect msaRect) {
		return super.create(GlobalConfig.sqlId+"msaRect.insert", msaRect)==null;
	}
	
	/**
	 * 根据主键删除通用管理整改建议
	 * @param msaRectId 通用管理整改建议主键
	 * @return 删除是否成功
	 */	
	public boolean delete(int msaRectId) {
		
		return super.delete(GlobalConfig.sqlId+"msaRect.delete", msaRectId)>0;
	}
	
	/**
	 * 修改通用管理整改建议
	 * @param MsaRect 通用管理整改建议
	 * @return 修改是否成功
	 */
	public boolean update(MsaRect MsaRect) {
		return super.update(GlobalConfig.sqlId+"msaRect.update", MsaRect)>0;
	}

	/**
	 * 根据主键查询通用管理整改建议
	 * @param msaRectId 主键
	 * @return
	 */
	public MsaRect queryById(int msaRectId){
		return (MsaRect)super.queryForObject(GlobalConfig.sqlId+"msaRect.queryById", msaRectId);
	}
	


}
