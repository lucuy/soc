package com.soc.service.systemsetting.rules;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.model.events.Filter;
import com.soc.model.systemsetting.rules.AssociationRules;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface AssociationRulesService
{
    /**
     * <查询所有过滤规则>
     * <功能详细描述>
     * @return List<Filter>
     * @see [类、类#方法、类#成员]
     */
    public List<AssociationRules> queryFilter();
    
    /**
     * <根据过滤规则ID查询过滤规则>
     * <功能详细描述>
     * @param filterId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public AssociationRules queryFilterById(String filterId);
    
    /**
     * <根据过滤规则ID查询对应的小表的名称>
     * <功能详细描述>
     * @param filterId
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String queryTableNameByFilterId(String filterId);
    
    public void updateAssociationStatus(String id, int userStatus);
    
    public SearchResult queryPage(Map map, Page page);
    
    /**
     * <排序>
     * @param map	
     * @param page
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public SearchResult sort(Map map,Page page);
    
}
