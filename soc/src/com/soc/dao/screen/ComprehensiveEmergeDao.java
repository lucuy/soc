package com.soc.dao.screen;

import java.util.List;
import java.util.Map;

/**
 * 
 * <安全态势展现Dao>
 * <对设备事件、安全事件、查询>
 * 
 * @author  zhaokui
 * @version  [V100R001C001, 2012-8-13]
 * @see  [相关类/方法]
 * @since  
 */
public interface ComprehensiveEmergeDao {

	
    /**
     * <查询设备事件top10>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	public List<Map> queryFacilityEvents(Map map);
	
	 /**
     * <查询安全事件top10>
     * <功能详细描述>
     * @param 
     * @return map
     * @see [类、类#方法、类#成员]
     */
	 
	public List<Map> querySafetyEvents(Map map);
	
	
	
}
