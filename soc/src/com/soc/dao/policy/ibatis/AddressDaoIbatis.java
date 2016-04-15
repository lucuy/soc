/*
 * 文 件 名:  AddressDaoIbatis.java
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

import com.soc.dao.ibatis.BaseDaoIbatis;
import com.soc.dao.policy.AddressDao;
import com.soc.model.conf.GlobalConfig;
import com.soc.model.policy.Address;
import com.soc.model.user.User;

/**
 * 地址Dao实现类
 * 对地址的添加，删除
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-13]
 * @see  
 * @since  [用户管理/地址策略管理/V100R001C001]
 */
public class AddressDaoIbatis extends BaseDaoIbatis implements AddressDao
{
    
    /**
     * {@inheritDoc}
     */
    public void delteAddress(long id)
    {
        super.delete(GlobalConfig.sqlId+"address.delete", id);
    }
    
    /**
     *   {@inheritDoc}
     */
    public void insertAddress(Address address)
    {
        this.getSqlMapClientTemplate().insert(GlobalConfig.sqlId+"address.insert", address);
    }

	@Override
	public List<Address> getAddressList(long id) {
		
		return this.getSqlMapClientTemplate().queryForList(GlobalConfig.sqlId+"address.queryByPolicyId",id);
	}


}
