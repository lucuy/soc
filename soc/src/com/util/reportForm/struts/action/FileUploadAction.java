package com.util.reportForm.struts.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.soc.webapp.action.BaseAction;

public class FileUploadAction extends BaseAction {
	private File filepath;
	private String filepathFileName;
	private String callbackUrl;
	private String paramList;
	private String zip;
	
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	/*
	 * 系统升级 
	 * @Return String
	 */
	public String upload(){
		
		log.info("文件上传...");
		String path=ServletActionContext.getServletContext().getRealPath("/tmp");
		File upgrade=new File(new File(path),filepathFileName);
		if(!upgrade.getParentFile().exists()){
			upgrade.getParentFile().mkdirs();
		}
		try {
			FileUtils.copyFile(filepath, upgrade);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		log.info("文件上传...完成！");
		
		return SUCCESS;
	}
	
	public File getFilepath() {
		return filepath;
	}

	public void setFilepath(File filepath) {
		this.filepath = filepath;
	}



	public String getFilepathFileName() {
		return filepathFileName;
	}

	public void setFilepathFileName(String filepathFileName) {
		this.filepathFileName = filepathFileName;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getParamList() {
		return paramList;
	}

	public void setParamList(String paramList) {
		this.paramList = paramList;
	}

	
}
