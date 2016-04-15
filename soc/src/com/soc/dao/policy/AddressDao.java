/*
 * 文 件 名:  Address.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.dao.policy;

import java.util.List;

import com.soc.model.policy.Address;
import com.soc.model.user.User;

/**
 * 地址Dao类
 * 地址的插入，删除
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-13]
 * @see  
 * @since  [用户管理/地址策略管理/V100R001C001]
 */
public interface AddressDao
{
    /**
     * 删除地址
     * 根据id删除地址
     * @param id long
     * @see 
     */
    public void delteAddress(long id);
    
    /**
     * 插入一条地址
     * @param address Address
     * @see 
     */
    public void insertAddress(Address address);
    
    /**
     * 根据策略Id获得策略所有的IP段
     * @param id
     * @return
     */
    public List<Address> getAddressList(long id);
   
}
