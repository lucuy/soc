package com.util;

import java.io.IOException;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 *读取freeMarker模板文件
 */

public class FreeMarkerUtil
{
    public void loadTemplate(String ftl, Map map, String fileName, Writer out)
    {
        //加载 freeMarker 配置
        Configuration cfg = new Configuration();
        //设置编码方式(设置本地默认的区域信息为'UTF-8')
        cfg.setEncoding(Locale.getDefault(), "UTF-8");
        
        //配置上下文路径
        cfg.setServletContextForTemplateLoading(ServletActionContext.getServletContext(), "/" + fileName);
        Template template = null;
        
        try
        {
            template = cfg.getTemplate(ftl);
            
            try
            {
                template.process(map, out);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                
                out.close();
            }
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
