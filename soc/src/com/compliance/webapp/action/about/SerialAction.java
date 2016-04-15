package com.compliance.webapp.action.about;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.compliance.webapp.action.BaseAction;
import com.util.DiskUtils;
import com.util.Encrypt;

/**
 * Description: 产品注册action
 * @author du
 * @Version 1.0
 * @Created at 2013-06-20
 * @Modified by 
 */
public class SerialAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	private File upFile;
	private String upFileFileName;
	private String upFileContentType;
	private String hardwareCode;
	
	/**
	 * 序列号页面
	 * @return
	 */
	public String serial() {
		try {
			hardwareCode = Encrypt.md5(DiskUtils.getSerialNumber("C"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}


	/**
	 * 注册产品
	 * @return
	 */
	public String register() {
		log.info("产品注册...");
		
		String path = this.getServletContext().getRealPath("/");
		File serialFile = new File(new File(path), upFileFileName);
		if(!serialFile.getParentFile().exists()){
			serialFile.getParentFile().mkdirs();
		}
		//System.out.println(serialFile);
		try {
			FileUtils.copyFile(upFile, serialFile);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("上传注册文件...完成");
		
		
		return SUCCESS;
	}


	public File getUpFile() {
		return upFile;
	}

	public void setUpFile(File upFile) {
		this.upFile = upFile;
	}

	public String getUpFileFileName() {
		return upFileFileName;
	}

	public void setUpFileFileName(String upFileFileName) {
		this.upFileFileName = upFileFileName;
	}

	public String getUpFileContentType() {
		return upFileContentType;
	}

	public void setUpFileContentType(String upFileContentType) {
		this.upFileContentType = upFileContentType;
	}
	
	public String getHardwareCode() {
		return hardwareCode;
	}

	public void setHardwareCode(String hardwareCode) {
		this.hardwareCode = hardwareCode;
	}

}