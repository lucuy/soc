package com.soc.dao.events;

import java.util.List;

import com.soc.model.events.FilterByGroup;

public interface FilterByGroupDao
{
    /**
     * <查询所有过滤规则>
     * <功能详细描述>
     * @return List<Filter>
     * @see [类、类#方法、类#成员]
     */
    public List<FilterByGroup> queryFilterByGroup();
    
    /**
     * <根据过滤规则ID查询过滤规则>
     * <功能详细描述>
     * @param filterByGroupId String
     * @return
     * @see [类、类#方法、类#成员]
     */
    public FilterByGroup queryFilterByGroupById(String filterByGroupId);
}
