package com.soc.service.events.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.events.QueryEventsGroupDao;
import com.soc.model.events.QueryEvents;
import com.soc.model.events.QueryEventsGroup;
import com.soc.service.events.QueryEventsGroupService;
import com.soc.service.impl.BaseServiceImpl;

public class QueryEventsGroupServiceImpl extends BaseServiceImpl implements QueryEventsGroupService
{
    private QueryEventsGroupDao queryEvevtsGroupDao;
    private static final String PICTURE_NAME_SEED = "arrow_03.gif";        //表示页子节点的图片
    private static final String PICTURE_NAME_CLOSE = "u321_normal.gif";        //表示关闭的图片
    //private static final String PICTURE_NAME_OPEN = "";         //表示打开的图片
    private static final int QUERY_EVENTS_GROUP_TPYE = 1;      //查询事件组类型 1：自定义组u321_normal.gif

    
    StringBuffer treeBuff;
    @Override
    public String createTrre(String objectBath)
    {
        treeBuff = new StringBuffer();
        Map<String, Long> map = new HashMap<String, Long>();
        map.put("parentId", Long.valueOf(0));
        List<QueryEventsGroup> queryEventsGroupList = queryEvevtsGroupDao.queryByParentId(map);
        
        //到得所有要节点组数据
        for(QueryEventsGroup queryEventsGroup : queryEventsGroupList)
        {
            Map<String, Long> map1 = new HashMap<String, Long>();
            map1.put("parentId", queryEventsGroup.getQueryEventsGroupId());
            //查询下一级组信息
            List<QueryEventsGroup>  groupNodeList = queryEvevtsGroupDao.queryByParentId(map1);
            //没有子节点
            if(groupNodeList.size() <= 0)
            {
                treeBuff.append("<ul>");
                treeBuff.append("<li><a href=\"javascript:linkTo('"+queryEventsGroup.getQueryEventsGroupId()+"','"+queryEventsGroup.getQueryEventsGroupType()+"');\"><img src=\""+objectBath+"/images/"+PICTURE_NAME_SEED+"\"></a>&nbsp;<a href=\"javascript:linkTo('"+queryEventsGroup.getQueryEventsGroupId()+"','"+queryEventsGroup.getQueryEventsGroupType()+"');\"><span class=\"eventext\">&nbsp;"+queryEventsGroup.getQueryEventsGroupName()+"</span></a>");
                treeBuff.append("</li>");
                treeBuff.append("</ul>");
            }
            else
            {
                //画出下级组
                treeBuff.append("<ul>");
                treeBuff.append("<li id=\"query_"+queryEventsGroup.getQueryEventsGroupId()+"\"><a href=\"javascript:action_event('"+queryEventsGroup.getQueryEventsGroupId()+"','img');\"><img src=\""+objectBath+"/images/"+PICTURE_NAME_CLOSE+"\" id=\"img_query_"+queryEventsGroup.getQueryEventsGroupId()+"\"></a>&nbsp;<a href=\"javascript:void(0);\" ondblclick=\"action_event('"+queryEventsGroup.getQueryEventsGroupId()+"','img',"+queryEventsGroup.getQueryEventsGroupType()+")\"><span class=\"eventext\">&nbsp;"+queryEventsGroup.getQueryEventsGroupName()+"</span></a>");
                drawSon(groupNodeList,objectBath);
                treeBuff.append("</li>");
                treeBuff.append("</ul>");
            }
            
        }
        return treeBuff.toString();
    }
    //写出下级组
    public void drawSon( List<QueryEventsGroup> groupNodeList,String objectBath)
    {
        for(QueryEventsGroup queryEventsGroup : groupNodeList)
        {
            Map<String, Long> map = new HashMap<String, Long>();
            map.put("parentId", queryEventsGroup.getQueryEventsGroupId());
            List<QueryEventsGroup>  groupNodeList1 = queryEvevtsGroupDao.queryByParentId(map);
            //如果没有下级组写出事件查询条件
            if(groupNodeList1.size() <=0)
            {
                treeBuff.append("<ul class=\"disply\">");
                treeBuff.append("<li class=\"eventleft\"><a href=\"javascript:linkTo('"+map.get("parentId")+"','"+queryEventsGroup.getQueryEventsGroupType()+"');\"><img src=\""+objectBath+"/images/"+PICTURE_NAME_SEED+"\" ></a>&nbsp;<a href=\"javascript:linkTo('"+map.get("parentId")+"','"+queryEventsGroup.getQueryEventsGroupType()+"');\"><span class=\"eventext\">&nbsp;"+queryEventsGroup.getQueryEventsGroupName()+"</span></a>");
                treeBuff.append("</li>");
                treeBuff.append("</ul>");
            }
            else
            {
                //循环写出组
                treeBuff.append("<ul  class=\"disply\">");
                treeBuff.append("<li id=\"query_"+queryEventsGroup.getQueryEventsGroupId()+"\" class=\"eventleft\"><a href=\"javascript:action_event('"+queryEventsGroup.getQueryEventsGroupId()+"','img');\"><img src=\""+objectBath+"/images/"+PICTURE_NAME_CLOSE+"\" id=\"img_query_"+queryEventsGroup.getQueryEventsGroupId()+"\"></a>&nbsp;<a href=\"javascript:void(0)\" ondblclick=\"action_event('"+queryEventsGroup.getQueryEventsGroupId()+"','img',"+queryEventsGroup.getQueryEventsGroupType()+");\"><span class=\"eventext\">&nbsp;"+queryEventsGroup.getQueryEventsGroupName()+"</span></a>");
                drawSon(groupNodeList1,objectBath);
                treeBuff.append("</li>");
                treeBuff.append("</ul>");
            }
        }
    }
    
    //写出事件查询条件
    public void pageSonNode(List<QueryEventsGroup> queryEventsList ,String objectBath)
    {
        for(QueryEventsGroup queryEvents :queryEventsList )
        {
            treeBuff.append("<ul  class=\"disply\">");
            treeBuff.append("<li class=\"eventleft_n\" ><a href=\"javascript:linkTo('"+queryEvents.getQueryEventsGroupId()+"','"+queryEvents.getQueryEventsGroupType()+"');\"><img src=\""+objectBath+"/images/"+PICTURE_NAME_SEED+"\"></a>&nbsp;<a href=\"javascript:linkTo('"+queryEvents.getQueryEventsGroupId()+"','"+queryEvents.getQueryEventsGroupType()+"');\"><span class=\"eventext\">&nbsp;"+queryEvents.getQueryEventsGroupName()+"</span></a>");
            treeBuff.append("</li>");
            treeBuff.append("</ul>");
        }
    }
    
    
    public QueryEventsGroupDao getQueryEvevtsGroupDao()
    {
        return queryEvevtsGroupDao;
    }
    
    public void setQueryEvevtsGroupDao(QueryEventsGroupDao queryEvevtsGroupDao)
    {
        this.queryEvevtsGroupDao = queryEvevtsGroupDao;
    }
    
}
