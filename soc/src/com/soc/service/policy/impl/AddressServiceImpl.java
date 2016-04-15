/*
 * 文 件 名:  AddressServiceImpl.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-14
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.policy.impl;

import java.util.List;

import com.soc.dao.policy.AddressDao;
import com.soc.model.policy.Address;
import com.soc.service.policy.AddressService;

/**
 * 地址Service实现类
 * 地址的添加，删除
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-14]
 * @see  com.soc.dao.policy.AddressDao
 * @since  [用户管理/地址策略管理/V100R001C001]
 */
public class AddressServiceImpl implements AddressService
{
    
    //地址策略业务类
    private AddressDao addressDao;
    
    /**
     * {@inheritDoc}
     */
    public void insert(Address address)
    {
        addressDao.insertAddress(address);
    }
    
    /**
     * {@inheritDoc}
     */
    public void delete(long id)
    {
        addressDao.delteAddress(id);
    }
    
    public AddressDao getAddressDao()
    {
        return addressDao;
    }
    
    public void setAddressDao(AddressDao addressDao)
    {
        this.addressDao = addressDao;
    }

	@Override
	public List<Address> getAddressList(long id) {
		return addressDao.getAddressList(id);
	}
    
}
