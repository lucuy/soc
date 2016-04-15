package com.soc.service.knowledge;

import java.util.List;
import java.util.Map;

import com.soc.model.knowledge.Leak;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <漏洞库server类> <功能详细描述>
 * 
 * @author gaosong
 * @version [版本号, 2013-1-20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface LeakService {
	/**
	 * <分页查询> <功能详细描述>
	 * 
	 * @param id
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
	public SearchResult queryLeak(Map<String, Object> map, Page page);

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
     * <导出资产信息>
     * <功能详细描述>
     * @param limit
     * @return
     * @see [类、类#方法、类#成员]
     */
	public List<Map> export(Map<String, String> limit);
}
