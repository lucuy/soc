/*
 * 文 件 名:  TimePolicy.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.model.policy;

import java.io.Serializable;
import java.util.Date;

/**
 * 时间策略实体类
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  
 * @since  [用户管理/时间策略管理/V100R001C001]
 */
public class TimePolicy implements Serializable
{
    
    //时间策略ID
    private long timePolicyId;
    
    //时间策略名
    private String timePolicyName;
    
    // 生效方式（0：禁止|1：允许）
    private int timePolicyEffectWay;
    
    //开始时间点
    private Date timePolicyTimeStart;
    
    //结束时间点
    private Date timePolicyTimeEnd;
    
    // 周期按时间段重复的开始日期
    private Date timePolicyDateStart;
    
    //  周期按时间段重复的结束日期
    private Date timePolicyDateEnd;
    
    //  时间点，有0和1组成（0：未选择 | 1：选择）
    private String timePolicyHour;
    
    //重复执行日 由0和1组成(0:未选择 |1:选择)
    private String timePolicyWeek;
    
    //按周执行或者按时间段执行(0:按照时间点|1：按周)
    private int timePolicyexecuteWay;
    
    // 创建者登录名
    
    private String timePolicyUserLoginName;
    
    //创建时间
    private Date timePolicyCreateDateTime;
    
    //更新时间
    private Date timePolicyUpdateDateTime;
    
    //标记删除标识（0：未删除 | 1：删除)
    private int timePolicyIsDelete;
    
    //时间策略状态
    private int timePolicyStates;
    
    //描述
    private String timePolicyMemo;
    
    public long getTimePolicyId()
    {
        return timePolicyId;
    }
    
    public void setTimePolicyId(long timePolicyId)
    {
        this.timePolicyId = timePolicyId;
    }
    
    public String getTimePolicyName()
    {
        return timePolicyName;
    }
    
    public void setTimePolicyName(String timePolicyName)
    {
        this.timePolicyName = timePolicyName;
    }
    
    public int getTimePolicyEffectWay()
    {
        return timePolicyEffectWay;
    }
    
    public void setTimePolicyEffectWay(int timePolicyEffectWay)
    {
        this.timePolicyEffectWay = timePolicyEffectWay;
    }
    
    public Date getTimePolicyTimeStart()
    {
        return timePolicyTimeStart;
    }
    
    public void setTimePolicyTimeStart(Date timePolicyTimeStart)
    {
        this.timePolicyTimeStart = timePolicyTimeStart;
    }
    
    public Date getTimePolicyTimeEnd()
    {
        return timePolicyTimeEnd;
    }
    
    public void setTimePolicyTimeEnd(Date timePolicyTimeEnd)
    {
        this.timePolicyTimeEnd = timePolicyTimeEnd;
    }
    
    public Date getTimePolicyDateStart()
    {
        return timePolicyDateStart;
    }
    
    public void setTimePolicyDateStart(Date timePolicyDateStart)
    {
        this.timePolicyDateStart = timePolicyDateStart;
    }
    
    public Date getTimePolicyDateEnd()
    {
        return timePolicyDateEnd;
    }
    
    public void setTimePolicyDateEnd(Date timePolicyDateEnd)
    {
        this.timePolicyDateEnd = timePolicyDateEnd;
    }
    
    public String getTimePolicyHour()
    {
        return timePolicyHour;
    }
    
    public void setTimePolicyHour(String timePolicyHour)
    {
        this.timePolicyHour = timePolicyHour;
    }
    
    public String getTimePolicyUserLoginName()
    {
        return timePolicyUserLoginName;
    }
    
    public void setTimePolicyUserLoginName(String timePolicyUserLoginName)
    {
        this.timePolicyUserLoginName = timePolicyUserLoginName;
    }
    
    public Date getTimePolicyCreateDateTime()
    {
        return timePolicyCreateDateTime;
    }
    
    public void setTimePolicyCreateDateTime(Date timePolicyCreateDateTime)
    {
        this.timePolicyCreateDateTime = timePolicyCreateDateTime;
    }
    
    public Date getTimePolicyUpdateDateTime()
    {
        return timePolicyUpdateDateTime;
    }
    
    public void setTimePolicyUpdateDateTime(Date timePolicyUpdateDateTime)
    {
        this.timePolicyUpdateDateTime = timePolicyUpdateDateTime;
    }
    
    public int getTimePolicyIsDelete()
    {
        return timePolicyIsDelete;
    }
    
    public void setTimePolicyIsDelete(int timePolicyIsDelete)
    {
        this.timePolicyIsDelete = timePolicyIsDelete;
    }
    
    public String getTimePolicyMemo()
    {
        return timePolicyMemo;
    }
    
    public void setTimePolicyMemo(String timePolicyMemo)
    {
        this.timePolicyMemo = timePolicyMemo;
    }
    
    public int getTimePolicyStates()
    {
        return timePolicyStates;
    }
    
    public void setTimePolicyStates(int timePolicyStates)
    {
        this.timePolicyStates = timePolicyStates;
    }
    
    public String getTimePolicyWeek()
    {
        return timePolicyWeek;
    }
    
    public void setTimePolicyWeek(String timePolicyWeek)
    {
        this.timePolicyWeek = timePolicyWeek;
    }
    
    public int getTimePolicyexecuteWay()
    {
        return timePolicyexecuteWay;
    }
    
    public void setTimePolicyexecuteWay(int timePolicyexecuteWay)
    {
        this.timePolicyexecuteWay = timePolicyexecuteWay;
    }
}
