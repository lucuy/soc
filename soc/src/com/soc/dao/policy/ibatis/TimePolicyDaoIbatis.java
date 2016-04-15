/*
 * 文 件 名:  TimePolicyDaoIbatis.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.dao.policy.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.policy.TimePolicyDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.policy.TimePolicy;
import com.soc.model.user.User;

/**
 * 时间策略Dao实现类
 * 时间策略的查询，添加，修改，删除，时间策略状态的改变
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  
 * @since  [用户管理/时间策略管理/V100R001C001]
 */
public class TimePolicyDaoIbatis extends BaseDaoIbatis implements TimePolicyDao
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
            ob = super.queryForObject(GlobalConfig.sqlId+"timePolicy.count", map);
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
    public List<TimePolicy> queryTimePolicy(Map map, int startRow, int pageSize)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"timePolicy.queryTimePolicy", map, startRow, pageSize);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<TimePolicy> queryTimePolicy(Map map)
    {
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"timePolicy.queryTimePolicyEx", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public TimePolicy queryTimePolicyById(long timePolicyId)
    {
        return (TimePolicy)super.queryForObject(GlobalConfig.sqlId+"timePolicy.queryByTimePolicyId", timePolicyId);
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateTimePolicyStatus(Map map)
    {
        super.update(GlobalConfig.sqlId+"timePolicy.updateTimePolicyStatus", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteTimePolicy(Map map)
    {
        
        super.update(GlobalConfig.sqlId+"timePolicy.deleteTimePolicy", map);
        
    }
    
    /**
     *  {@inheritDoc}
     */
    public void updateTimePolicy(TimePolicy timePolicy)
    {
        super.update(GlobalConfig.sqlId+"timePolicy.updateTimePolicy", timePolicy);
    }
    
    /**
     * {@inheritDoc}
     */
    public void insertTimePolicy(TimePolicy timePolicy)
    {
        this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"timePolicy.insert", timePolicy);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<TimePolicy> queryBytimePolicyName(String timePolicyName)
    {
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"timePolicy.queryBytimePolicyName", timePolicyName);
    }
    
    /**
     * {@inheritDoc}
     */
	public List<TimePolicy> queryBySort(String str, int startRow, int pageSize) {
		// TODO Auto-generated method stub
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"timePolicy.sort",str,startRow,pageSize);
	}

	@Override
	public List<User> queryUserByTimePolicyId(long id) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"timePolicy.queryuser",id);
	}
    
}
