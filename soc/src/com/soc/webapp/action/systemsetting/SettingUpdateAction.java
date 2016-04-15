package com.soc.webapp.action.systemsetting;

import java.io.File;
import java.io.FileOutputStream;

import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import com.soc.service.systemsetting.SettingService;
import com.soc.webapp.action.BaseAction;

import com.util.ThreadPool;

public class SettingUpdateAction extends BaseAction
{
    private static final long serialVersionUID = -6683490262468817364L;
    
    private File upTar;
    
    //xml文件名
    private String upTarFileName;
    
    //批量操作复选框的值
    private String ids;
    
    //xml标签
    private String oid;
    
    //线程池
    public static ThreadPool threadPool = null;
    
    //路径
    private String path;
    
    //系统设置业务类
    private SettingService settingManager;
    
    /**
     * <查询指定文件夹下的所有子文件的路径>
     * <功能详细描述>
     * @param p String
     * @return List<String>
     * @see [类、类#方法、类#成员]
     */
    public List<String> find(String p)
    {
        //文件路径
        List<String> list = new ArrayList<String>();
        
        //编译传过来的路径
        File file = new File(p);
        
        File[] files = file.listFiles();
        
        //循环传过来的路径中有几个文件
        for (int i = 0; i < files.length; i++)
        {
            
            if (files[i].isDirectory())
            {
                //判断是不是文件夹，如果是文件夹则继续向下查找文件
                find(files[i].getAbsolutePath());
            }
            else
            {
                //获取文件中子文件的路径(改动)
                String filePath = files[i].getAbsolutePath()/*.toLowerCase()*/;
                
                //把获取的到路径放入LIST
                list.add(filePath);
            }
        }
        return list;
    }
    
    /**
     * 删除指定目录下的文件
     * 
     * @param p
     * @see [类、类#方法、类#成员]
     */
    public void delteFile(String p)
    {
        //编译传过来的路径
        File file = new File(p);
        
        File[] files = file.listFiles();
        
        //循环传过来的路径中有几个文件
        for (int i = 0; i < files.length; i++)
        {
            
            if (files[i].isDirectory())
            {
                //判断是不是文件夹，如果是文件夹则继续向下查找文件
                File f = files[i];
                
                delteFile(files[i].getAbsolutePath());
                
                f.delete();
            }
            else
            {
                //删除文件
                files[i].delete();
            }
        }
    }
    
    /**
     * 将脚本信息写入文件
     * 将每个xml的脚本信息写入文件
     * @param fileName
     * @param content
     * @param enc
     * @return
     * @see 
     */
    public boolean writeStringToFile(String fileName, String content, String enc)
    {
        File file = new File(fileName);
        try
        {
            //判断文件是否已存在
            if (file.exists())
            {
                return false;
            }
            
            //判断文件的父亲路径是否存在
            if (!file.getParentFile().exists())
            {
                
                //不存在创建父亲路径
                if (!file.getParentFile().mkdirs())
                {
                    return false;
                }
            }
            
            file.createNewFile();
            
            //创建输出流
            OutputStreamWriter os = null;
            
            //判断传递过来的保存文件格式类型参数
            if (enc == null || enc.length() == 0)
            {
                os = new OutputStreamWriter(new FileOutputStream(file));
            }
            else
            {
                os = new OutputStreamWriter(new FileOutputStream(file), enc);
            }
            
            os.write(content);
            
            os.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
    public File getUpTar()
    {
        return upTar;
    }
    
    public void setUpTar(File upTar)
    {
        this.upTar = upTar;
    }
    
    public String getUpTarFileName()
    {
        return upTarFileName;
    }
    
    public void setUpTarFileName(String upTarFileName)
    {
        this.upTarFileName = upTarFileName;
    }
    
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }
    
    public String getOid()
    {
        return oid;
    }
    
    public void setOid(String oid)
    {
        this.oid = oid;
    }
    
    public String getIds()
    {
        return ids;
    }
    
    public void setIds(String ids)
    {
        this.ids = ids;
    }
    
    public SettingService getSettingManager()
    {
        return settingManager;
    }
    
    public void setSettingManager(SettingService settingManager)
    {
        this.settingManager = settingManager;
    }
    
}
