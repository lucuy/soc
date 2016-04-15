/*
 * 文 件 名:  AddressPolicyDao.java
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
import java.util.Map;

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
public interface AddressPolicyDao
{
    /**
     * 
     * 查询符合条件的地址策略的条数
     * @param map Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * <一句话功能简述>
     * 查询符合条件的记录
     * @param map Map
     * @param startRow int
     * @param pageSize int
     * @return List<AddressPolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<AddressPolicy> queryAddressPolicy(Map map, int startRow, int pageSize);
    
    /**
     * <一句话功能简述>
     * 查询符合条件的记录
     * @param map Map
     * @return List<AddressPolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<AddressPolicy> queryAddressPolicy(Map map);
    
    /**
     * <一句话功能简述>
     * 更新地址策略的状态
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void updateAddressPolicyStatus(Map map);
    
    /**
     * <一句话功能简述>
     * 根据id查询地址策略
     * @param id long
     * @return AddressPolicy
     * @see [类、类#方法、类#成员]
     */
    public AddressPolicy queryById(long id);
    
    /**
     * 删除功能
     * 根据传入的id标记删除地址策略
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void deleteAddressPolicy(Map map);
    
    /**
     * 更新操作
     * 更新地址策略
     * @param addressPolicy AddressPolicy
     * @see [类、类#方法、类#成员]
     */
    public void updateAddressPolicy(AddressPolicy addressPolicy);
    
    /**
     * 插入操作
     * 插入一条地址策略
     * @param addressPolicy AddressPolicy
     * @return long
     * @see [类、类#方法、类#成员]
     */
    public long insertAddressPolicy(AddressPolicy addressPolicy);
    
    /**
     * 查询策略
     * 根据策略名称查询
     * @param addressName String
     * @return List<AddressPolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<AddressPolicy> queryByAddressName(String addressName);
    
    /**
     * 排序
     * @param addressName String
     * @param addressName Integer
     * @param addressName Integer
     * @return List<AddressPolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<AddressPolicy> queryBySort(String str,int startRow,int pageSize);
    
    
    
    /**
     * 根据地址策略Id获得所有用户
     * @param id
     * @return
     */
    public List<User> queryUserByAddressPolicyId(long id);
}
