package com.soc.dao.systemsetting.ibatis.rules;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.systemsetting.rules.AssociationRulesDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Collector;
import com.soc.model.systemsetting.rules.AssociationRules;
/**
 * 
 * <关联规则>
 * <功能详细描述>
 * 
 * @author  yinhaiping
 * @version  [版本号, 2012-11-28]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class AssociationRulesDaoIbatis extends BaseDaoIbatis implements AssociationRulesDao
{
    
    /**
     * {@inheritDoc}
     */
    public List<AssociationRules> queryFilter()
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"query.association");
    }
    
    /**
     * {@inheritDoc}
     */
    public AssociationRules queryFilterById(String filterId)
    {
        return (AssociationRules)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.associationById", filterId);
    }

    /**
     * {@inheritDoc}
     */
    public String queryTableNameByFilterId(String filterId)
    {
        return (String)this.getSqlMapClientTemplate().queryForObject(GlobalConfig.sqlId+"query.tableNameByassociationId", filterId);
    }

    @Override
    public void updateAssociationStatus(Map map)
    {
        super.update(GlobalConfig.sqlId+"associationRules.updateUserStatus", map);
    }
/**
 * {@分页}
 */
    @Override
    public int associationPage(Map map)
    {
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"countPage.association", map);
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
    public List<AssociationRules> queryPage(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"association.query",map,startRow,pageSize);
    }

    @Override
	public List<AssociationRules> sort(String str, int startRow, int pageSize) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"association.sort",str,startRow,pageSize) ;
		
	}
}
