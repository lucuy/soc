package com.soc.dao.systemsetting.rules;

import java.util.List;
import java.util.Map;

import com.soc.model.systemsetting.rules.AnalysisRules;
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
public interface AnalysisRulesDao
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
    
    public void updateAnalysisStatus(Map map);
    
    /**
     * <分页>
     * <功能详细描述>
     * @param map
     * @return
     * @see [类、类#方法、类#成员]
     */
    public int analysisPage(Map map);
    /**
     * <分页>
     * <功能详细描述>
     * @param map
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AnalysisRules> queryPage(Map map, int startRow, int pageSize);
    /**
     * <分页排序>
     * <功能详细描述>
     * @param str
     * @param startRow
     * @param pageSize
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<AnalysisRules> sort(String str, int startRow, int pageSize);
}
