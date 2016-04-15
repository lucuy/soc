package com.soc.webapp.quartz;

import java.io.File;

import com.soc.model.conf.GlobalConfig;
import com.soc.service.scanTask.ScanTaskService;
import com.util.StringUtil;
/**
 * 执行漏扫联动返回扫描结果
 * 每两分钟执行一次
 * @author changhong
 *
 */

public class ScanUpdateState {
	private ScanTaskService scanTaskManager;
	/*
	 * 查看指定目录下是否有扫描结果
	 */
	public void checkFiles(){
		File file = new File(GlobalConfig.scanTaskPath+File.separator);
		String [] fileNames =file.list();
		for (String fileName : fileNames) {
			String [] str = fileName.split("\\.");
			if(str.length>1){
				if("xml".equals(str[1])){
					
				String result =scanTaskManager.queryScanTaskByFileName(str[0]);
					if (StringUtil.isNotEmpty(result)) {
						scanTaskManager.updateStateByFileName(str[0]);
					}
				}
			}
		}
	}
	

	public ScanTaskService getScanTaskManager() {
		return scanTaskManager;
	}

	public void setScanTaskManager(ScanTaskService scanTaskManager) {
		this.scanTaskManager = scanTaskManager;
	}
	public static void main(String[] args) {
		File file = new File("D:\\apache-tomcat-6.0.35\\webapps\\soc\\scanData\\");
		String [] fileNames =file.list();
		for (String fileName : fileNames) {
			String [] str = fileName.split("\\.");
			if(str.length>1){
				if("xml".equals(str[1])){
					System.out.println(str[0]);
				}
			}
		}
 	}
	
}
