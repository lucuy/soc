package com.compliance.dao.basicinfo.division.ibatis;


import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.division.DivisionDao;
import com.compliance.dao.ibatis.BaseDaoiBatis;
import com.compliance.model.basicinfo.division.Division;
import com.soc.model.conf.GlobalConfig;

public class DivisionDaoIbatis extends BaseDaoiBatis implements DivisionDao {
	
	@SuppressWarnings("unchecked")
	public int insert(Division division) {
		return Integer.parseInt(this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"division.insert",division).toString());
		 
	}
	
	@SuppressWarnings("unchecked")
	public void delete(int id) {
		this.getSqlMapClientTemplate().delete(GlobalConfig.sqlId+"division.delete", id);
	}
	
	@SuppressWarnings("unchecked")
	public void updata(Division division) {
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"division.update",division);
	}
	
	public void updateEmp(Map map){
		this.getSqlMapClientTemplate().update(GlobalConfig.sqlId+"division.updateEmp",map);
		
	}

	@SuppressWarnings("unchecked")
	public List<Division> query() {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"division.query");
	}

	@SuppressWarnings("unchecked")
	public Division queryById(int id) {
		return (Division)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"division.queryById",id);
	}
	
	@SuppressWarnings("unchecked")
	public List<Division> queryByParentId(int parentId) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"division.queryByParentId", parentId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Division> queryByDepName(String depName) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"division.queryByDepName", depName);
	}
	
	
	

}
