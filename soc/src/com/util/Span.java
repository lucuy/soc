package com.util;

import com.util.ClassUtil;

/**
 * 
 * 文字链接组件
 * <功能详细描述>
 * 
 * @author  郭煜玺
 * @version  V100R001C001, 2011-2-16
 * @see  [相关类/方法]
 * @since  V100R001C001
 */
public class Span
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
    
    private String serverTextVar;
    
    private String appendText;
    
    /**
     * 显示Radio
     * <功能详细描述>
     * @param object Object
     * @return String
     * @see [类、类#方法、类#成员]
     */
    public String show(Object object)
    {
        htmlBuffer = new StringBuffer();
        htmlBuffer.append("<span");
        htmlBuffer.append(varName != null ? " name=\"" + varName + "\"" : "");
        htmlBuffer.append(varId != null ? " id=\"" + varId + "\"" : "");
        htmlBuffer.append(className != null ? " class=\"" + className + "\"" : "");
        htmlBuffer.append(css != null ? " style=\"vertical-align:middle;" + css + "\"" : "");
        htmlBuffer.append(serverVar != null ? " value=\"" + ClassUtil.property(object, serverVar) + "\"" : "");
        htmlBuffer.append(misc != null ? " " + misc : "");
        if(Integer.parseInt(ClassUtil.property(object, serverVar).toString())==1||Integer.parseInt(ClassUtil.property(object, serverVar).toString())==2){
        	
        }else{
        	
           htmlBuffer.append(onClick != null ? " onclick=\"" + onClick + "(" : "");
           showOnClick(object);
       
        }
        htmlBuffer.append(onClick != null ? ")\">" : ">");
        
        htmlBuffer.append(serverTextVar != null ? ClassUtil.property(object, serverTextVar) : "");
        htmlBuffer.append(appendText != null ? appendText : "");
        
        htmlBuffer.append("</span>");
        
        //System.out.println("span: " + htmlBuffer.toString());
        
        return htmlBuffer.toString();
    }
    
    /**
     * onclick
     * <功能详细描述>
     * @param object Object
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
    
    public String getAppendText()
    {
        return appendText;
    }
    
    public void setAppendText(String appendText)
    {
        this.appendText = appendText;
    }
    
    public String getServerTextVar()
    {
        return serverTextVar;
    }
    
    public void setServerTextVar(String serverTextVar)
    {
        this.serverTextVar = serverTextVar;
    }
}
