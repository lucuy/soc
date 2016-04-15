package com.util;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.systemsetting.Collector;

public class FileruleCreateFile {
	
	public static String path ="/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/";
	
	//重新新的文件用于过滤规则的标示
	public static int createFile()
	{
		try {
			OutputStream collectVersion = new FileOutputStream(path + "filter.version");
			DataOutputStream dos = new DataOutputStream(collectVersion);
			
			String temp = "filter=" + new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format((new Date()));
			dos.writeBytes(temp);
			dos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}
    /**
     * 更新version文件
     * @param readFilePath
     * @param writeFilePath
     */
	 public static void modifinyConfigFile(String readFilePath,String writeFilePath)
	    {
	        List<Collector> collectorList =  GlobalConfig.collectorList;
	        String filterPath = "/usr/local/tomcat-upgrade/webapps/soc-upgrade/upgrade_java/filter.version";
	    	File filter = new File(filterPath);
	    	String temp ="";
	    	if(filter.exists())
	    	{
	    		
	    		temp = ReadAndWriteFileUtil.readFileContent(filterPath);
	    	}

	        File version = new File(readFilePath);
	        String content= "";
	        if(version.exists())
	        {
	           content = ReadAndWriteFileUtil.readFileContent(readFilePath);
	        }
	        StringBuffer sontentSb = null;
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
	        
	        for(Collector collector : collectorList)
	        {
	            sontentSb = new StringBuffer();
	            sontentSb.append(content+"\n");
	            sontentSb.append("conf="+sdf.format(new Date())+"\n");
	            sontentSb.append("ctl="+collector.getCollectorStatus()+"\n");
	            sontentSb.append(temp);
	           // System.out.println(sontentSb.toString()+"mac"+collector.getCollectorMac());
	            ReadAndWriteFileUtil.writeFile(writeFilePath, collector.getCollectorMac()+".version", sontentSb.toString(), false);
	        }
	    }
}
