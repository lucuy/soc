package com.soc.dao.systemsetting.ibatis.rules;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.rules.AnalysisRulesDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.rules.AnalysisRules;
/**
 * 
 * <解析规则>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AnalysisRulesDaoIbatis extends BaseDaoIbatis implements AnalysisRulesDao
{
    
    /**
     * {@inheritDoc}
     */
    public List<AnalysisRules> queryAnalysis()
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.analysis");
    }
    
    /**
     * {@inheritDoc}
     */
    public List<AnalysisRules> queryAnalysis(Map<String, Object> map)
    {
        return super.queryForList(GlobalConfig.sqlId+"analysis.query", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public AnalysisRules queryAnalysisById(String analysisId)
    {
        return (AnalysisRules)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.analysisById", analysisId);
    }

    /**
     * {@inheritDoc}
     */
    public String queryTableNameByAnalysisId(String analysisId)
    {
        return (String)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.tableNameByanalysisId", analysisId);
    }

    @Override
    public void updateAnalysisStatus(Map map)
    {
        super.update(GlobalConfig.sqlId+"analysisRules.updateUserStatus", map);
    }
/**
 * {@分页}
 */
    @Override
    public int analysisPage(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"countPage.analysis", map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        
        //总条数
        int totalRows = 0;
        
        if (ob != null)
        {
            totalRows = ((Integer)ob).intValue();
        }
        
        return totalRows;
    }

    @Override
    public List<AnalysisRules> queryPage(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"analysis.query",map,startRow,pageSize);
    }
    
    @Override
	public List<AnalysisRules> sort(String str, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"analysisRules.sort",str,startRow,pageSize);
	}
}
