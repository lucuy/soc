package com.soc.webapp.action.about;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.soc.model.conf.GlobalConfig;
import com.soc.model.serial.Serial;
import com.soc.webapp.action.BaseAction;
import com.util.EncodeUtil;
import com.util.FileUtil;
import com.util.OSUtil;
import com.util.StringUtil;

/**
 * 
 * @author liuyanxu
 *  产品注册Action
 */
public class SerialAction extends BaseAction{
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
		Process process;
		BufferedReader br = null;
		String buf;
		
		// 清除历史消息
		super.clearErrorsAndMessages();
		
		// 判断操作系统
		if(!OSUtil.getOSName().toLowerCase().contains("linux")){
			addActionMessage("系统必须部署在liunx系统上");
			return SUCCESS;
		}
		
		
		try {
			// 执行gensn获取硬件特征码
			process = Runtime.getRuntime().exec(GlobalConfig.keyOrFile);
			br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			buf = br.readLine();
			
			if(StringUtil.isNotBlank(buf)) {
				hardwareCode = buf;
			} else {
				hardwareCode = "";
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(br != null) {
					br.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}


	/**
	 * 注册产品
	 * @return
	 */
	public String register() {
		
		log.info("产品注册...");
		
		//String path = ServletActionContext.getServletContext().getRealPath("/serial");
		String path = "/etc/";
		File serialFile = new File(new File(path), upFileFileName);
		if(!serialFile.getParentFile().exists()){
			serialFile.getParentFile().mkdirs();
		}
		try {
			FileUtil.copyFile(upFile, serialFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		log.info("上传注册文件...完成");
		
		try {
			
			char[] name = {0x66, 0x6F, 0x72, 0x74, 0x2E, 0x6C, 0x69, 0x63, 0x65, 0x6E, 0x63, 0x65};
			
			File file = new File("/etc/" + String.valueOf(name));
			if(file.exists()) {
				FileInputStream in = null;
				        
				in = new FileInputStream(file);
				byte[] buf = new byte[4096];
				int len = in.read(buf, 0, 4096);
				in.close();
				
				byte[] buffer = new byte[len];
				for(int i = 0; i < len; i++) {
					buffer[i] = buf[i];
				}		
				
				String str = new String(buffer);
				char[] key1 = {0x3F, 0xEE, 0x3F, 0x5A, 0xAE, 0xFA, 0x1F, 0x0A};
				char[] key2 = {0x3D, 0xAE, 0x3A, 0x5B, 0x3F, 0x6A, 0x11, 0xAA};
				char[] key3 = {0x0B, 0x9E, 0xDF, 0x2A, 0xAA, 0xF0, 0x7D, 0x6E};
				
				buffer = EncodeUtil.xorDecode(buffer, key3);
		
				buffer = EncodeUtil.xorDecode(buffer, key2);
	
				buffer = EncodeUtil.xorDecode(buffer, key1);
				
				// 解析XML
				Document document = DocumentHelper.parseText(new String(buffer));
				Element root = document.getRootElement();
				
				if(root.getName().equals("reginfo")) {
					List<Element> elementsList = root.elements();
					Iterator iterator = elementsList.iterator();
					while(iterator.hasNext()) {
						Element element = (Element)iterator.next();
						
						if(element.getName().equals("sn")) {
							Serial.SERIAL_SN = element.getText(); 
						} else if(element.getName().equals("resource_num")) {
							Serial.SERIAL_RESOURCE_NUM = Integer.parseInt(element.getText()); 
						} else if(element.getName().equals("auth_day")) {
							Serial.SERIAL_AUTH_DAY = Integer.parseInt(element.getText());
						} else if(element.getName().equals("gen_time")) {
							Serial.SERIAL_GEN_TIME = Long.parseLong(element.getText());
						} else if(element.getName().equals("sign")) {
							Serial.SERIAL_SIGN = element.getText(); 
						}
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		HttpServletRequest request = super.getRequest();
		if(!hardwareCode.trim().equals(Serial.SERIAL_SN)){
			request.setAttribute("registerMessage", "注册文件错误！");
			GlobalConfig.lienceFlag = 0;
		}else{
			request.setAttribute("registerMessage", "注册成功！");
			//java处用的标示，主要是接收事件用。
			GlobalConfig.lienceFlag = 1;
		}
		
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
