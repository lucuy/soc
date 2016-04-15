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
package com.soc.model.policy;

/**
 * 地址段
 * 地址段的添加，删除，查询
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-13]
 * @see  
 * @since  [用户管理/地址策略管理/V100R001C001]
 */
public class Address
{
    
   

    //地址策略id;
    private Long addressId;
    
    //地址策略起始Ip
    private String addressStartIp;
    
    //地址策略结束IP
    private String addressEndIp;
    
    //关联地址策略Id
    private Long relAddressPolicyId;
    
    public Long getAddressId()
    {
        return addressId;
    }

    public void setAddressId(Long addressId)
    {
        this.addressId = addressId;
    }

    public String getAddressStartIp()
    {
        return addressStartIp;
    }

    public void setAddressStartIp(String addressStartIp)
    {
        this.addressStartIp = addressStartIp;
    }

    public String getAddressEndIp()
    {
        return addressEndIp;
    }

    public void setAddressEndIp(String addressEndIp)
    {
        this.addressEndIp = addressEndIp;
    }

    public Long getRelAddressPolicyId()
    {
        return relAddressPolicyId;
    }

    public void setRelAddressPolicyId(Long relAddressPolicyId)
    {
        this.relAddressPolicyId = relAddressPolicyId;
    }

}
