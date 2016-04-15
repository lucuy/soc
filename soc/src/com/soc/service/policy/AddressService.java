/*
 * 文 件 名:  AddressService.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-14
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.policy;

import java.io.Serializable;
import java.util.List;

import com.soc.model.policy.Address;



/**
 * 地址Service类
 * 地址的添加，删除
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-14]
 * @see  com.soc.dao.policy.AddressDao
 * @since  [用户管理/地址策略管理/V100R001C001]
 */

public interface AddressService extends Serializable
{
    /**
     * 插入地址
     * 根据传入的对象插入数据库
     * @param address Address
     * @see com.soc.dao.policy.AddressDao#insertAddress(Address)
     * 
     */
    public void insert(Address address);
    
    /**
     * 删除地址
     * 根据id删除地址
     * @param id long
     * @see com.soc.dao.policy.AddressDao#delteAddress(long)
     * 
     */
    public void delete(long id);
    /**
     * 根据策略Id获得Ip段
     * @param id
     * @return
     */
    public List<Address> getAddressList(long id);
}
