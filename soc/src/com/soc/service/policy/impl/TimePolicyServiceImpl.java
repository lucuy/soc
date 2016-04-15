/*
 * 文 件 名:  TimePolicyServiceImpl.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-11
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.soc.service.policy.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.policy.TimePolicyDao;
import com.soc.model.policy.TimePolicy;
import com.soc.model.user.User;
import com.soc.service.policy.TimePolicyService;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 时间策略Service实现类
 * 时间策略的查询，修改，添加，删除，时间策略的状态修改
 * 
 * @author  liuyanxu
 * @version  [V100R001C001, 2012-8-11]
 * @see  com.soc.dao.policy.TimePolicyDao
 * @since  [用户管理/时间策略管理/V100R001C001]
 */
public class TimePolicyServiceImpl implements TimePolicyService
{
    
    //时间管理策略业务对象
    private TimePolicyDao timePolicyDao;
    
    private String set = "1";
    
    /**
    * {@inheritDoc}
    */
    public int count(Map map)
    {
        
        return timePolicyDao.count(map);
    }
    
    /**
     * {@inheritDoc}
     */
    public SearchResult queryTimePolicy(Map map, Page page)
    {
        int rowsCount = timePolicyDao.count(map);
        page.setTotalCount(rowsCount);
        List<TimePolicy> list = timePolicyDao.queryTimePolicy(map, page.getStartIndex(), page.getPageSize());
        // 对查找的结果做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    
    /**
     * {@inheritDoc}
     */
    public List<TimePolicy> queryTimePolicy(Map map)
    {
        return timePolicyDao.queryTimePolicy(map);
    }
    
    /**
    * {@inheritDoc}
    */
    public void updateTimePolicyStatus(long id, int timePolicyStatus)
    {
        
        Map<String, Object> map = new HashMap<String, Object>();
        //将传入的放到Map内
        map.put("timePolicyId", id);
        //将要更新为的状态，放到Map内
        map.put("timePolicyStatus", timePolicyStatus);
        //将当前时间赋，放到Map内
        map.put("timePolicyUpdateDateTime", new Date());
        
        timePolicyDao.updateTimePolicyStatus(map);
    }
    
    /**
    * {@inheritDoc}
    */
    public TimePolicy queryTimePolicyById(long timePolicyId)
    {
        
        return timePolicyDao.queryTimePolicyById(timePolicyId);
    }
    
    /**
    * {@inheritDoc}
    */
    public void deleteTimePolicy(long id)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        //将传递过来的id放到map内
        map.put("timePolicyTd", id);
        //将当前时间存到map内       
        map.put("timePolicyUpdateDateTime", new Date());
        //将删除标记设置成1,存入map
        map.put("timePolicyIsDelete", 1);
        
        timePolicyDao.deleteTimePolicy(map);
    }
    
    public TimePolicyDao getTimePolicyDao()
    {
        return timePolicyDao;
    }
    
    public void setTimePolicyDao(TimePolicyDao timePolicyDao)
    {
        this.timePolicyDao = timePolicyDao;
    }
    
    /**
     * {@inheritDoc}
     */
    public void updateTimePolicy(TimePolicy tp)
    {
        //拼接时间点
        if (tp.getTimePolicyHour() != null)
        {
            tp.setTimePolicyHour(this.sethour(tp.getTimePolicyHour()));
        }
        else
        {
            tp.setTimePolicyHour("000000000000000000000000");
        }
        //拼接周时间点
        if (tp.getTimePolicyWeek() != null)
        {
            
            ////System.out.println(tp.getTimePolicyWeek());
            ////System.out.println(this.setweek(tp.getTimePolicyWeek()));
            tp.setTimePolicyWeek(this.setweek(tp.getTimePolicyWeek()));
        }
        else
        {
            tp.setTimePolicyWeek("0000000");
        }
        
        //判断用户采用那种方式的时间策略,如果为固定时间段，将周时间段赋值为"0000000",如果为周时间段，将固定时间段设置为空
        if (tp.getTimePolicyexecuteWay() == 0)
        {
            tp.setTimePolicyWeek("00000000");
        }
        else
        {
            tp.setTimePolicyDateStart(null);
            tp.setTimePolicyDateEnd(null);
        }
        //判断时间策略对象的Id是否为空，如果为空执行更新操作,不为空执行添加操作
        if (tp.getTimePolicyId() != 0)
        {
            //将当前时间赋值给时间对象的更新时间
            tp.setTimePolicyUpdateDateTime(new Date());
            timePolicyDao.updateTimePolicy(tp);
        }
        else
        {
            //将当前时间赋值给时间策略的创建时间
            tp.setTimePolicyCreateDateTime(new Date());
            //将当前时间赋值给时间策略的更新时间
            tp.setTimePolicyUpdateDateTime(new Date());
            //将时间策略的状态赋值为激活状态
            tp.setTimePolicyStates(1);
            //将时间策略的删除标识设置为未删除
            tp.setTimePolicyIsDelete(0);
            
           
            timePolicyDao.insertTimePolicy(tp);
        }
    }
    
    /**
     * 拼接周操作
     * 
     * @param getweek
     * @return
     */
    private String setweek(String getweek)
    {
        String week = "0,0,0,0,0,0,0";
        String[] weeks = week.split(",");
        String[] newWeek = getweek.split(",");
        Integer w = 0;
        for (int i = 0; i < newWeek.length; i++)
        {
            w = Integer.parseInt(newWeek[i].trim());
            weeks[w - 1] = set;
        }
        StringBuffer weekResult = new StringBuffer();
        for (String s : weeks)
        {
            weekResult = weekResult.append(s);
        }
        return weekResult.toString();
    }
    
    /**
     * 拼接时间点操作
     * 
     * @param gethour
     * @return
     */
    private String sethour(String gethour)
    {
        String hour = "0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0";
        String[] hours = hour.split(",");
        String[] newHour = gethour.split(",");
        Integer h = 0;
        for (int j = 0; j < newHour.length; j++)
        {
            h = Integer.parseInt(newHour[j].trim());
            if (h == 24)
            {
                hours[0] = set;
            }
            else
            {
                hours[h] = set;
                
            }
        }
        
        StringBuffer newhour = new StringBuffer();
        for (String s1 : hours)
        {
            newhour = newhour.append(s1);
        }
        return newhour.toString();
    }
    
    /** {@inheritDoc} */
    
    @Override
    public List<TimePolicy> queryByTimePolicyName(String timePolicyName)
    {
        // TODO Auto-generated method stub
        return timePolicyDao.queryBytimePolicyName(timePolicyName);
    }
    
    @Override
	public SearchResult sort(Map map, Page page) {
		 int rowsCount = timePolicyDao.count(map);
	        page.setTotalCount(rowsCount);
	        String field=(String)map.get("field");
	        String sortType=(String)map.get("sortType");
	        String str=" \""+field+"\""+" "+sortType;
	        List<TimePolicy> list = timePolicyDao.queryBySort(str, page.getStartIndex(), page.getPageSize());
	        SearchResult sr = new SearchResult() ; 
	        sr.setList(list);
	        sr.setPage(page);
	        return sr;
	}

	@Override
	public List<User> queryUserByTimePolicyId(long id) {
		return timePolicyDao.queryUserByTimePolicyId(id);
	}
    
}
