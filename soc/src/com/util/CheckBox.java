/*
 * 文 件 名:  CheckBox.java
 * 版    权:  jidisec Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  liuyanxu
 * 修改时间:  2012-9-9
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.util;

import com.util.ClassUtil;

/**
 * <一句话功能简述>
 * <功能详细描述>
 * 
 * @author  liuyanxu
 * @version  [版本号, 2012-9-9]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */
public class CheckBox
{
    
    private StringBuffer htmlBuffer;
    
    private String varName;
    
    private String varId;
    
    private String className;
    
    private String css;
    
    private String onClick;
    
    private String misc;
    
    private String serverVar;
    
    private String actionParam;
    
    /**
     * 显示复选框
     * <功能详细描述>
     * @param object
     * @param type
     * @param selIds
     * @return
     * @see [类、类#方法、类#成员]
     */
    public String show(Object object, String type, String selIds)
    {
        htmlBuffer = new StringBuffer();
        
        htmlBuffer.append("<input type=checkbox");
        htmlBuffer.append(varName != null ? " name=\"" + varName + "\"" : "");
        htmlBuffer.append(varId != null ? " id=\"" + ClassUtil.property(object, varId) + "\"" : "");
        htmlBuffer.append(className != null ? " class=\"" + className + "\"" : "");
        htmlBuffer.append(css != null ? " style=\"" + css + "\"" : "");
        htmlBuffer.append(serverVar != null ? " value=\"" + ClassUtil.property(object, serverVar) + "\"" : "");
        htmlBuffer.append(misc != null ? " " + misc : "");
        htmlBuffer.append(onClick != null ? " onclick=\"" + onClick + "(" : "");
        showOnClick(object);
        htmlBuffer.append(onClick != null ? ")\"" : "");
        
        if (serverVar.equals(selIds))
        {
            htmlBuffer.append(" checked ");
        }
        htmlBuffer.append(" />");
        
        //System.out.println("checkbox: " + htmlBuffer.toString());
        
        return htmlBuffer.toString();
    }
    
    /**
     * onclick
     * <功能详细描述>
     * @param object
     * @see [类、类#方法、类#成员]
     */
    private void showOnClick(Object object)
    {
        boolean flag = false;
        if (actionParam != null)
        {
            if (actionParam.indexOf(",") >= 0)
            {
                String[] arrayParam = actionParam.split(",");
                
                for (String param : arrayParam)
                {
                    // 首个参数不加逗号
                    if (flag)
                    {
                        htmlBuffer.append(',');
                    }
                    else
                    {
                        flag = true;
                    }
                    htmlBuffer.append(!param.equals("") ? "'" + ClassUtil.property(object, param) + "'" : "");
                }
            }
            else
            {
                htmlBuffer.append(!actionParam.equals("") ? "'" + ClassUtil.property(object, actionParam) + "'" : "");
            }
        }
        
    }
    
    public StringBuffer getHtmlBuffer()
    {
        return htmlBuffer;
    }
    
    public void setHtmlBuffer(StringBuffer htmlBuffer)
    {
        this.htmlBuffer = htmlBuffer;
    }
    
    public String getVarName()
    {
        return varName;
    }
    
    public void setVarName(String varName)
    {
        this.varName = varName;
    }
    
    public String getVarId()
    {
        return varId;
    }
    
    public void setVarId(String varId)
    {
        this.varId = varId;
    }
    
    public String getClassName()
    {
        return className;
    }
    
    public void setClassName(String className)
    {
        this.className = className;
    }
    
    public String getCss()
    {
        return css;
    }
    
    public void setCss(String css)
    {
        this.css = css;
    }
    
    public String getOnClick()
    {
        return onClick;
    }
    
    public void setOnClick(String onClick)
    {
        this.onClick = onClick;
    }
    
    public String getMisc()
    {
        return misc;
    }
    
    public void setMisc(String misc)
    {
        this.misc = misc;
    }
    
    public String getServerVar()
    {
        return serverVar;
    }
    
    public void setServerVar(String serverVar)
    {
        this.serverVar = serverVar;
    }
    
    public String getActionParam()
    {
        return actionParam;
    }
    
    public void setActionParam(String actionParam)
    {
        this.actionParam = actionParam;
    }
}
