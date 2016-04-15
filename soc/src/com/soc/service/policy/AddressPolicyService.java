/*
 * 文 件 名:  AddressPolicyService.java
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
import java.util.Map;

import com.soc.model.policy.Address;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.user.User;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 地址策略Service类
 * 地址策略的查询，快速查询，高级查询，标记删除，修改，地址策略状态的修改
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-14]
 * @see  com.soc.dao.policy.AddressPolicyDao
 * @since  [用户管理/地址策略管理/V100R001C001]
 */
public interface AddressPolicyService extends Serializable
{
    /**
     * 记录符合条件的地址策略记录条数
     * @param map Map
     * @return int
     * @see com.soc.dao.policy.AddressPolicyDao#count(Map)
     */
    public int count(Map map);
    
    /**
     *
     * 查询符合条件的记录
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see com.soc.dao.policy.AddressPolicyDao#queryAddressPolicy(Map, int, int)
     */
    public SearchResult queryAddressPolicy(Map map, Page page);
    
    /**
     * <一句话功能简述>
     * 查询符合条件的记录
     * @param map Map
     * @return List<AddressPolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<AddressPolicy> queryAddressPolicy(Map map);
    
    /**
     * 
     * 根据Id更新地址策略的状态
     * @param addreesId long
     * @param status int
     * @see com.soc.dao.policy.AddressPolicyDao#updateAddressPolicyStatus(Map)
     * 
     */
    public void updateAddressPolicyStatus(long addreesId, int status);
    
    /**
     * 根据id查询地址策略对象
     * @param id long
     * @return AddressPolicy
     * @see com.soc.dao.policy.AddressPolicyDao#queryById(long)
     */
    public AddressPolicy queryById(long id);
    
    /**
     * 删除功能
     * 根据id标记删除地址策略
     * @param id long
     * @see com.soc.dao.policy.AddressPolicyDao#deleteAddressPolicy(Map)
     */
    public void deleteAddressPolicy(long id);
    
    /**
     * 更新功能
     * 更新地址策略
     * @param addressPolicy AddressPolicy
     * @param ips String
     * @see com.soc.dao.policy.AddressPolicyDao#insertAddressPolicy(AddressPolicy)
     */
    public void updateAddressPolicy(AddressPolicy addressPolicy, String ips);
    
    /**
     * 查询地址策略
     * 根据策略名称查询地址策略
     * @param addressName String
     * @return List<AddressPolicy>
     * @see com.soc.dao.policy.AddressPolicyDao#queryByAddressName(String)
     */
    public List<AddressPolicy> queryByAddressName(String addressName);
    
    /**
     * 排序
     * @param map Map
     * @param page Page
     * @return List<AddressPolicy>
     * @see com.soc.dao.policy.AddressPolicyDao#queryByAddressName(String)
     */
    public SearchResult sort(Map map,Page page);
    
    /**
     * 根据地址策略Id获得所有用户
     * @param id
     * @return
     */
    public List<User> queryUserByAddressPolicyId(long id);
    
    /**
     * 根据策略Id获得Ip段
     * @param id
     * @return
     */
    public List<Address> getAddressList(long id);
}
