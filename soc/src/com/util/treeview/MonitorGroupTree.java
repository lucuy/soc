/*
 * 文 件 名:  AssetGroupTree.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-8-25
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util.treeview;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;


import com.soc.model.monitor.MonitorGroup;

import com.soc.service.monitor.MonitorGroupService;
import com.util.CheckBox;
import com.util.Radio;
import com.util.Span;
import com.util.StringUtil;

/**
 * 资产组树形实现
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-8-25]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class MonitorGroupTree
{
    private String ctx;
    
    private MonitorGroupService monitorGroupManager;
    
    private StringBuffer htmlBuffer;
    
    private Span span = null;
    
    private Radio radio = null;
    
    private CheckBox checkBox = null;
    
    private int m_InDent;
    
    public String getCtx()
    {
        return ctx;
    }

    public void setCtx(String ctx)
    {
        this.ctx = ctx;
    }

    

    public Span getSpan()
    {
        return span;
    }

    public void setSpan(Span span)
    {
        this.span = span;
    }

    public Radio getRadio()
    {
        return radio;
    }

    public void setRadio(Radio radio)
    {
        this.radio = radio;
    }

    public int getM_InDent()
    {
        return m_InDent;
    }

    public void setM_InDent(int m_InDent)
    {
        this.m_InDent = m_InDent;
    }
    
    public StringBuffer getHtmlBuffer()
    {
        return htmlBuffer;
    }

    public void setHtmlBuffer(StringBuffer htmlBuffer)
    {
        this.htmlBuffer = htmlBuffer;
    }
    
    /**
     * <默认构造函数>
     */
    public MonitorGroupTree(MonitorGroupService monitorGroupManager,String ctx)
    {
        this.monitorGroupManager = monitorGroupManager;
        this.ctx = ctx;
        htmlBuffer = new StringBuffer();
    }
    
    public String displayTree() throws IOException
    {
        //System.out.println("进入显示树函数");
        
        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("monitorGroupParentId", 0);
        
        ////System.out.println(map.get("assetGroupParentId"));
        
        List<MonitorGroup> monitorGroupList = monitorGroupManager.queryByParentId(map);
        
        for(int i =0; i<monitorGroupList.size();i++)
        {
        	MonitorGroup monitorGroup =monitorGroupList.get(i);
            boolean hasSon = false;
            map.put("monitorGroupParentId", monitorGroup.getMonitorGroupId());
            
            if(!monitorGroupManager.queryByParentId(map).isEmpty())
            {
                hasSon = true;
            }
            
            htmlBuffer.append("<div class=\"root_tree_node\">");
            htmlBuffer.append("<span style='display:block'>");
            
            if(hasSon)
            {
                htmlBuffer.append("<img id ='img_" + monitorGroup.getMonitorGroupId() + "' src=\"" + ctx
                    + "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('" + monitorGroup.getMonitorGroupId() + "')\"/>&nbsp;");
            }
            else
            {
                htmlBuffer.append("<img id ='img_" + monitorGroup.getMonitorGroupId() + "' src=\"" + ctx
                    + "/images/tree/minusno.gif\" class=\"hand\" />&nbsp;"); 
            }
            //显示单选按钮
            if(radio != null)
            {
                htmlBuffer.append(radio.show(monitorGroup,"",""));
            }
            if(checkBox !=null)
            {
                 htmlBuffer.append(checkBox.show(monitorGroup,"", ""));
            }
            htmlBuffer.append("<s:hidden id="+monitorGroup.getMonitorGroupId()+"_id  value=\""+monitorGroup.getMonitorGroupName()+"\"/>");
           /* htmlBuffer.append("</s:hidden>");*/
            //htmlBuffer.append("<s:hidden id="+monitorGroup.getMonitorGroupId()+"_idF  value=\""+assetGroup.getAssetParentsFeature()+"\"/>");
            /*htmlBuffer.append("</s:hidden>");*/
            if(span != null)
            {
                htmlBuffer.append(span.show(monitorGroup));
            }
            
            else
            {
                htmlBuffer.append(monitorGroup.getMonitorGroupId());
            }
            htmlBuffer.append("</span>");
            
            //显示其子节点
            htmlBuffer.append("<div id=\"span_node_"+monitorGroup.getMonitorGroupId()+"\">");
           
            drawSon(monitorGroup,"","p_1");
            
            htmlBuffer.append("</div>");
            htmlBuffer.append("</div>");
        }
        return htmlBuffer.toString();
        
    }
    
    public void drawSon(MonitorGroup ag,String sIndentStr,String sDivId)
    {
        int iSonCounts;
        int iNowSon;
        String sIndent;
        String strSonIndent;
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("monitorGroupParentId", ag.getMonitorGroupId());
        List<MonitorGroup> monitorGroupList = monitorGroupManager.queryByParentId(map);
        iSonCounts = monitorGroupList.size();
        iNowSon=1;
        for(int i=0;i<= iSonCounts;i++)
        {
            if(iNowSon <=iSonCounts)
            {
                MonitorGroup agp = monitorGroupList.get(i);
                //如果不是最后一个儿子，那么显示的前缀为├
                if(iNowSon != iSonCounts)
                {
                    sIndent =
                        sIndentStr + "<img src=\"" + ctx + "/images/tree/node.gif\" />"
                            + dupchr("<img src=\"" + ctx + "/images/tree/line.gif\" />", m_InDent);
                    strSonIndent =
                        sIndentStr + "<img src=\"" + ctx + "/images/tree/vertline.gif\" />"
                            + dupchr("<img src=\"" + ctx + "/images/tree/blank.gif\" />", m_InDent);
                }
                else
                    //最后一个儿子的前缀应该是└
                    {
                        sIndent =
                            sIndentStr + "<img src=\"" + ctx + "/images/tree/lastnode.gif\" />"
                                + dupchr("<img src=\"" + ctx + "/images/tree/line.gif\" />", m_InDent);
                        strSonIndent =
                            sIndentStr + dupchr("<img src=\"" + ctx + "/images/tree/blank.gif\" />", m_InDent + 1);
                    }
                    
                    htmlBuffer.append(sIndent);
                    
                    Map<String, Object> mapSon = new HashMap<String, Object>();
                    
                    mapSon.put("monitorGroupParentId",agp.getMonitorGroupId());
                    
                    List<MonitorGroup> sonList = monitorGroupManager.queryByParentId(mapSon);
                    
                    if(sonList.size()>0)//如果有儿子，那么应该使用+号
                    {
                        htmlBuffer.append("<img id ='img_" + agp.getMonitorGroupId() + "' src=\"" + ctx
                            + "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('" + agp.getMonitorGroupId()+ "')\"/>&nbsp;");
                    }
                    else//否则使用空
                    {
                        htmlBuffer.append("<img id ='img_" + agp.getMonitorGroupId() + "' src=\"" + ctx
                            + "/images/tree/minusno.gif\" class=\"hand\" />&nbsp;");
                    }
                    if(radio !=null)
                    {
                        htmlBuffer.append(radio.show(agp, "", ""));
                    }
                    if(checkBox !=null)
                    {
                         htmlBuffer.append(checkBox.show(agp,"", ""));
                    }
                    htmlBuffer.append("<s:hidden id="+agp.getMonitorGroupId()+"_id  value=\""+agp.getMonitorGroupName()+"\"/>");
                   /* htmlBuffer.append("</s:hidden>");*/
                   // htmlBuffer.append("<s:hidden id="+agp.getAssetGroupId()+"_idF  value=\""+agp.getAssetParentsFeature()+"\"/>");
                    /*htmlBuffer.append("</s:hidden>");*/
                    if (span != null)
                    {
                        htmlBuffer.append(span.show(agp));
                    }
                    else
                    {  
                        htmlBuffer.append(agp.getMonitorGroupName());
                    }
                    htmlBuffer.append("<br>");
                    htmlBuffer.append("<div id=\"d" + sDivId + "_" + iNowSon + "\" style=\"display:block\">");
                    htmlBuffer.append("<div id=\"span_node_" + agp.getMonitorGroupId() + "\">");
                    
                    drawSon(agp,strSonIndent,sDivId+"_"+iNowSon);
                    
                    iNowSon = iNowSon +1;
                    htmlBuffer.append("</div>");
                    htmlBuffer.append("</div>");
            }
        }
    }
    
    public String dupchr(String str, int num)
    {
        String dupchr="";
        
        int iTmp=1;
        
        String strtmp ="";
        
        if(StringUtil.isBlank(str))
        {
            return dupchr;
        }
        else
        {
            for(int i=0;i<=num;i++)
            {
                strtmp = strtmp +str;
                iTmp = iTmp+1;             
            }
            dupchr = strtmp;
        }
        return dupchr;
    }
    
    /**
     * 显示子节点
     * <功能详细描述>
     * @param parentId Integer
     * @throws IOException IOException 
     * @see [类、类#方法、类#成员]
     */
    public void drawSon(Long parentId)
        throws IOException
    {
        
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("monitorGroupParentId", parentId);
        
        List<MonitorGroup> monitorGroupList = monitorGroupManager.queryByParentId(map);
        
        htmlBuffer.append("<div>");
        for (int i = 0; i < monitorGroupList.size(); i++)
        {
            MonitorGroup obj = monitorGroupList.get(i);
            
            String indexs = String.valueOf(obj.getMonitorGroupId());
            boolean hasSon = false;
            map.put("monitorGroupParentId", obj.getMonitorGroupId());
            if (!monitorGroupManager.queryByParentId(map).isEmpty())
            {
                hasSon = true;
            }
            
            // 显示节点图标与描述
            htmlBuffer.append("<div>");
            
            if (i == monitorGroupList.size() - 1)
            {
                htmlBuffer.append("<img src=\"" + ctx + "/images/tree/vertline.gif\" />");
                htmlBuffer.append("<img src=\"" + ctx + "/images/tree/blank.gif\" />");
                htmlBuffer.append("<img src=\"" + ctx + "/images/tree/lastnode.gif\" />");
                htmlBuffer.append("<img src=\"" + ctx + "/images/tree/line.gif\" />");
            }
            else
            {
                htmlBuffer.append("<img src=\"" + ctx + "/images/tree/node.gif\" />");
                htmlBuffer.append("<img src=\"" + ctx + "/images/tree/blank.gif\" />");
                
            }
            if (hasSon)
            {
                htmlBuffer.append("<img id ='img_" + indexs + "' src=\"" + ctx
                    + "/images/tree/minus.gif\" class=\"hand\" onclick=\"node('" + indexs + "')\"/>&nbsp;");
            }
            else
            {
                htmlBuffer.append("<img id ='img_" + indexs + "' src=\"" + ctx
                    + "/images/tree/minusno.gif\" class=\"hand\"/>&nbsp;");
            }
            if (radio != null)
            {
                htmlBuffer.append(radio.show(obj, "", ""));
            }
            if(checkBox != null)
            {
                htmlBuffer.append(checkBox.show(obj, "", ""));
            }
            if (span != null)
            {
                htmlBuffer.append(span.show(obj));
            }
            else
            {
                
                htmlBuffer.append(obj.getMonitorGroupName());
            }
            htmlBuffer.append("</div>");
            
            // 递归调用，递归显示其子节点
            htmlBuffer.append("<div id=\"span_node_" + obj.getMonitorGroupId() + "\" >");
            
            drawSon(obj.getMonitorGroupId());
            htmlBuffer.append("</div>");
            
        }
        htmlBuffer.append("</div>");
             
    }

    public CheckBox getCheckBox()
    {
        return checkBox;
    }

    public void setCheckBox(CheckBox checkBox)
    {
        this.checkBox = checkBox;
    }

	public MonitorGroupService getMonitorGroupManager() {
		return monitorGroupManager;
	}

	public void setMonitorGroupManager(MonitorGroupService monitorGroupManager) {
		this.monitorGroupManager = monitorGroupManager;
	}
    
}
