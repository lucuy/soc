/*
 * 文 件 名:  TimePolicyService.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.policy;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.soc.model.policy.TimePolicy;
import com.soc.model.user.User;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 时间策略Service接口
 * 时间策略的修改，删除，添加，更新，时间策略的状态的修改
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  com.soc.dao.policy.TimePolicyDao
 * @since  [用户管理/时间策略管理/V100R001C001]
 */
public interface TimePolicyService extends Serializable
{
    /**
     * 
     * 记录符合条件的时间策略记录条数
     * @param map Map
     * @return int
     * @see com.soc.dao.policy.TimePolicyDao#count(Map)
     */
    public int count(Map map);
    
    /**
     * 查询符合条件的记录
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see com.soc.dao.policy.TimePolicyDao#queryTimePolicy(Map, int, int)
     */
    public SearchResult queryTimePolicy(Map map, Page page);
    
    /**
     * 查询符合条件的记录
     * @param map Map
     * @return List<TimePolicy>
     * @see [类、类#方法、类#成员]
     */
    public List<TimePolicy> queryTimePolicy(Map map);
    
    /**
     * 根据ID查询时间策略
     * @param timePolicyId long
     * @return TimePolicy
     * @see com.soc.dao.policy.TimePolicyDao#queryTimePolicyById(long)
     */
    public TimePolicy queryTimePolicyById(long timePolicyId);
    
    /**
     * 更新时间策略的状态
     * @param id long
     * @param timePolicyStatus int
     * @see com.soc.dao.policy.TimePolicyDao#updateTimePolicyStatus(Map)
     */
    public void updateTimePolicyStatus(long id,int timePolicyStatus);
    
    /**
     * 根据ID标记删除时间策略
     * @param id long
     * @see com.soc.dao.policy.TimePolicyDao#deleteTimePolicy(Map)
     */
    public void deleteTimePolicy(long id);
   
    /**
     * 更新时间策略
     * 时间策略的更新，时间策略的添加
     * @param tp TimePolicy
     * @see com.soc.dao.policy.TimePolicyDao#insertTimePolicy(TimePolicy)
     *      
     */
    public void updateTimePolicy(TimePolicy tp);
    
    /**
     * 根据策略名查询策略
     * @param timePolicyName String
     * @return List<TimePolicy>
     * @see com.soc.dao.policy.TimePolicyDao#queryBytimePolicyName(String)
     */
    public List<TimePolicy> queryByTimePolicyName(String timePolicyName);
    
    /**
     * 排序
     * @param map Map
     * @param page Page
     * @return SearchResult
     * @see com.soc.dao.policy.TimePolicyDao#queryTimePolicy(Map, int, int)
     */
    public SearchResult sort(Map map,Page page);
    
    /**
     * 根据时间策略Id查询与之关联的所有用户
     * @param id
     * @return
     */
    public List<User> queryUserByTimePolicyId(long id);
}
