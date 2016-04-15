package com.compliance.service.basicinfo.division;

import java.util.List;
import java.util.Map;

import com.compliance.model.basicinfo.division.Division;

public interface DivisionService {

	/**
	 * 添加组织部门信息
	 * @param  Division
	 */
	@SuppressWarnings("unchecked")
	public int insert(Division division);
	
	/**
	 * 根据id删除组织部门信息
	 * @param id
	 */ 
	@SuppressWarnings("unchecked")
	public void delete(int id);
	
	/**
	 * @param Division
	 * 更新组织部门信息
	 */
	@SuppressWarnings("unchecked")
	public void updata(Division division);
	
	
	/**
	 * 查询所有组织部门信息
	 * @return List<Division>
	 */
	@SuppressWarnings("unchecked")
	 public List<Division> query();
	 
	/**
	 * 根据id查询所有组织部门信息
	 * @param id
	 * @return List<Division>
	 */
	@SuppressWarnings("unchecked")
	 public Division queryById(int id);
	
	/**
	 * 根据父类编号查询
	 * @param parentId 父类编号
	 * @return
	 */
	public List<Division> queryByParentId(int parentId) ;
	
	/**
	 * 根据部门名称查询
	 * @param depName 部门名称
	 * @return
	 */
	public List<Division> queryByDepName(String depName);

	
	
}
