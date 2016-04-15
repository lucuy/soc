/*
 * 文 件 名:  AddressPolicyDaoIbatis.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.dao.policy.ibatis;

import java.util.List;
import java.util.Map;

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.policy.AddressPolicyDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.user.User;

/**
 * 地址策略dao实现类
 * 地址策略的查询，快速查询，高级查询，标记删除，修改，地址策略状态的修改
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-13]
 * @see  
 * @since  [用户管理/地址策略管理/V100R001C001]
 */
public class AddressPolicyDaoIbatis extends BaseDaoIbatis implements AddressPolicyDao
{
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        Object ob = null;
        //根据map中存储的条件查询符合条件的记录数
        try
        {
            ob = super.queryForObject(GlobalConfig.sqlId+"addressPolicy.count", map);
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
    public List<AddressPolicy> queryAddressPolicy(Map map, int startRow, int pageSize)
    {
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"addressPolicy.queryAddressPolicy", map, startRow, pageSize);
    }
    
    /**
     * {@inheritDoc}
     */
    public List<AddressPolicy> queryAddressPolicy(Map map)
    {
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"addressPolicy.queryAddressPolicyEx", map);
    }
    
    /**
    * {@inheritDoc}
    */
    public void updateAddressPolicyStatus(Map map)
    {
        super.update(GlobalConfig.sqlId+"addressPolicy.updateStatus", map);
    }
    
    /**
     * {@inheritDoc}
     */
    public AddressPolicy queryById(long id)
    {
        return (AddressPolicy)super.queryForObject(GlobalConfig.sqlId+"addressPolicy.queryAddressPolicyById", id);
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteAddressPolicy(Map map)
    {
        super.update(GlobalConfig.sqlId+"addressPolicy.deletePolicy", map);
    }

    /**
     * {@inheritDoc}
     */
    public void updateAddressPolicy(AddressPolicy addressPolicy)
    {
        super.update(GlobalConfig.sqlId+"addressPolicy.updateAddress",addressPolicy);
        
    }

    /**
     * {@inheritDoc}
     */
    public long insertAddressPolicy(AddressPolicy addressPolicy)
    {
        
        return (Long)this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"addressPolicy.insertAddressPolicy", addressPolicy);
    }
     
    /**
     * {@inheritDoc}
     */
    public List<AddressPolicy> queryByAddressName(String addressName)
    {
        
        return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"addressPolicy.queryByAddressName", addressName);
    }
    
    @Override
	public List<AddressPolicy> queryBySort(String str, int startRow,
			int pageSize) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"addressPolicy.sort",str,startRow,pageSize);
	}
    
	/*
	 * (non-Javadoc)
	 * @see com.soc.dao.policy.AddressDao#queryUserByAddressPolicyId(long)
	 */
	public List<User> queryUserByAddressPolicyId(long id) {
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"addressPolicy.queryuser",id);
	}
    
}
