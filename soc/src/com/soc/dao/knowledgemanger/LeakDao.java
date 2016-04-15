package com.soc.dao.knowledgemanger;

import java.util.List;
import java.util.Map;

import com.soc.model.knowledge.Leak;

/**
 * 
 * <漏洞库的dao类> <查询显示等功能>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-19]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface LeakDao {

	/**
	 * <根据id查漏洞> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Leak selectLeakById(int id);

	/**
	 * <条件查询功能> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Leak> queryLeak(Map map, int startIndex, int pageSize);

	/**
	 * <计算总数量用于分页> <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int count(Map map);

	/**
	 * <按漏洞名查询> <功能详细描述>
	 * 
	 * @param leakName
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Leak> queryByName(String leakName);

	/**
	 * <插入漏洞信息> <功能详细描述>
	 * 
	 * @param leak
	 * @see [类、类#方法、类#成员]
	 */
	public void insertLeak(Leak leak);
	/**
	 * <根据条件导出漏洞库>
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map> export(Map map);
}
