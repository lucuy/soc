package com.soc.service.systemsetting.impl.rules;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.systemsetting.rules.AnalysisRulesDao;
import com.soc.model.systemsetting.rules.AnalysisRules;
import com.soc.service.systemsetting.rules.AnalysisRulesService;
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
public class AnalysisRulesServiceImpl implements AnalysisRulesService
{
    private AnalysisRulesDao analysisDao;
    
    /**
     * {@inheritDoc}
     */
    public List<AnalysisRules> queryAnalysis()
    {
        return analysisDao.queryAnalysis();
    }
    
    /**
     * {@inheritDoc}
     */
    public List<AnalysisRules> queryAnalysis(Map<String, Object> map)
    {
        return analysisDao.queryAnalysis(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public AnalysisRules queryAnalysisById(String analysisId)
    {
        return analysisDao.queryAnalysisById(analysisId);
    }
    
    /**
     * {@inheritDoc}
     */
    public String queryTableNameByAnalysisId(String analysisId)
    {
        return analysisDao.queryTableNameByAnalysisId(analysisId);
    }
    
    @Override
    public void updateAnalysisStatus(String id, int userStatus)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        
        // 设置用户状态(锁定|激活)
        map.put("analysisId", id);
        map.put("analysisType", Long.valueOf(userStatus));
        map.put("analysisUpdateTime", new Date());
        
        analysisDao.updateAnalysisStatus(map);
    }
   
    @Override
    public SearchResult queryPage(Map map, Page page)
    {
        int rowsCount = analysisDao.analysisPage(map);
        page.setTotalCount(rowsCount);
        List<AnalysisRules> list = analysisDao.queryPage(map, page.getStartIndex(), page.getPageSize());
        
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }

    public AnalysisRulesDao getAnalysisDao()
    {
        return analysisDao;
    }

    public void setAnalysisDao(AnalysisRulesDao analysisDao)
    {
        this.analysisDao = analysisDao;
    }
    @Override
	public SearchResult sort(Map map, Page page) {
		
		 int rowsCount = analysisDao.analysisPage(map);
	        page.setTotalCount(rowsCount);
	        String field=(String)map.get("field");
	        String sortType=(String)map.get("sortType");
	        String str=" \""+field+"\""+" "+sortType;
	        List<AnalysisRules> list = analysisDao.sort(str, page.getStartIndex(), page.getPageSize());
	        SearchResult sr = new SearchResult() ; 
	        sr.setList(list);
	        sr.setPage(page);
	        return sr;

	}
}
