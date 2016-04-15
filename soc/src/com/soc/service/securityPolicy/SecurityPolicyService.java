/*
 * 文 件 名:  PolicyService.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.securityPolicy;

import java.io.Serializable;
import java.security.Policy;
import java.util.List;
import java.util.Map;

import com.soc.model.asset.Asset;
import com.soc.model.securityPolicy.SecurityPolicy;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 时间策略Service接口
 * 时间策略的修改，删除，添加，更新，时间策略的状态的修改
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  com.soc.dao.policy.PolicyDao
 * @since  [用户管理/时间策略管理/V100R001C001]
 */
public interface SecurityPolicyService extends Serializable
{
    /**
     * 
     * 记录符合条件的时间策略记录条数
     * @param map Map
     * @return int
     * @see com.soc.dao.policy.PolicyDao#count(Map)
     */
    public int count(Map map);
    
    /**
     * 查询符合条件的记录
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see com.soc.dao.policy.PolicyDao#queryPolicy(Map, int, int)
     */
    public SearchResult queryPolicy(Map map, Page page);
    
    /**
     * 查询符合条件的记录
     * @param map Map
     * @return List<Policy>
     * @see [类、类#方法、类#成员]
     */
    
    /**
     * 根据ID查询时间策略
     * @param PolicyId long
     * @return Policy
     * @see com.soc.dao.policy.PolicyDao#queryPolicyById(long)
     */
    public SecurityPolicy queryPolicyById(int policyId);
    
    /**
     * 根据ID标记删除时间策略
     * @param id long
     * @see com.soc.dao.policy.PolicyDao#deletePolicy(Map)
     */
    public void deletePolicy(int id);
   
    /**
     * 更新时间策略
     * 时间策略的更新，时间策略的添加
     * @param tp Policy
     * @see com.soc.dao.policy.PolicyDao#insertPolicy(Policy)
     *      
     */
    public void updatePolicy(SecurityPolicy tp);
    
    /**
     * 根据策略名查询策略
     * @param PolicyName String
     * @return List<Policy>
     * @see com.soc.dao.policy.PolicyDao#queryByPolicyName(String)
     */
    public List<SecurityPolicy> queryByPolicyName(String policyName);

	public String issuedPolicy(Asset asset, int policyId,String path);
	//告警信息执行策略
	public void actionPolicy(Asset asset, String relPolicyName ,String path);
	/**
     * 查询所有
     * @param 
     * @return List<Policy>
     * @see com.soc.dao.policy.PolicyDao#queryAllSecurityPolicy()
     */
	public List<SecurityPolicy> queryAllSecurityPolicy();
 
	
	/**
  * 根据名字判断数量
  * @param name
  * @return
  */
	 public int countOfName(String name);
  
}
