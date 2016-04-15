/*
 * 文 件 名:  AddressPolicy.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.model.policy;

import java.util.Date;
import java.util.List;

/**
 * 地址策略实体类
 * 地址策略的查询，快速查询，高级查询，地址策略状态的改变,地址策略的修改，地址策略的标记删除
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-13]
 * @see  
 * @since  [用户管理/地址策略管理/V100R001C001]
 */
public class AddressPolicy
{
    
    //地址策略Id
    private long addressPolicyId;
   
    //地址策略名称
    private String addressPolicyName;
    
    //地址策略生效方式(0禁止|1允许)
    private int addressPolicyEffectWay;
    
    //地址策略状态(0锁定|1激活)
    private int addressPolicyStatus;
    
    //地址策略创建世间
    private Date addressPolicyCreateTime;
    
    //地址策略更新时间
    private Date addressPolicyUpdateTime;
    
    //地址策略创建者登录名
    private String  addressPolicyCreateUserLoginName;
    
    //标记删除标识(0未删除|1删除)
    private int addressPolicyIsDelete;
    
    //策略描述
    private String addressPolicyMemo;
    
    //Ip地址段列表
    private List<Address> addressList;
    
    public List<Address> getAddressList()
    {
        return addressList;
    }
    public void setAddressList(List<Address> addressList)
    {
        this.addressList = addressList;
    }
    public long getAddressPolicyId()
    {
        return addressPolicyId;
    }
    public void setAddressPolicyId(long addressPolicyId)
    {
        this.addressPolicyId = addressPolicyId;
    }
    public String getAddressPolicyName()
    {
        return addressPolicyName;
    }
    public void setAddressPolicyName(String addressPolicyName)
    {
        this.addressPolicyName = addressPolicyName;
    }
    public int getAddressPolicyEffectWay()
    {
        return addressPolicyEffectWay;
    }
    public void setAddressPolicyEffectWay(int addressPolicyEffectWay)
    {
        this.addressPolicyEffectWay = addressPolicyEffectWay;
    }
    public int getAddressPolicyStatus()
    {
        return addressPolicyStatus;
    }
    public void setAddressPolicyStatus(int addressPolicyStatus)
    {
        this.addressPolicyStatus = addressPolicyStatus;
    }
    public Date getAddressPolicyCreateTime()
    {
        return addressPolicyCreateTime;
    }
    public void setAddressPolicyCreateTime(Date addressPolicyCreateTime)
    {
        this.addressPolicyCreateTime = addressPolicyCreateTime;
    }
    public Date getAddressPolicyUpdateTime()
    {
        return addressPolicyUpdateTime;
    }
    public void setAddressPolicyUpdateTime(Date addressPolicyUpdateTime)
    {
        this.addressPolicyUpdateTime = addressPolicyUpdateTime;
    }
    public String getAddressPolicyCreateUserLoginName()
    {
        return addressPolicyCreateUserLoginName;
    }
    public void setAddressPolicyCreateUserLoginName(String addressPolicyCreateUserLoginName)
    {
        this.addressPolicyCreateUserLoginName = addressPolicyCreateUserLoginName;
    }
    public int getAddressPolicyIsDelete()
    {
        return addressPolicyIsDelete;
    }
    public void setAddressPolicyIsDelete(int addressPolicyIsDelete)
    {
        this.addressPolicyIsDelete = addressPolicyIsDelete;
    }
    public String getAddressPolicyMemo()
    {
        return addressPolicyMemo;
    }
    public void setAddressPolicyMemo(String addressPolicyMemo)
    {
        this.addressPolicyMemo = addressPolicyMemo;
    }
}
