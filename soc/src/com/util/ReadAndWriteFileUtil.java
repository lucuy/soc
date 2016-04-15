package com.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/**
 * 读写文件util类
 * @author 王纯
 */
public class ReadAndWriteFileUtil
{
    /**
     * 写文件当append为true时以追加方法写出内容 
     * 当内容为空时只创建空文件
     * @param filePath   String
     * @param fileName   String 
     * @param content    String
     * @param append     boolean
     */
    public static void writeFile(String filePath, String fileName,String content,boolean append)
    {
        // 测试路径是否存在 如果不存在创建
        File path = new File(filePath);
        if(path.isDirectory())
        {
            if(!path.exists())
            {
                path.mkdirs();
            }
        }
        OutputStream os = null;
        try
        {
            os = new FileOutputStream(filePath + "/" + fileName,append);
            if(!content.isEmpty() && !"".equals(content))
            {
                DataOutputStream dos = new DataOutputStream(os);
                try
                {
                    dos.writeBytes(new String(content.getBytes(), "utf-8"));
                }
                catch (IOException e1)
                {
                    e1.printStackTrace();
                }
            }
        }
        catch (FileNotFoundException e2)
        {
            e2.printStackTrace();
        }
        finally
        {
            try
            {
                os.close();
            }
            catch (IOException e)
            {
            }
        }
    }
    
    /**
     * 读取文件内容
     * @param filePath
     * @return content String 
     */
    public static String readFileContent(String filePath)
    {
        File file = new File(filePath);
        StringBuffer returnCount = new StringBuffer();
        if(file.exists())
        {
            BufferedReader reader= null;
            try
            {
            	FileInputStream fis = new FileInputStream(new File(filePath));
            	reader = new BufferedReader(new InputStreamReader(fis));
                String tempStr = null;
                try
                {
                    while((tempStr = reader.readLine()) != null)
                    {
                    	if(returnCount.length() > 0)
                    	{
                    		returnCount.append("\n" + tempStr);
                    	}
                    	else
                    	{
                    		returnCount.append(tempStr);
                    	}
                        
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }finally
            {
                if(reader != null)
                {
                    try
                    {
                        reader.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            new Exception("文件或路径不存在");
        }
        
        return returnCount.toString();
    }
    public static String readFileContentUTF8(File file)
    {
        //File file = new File(filePath);
        StringBuffer returnCount = new StringBuffer();
        if(file.exists())
        {
            BufferedReader reader= null;
            try
            {
            	FileInputStream fis = new FileInputStream(file);
            	reader = new BufferedReader(new InputStreamReader(fis,"UTF-8"));
                String tempStr = null;
                try
                {
                    while((tempStr = reader.readLine()) != null)
                    {
                    	if(returnCount.length() > 0)
                    	{
                    		returnCount.append("\n" + tempStr);
                    	}
                    	else
                    	{
                    		returnCount.append(tempStr);
                    	}
                        
                    }
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
			
				e.printStackTrace();
			}finally
            {
                if(reader != null)
                {
                    try
                    {
                        reader.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
        else
        {
            new Exception("文件或路径不存在");
        }
        
        return returnCount.toString();
    }
    
}
