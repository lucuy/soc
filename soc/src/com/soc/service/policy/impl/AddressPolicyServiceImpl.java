/*
 * 文 件 名:  AddressPolicyServiceImpl.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-14
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.policy.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.policy.AddressDao;
import com.soc.dao.policy.AddressPolicyDao;
import com.soc.model.policy.Address;
import com.soc.model.policy.AddressPolicy;
import com.soc.model.user.User;
import com.soc.service.policy.AddressPolicyService;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 地址策略Service实现类
 * 地址策略的添加，删除，查询，修改，快速搜索，高级搜索
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-14]
 * @see  com.soc.dao.policy.AddressPolicyDao
 * @since  [用户管理/地址策略管理/V100R001C001]
 */
public class AddressPolicyServiceImpl implements AddressPolicyService
{
    //地址策略业务对象
    private AddressPolicyDao addressPolicyDao;
    
    //地址业务对象
    private AddressDao addressDao;
    
    /**
     * {@inheritDoc}
     */
    public int count(Map map)
    {
        
        return addressPolicyDao.count(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public SearchResult queryAddressPolicy(Map map, Page page)
    {
        int rowsCount = addressPolicyDao.count(map);
        page.setTotalCount(rowsCount);
        List<AddressPolicy> list = addressPolicyDao.queryAddressPolicy(map, page.getStartIndex(), page.getPageSize());
        //对查找结果进行处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<AddressPolicy> queryAddressPolicy(Map map)
    {
        return addressPolicyDao.queryAddressPolicy(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateAddressPolicyStatus(long addreesId, int status)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        //将传入的值放入map内
        map.put("addressPolicyStatus", status);
        map.put("addressPolicyUpdateDateTime", new Date());
        map.put("addressPolicyId", addreesId);
        
        //执行更新方法
        addressPolicyDao.updateAddressPolicyStatus(map);
        
    }
    
    /**
     * {@inheritDoc}
     */
    public void deleteAddressPolicy(long id)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        //将值放入map内
        map.put("addressPolicyId", id);
        map.put("addressPolicyUpdateDateTime", new Date());
        
        //执行删除操作
        addressPolicyDao.deleteAddressPolicy(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateAddressPolicy(AddressPolicy addressPolicy, String ips)
    {
        
        String ipStart = "";
        String ipEnd = "";
        long addressPolicyId = addressPolicy.getAddressPolicyId();
        //新建一个地址对象
        Address address = new Address();
        //判断对象内是否有值,有值执行修改 
        if (addressPolicyId != 0)
        {
            //删除地址策略所关联的地址
            addressDao.delteAddress(addressPolicy.getAddressPolicyId());
            //判断ips内是否有值
            if (!ips.equals(""))
            {
                //将所获得到的IP地址分段,并且存储到地址表内
                String[] ipAll = ips.split(";");
                
                //循环每个段，将起始ip跟终止ip获得
                for (int i = 0; i < ipAll.length; i++)
                {
                    ipStart = ipAll[i].split("-")[0];
                    ipEnd = ipAll[i].split("-")[1];
                    //为地址对象赋值
                    address.setAddressStartIp(ipStart);
                    address.setAddressEndIp(ipEnd);
                    address.setRelAddressPolicyId(addressPolicy.getAddressPolicyId());
                    //执行插入地址
                    addressDao.insertAddress(address);
                }
                
            }
            //将对象内的更新时间设置为当前时间
            addressPolicy.setAddressPolicyUpdateTime(new Date());
            //执行更新操作
            addressPolicyDao.updateAddressPolicy(addressPolicy);
        }
        //执行插入操作
        else
        {
            addressPolicy.setAddressPolicyCreateTime(new Date());
            addressPolicy.setAddressPolicyUpdateTime(new Date());
            addressPolicy.setAddressPolicyIsDelete(0);
            addressPolicy.setAddressPolicyStatus(1);
            //执行插入地址策略
            addressPolicyId = addressPolicyDao.insertAddressPolicy(addressPolicy);
            //将所获得到的IP地址分段,并且存储到地址表内
            //判断ips是否有值
            if (!ips.equals(""))
            {
                String[] ipAll1 = ips.split(";");
                
                //循环每个段，将起始ip跟终止ip获得
                for (String ip : ipAll1)
                {
                    ipStart = ip.split("-")[0];
                    ipEnd = ip.split("-")[1];
                    //为地址对象赋值
                    address.setAddressStartIp(ipStart);
                    address.setAddressEndIp(ipEnd);
                    address.setRelAddressPolicyId(addressPolicyId);
                    //执行插入地址
                    addressDao.insertAddress(address);
                }
                
            }
        }
    }
    
    /**
     * {@inheritDoc}
     */
    public List<AddressPolicy> queryByAddressName(String addressName)
    {
        
        return addressPolicyDao.queryByAddressName(addressName);
    }
    
    /**
     * {@inheritDoc}
     */
    public AddressPolicy queryById(long id)
    {
        return addressPolicyDao.queryById(id);
    }
    
    public AddressPolicyDao getAddressPolicyDao()
    {
        return addressPolicyDao;
    }
    
    public void setAddressPolicyDao(AddressPolicyDao addressPolicyDao)
    {
        this.addressPolicyDao = addressPolicyDao;
    }
    
    public AddressDao getAddressDao()
    {
        return addressDao;
    }
    
    public void setAddressDao(AddressDao addressDao)
    {
        this.addressDao = addressDao;
    }
    
    /**
     * {@inheritDoc}
     */
	public SearchResult sort(Map map, Page page) {
		int rowsCount = addressPolicyDao.count(map);
        page.setTotalCount(rowsCount);
        String field=(String)map.get("field");
        String sortType=(String)map.get("sortType");
        String str=" \""+field+"\""+" "+sortType;
        List<AddressPolicy> list = addressPolicyDao.queryBySort(str, page.getStartIndex(), page.getPageSize());
        SearchResult sr = new SearchResult() ; 
        sr.setList(list);
        sr.setPage(page);
        return sr;
	}

	@Override
	public List<User> queryUserByAddressPolicyId(long id) {
		return addressPolicyDao.queryUserByAddressPolicyId(id);
	}

	@Override
	public List<Address> getAddressList(long id) {
		return addressDao.getAddressList(id);
	}

}
