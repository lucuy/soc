package com.soc.service.systemsetting.impl.rules;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.rules.AssociationRulesDao;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.rules.AssociationRules;
import com.soc.service.systemsetting.rules.AssociationRulesService;
import com.util.page.Page;
import com.util.page.SearchResult;
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
public class AssociationRulesServiceImpl implements AssociationRulesService
{
    private AssociationRulesDao associationDao;
    
    /**
     * {@inheritDoc}
     */
    public List<AssociationRules> queryFilter()
    {
        return associationDao.queryFilter();
    }
    
    public AssociationRulesDao getAssociationDao()
    {
        return associationDao;
    }

    public void setAssociationDao(AssociationRulesDao associationDao)
    {
        this.associationDao = associationDao;
    }

    /**
     * {@inheritDoc}
     */
    public AssociationRules queryFilterById(String filterId)
    {
        return associationDao.queryFilterById(filterId);
    }
    
    /**
     * {@inheritDoc}
     */
    public String queryTableNameByFilterId(String filterId)
    {
        return associationDao.queryTableNameByFilterId(filterId);
    }
    
    @Override
    public void updateAssociationStatus(String id, int userStatus)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        
        // 设置用户状态(锁定|激活)
        map.put("filterId", id);
        map.put("filterType", Long.valueOf(userStatus));
        map.put("filterUpdateTime", new Date());
        
        associationDao.updateAssociationStatus(map);
    }
   
    public AssociationRulesDao getFilterDao()
    {
        return associationDao;
    }

    public void setFilterDao(AssociationRulesDao associationDao)
    {
        this.associationDao = associationDao;
    }

    @Override
    public SearchResult queryPage(Map map, Page page)
    {
        int rowsCount = associationDao.associationPage(map);
        page.setTotalCount(rowsCount);
        List<AssociationRules> list = associationDao.queryPage(map, page.getStartIndex(), page.getPageSize());
        
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    @Override
	public SearchResult sort(Map map, Page page) {
		int rowsCount = associationDao.associationPage(map);
		page.setTotalCount(rowsCount);
		String field=(String)map.get("field");
		String sortType=(String)map.get("sortType");
		String str=" \""+field+"\""+" "+sortType;
		List<AssociationRules> list = associationDao.sort(str, page.getStartIndex(), page.getPageSize());
		SearchResult sr = new SearchResult() ; 
		sr.setList(list);
		sr.setPage(page);
		return sr;
	}
    
}
