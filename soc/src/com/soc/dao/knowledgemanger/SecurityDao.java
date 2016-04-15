package com.soc.dao.knowledgemanger;

import java.util.List;
import java.util.Map;

import com.soc.model.knowledge.Security;

/**
 * 
 * <安全公告dao> <安全公告模块的增删改查>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SecurityDao {

	/**
	 * <查找安全公告信息> <功能详细描述>
	 * 
	 * @param map
	 * @param start
	 * @param end
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public List<Security> querySecurity(Map map, int start, int end);

	/**
	 * <删除公告> <功能详细描述>
	 * 
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteSecurity(int id);

	/**
	 * <添加公告> <功能详细描述>
	 * 
	 * @param securty
	 * @see [类、类#方法、类#成员]
	 */
	public void insertSecuity(Security securty);

	/**
	 * 计算总条数用来分页> <功能详细描述>
	 * 
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public int count(Map map);

	/**
	 * <用于更新公告> <功能详细描述>
	 * 
	 * @param security
	 * @see [类、类#方法、类#成员]
	 */
	public void updateSecurity(Security security);

	/**
	 * <查找公告用来显示详细信息> <功能详细描述>
	 * 
	 * @param id
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Security selectSecurityByid(int id);
	
	/**
	 * <公告信息查询出来后排序> 
	 * 
	 * @param id
	 * @return List<Security>
	 * @see [类、类#方法、类#成员]
	 */
	public List<Security> queryBySort(String str,int startRow,int pageSize);
	
	/**
	 * <要导出的公告信息> 
	 * 
	 * @param map
	 * @return List<map>
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map> securityExport(Map map);
	
	/**
	 * <查询所有标题与参数相同的公告信息> 
	 * 
	 * @param map
	 * @return List<map>
	 * @see [类、类#方法、类#成员]
	 */
	public List<Security> queryAllSecurity(Map map);
	
	/**
	 * <要导出的公告信息> 
	 * 
	 * @param map
	 * @return List<map>
	 * @see [类、类#方法、类#成员]
	 */
	public List<Security> securityForExport(Map map);
}
