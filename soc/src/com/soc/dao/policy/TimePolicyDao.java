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
package com.soc.dao.policy;

import java.util.List;
import java.util.Map;

import com.soc.model.policy.TimePolicy;
import com.soc.model.user.User;

/**
 * 时间策略Dao类
 * 时间策略的查询，修改，添加，删除,时间策略状态的改变
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  
 * @since  [用户管理/时间策略管理/V100R001C001]
 */
public interface TimePolicyDao
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
    public List<TimePolicy> queryTimePolicy(Map map, int startRow, int pageSize);
    
    /**
     * 查询符合条件的记录
     * @param map Map
     * @return List<TimePolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<TimePolicy> queryTimePolicy(Map map);
    
    /**
     * 根据Id查询时间策略对象
     * @param timePolicyId long
     * @return  TimePolicy
     * @see [类、类#方法、类#成员]
     */
    public TimePolicy queryTimePolicyById(long timePolicyId);
    
    /**
     * 更改时间策略状态
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void updateTimePolicyStatus(Map map);
    
    /**
     * 标记删除时间策略
     * @param map Map
     * @see [类、类#方法、类#成员]
     */
    public void deleteTimePolicy(Map map);
    
    /**
     * 更改时间策略信息
     * @param timePolicy TimePolicy
     * @see [类、类#方法、类#成员]
     */
    public void updateTimePolicy(TimePolicy timePolicy);
    
    /**
     *插入时间策略信息
     * @param timePolicy TimePolicy
     * @see [类、类#方法、类#成员]
     */
    public void insertTimePolicy(TimePolicy timePolicy);
    
    /**
     * 根据策略名称查询策略
     * @param timePolicyName String
     * @return List<TimePolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<TimePolicy> queryBytimePolicyName(String timePolicyName);
    
    /**
     * 排序
     * @param map Map
     * @param startRow int
     * @param pageSize int
     * @return List<TimePolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<TimePolicy> queryBySort(String str,int startRow, int pageSize);
    
    /**
     * 通过时间策略Id获得与之关联的用户
     * 
     * @param id long
     * @return 
     */
    public List<User> queryUserByTimePolicyId(long id);
    
}
