package com.soc.service.events;

import java.util.HashMap;
import java.util.List;

import com.soc.model.events.Filter;

public interface FilterService
{
    /**
     * <查询所有过滤规则>
     * <功能详细描述>
     * @return List<Filter>
     * @see [类、类#方法、类#成员]
     */
    public List<Filter> queryFilter();
    
    /**
     * <根据过滤规则ID查询过滤规则>
     * <功能详细描述>
     * @param filterId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public Filter queryFilterById(String filterId);
    
    /**
     * <根据过滤规则ID查询对应的小表的名称>
     * <功能详细描述>
     * @param filterId
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String queryTableNameByFilterId(String filterId);
}
