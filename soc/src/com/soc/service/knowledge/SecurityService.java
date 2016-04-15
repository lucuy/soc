package com.soc.service.knowledge;

import java.util.List;
import java.util.Map;

import com.soc.model.knowledge.Security;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <安全公告Server接口> <安全公告模块的增删改查>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-22]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface SecurityService {
	
	/**
	 * <查找安全公告信息> <功能详细描述>
	 * 
	 * @param map
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public SearchResult querySecurity(Map map,Page page);

	/**
	 * <删除公告> <功能详细描述>
	 * 
	 * @param id
	 * @see [类、类#方法、类#成员]
	 */
	public void deleteSecurity(String ids);

	/**
	 * <添加公告> <功能详细描述>
	 * 
	 * @param securty
	 * @see [类、类#方法、类#成员]
	 */
	public void insertSecuity(Security securty);

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
	 * @param ids 前台传过来的id组成的字符串
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	public Security selectSecurityByid(int id);
	
	/**
	 * <查找安全公告信息并排序> 
	 * 
	 * @param map
	 * @return SearchResult
	 * @see [类、类#方法、类#成员]
	 */
	public SearchResult queryBySort(Map map,Page page);
	
	/**
	 * <查找要导出的安全公告信息> 
	 * 
	 * @param map
	 * @return SearchResult
	 * @see [类、类#方法、类#成员]
	 */
	public List<Map> securityExport(Map map);
	
	/**
	 * <查找所有标题与参数相同的安全公告信息> 
	 * 
	 * @param map
	 * @return SearchResult
	 * @see [类、类#方法、类#成员]
	 */
	public List<Security> queryAllSecurity(Map map);

	/**
	 * <查找要导出的安全公告信息> 
	 * 
	 * @param map
	 * @return SearchResult
	 * @see [类、类#方法、类#成员]
	 */
	public List<Security> securityForExport(Map map);
}
