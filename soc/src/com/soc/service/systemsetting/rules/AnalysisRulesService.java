package com.soc.service.systemsetting.rules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.rules.AnalysisRules;
import com.util.page.Page;
import com.util.page.SearchResult;

public interface AnalysisRulesService
{
    /**
     * <查询所有过滤规则>
     * <功能详细描述>
     * @return List<Analysis>
     * @see [类、类#方法、类#成员]
     */
    public List<AnalysisRules> queryAnalysis();
    
    /**
     * <查询符合条件的过滤规则>
     * <功能详细描述>
     * @return List<Analysis>
     * @see [类、类#方法、类#成员]
     */
    public List<AnalysisRules> queryAnalysis(Map<String, Object> map);
    
    /**
     * <根据过滤规则ID查询过滤规则>
     * <功能详细描述>
     * @param analysisId
     * @return
     * @see [类、类#方法、类#成员]
     */
    public AnalysisRules queryAnalysisById(String analysisId);
    
    /**
     * <根据过滤规则ID查询对应的小表的名称>
     * <功能详细描述>
     * @param analysisId
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String queryTableNameByAnalysisId(String analysisId);
    
    public void updateAnalysisStatus(String id, int userStatus);
    
    public SearchResult queryPage(Map map, Page page);
    /**
     * <排序>
     * @param map
     * @param page
     * @return SearchResult
     * @see [类、类#方法、类#成员]
     */
    public SearchResult sort(Map map,Page page) ; 
    
}
