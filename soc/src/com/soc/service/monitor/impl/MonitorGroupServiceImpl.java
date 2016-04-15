package com.soc.service.monitor.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.soc.dao.monitor.MonitorGroupDao;
import com.soc.model.monitor.MonitorGroup;
import com.soc.model.policy.TimePolicy;
import com.soc.service.impl.BaseServiceImpl;
import com.soc.service.monitor.MonitorGroupService;
import com.util.page.Page;
import com.util.page.SearchResult;

/**
 * 
 * <一句话功能简述> <功能详细描述>
 * 
 * @author liuyanxu
 * @version [版本号, 2012-11-4]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MonitorGroupServiceImpl extends BaseServiceImpl implements MonitorGroupService
{
    
    private MonitorGroupDao monitorGroupDao;
    
    private static final String PICTURE_NAME_SEED = "arrow_03.gif"; // 表示页子节点的图片
    
    private static final String PICTURE_NAME_CLOSE = "u321_normal.gif"; // 表示关闭的图片
    
    StringBuffer treeBuff;
    
    @Override
    public List<MonitorGroup> queryByParentId(Map map)
    {
        // TODO Auto-generated method stub
        return monitorGroupDao.queryByParentId(map);
    }
    
    public MonitorGroupDao getMonitorGroupDao()
    {
        return monitorGroupDao;
    }
    
    public void setMonitorGroupDao(MonitorGroupDao monitorGroupDao)
    {
        this.monitorGroupDao = monitorGroupDao;
    }
    
    @Override
    public MonitorGroup queryById(long id)
    {
        // TODO Auto-generated method stub
        return monitorGroupDao.queryById(id);
    }
    
    @Override
    public void updateRelMonitors(Map map)
    {
        // TODO Auto-generated method stub
        monitorGroupDao.updateRelMonitors(map);
    }
    
    @Override
    public int count(Map map)
    {
        // TODO Auto-generated method stub
        return monitorGroupDao.countGroups(map);
    }
    
    @Override
    public SearchResult queryAll(Map map, Page page)
    {
        // TODO Auto-generated method stub
        int rowsCount = monitorGroupDao.countGroups(map);
        page.setTotalCount(rowsCount);
        List<MonitorGroup> list = monitorGroupDao.queryall(map, page.getStartIndex(), page.getPageSize());
        // 对查找的结果做分页处理
        SearchResult sr = new SearchResult();
        sr.setList(list);
        sr.setPage(page);
        return sr;
    }
    
    @Override
    public void insertGroup(MonitorGroup monitorGroup)
    {
        // TODO Auto-generated method stub
        monitorGroupDao.insertGroup(monitorGroup);
    }
    
    @Override
    public void deleteGroup(Long id)
    {
        // TODO Auto-generated method stub
        monitorGroupDao.deleteGroup(id);
    }
    
    @Override
    public String createTree(String objectpath)
    {
        // TODO Auto-generated method stub
    	 treeBuff = new StringBuffer();
         Map<String, Long> map = new HashMap<String, Long>();
         map.put("monitorGroupParentId", Long.valueOf(0));
         
         List<MonitorGroup> monitorGroupList = monitorGroupDao.queryByParentId(map);
         
         for (MonitorGroup monitorGroup : monitorGroupList)
         {
             Map<String, Long> map1 = new HashMap<String, Long>();
             
             map1.put("monitorGroupParentId", monitorGroup.getMonitorGroupId());
             
             List<MonitorGroup> monitorGroupList1 = monitorGroupDao.queryByParentId(map1);
             if (monitorGroup.getMonitorGroupName().equals("自定义监控频道"))
             {
                 if (monitorGroupList1.size() <= 0)
                 {
                     
                     treeBuff.append("<ul>");
                     treeBuff.append("<li id=\"query_" + monitorGroup.getMonitorGroupId()
                         + "\"><a href=\"javascript:action('" + monitorGroup.getMonitorGroupId()
                         + "','img');\"><img src=\"" + objectpath + "/images/" + PICTURE_NAME_SEED
                         + "\" id=\"img_query_" + monitorGroup.getMonitorGroupId()
                         + "\"></a>&nbsp;<a href=\"javascript:showGroup('" + monitorGroup.getMonitorGroupId()
                         + "');\" ondblclick=\"action('" + monitorGroup.getMonitorGroupId()
                         + "','img')\"><span class=\"eventext\">&nbsp;" + monitorGroup.getMonitorGroupName() + "</span></a>");
                     treeBuff.append("</li>");
                     treeBuff.append("</ul>");
                 }
                 else
                 {
                     // 画出下级组
                     treeBuff.append("<ul>");
                     treeBuff.append("<li id=\"query_img_query_monitor" + monitorGroup.getMonitorGroupId()
                         + "\"><a href=\"javascript:action('" + monitorGroup.getMonitorGroupId()
                         + "','img_query_monitor');\"><img src=\"" + objectpath + "/images/" + PICTURE_NAME_CLOSE
                         + "\" id=\"img_query_monitor" + monitorGroup.getMonitorGroupId()
                         + "\"></a>&nbsp;<a href=\"javascript:showGroup('" + monitorGroup.getMonitorGroupId()
                         + "');\" ondblclick=\"action('" + monitorGroup.getMonitorGroupId()
                         + "','img_query_monitor')\"><span class=\"eventext\">&nbsp;" + monitorGroup.getMonitorGroupName() + "</span></a>");
                     drawSon(monitorGroupList1, objectpath);
                     treeBuff.append("</li>");
                     treeBuff.append("</ul>");
                 }
                 
             }
             else
             {
                 
                 if (monitorGroupList1.size() <= 0)
                 {
                     
                     treeBuff.append("<ul>");
                     treeBuff.append("<li><a href=\"javascript:linkTo(" + monitorGroup.getMonitorGroupId()
                         + ");\"><img src=\"" + objectpath + "/images/" + PICTURE_NAME_SEED
                         + "\"></a>&nbsp;<a href=\"javascript:linkTo(" + monitorGroup.getMonitorGroupId()
                         + ");\"><span class=\"eventext\">" + monitorGroup.getMonitorGroupName() + "</span></a>");
                     treeBuff.append("</li>");
                     treeBuff.append("</ul>");
                 }
                 
                 else
                 {
                     // 画出下级组
                     treeBuff.append("<ul>");
                     treeBuff.append("<li id=\"query_img_query_monitor" + monitorGroup.getMonitorGroupId()
                         + "\"><a href=\"javascript:action('" + monitorGroup.getMonitorGroupId()
                         + "','img_query_monitor');\"><img src=\"" + objectpath + "/images/" + PICTURE_NAME_CLOSE
                         + "\" id=\"img_query_monitor" + monitorGroup.getMonitorGroupId()
                         + "\"></a>&nbsp;<a href=\"javascript:showGroup('" + monitorGroup.getMonitorGroupId()
                         + "');\" ondblclick=\"action('" + monitorGroup.getMonitorGroupId()
                         + "','img_query_monitor')\"><span class=\"eventext\">" + monitorGroup.getMonitorGroupName() + "</span></a>");
                     drawSon(monitorGroupList1, objectpath);
                     treeBuff.append("</li>");
                     treeBuff.append("</ul>");
                 }
                 
             }
             
         }
         
         return treeBuff.toString();
    }
    
    // 画出下级组
    public void drawSon(List<MonitorGroup> monitorGroupList, String basePath)
    {
    	for (MonitorGroup monitorgroup : monitorGroupList)
        {
            Map<String, Long> map = new HashMap<String, Long>();
            map.put("monitorGroupParentId", monitorgroup.getMonitorGroupId());
            List<MonitorGroup> groupList = monitorGroupDao.queryByParentId(map);
            if (groupList.size() <= 0)
            {
                /*
                 * treeBuff.append("<ul class=\"disply\">");
                 * treeBuff.append("<li id=\"query_"
                 * +map.get("monitorGroupParentId"
                 * )+"\" class=\"eventleft\"><a href=\"javascript:action('"
                 * +map.get
                 * ("monitorGroupParentId")+"','img');\"><img src=\"soc/images/"
                 * +PICTURE_NAME_CLOSE+"\" id=\"img_query_"+map.get(
                 * "monitorGroupParentId")+
                 * "\"></a>&nbsp;<a href=\"javascript:void(0)\" ondblclick=\"action('"
                 * +
                 * map.get("monitorGroupParentId")+");\"><span class=\"eventext\">"
                 * +monitorgroup.getMonitorGroupName()+"</span></a>");
                 * treeBuff.append("</li>"); treeBuff.append("</ul>");
                 */
                break;
            }
            else
            {
                // 循环写出组
                treeBuff.append("<ul  class=\"disply\">");
                treeBuff.append("<li id=\"query_" + monitorgroup.getMonitorGroupId()
                    + "\" class=\"eventleft\"><a href=\"javascript:action('" + monitorgroup.getMonitorGroupId()
                    + "','img');\"><img src=\"" + basePath + "/images/" + PICTURE_NAME_CLOSE + "\" id=\"img_query_"
                    + monitorgroup.getMonitorGroupId() + "\"></a>&nbsp;<a href=\"javascript:showGroup('"
                    + monitorgroup.getMonitorGroupId() + "');\" ondblclick=\"action('"
                    + monitorgroup.getMonitorGroupId() + "');\"><span class=\"eventext\">"
                    + monitorgroup.getMonitorGroupName() + "</span></a>");
                drawSon(groupList, basePath);
                treeBuff.append("</li>");
                treeBuff.append("</ul>");
            }
        }
        
        for (MonitorGroup monitorGroup : monitorGroupList)
        {
            treeBuff.append("<ul  class=\"disply\">");
            treeBuff.append("<li class=\"eventleft\"><a href=\"javascript:linkTo('" + monitorGroup.getMonitorGroupId()
                + "');\"><img src=\"" + basePath + "/images/" + PICTURE_NAME_SEED
                + "\"></a>&nbsp;<a href=\"javascript:linkTo('" + monitorGroup.getMonitorGroupId()
                + "');\"><span class=\"eventext\">&nbsp;" + monitorGroup.getMonitorGroupName() + "</span></a>");
            treeBuff.append("</li>");
            treeBuff.append("</ul>");
        }
        
    }
    
}
