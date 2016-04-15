package com.soc.dao.systemsetting.rules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.rules.AssociationRules;
/**
 * 
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public interface AssociationRulesDao
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
    
    public void updateAssociationStatus(Map map);
    
    /**
     * <分页>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int associationPage(Map map);
    /**
     * <分页>
     * <功能详细描述>
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AssociationRules> queryPage(Map map, int startRow, int pageSize);
    /**
     * <排序>
     * @param str
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AssociationRules> sort(String str,int startRow,int pageSize);
    
}
