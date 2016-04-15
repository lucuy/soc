package com.compliance.service.basicinfo.division.ibatis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.compliance.dao.basicinfo.division.DivisionDao;
import com.compliance.model.basicinfo.division.Division;
import com.compliance.service.basicinfo.division.DivisionService;
import com.compliance.service.impl.BaseServiceImpl;

public class DivisionServiceImpl extends BaseServiceImpl implements DivisionService {
	
	private DivisionDao divisionDao;
	
	public int insert(Division division) {
		return divisionDao.insert(division);
	}

	public void delete(int id) {
		divisionDao.delete(id);
	}

	public void updata(Division division) {
		Division div=divisionDao.queryById(division.getId());
		divisionDao.updata(division);
		Map map=new HashMap();
		map.put("keyword1", division.getDepName());
		map.put("keyword2", div.getDepName());
		divisionDao.updateEmp(map);
	}
	

	public List<Division> query() {
		return (List<Division>)divisionDao.query();
	}
	
	/**
	 * 根据部门名称查询
	 * @param depName 部门名称
	 * @return
	 */
	public List<Division> queryByDepName(String depName){
		return divisionDao.queryByDepName(depName);
	}


	public Division queryById(int id) {
		return (Division)divisionDao.queryById(id);
	}
	
	/**
	 * 根据父类编号查询
	 * @param parentId 父类编号
	 * @return
	 */
	public List<Division> queryByParentId(int parentId) {
		return divisionDao.queryByParentId(parentId);
	}

	public DivisionDao getDivisionDao() {
		return divisionDao;
	}

	public void setDivisionDao(DivisionDao divisionDao) {
		this.divisionDao = divisionDao;
	}

	
	

}
