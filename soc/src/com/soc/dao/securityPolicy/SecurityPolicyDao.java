/*
 * 文 件 名:  TimePolicyDao.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.dao.securityPolicy;

import java.util.List;
import java.util.Map;

import com.soc.model.securityPolicy.SecurityPolicy;

/**
 * 时间策略Dao类
 * 时间策略的查询，修改，添加，删除,时间策略状态的改变
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  
 * @since  [用户管理/时间策略管理/V100R001C001]
 */
public interface SecurityPolicyDao
{
    /**
     * 查询时间策略记录的条数
     * @param map Map
     * @return int
     * @see [类、类#方法、类#成员]
     */
    public int count(Map map);
    
    /**
     * 查询符合条件的记录
     * @param map Map
     * @param startRow int
     * @param pageSize int
     * @return List<TimePolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<SecurityPolicy> queryPolicy(Map map, int startRow, int pageSize);
    
  
    
    /**
     * 根据Id查询时间策略对象
     * @param timePolicyId long
     * @return  TimePolicy
     * @see [类、类#方法、类#成员]
     */
    public SecurityPolicy queryPolicyById(long policyId);
    

    
    /**
     * 标记删除时间策略
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void deletePolicy(Map map);
    
    /**
     * 更改时间策略信息
     * @param timePolicy TimePolicy
     * @see [类、类#方法、类#成员]
     */
    public void updatePolicy(SecurityPolicy policy);
    
    /**
     *插入时间策略信息
     * @param timePolicy TimePolicy
     * @see [类、类#方法、类#成员]
     */
    public void insertPolicy(SecurityPolicy policy);
    
    /**
     * 根据策略名称查询策略
     * @param timePolicyName String
     * @return List<TimePolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<SecurityPolicy> queryByPolicyName(String timePolicyName);
    
    public List<SecurityPolicy> queryAllSecurityPolicy();
    
    public int countOfName(String name);
}
