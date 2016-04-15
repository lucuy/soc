/*
 * 文 件 名:  PolicyDaoIbatis.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.dao.securityPolicy.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.securityPolicy.SecurityPolicyDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.securityPolicy.SecurityPolicy;

/**
 * 时间策略Dao实现类
 * 时间策略的查询，添加，修改，删除，时间策略状态的改变
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  
 * @since  [用户管理/时间策略管理/V100R001C001]
 */
public class SecurityPolicyDaoIbatis extends BaseDaoIbatis implements SecurityPolicyDao
{
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"securityPolicy.count", map);
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
    /**
     * {@inheritDoc}
     */
    public int  countOfName(String name)
    {
        
        Object ob = null;
        
        // 根据map中存储的条件查询符合条件的用户的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"securityPolicy.countOfName", name);
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
    /**
     * {@inheritDoc}
     */
    public List<SecurityPolicy> queryPolicy(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"securityPolicy.queryPolicy", map, startRow, pageSize);
    }
    
    
    
    /**
     * {@inheritDoc}
     */
    public SecurityPolicy queryPolicyById(long securityPolicyId)
    {
        return (SecurityPolicy)super.queryForObject(GlobalConfig.sqlId+"securityPolicy.queryByPolicyId", securityPolicyId);
    }
    
    
    /**
     * {@inheritDoc}
     */
    public void deletePolicy(Map map)
    {
        
        super.update(GlobalConfig.sqlId+"securityPolicy.deletePolicy", map);
        
    }
    
    /**
     *  {@inheritDoc}
     */
    public void updatePolicy(SecurityPolicy securityPolicy)
    {
        super.update(GlobalConfig.sqlId+"securityPolicy.updatePolicy", securityPolicy);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertPolicy(SecurityPolicy securityPolicy)
    {
        this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"securityPolicy.insert", securityPolicy);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<SecurityPolicy> queryByPolicyName(String securityPolicyName)
    {
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"securityPolicy.queryBysecurityPolicyName", securityPolicyName);
    }

	@Override
	public List<SecurityPolicy> queryAllSecurityPolicy() {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"securityPolicy.queryPolicy");
	}
    
   


}
